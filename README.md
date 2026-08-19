# CustoMIUIzer A13｜MIUI 14 / Android 13

简体中文 | [English](README_EN.md)

> **维护状态**
>
> 本项目已停止主动开发，维护者已转向其他系统。
> 当前版本继续作为 MIUI 14 / Android 13 的稳定维护版本保留。
> 后续仅在出现严重问题、必要兼容修复或有实际使用需求时更新。

CustoMIUIzer A13 是面向 MIUI 14 / Android 13 的系统界面与交互定制模块，并为 HyperOS 1 / Android 13 提供基于能力探测的兼容路径。

## 当前版本

| 项目 | 值 |
| --- | --- |
| 版本 | `r13.12.2` |
| versionCode | `140` |
| 应用 ID | `tv.withaibuild.customiuizer.r13` |
| 架构 | `arm64-v8a` |
| 实装框架 | LSPosed / Vector |
| 源码 | <https://github.com/tomthenpc/customiuizer-a13> |

## 兼容范围与要求

- MIUI 14 / Android 13 为主要兼容目标；
- HyperOS 1 / Android 13 通过 ROM Contract 与目标能力探测选择兼容路径，具体功能取决于 ROM 与系统应用版本；
- 设备需要 Root，并安装 LSPosed / Vector 框架；
- 模块元数据：`minApiVersion=101`、`targetApiVersion=102`、`staticScope=false`；
- 不支持 Android 14 及以上版本；
- 请勿与上游版或其他 CustoMIUIzer 派生模块同时启用。

已知实装基线：Redmi Note 11T Pro（`xaga`）、MIUI `V14.0.10.0.TLOINXM`。

## 主要功能

- 状态栏时钟、日期、温度、网速、电池、信号与图标布局；
- 控制中心、通知、音量、亮度、锁屏、媒体与充电界面；
- Launcher 图标、文件夹、Dock、最近任务、手势与动画；
- 导航键、按键动作、电源菜单、浮窗、多窗口、安装器、分享与应用权限。

`r13.12.2` 已包含并取代 `r13.12.0` / `r13.12.1`。本版本最终纳入 USB 默认用途、安装器净化、桌面 Dock 高度、隐藏输入法关闭按钮、状态栏视觉能力、文件夹模糊关闭、Backup V2 + 旧备份恢复、自定义电池指示条颜色与息屏 dim 比例等新增能力，并修复 MultiAction / 手势持久化、Spinner 越界、Launcher 重启范围、USB 重插、应用/快捷方式/Activity 选择结果回传等问题。详细变化见 [CHANGELOG.md](CHANGELOG.md)。

## 安装

1. 安装 LSPosed / Vector 框架；
2. 从本仓库最新 Release 下载并安装 APK；
3. 在 LSPosed / Vector 框架中启用模块并确认建议作用域；
4. 打开一次模块设置，然后完整重启设备。

## 问题反馈

功能兼容性取决于 ROM 与系统应用版本。异常时请先关闭对应功能，并提供设备、ROM、框架版本、作用域、复现步骤与相关日志。

源码与问题反馈：<https://github.com/tomthenpc/customiuizer-a13>
