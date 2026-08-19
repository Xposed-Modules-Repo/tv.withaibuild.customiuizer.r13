# CustoMIUIzer A13｜MIUI 14 / Android 13

简体中�?| [English](README_EN.md)

CustoMIUIzer A13 是面�?MIUI 14 / Android 13 的系统界面与交互定制模块，并�?HyperOS 1 / Android 13 提供基于能力探测的兼容路径�?

## 当前版本

| 项目 | �?|
| --- | --- |
| 版本 | `r13.12.0` |
| versionCode | `139` |
| 应用 ID | `tv.withaibuild.customiuizer.r13` |
| 架构 | `arm64-v8a` |
| 实装框架 | LSPosed / Vector |
| 源码 | <https://github.com/tomthenpc/customiuizer-a13> |

## 兼容范围与要�?

- MIUI 14 / Android 13 为主要兼容目标；
- HyperOS 1 / Android 13 通过 ROM Contract 与目标能力探测选择兼容路径，具体功能取决于 ROM 与系统应用版本；
- 设备需�?Root，并安装 LSPosed / Vector 框架�?
- 模块元数据：`minApiVersion=101`、`targetApiVersion=102`、`staticScope=false`�?
- 不支�?Android 14 及以上版本；
- 请勿与上游版或其�?CustoMIUIzer 派生模块同时启用�?

已知实装基线：Redmi Note 11T Pro（`xaga`）、MIUI `V14.0.10.0.TLOINXM`�?

## 主要功能

- 状态栏时钟、日期、温度、网速、电池、信号与图标布局�?
- 控制中心、通知、音量、亮度、锁屏、媒体与充电界面�?
- Launcher 图标、文件夹、Dock、最近任务、手势与动画�?
- 导航键、按键动作、电源菜单、浮窗、多窗口、安装器、分享与应用权限�?

`r13.12.0` 修复自定义动�?桌面手势保存后回到“无动作”的问题，并补齐桌面手势页的“重启桌面”入口；同时纳入 USB 默认用途、安装器净化、文件夹模糊、备�?V2 与若干状态栏/桌面视觉增强。详细变化见 [CHANGELOG.md](CHANGELOG.md)�?

## 安装

1. 安装 LSPosed / Vector 框架�?
2. 从本仓库最�?Release 下载并安�?APK�?
3. �?LSPosed / Vector 框架中启用模块并确认建议作用域；
4. 打开一次模块设置，然后完整重启设备�?

## 问题反馈

功能兼容性取决于 ROM 与系统应用版本。异常时请先关闭对应功能，并提供设备、ROM、框架版本、作用域、复现步骤与相关日志�?

源码与问题反馈：<https://github.com/tomthenpc/customiuizer-a13>
