# CustoMIUIzer A13（米客 A13）

简体中文 | [English](README_EN.md)

适用于 Android 13 的 MIUI / HyperOS 系统界面与交互定制模块。

## 当前正式版

- 版本：`r13.9.1`（versionCode `132`）
- APK：`CustoMIUIzer-A13-r13.9.1.apk`
- 大小：`2,860,194 bytes`
- SHA-256：`98F03BFB1FA29E776C3A638E771CCE6D1672F5C94F91B39B7D7D4362DB6EF96C`
- 签名证书 SHA-256：`15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

## 兼容范围与要求

- MIUI 14 / Android 13；
- HyperOS 1 / Android 13；
- `arm64-v8a`；
- 已 Root，并安装支持 libxposed API 101 或 102 的 LSPosed / Vector；
- 不支持 Android 14 及以上版本。

已知实机基线为 Redmi Note 11T Pro（`xaga`）、MIUI `V14.0.10.0.TLOINXM`、LSPosed 2.1.1。HyperOS 1 / Android 13 是正式兼容目标，但不同 ROM 的 SystemUI、Launcher 和 system_server 结构可能不同，具体功能仍需按 ROM 日志确认。

## 主要功能

- 状态栏时钟、日期、温度、网速、电池、信号和图标布局；
- 控制中心、通知、音量、亮度、锁屏、媒体与充电信息；
- 桌面图标、文件夹、Dock、最近任务、手势和动画；
- 导航键、按键动作、电源菜单、浮窗、安装器、分享和应用权限。

## 安装

1. 从本仓库最新 Release 下载 APK，并核对 SHA-256；
2. 备份现有模块设置；
3. 如果已安装 r13.8.6，请先卸载旧版；该版本使用了不同证书，不能直接覆盖安装；
4. 安装 r13.9.1，在 LSPosed / Vector 中启用模块并确认建议作用域；
5. 打开一次模块设置，然后完整重启设备。

## 风险提示

这是修改系统进程和系统界面的 Xposed 模块。ROM 更新、系统应用更新或不匹配的作用域可能导致单项功能失效、SystemUI 重启或 Launcher 异常。首次安装后请分批启用功能；遇到问题时先关闭相关功能，并提交设备、ROM、框架版本、作用域、复现步骤和完整 LSPosed 日志。

静态测试和 APK 校验不能替代所有 ROM 的实机回归。本版本新增改动及 HyperOS 1 / Android 13 仍待更多实机日志验证。

源码：<https://github.com/tomthenpc/customiuizer-a13>
