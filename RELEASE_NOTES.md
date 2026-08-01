# CustoMIUIzer A13 r13.9.2

适用于 MIUI 14 / Android 13 与 HyperOS 1 / Android 13。

- 锁屏专辑图 View 脱离后释放已处理的大 Bitmap；
- 设置页切换恢复 350ms 节奏；
- 开关按下即反馈且不再创建逐次透明度动画；
- LSPosed 模块列表改用简洁摘要；
- ROM Hook 目标、Contract 与 fallback 未改变。

下载校验：

- `CustoMIUIzer-A13-r13.9.2.apk`
- `2,859,894 bytes`
- APK SHA-256：`0542E87E5FED06A1BBEC5509C7F5412555D8677850BE939032CD15A2F439BD80`
- 签名证书 SHA-256：`15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

r13.8.6 使用了不同证书，不能直接覆盖安装；r13.9.1 可直接升级。r13.9.2 新改动与 HyperOS 1 / Android 13 仍需完整 LSPosed 日志验证。

---

For MIUI 14 / Android 13 and HyperOS 1 / Android 13. This release frees processed lock-screen artwork when its View detaches, restores 350ms settings transitions, gives switches immediate feedback without per-tap alpha animators, and adds a concise LSPosed listing summary. ROM Hook targets, Contracts, and fallbacks are unchanged. r13.9.1 can be upgraded in place; r13.8.6 used a different certificate. New changes and HyperOS 1 targets still require complete on-device LSPosed logs.
