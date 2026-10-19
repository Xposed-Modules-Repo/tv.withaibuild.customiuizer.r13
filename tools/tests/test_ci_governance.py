import tempfile
import unittest
from pathlib import Path

from tools import catalog_contract_probe
from tools import ci_contract_scan
from tools import source_hazard_scan


class SourceHazardTest(unittest.TestCase):
    def test_finds_swallowed_throwable(self):
        with tempfile.TemporaryDirectory() as td:
            root = Path(td)
            path = root / "app/src/main/java/x/Bad.kt"
            path.parent.mkdir(parents=True)
            path.write_text(
                "package x\nobject Bad { fun x() { try {} catch (t: Throwable) { } } }\n",
                encoding="utf-8",
            )
            findings = source_hazard_scan.collect(root, ["app/src/main/java"])
            rules = {f.rule for f in findings}
            self.assertIn("EMPTY_CATCH", rules)
            self.assertIn("CATCH_THROWABLE_NO_FATAL", rules)

    def test_allow_marker_is_narrow(self):
        with tempfile.TemporaryDirectory() as td:
            root = Path(td)
            path = root / "app/src/main/java/x/Bad.kt"
            path.parent.mkdir(parents=True)
            path.write_text(
                "package x\nfun x() { try {} catch (t: Throwable) { } } "
                "// BRUTAL_ALLOW:EMPTY_CATCH\n",
                encoding="utf-8",
            )
            rules = {f.rule for f in source_hazard_scan.collect(root, ["app/src/main/java"])}
            self.assertNotIn("EMPTY_CATCH", rules)
            self.assertIn("CATCH_THROWABLE_NO_FATAL", rules)


class CIContractTest(unittest.TestCase):
    def test_catches_shallow_signing_and_brittle_sdk(self):
        with tempfile.TemporaryDirectory() as td:
            path = Path(td) / "bad.yml"
            path.write_text(
                """name: bad
on:
  push:
    branches:
      - main
jobs:
  x:
    runs-on: ubuntu-24.04
    steps:
      - uses: actions/checkout@v4
      - run: sdkmanager "platforms;android-37"
      - run: ./gradlew -PofficialRelease=true assembleDevelop
""",
                encoding="utf-8",
            )
            errors = ci_contract_scan.scan_workflow(path, "devin/audit", "main")
            text = "\n".join(errors)
            self.assertIn("CI_FULL_HISTORY", text)
            self.assertIn("CI_SIGNING", text)
            self.assertIn("CI_API37_RESOLUTION", text)
            self.assertIn("CI_EXACT_BRANCH", text)

    def test_catches_windows_only_tool_path(self):
        with tempfile.TemporaryDirectory() as td:
            root = Path(td)
            path = root / "tools" / "bad.py"
            path.parent.mkdir(parents=True)
            drive = f"{chr(67)}{chr(58)}"
            sep = chr(92) * 2
            path.write_text(
                f'x = "a/b".replace("/", "{sep}")\n'
                + fr'y = "{drive}\tmp\x"' + "\n",
                encoding="utf-8",
            )
            errors = ci_contract_scan.scan_repo_scripts(root)
            joined = "\n".join(errors)
            self.assertIn("CI_WINDOWS_PATH_REPLACE", joined)
            self.assertIn("CI_HARDCODED_DRIVE", joined)


class CatalogParserTest(unittest.TestCase):
    def test_balanced_feature_spec_blocks(self):
        text = 'FeatureSpec(id = "a", condition = { x(1) }), FeatureSpec(id = "b")'
        blocks = catalog_contract_probe.balanced_blocks(text, "FeatureSpec")
        self.assertEqual(2, len(blocks))
        self.assertEqual("a", catalog_contract_probe.field(blocks[0], "id"))


