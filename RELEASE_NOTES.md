# CustoMIUIzer A13 r13.12.1

适用于 MIUI 14 / Android 13 与 HyperOS 1 / Android 13。

- 修复 r13.12.0 引入的 MultiAction 选择器结果回传回归；
- 返回上层后应用/快捷方式/Activity 选择不再丢失；
- 保留 r13.12.0 的动作值规范化、Spinner 越界保护、动作契约与 Launcher 重启范围。

下载校验：

- `CustoMIUIzer-A13-r13.12.1.apk`
- `2,936,908 bytes`
- APK SHA-256：`DE5C9979098C6A0C00833E49A53F32A191C8CCB999FC37ACF8F634D41D7B7FB1`
- 签名证书 SHA-256：`15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

静态门禁、Release 编译、Lint、R8 与签名 APK 检查已通过；无 ADB，不声明实机或 MIUI/HyperOS 运行时已验证。

---

For MIUI 14 / Android 13 and HyperOS 1 / Android 13. This hotfix resolves the selector-result regression introduced in r13.12.0, so app/shortcut/activity choices in MultiAction are no longer dropped when returning from child selectors. Static gates, Release compile, Lint, R8, and signed APK inspection passed; no ADB, so on-device or MIUI/HyperOS runtime verification is not claimed.
