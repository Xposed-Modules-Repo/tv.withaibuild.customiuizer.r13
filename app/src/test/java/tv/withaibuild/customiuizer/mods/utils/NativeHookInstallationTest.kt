package tv.withaibuild.customiuizer.mods.utils

import io.github.libxposed.api.XposedInterface
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import java.lang.reflect.Executable
import java.lang.reflect.Proxy

class NativeHookInstallationTest {
    private val previous = XposedHelpers.moduleInst
    private val callback = object : HookerClassHelper.MethodHook(73) {}

    @After fun restore() { XposedHelpers.moduleInst = previous }

    @Test fun methodsAndConstructorsSharePriorityModeAndUnhookPolicy() {
        for (constructor in listOf(false, true)) {
            val expected = if (constructor) Target::class.java.getDeclaredConstructor()
                else Target::class.java.getDeclaredMethod("run")
            val native = Native(expected).also { it.bind() }
            val handle = if (constructor) XposedHelpers.findAndHookConstructor(Target::class.java, callback)
                else XposedHelpers.findAndHookMethod(Target::class.java, "run", callback)
            assertEquals(listOf("priority", "mode", "intercept"), native.calls)
            assertEquals(73, native.priority)
            assertSame(XposedInterface.ExceptionMode.PASSTHROUGH, native.mode)
            assertSame(callback, native.hook)
            handle.unhook()
            assertEquals(1, native.unhooks)
        }
    }

    @Test fun infrastructureIdIsAssignedBeforeTheOnlyInstallAndReadBack() {
        val native = Native().also { it.bind() }
        val handle = install()
        assertNotNull(handle)
        assertEquals(listOf("priority", "mode", "id", "intercept", "readId"), native.calls)
        assertEquals("application.attach", native.id)
    }

    @Test fun failedIdAssignmentNeverInstallsAnUnidentifiedHook() {
        val native = Native().apply { idFailure = IllegalArgumentException("ID rejected"); bind() }
        assertNull(install())
        assertFalse(native.calls.contains("intercept"))
    }

    @Test fun uncertainInstallIsNeverRetried() {
        val native = Native().apply { installFailure = IllegalStateException("partial install"); bind() }
        assertNull(install())
        assertEquals(1, native.calls.count { it == "intercept" })
    }

    @Test fun diagnosticReadbackFailureKeepsTheInstalledHandle() {
        val native = Native().apply { readFailure = IllegalStateException("readback"); bind() }
        val handle = install()
        assertNotNull(handle)
        assertEquals(1, native.calls.count { it == "intercept" })
        handle!!.unhook()
        assertEquals(1, native.unhooks)
    }

    @Test fun wrappedFatalReadbackEscapesWithoutReplacingOrReinstalling() {
        for (fatal in listOf(OutOfMemoryError("ID"), ThreadDeath(), InternalError("ID"))) {
            val native = Native().apply { readFailure = RuntimeException(fatal); bind() }
            try {
                install()
                fail("fatal must escape")
            } catch (actual: Throwable) {
                assertSame(fatal, actual)
            }
            assertEquals(1, native.calls.count { it == "intercept" })
            assertEquals(0, native.unhooks)
        }
    }

    private fun install() = ModuleHelper.findAndHookMethodWithId(
        Target::class.java, "run", XposedHelpers.HOOK_ID_APPLICATION_ATTACH, callback
    )

    class Target { fun run() {} }

    private class Native(private val expected: Executable = Target::class.java.getDeclaredMethod("run")) {
        val calls = ArrayList<String>()
        var priority = 0
        var mode: Any? = null
        var id: String? = null
        var hook: Any? = null
        var unhooks = 0
        var idFailure: Throwable? = null
        var installFailure: Throwable? = null
        var readFailure: Throwable? = null
        private val loader = javaClass.classLoader!!
        private val handle = Proxy.newProxyInstance(loader, arrayOf(XposedInterface.HookHandle::class.java)) { _, method, _ ->
            when (method.name) {
                "getId" -> { calls.add("readId"); readFailure?.let { throw it }; id }
                "unhook" -> { unhooks++; null }
                "replaceHook" -> error("replacement is not enabled")
                else -> null
            }
        }
        private val builder = Proxy.newProxyInstance(loader, arrayOf(XposedInterface.HookBuilder::class.java)) { proxy, method, args ->
            when (method.name) {
                "setPriority" -> { calls.add("priority"); priority = args!![0] as Int; proxy }
                "setExceptionMode" -> { calls.add("mode"); mode = args!![0]; proxy }
                "setId" -> { calls.add("id"); idFailure?.let { throw it }; id = args!![0] as String; proxy }
                "intercept" -> { calls.add("intercept"); installFailure?.let { throw it }; hook = args!![0]; handle }
                else -> null
            }
        }
        fun bind() {
            XposedHelpers.moduleInst = Proxy.newProxyInstance(loader, arrayOf(XposedInterface::class.java)) { _, method, args ->
                when (method.name) {
                    "hook" -> { assertEquals(expected, args!![0]); builder }
                    else -> null
                }
            } as XposedInterface
        }
    }
}
