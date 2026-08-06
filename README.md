# CustoMIUIzer A13｜MIUI 14 / Android 13

简体中文 | [English](README_EN.md)

CustoMIUIzer A13 是面向 MIUI 14 / Android 13 的系统界面与交互定制模块，并为 HyperOS 1 / Android 13 提供基于能力探测的兼容路径。

## 当前版本

| 项目 | 值 |
| --- | --- |
| 版本 | `r13.10.1` |
| versionCode | `135` |
| 应用 ID | `tv.withaibuild.customiuizer.r13` |
| 架构 | `arm64-v8a` |
| 实装框架 | [Vector v2.2](https://github.com/JingMatrix/Vector/releases/tag/v2.2) |
| 源码 | <https://github.com/tomthenpc/customiuizer-a13> |

## 兼容范围与要求

- MIUI 14 / Android 13 为主要兼容目标；
- HyperOS 1 / Android 13 通过 ROM Contract 与目标能力探测选择兼容路径，具体功能取决于 ROM 与系统应用版本；
- 设备需要 Root，并安装 [Vector v2.2](https://github.com/JingMatrix/Vector/releases/tag/v2.2)；
- 模块元数据：`minApiVersion=101`、`targetApiVersion=102`、`staticScope=false`；
- 不支持 Android 14 及以上版本；
- 请勿与上游版或其他 CustoMIUIzer 派生模块同时启用。

已知实装基线：Redmi Note 11T Pro（`xaga`）、MIUI `V14.0.10.0.TLOINXM`、Vector v2.2。

## 主要功能

- 状态栏时钟、日期、温度、网速、电池、信号与图标布局；
- 控制中心、通知、音量、亮度、锁屏、媒体与充电界面；
- Launcher 图标、文件夹、Dock、最近任务、手势与动画；
- 导航键、按键动作、电源菜单、浮窗、多窗口、安装器、分享与应用权限。

`r13.10.1` 重点完善按进程安装架构、ROM 兼容探测、偏好与生命周期状态、异常边界和用户行为回调，并优化 DeviceInfo 与 Launcher 高频路径。详细变化见 [CHANGELOG.md](CHANGELOG.md)。

## 安装

1. 安装 [Vector v2.2](https://github.com/JingMatrix/Vector/releases/tag/v2.2)；
2. 从本仓库最新 Release 下载并安装 APK；
3. 在 Vector 中启用模块并确认建议作用域；
4. 打开一次模块设置，然后完整重启设备。

## 问题反馈

功能兼容性取决于 ROM 与系统应用版本。异常时请先关闭对应功能，并提供设备、ROM、Vector 版本、作用域、复现步骤与相关日志。

源码与问题反馈：<https://github.com/tomthenpc/customiuizer-a13>
