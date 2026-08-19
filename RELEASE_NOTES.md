# CustoMIUIzer A13 r13.12.2

> 维护状态：本项目已停止主动开发，维护者已转向其他系统。当前版本继续作为 MIUI 14 / Android 13 的稳定维护版本保留。

适用于 MIUI 14 / Android 13 与 HyperOS 1 / Android 13。

`r13.12.2` 已包含并取代 `r13.12.0` / `r13.12.1`：

- 完成 r13.12.1 未完全覆盖的 Activity 选择结果两跳回传路径；
- 修复 `MultiAction -> AppSelector -> ActivitySelector -> AppSelector -> MultiAction` 中回栈中继结果丢失；
- 修复自定义动作/桌面手势保存后回到“无动作”的问题；
- 桌面手势页补齐“重启桌面”入口；
- USB 默认用途在拔线后重新插上会再次应用；
- 新增/改进：USB 默认用途映射、安装器净化、隐藏应用详情举报入口、Dock 高度、隐藏输入法关闭按钮、文件夹模糊开关、设置备份 V2、电池指示条自定义颜色、息屏变暗比例。

下载校验：

- `CustoMIUIzer-A13-r13.12.2.apk`
- `2,936,876 bytes`
- APK SHA-256：`D847B1608F465BD9996B444E824109C9D81CD92EBF946157489BA0795E529B4A`
- 签名证书 SHA-256：`15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

静态门禁、Release 编译、Lint、R8 与签名 APK 检查已通过；无 ADB，不声明实机或 MIUI/HyperOS 运行时已验证。

---

> Maintenance status: Active development has ended as the maintainer has moved to other systems. The project remains available as a stable maintenance build for MIUI 14 / Android 13.

For MIUI 14 / Android 13 and HyperOS 1 / Android 13.

`r13.12.2` supersedes `r13.12.0` / `r13.12.1`:

- Completes the Activity-selection relay path left incomplete by r13.12.1;
- Fixes `MultiAction -> AppSelector -> ActivitySelector -> AppSelector -> MultiAction` dropping the returned Activity choice;
- Fixes custom/gesture actions snapping back to "No action";
- Adds Restart Launcher on the Launcher gesture page;
- USB default mode reapplies after unplug/replug;
- Adds/improves USB default-mode mapping, installer purify, hide app-details report entry, dock height, hide IME dismiss button, folder blur toggle, settings backup V2, battery indicator custom colors, and screen-dim ratio.

Static gates, Release compile, Lint, R8, and signed APK inspection passed; no ADB, so on-device or MIUI/HyperOS runtime verification is not claimed.
