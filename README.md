# 米客 A13｜CustoMIUIzer A13

简体中文 | [English](README_EN.md)

面向 **MIUI 14 / Android 13** 的系统界面与交互定制模块，持续维护 Android 13 版本线。**HyperOS 1 / Android 13 为实验兼容**。

[下载最新正式版](https://github.com/Xposed-Modules-Repo/tv.withaibuild.customiuizer.r13/releases/latest) · [发行记录](https://github.com/Xposed-Modules-Repo/tv.withaibuild.customiuizer.r13/releases) · [源码与反馈](https://github.com/thetvplus/customiuizer-a13)

> **签名已变更，不能直接覆盖旧版。** 原 A13 发布密钥目前无法取得。请先将设置备份成功导出到下载或文档目录，再卸载旧版、安装新版、恢复备份，重新启用模块并重启。备份失败时请保留旧版。

## 当前版本

| 项目 | 值 |
| --- | --- |
| 版本 | `r13.13.0`（versionCode `141`） |
| 应用 ID | `tv.withaibuild.customiuizer.r13` |
| 架构 | `arm64-v8a` |
| 框架 | 提供 libxposed API 102 的 LSPosed / Vector |
| 源码 tag | [r13.13.0](https://github.com/thetvplus/customiuizer-a13/tree/r13.13.0) |
| APK | `CustoMIUIzer-A13-r13.13.0.apk`，两仓库文件一致 |

本版修复设置备份、来电过滤和通知设置，收敛 Hook 安装、Service 与偏好监听生命周期。完整变化见 [CHANGELOG.md](CHANGELOG.md)。

## 兼容范围

- MIUI 14 / Android 13 为主要目标；HyperOS 1 / Android 13 的功能可用性取决于具体 ROM 与系统应用版本。
- 需要 Root、`arm64-v8a` 设备和 libxposed API 102；不再兼容仅提供 API 101 的框架。
- 模块元数据：`minApiVersion=102`、`targetApiVersion=102`、`staticScope=true`。
- 不支持 Android 14+；请勿与上游或其他 CustoMIUIzer 派生模块同时启用。

## 主要功能

- 状态栏时钟、日期、温度、网速、电池、信号与图标布局；
- 控制中心、通知、音量、亮度、锁屏、媒体与充电界面；
- Launcher 图标、文件夹、Dock、最近任务、手势与动画；
- 导航键、按键动作、电源菜单、浮窗、多窗口、安装器、分享与应用权限。

## 安装

1. 确认框架支持 API 102；从本仓库 Release 下载 APK。旧版用户先按上方说明备份、卸载。
2. 安装后启用模块并确认建议作用域。
3. 打开模块设置；升级用户恢复备份，然后完整重启设备。

静态检查、单元测试、lint、R8 与 CI 已通过；本版尚未完成 A13 实机验收。功能异常时请先关闭对应选项，反馈设备、ROM、框架版本、作用域、复现步骤及相关日志。
