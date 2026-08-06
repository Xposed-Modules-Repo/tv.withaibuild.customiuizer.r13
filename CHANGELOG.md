# Changelog

简体中文 | [English](CHANGELOG_EN.md)

## r13.10.1 — 2026-08-06

- 按目标进程拆分 SystemUI、Launcher、`system_server` 与普通应用安装入口，并使用稳定 Feature 身份和安装状态减少无关加载与重复安装；
- 加固启动早期偏好快照、并发加载、空快照和失败状态，避免偏好更新导致重复 Hook 或错误状态回退；
- 完善 MIUI 14 与 HyperOS 1 / Android 13 的 ROM Contract、目标解析和 variant 选择，缺失目标时仅跳过受影响功能；
- 普通 ROM、反射和回调异常继续隔离，`OutOfMemoryError`、`ThreadDeath` 与 `VirtualMachineError` 保持传播；
- 完善 Receiver、Observer、View、Handler 和控制器的替换、失效与释放路径，并移除选择器界面的阻塞等待；
- 加固来电不中断、移除安全窗口、截图临时隐藏悬浮窗、通知/分享浮窗、小窗与多窗口限制等回调路径；
- 优化 Launcher 动画缩放、HotSeats 手势状态和 FSG Class 解析，减少重复反射、配置读取与触摸事件开销；
- DeviceInfo 改用固定缓冲区和逐字节 sysfs 解析，减少周期性 I/O、Binder 查询和临时对象；
- 增加 Release 编译、测试、Lint、R8、依赖完整性、Manifest 与 Xposed 元数据门禁，并清理未引用依赖和异常元数据。

已知实装基线：Redmi Note 11T Pro（`xaga`）、MIUI `V14.0.10.0.TLOINXM`。HyperOS 1 的具体功能可用性取决于 ROM 与系统应用版本。

## r13.9.2 — 2026-08-01

- 锁屏专辑图 View 脱离后释放背景、单帧缓存和静态处理结果；
- 设置页切换动画调整为 `350ms`；
- 开关按下即显示反馈，并移除逐次创建的透明度动画；
- 模块列表使用独立简洁摘要。

## 历代核心实现总结

A13 版本线建立了独立包名和 Android 13 维护线，完成 libxposed API 101/102 兼容、System/SystemUI/Launcher 拆分、分批 Kotlin 化、资源与偏好 Hook 加固、生命周期治理、有界缓存、致命异常边界与 Contract/Resolver 兼容诊断。详细历史保留在源码仓库的 Git commits 和历史 tags 中。