class StaticOwnerHazardTest(unittest.TestCase):
    """Tests for STATIC_STRONG_ANDROID_OWNER detection of Context fields."""

    def _scan(self, code: str) -> set[str]:
        with tempfile.TemporaryDirectory() as td:
            root = Path(td)
            p = root / "app" / "src" / "main" / "java" / "x" / "Test.kt"
            p.parent.mkdir(parents=True)
            p.write_text(code, encoding="utf-8")
            return {f.rule for f in source_hazard_scan.collect(root, ["app/src/main/java"])}

    def test_object_context_field_detected(self):
        rules = self._scan(
            "package x\nobject Bad {\n    @JvmField\n    var ctx: Context? = null\n}\n"
        )
        self.assertIn("STATIC_STRONG_ANDROID_OWNER", rules)

    def test_object_fq_context_field_detected(self):
        rules = self._scan(
            "package x\nobject Bad {\n    @JvmField\n    var ctx: android.content.Context? = null\n}\n"
        )
        self.assertIn("STATIC_STRONG_ANDROID_OWNER", rules)

    def test_companion_context_field_detected(self):
        rules = self._scan(
            "package x\nclass Foo {\n    companion object {\n        @JvmField\n        var ctx: Context? = null\n    }\n}\n"
        )
        self.assertIn("STATIC_STRONG_ANDROID_OWNER", rules)

    def test_local_context_variable_ignored(self):
        rules = self._scan(
            "package x\nclass Foo {\n    fun doWork() {\n        val ctx: Context? = null\n    }\n}\n"
        )
        self.assertNotIn("STATIC_STRONG_ANDROID_OWNER", rules)

    def test_instance_context_field_ignored(self):
        rules = self._scan(
            "package x\nclass Foo {\n    var ctx: Context? = null\n}\n"
        )
        self.assertNotIn("STATIC_STRONG_ANDROID_OWNER", rules)

    def test_weak_reference_context_ignored(self):
        rules = self._scan(
            "package x\nobject Bad {\n    var ctx: WeakReference<Context>? = null\n}\n"
        )
        self.assertNotIn("STATIC_STRONG_ANDROID_OWNER", rules)


class EagerHandlerThreadHazardTest(unittest.TestCase):
    """Tests for EAGER_HANDLER_THREAD detection."""

    def _scan(self, code: str) -> set[str]:
        with tempfile.TemporaryDirectory() as td:
            root = Path(td)
            p = root / "app" / "src" / "main" / "java" / "x" / "Test.kt"
            p.parent.mkdir(parents=True)
            p.write_text(code, encoding="utf-8")
            return {f.rule for f in source_hazard_scan.collect(root, ["app/src/main/java"])}

    def test_apply_start_detected(self):
        rules = self._scan(
            'package x\nobject Bad {\n    val w = android.os.HandlerThread("bad").apply { start() }\n}\n'
        )
        self.assertIn("EAGER_HANDLER_THREAD", rules)

    def test_chained_start_detected(self):
        rules = self._scan(
            'package x\nobject Bad {\n    val w = android.os.HandlerThread("bad")\n    init { w.start() }\n}\n'
        )
        self.assertIn("EAGER_HANDLER_THREAD", rules)

    def test_no_start_ignored(self):
        rules = self._scan(
            'package x\nclass Foo {\n    val w = android.os.HandlerThread("worker")\n}\n'
        )
        self.assertNotIn("EAGER_HANDLER_THREAD", rules)

    def test_lazy_lifecycle_start_ignored(self):
        # start() is far from construction (in a separate method, >160 chars apart)
        padding = "    " + "// lifecycle logic\n" * 12
        rules = self._scan(
            'package x\nclass Foo {\n    val w = android.os.HandlerThread("worker")\n'
            + padding
            + '    fun onStart() {\n        w.start()\n    }\n}\n'
        )
        self.assertNotIn("EAGER_HANDLER_THREAD", rules)

    def test_explicit_lifecycle_method_construction_is_not_eager_initialization(self):
        rules = self._scan(
            'package x\nobject Owner {\n    fun initContext() {\n'
            '        val worker = android.os.HandlerThread("query").apply { start() }\n'
            '    }\n}\n'
        )
        self.assertNotIn("EAGER_HANDLER_THREAD", rules)




if __name__ == "__main__":
    unittest.main()
