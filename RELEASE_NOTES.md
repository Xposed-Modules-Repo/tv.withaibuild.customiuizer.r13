# CustoMIUIzer A13 r13.9.1

适用于 MIUI 14 / Android 13 与 HyperOS 1 / Android 13。

- 改进 Hook 目标探测、ResourceHooks 和状态栏时钟诊断；
- 加固 OOM、Receiver、异步任务和 View 生命周期边界；
- 降低状态栏、通知、Launcher 与资源 Hook 热路径开销；
- 优化设置页动画、Preference 点击和开关即时反馈。

下载校验：

- `CustoMIUIzer-A13-r13.9.1.apk`
- `2,860,194 bytes`
- APK SHA-256：`98F03BFB1FA29E776C3A638E771CCE6D1672F5C94F91B39B7D7D4362DB6EF96C`
- 签名证书 SHA-256：`15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

重要：r13.8.6 使用了不同的历史证书，不能直接覆盖安装。请先备份设置、卸载旧版，再安装 r13.9.1。

MIUI 14 保留既有实机稳定基线；本版本新增改动及 HyperOS 1 / Android 13 仍待新的 LSPosed 详细日志验证。

---

For MIUI 14 / Android 13 and HyperOS 1 / Android 13. This release improves Hook target diagnostics, ResourceHooks, OOM and lifecycle boundaries, hot-path performance, settings transitions, and immediate Preference/switch feedback.

Important: r13.8.6 used a different historical certificate and cannot be upgraded in place. Back up settings, uninstall the old release, and then install r13.9.1. New release changes and HyperOS 1 targets still require detailed on-device LSPosed-log validation.
