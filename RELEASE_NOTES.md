# CustoMIUIzer A13 r13.12.2

适用于 MIUI 14 / Android 13 与 HyperOS 1 / Android 13。

- 完成 r13.12.1 未完全覆盖的 Activity 选择结果两跳回传路径；
- 修复 `MultiAction -> AppSelector -> ActivitySelector -> AppSelector -> MultiAction` 中回栈中继结果丢失；
- 保留 r13.12.0 的动作值规范化、Spinner 越界保护、动作契约与 Launcher 重启范围。

下载校验：

- `CustoMIUIzer-A13-r13.12.2.apk`
- `2,936,876 bytes`
- APK SHA-256：`D847B1608F465BD9996B444E824109C9D81CD92EBF946157489BA0795E529B4A`
- 签名证书 SHA-256：`15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

静态门禁、Release 编译、Lint、R8 与签名 APK 检查已通过；无 ADB，不声明实机或 MIUI/HyperOS 运行时已验证。

---

For MIUI 14 / Android 13 and HyperOS 1 / Android 13. This final hotfix completes the Activity-selection relay path left incomplete by r13.12.1, so `MultiAction -> AppSelector -> ActivitySelector -> AppSelector -> MultiAction` no longer drops the returned activity choice. Static gates, Release compile, Lint, R8, and signed APK inspection passed; no ADB, so on-device or MIUI/HyperOS runtime verification is not claimed.
