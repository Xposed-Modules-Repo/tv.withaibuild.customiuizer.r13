# A13 构建说明

| 工具 | 当前配置 |
| --- | --- |
| Gradle / AGP | 9.8.1 / 9.4.1 |
| JDK / Java target | 25 / 25 |
| Kotlin compiler / language / API / JVM target | 2.4.21 / 2.4 / 2.4 / 25 |
| Android compile / min / target SDK | 37 / 33 / 34 |
| SDK Build Tools | 36.0.0 |
| libxposed API / service | 102.0.0 / 102.0.0 |

AGP 管理内置 Kotlin 集成。根构建脚本显式提供 Kotlin 编译器 2.4.21，以保留 JVM 25 支持；
不再应用 `org.jetbrains.kotlin.android`，不使用 `android.builtInKotlin=false` 或
`android.newDsl=false`。Kotlin runtime 仍由版本目录与 BOM 管理。

工具链参考 A14 `9feacdc6736de98232958ea24b8f722cfb3eff85`，
不移植 A14 的最低 Android SDK 或额外运行依赖。API 102 迁移参考 A14
`cb89042b0ff9e7ce1e9a8efe31ee696fa0694e06` 与 Maven 发布的 API / Service 102.0.0 源码；
Hook API 为 compileOnly，Service 为设置端运行依赖。
升级 `compileSdk` 不改变 A13 产品边界。

开发中运行 `python tools/verify.py fast --changed`；针对性测试可用
`python tools/verify.py fast --tests <TestClassName>`。
收口运行 `python tools/verify.py full`、`python -m compileall tools`、
`python -m unittest discover -s tools/tests -p "test_*.py"` 与 `git diff --check`。
涉及优化器时额外运行 `gradlew :app:minifyReleaseWithR8`；此任务不组装 APK。

依赖校验保持开启。升级后为实际解析的依赖更新 `gradle/verification-metadata.xml`，
正常门禁使用严格校验；Wrapper 使用官方分发与固定 SHA-256。
配置缓存与构建缓存保持启用，不要求反复 clean。

普通验证不读取正式签名。正式包装另需显式 `-PofficialRelease=true` 和仓库外的 A13 签名配置；
不得在 CI 中放入签名密钥。构建成功只证明构建，不证明 A13 ROM 运行行为。

参考：[AGP 9.4](https://developer.android.com/build/releases/agp-9-4-0-release-notes)、
[内置 Kotlin](https://developer.android.com/build/migrate-to-built-in-kotlin)、
[Gradle 9.8.1](https://docs.gradle.org/9.8.1/release-notes.html)。
