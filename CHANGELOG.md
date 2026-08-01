# 更新日志

## r13.9.1（2026-08-01）

- 增强 MIUI 14 / Android 13 与 HyperOS 1 / Android 13 的 Hook 目标能力探测和失败诊断；
- 修复 ResourceHooks 参数处理与状态栏时钟安装诊断；
- 加固 Receiver、偏好、计步器、设备监控、音频可视化、锁屏专辑图和延迟输入的生命周期；
- 减少状态栏、通知、桌面与资源替换高频路径的反射和临时对象；
- 缩短设置页面动画，使 Preference 和开关点击更快显示反馈；
- 普通功能异常安全隔离，`OutOfMemoryError` 不被吞掉。

正式 APK 为 `CustoMIUIzer-A13-r13.9.1.apk`，SHA-256 为 `98F03BFB1FA29E776C3A638E771CCE6D1672F5C94F91B39B7D7D4362DB6EF96C`，A13 签名证书 SHA-256 为 `15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`。

> r13.8.6 使用了不同的历史证书，升级前必须备份设置并卸载旧版。

## 历代核心实现总结

历代 A13 版本完成了独立包名和 libxposed API 101/102 迁移、System/SystemUI/Launcher 拆分、小批量 Kotlin 化、偏好与资源 Hook 加固、Receiver/Observer/Handler 生命周期治理、有界缓存和异步任务取消、OOM 边界、Contract/Resolver 兼容诊断，以及状态栏、锁屏、通知、桌面和设置界面的持续性能优化。详细历史保留在源码仓库的 Git tag 和提交记录中。
