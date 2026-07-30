# 米客 A13 · Kotlin 重构版

[简体中文](README.md) | [English](README_EN.md)

面向 MIUI 14 / Android 13 的 Kotlin 重构版系统界面与交互定制 Xposed 模块。

当前维护线以 Kotlin 为主体，同时保留经过审计的稳定 Java/JVM 边界；“Kotlin 重构版”不表示 100% Kotlin。

本页面用于 LSPosed 模块仓库展示和下载；源码、完整 changelog、构建说明与工程文档位于个人维护仓库 [tomthenpc/customiuizer-a13](https://github.com/tomthenpc/customiuizer-a13)。

## 当前正式版

| 项目 | 值 |
|---|---|
| 版本 | `r13.7.0`（versionCode `124`） |
| 系统 | MIUI 14 / Android 13 |
| ABI | `arm64-v8a` |
| 应用 ID | `tv.withaibuild.customiuizer.r13` |
| libxposed | API 101–102，`staticScope=false` |
| APK | `CustoMIUIzer-A13-r13.7.0.apk` |
| 大小 | `2,781,130` bytes（`2.652 MiB`） |
| APK SHA-256 | `0A7A07C6A6639DA8890912D6EE145FAB5123F6288D1F98F121AFB1572F75C8A8` |
| 证书 SHA-256 | `C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70` |

[下载 r13.7.0](https://github.com/Xposed-Modules-Repo/tv.withaibuild.customiuizer.r13/releases/tag/124-r13.7.0)

## 适用范围

- MIUI 14 / Android 13（API 33）；
- 主要验证设备：Redmi Note 11T Pro / Pro+（`xaga`）；
- 参考 ROM：`V14.0.10.0.TLOINXM`、`V14.0.7.0.TLOCNXM`；
- 建议框架：LSPosed 2.x / Vector 2.x。

Android 14 及更高版本不在本模块支持范围内。不同 MIUI 14 ROM 的 SystemUI、Launcher 和系统应用签名可能不同，部分功能需要单独兼容。

## 维护与 API 边界

- 使用独立包名、版本线、签名和发布流程，不与上游或其他 Android 版本共用安装身份；
- 使用现代 libxposed API，单一 APK 通过 API 101 公共运行路径与 API 102 元数据兼容 API 101/102 实现；
- 不依赖 `de.robv.android.xposed` Legacy Xposed Hook API；
- 当前验证框架为 LSPosed 2.1.1（7790）；
- 仅支持 MIUI 14 / Android 13。

## 功能概览

- 状态栏、电池、信号、网速、时钟、日期和温度；
- 控制中心、音量、亮度、通知和系统动画；
- 锁屏、充电信息、媒体界面、快捷操作和专辑图；
- 桌面、最近任务、文件夹、图标、Dock、抽屉和桌面手势；
- 导航栏、按键、自定义动作、电源菜单、浮窗和 Tasker；
- 应用权限、安装器、分享、隐藏应用、应用锁及其他 MIUI 行为。

## r13.7.0 重点

- 完成 System、SystemUI、Launcher 的工程拆分和迁移审计；
- 隔离异步回调异常，补齐 Receiver / Observer / Listener 的所有权与注销边界；
- 修复偏好初始化、弱引用清理和 Hook 实例生命周期问题；
- 优化设备监控、应用图标、设置搜索、音频可视化与锁屏专辑图的队列和缓存；
- 保持 libxposed API 101 最低运行基线，以 API 102 元数据发布；
- LSPosed 2.1.1（7790）日志中未发现模块因果 crash、ANR、Fatal、Hook/反射失败或异常刷屏。

完整变更见 [CHANGELOG.md](CHANGELOG.md)。

## 安装

1. 从本仓库 Release 下载 APK，并核对 SHA-256。
2. 安装 APK，在 LSPosed / Vector 中启用建议作用域。
3. 打开模块设置一次，并完整重启设备。
4. 按功能组逐项启用和验证。

早期使用不同签名的构建不能直接覆盖安装。若 Android 提示签名不一致，请先备份设置，再卸载旧版本。

证书 DN 保留历史名称 `CN=CustoMIUIzer A14`，但 A13 使用独立包名和版本线，且本次发布没有改变既有 A13 签名者；保留它是为了已测试构建的升级兼容，不表示本 APK 支持 Android 14。

## 反馈

请在 [源码仓库 Issues](https://github.com/tomthenpc/customiuizer-a13/issues) 提交问题，并附上模块版本、设备与 ROM、系统应用版本、LSPosed/Vector 版本、实际作用域、复现步骤和完整日志。

包名出现在系统日志中不等于模块因果问题。请同时提供 Hook 失败、模块栈、崩溃或 ANR 上下文。
