# 米客 A13 Kotlin 重构｜MIUI 14 / Android 13｜支持 API 101/102

简体中文 | [English](README_EN.md)

面向 **MIUI 14 / Android 13** 的系统界面与交互定制模块。

## 当前正式版

| 项目           | 值                                 |
| ------------ | --------------------------------- |
| 版本           | `r13.8.6`                         |
| versionCode  | `131`                             |
| 系统           | MIUI 14 / Android 13              |
| ABI          | `arm64-v8a`                       |
| 应用 ID        | `tv.withaibuild.customiuizer.r13` |
| libxposed    | API 101–102                       |
| APK          | `CustoMIUIzer-A13-r13.8.6.apk`    |
| 大小           | `2836582 bytes`        |
| APK SHA-256  | `ABF31CE311253AE863F7B2CEB87BF95140EE706EFF39ADA219033552B6FA7287`                  |
| 签名证书 SHA-256 | `C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70`                 |

> Releases 页面仅保留当前正式版。旧版本的变更记录已合并到当前 Release 和 CHANGELOG；旧版 APK 不再提供下载，历史源码 tag 继续保留。

## r13.8.6 更新重点

* 合并 A13 最新维护、兼容诊断、作用域和 UI 改进；
* 完善 Hook 目标解析、安装结果记录和兼容回退；
* 加强 Receiver、Observer、计步器、设备监控和锁屏专辑图生命周期；
* 优化状态栏、通知、网速、电池、时钟和桌面高频路径；
* 状态栏网速保留系统字体，并支持双排行距调整；
* 修复设置文本样式和 About 页面换行。

## 适用范围

* MIUI 14 / Android 13（API 33）；
* `arm64-v8a`；
* 实现 libxposed API 101 或 API 102 的 LSPosed / Vector；
* 不支持 Android 14 及更高版本。

不同 ROM 的 SystemUI、Launcher 和系统应用实现可能不同，部分功能可能需要单独适配。

## 安装

1. 从本仓库 Release 下载 APK；
2. 核对 APK SHA-256；
3. 安装并在 LSPosed / Vector 中启用模块；
4. 确认建议作用域；
5. 打开一次模块设置并完整重启设备。

使用不同签名的早期构建不能直接覆盖安装。遇到签名不一致时，请先备份设置，再卸载旧版。

## 验证状态

本版本已完成 Release APK 构建、签名、zipalign、包信息和 Xposed 元数据基础检查。

本次发布未执行完整单元测试、Lint、工程 Audit 或全部功能实机回归。

## 源码与反馈

源码、完整 changelog 和工程说明：

`https://github.com/tomthenpc/customiuizer-a13`

提交问题时请附模块版本、设备、ROM、框架版本、实际作用域、复现步骤和完整日志。
