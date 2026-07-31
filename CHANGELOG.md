# 更新日志

> 说明：Releases 页面仅保留当前正式版。旧版本完整变更记录继续保留在本文件；旧版 APK 不再提供下载，历史源码 tag 仍保留。

## r13.8.6

* 合并 A13 最新维护、兼容诊断、作用域和 UI 改进；
* 完善 Hook 目标解析、安装结果记录和兼容回退；
* 加强 Receiver、Observer、计步器、设备监控和锁屏专辑图生命周期；
* 优化状态栏、通知、网速、电池、时钟和 Launcher 高频路径；
* 状态栏网速保留系统字体，并支持双排网速行距调整；
* 修复设置文本样式继承和 About 页面文字换行；
* 保持 MIUI 14 / Android 13、`arm64-v8a` 与 libxposed API 101/102 兼容。

### APK

* 文件：`CustoMIUIzer-A13-r13.8.6.apk`
* 大小：`2836582 bytes`
* SHA-256：`ABF31CE311253AE863F7B2CEB87BF95140EE706EFF39ADA219033552B6FA7287`
* 签名证书 SHA-256：`C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70`
* versionCode / versionName：`131 / r13.8.6`

### 验证说明

本版本已完成 APK 构建、签名、zipalign、包信息和 Xposed 元数据基础检查；未执行完整测试套件和全功能实机回归。

## r13.7.0

### 稳定性

- 为 Hook 注册的 Receiver、Observer、Listener、Handler 与 Runnable 增加模块异常边界。
- 使用稳定 owner 和注册 key 管理回调替换、注销和宿主销毁。
- 修复 RemotePreferences 初始化、监听注册与镜像恢复问题。
- 修复弱引用清理、Hook 实例附加字段和 Launcher 循环退出语义。

### 性能与生命周期

- 避免只读 Hook 参数时复制参数数组。
- 设备监控在熄屏时暂停，并使用有界退避。
- 应用图标加载增加有界队列、请求去重、弱 View 等待者和字节 LRU。
- 设置搜索、AudioVisualizer 和锁屏专辑图采用 latest-wins、generation 与明确取消边界。

### 兼容与验证

- 仅支持 MIUI 14 / Android 13，ABI 为 `arm64-v8a`。
- applicationId 保持 `tv.withaibuild.customiuizer.r13`。
- libxposed 保持 API 101–102，`staticScope=false`。
- LSPosed 2.1.1（7790）日志未发现模块因果 crash、ANR、Fatal、Hook/反射失败或异常刷屏。
- 678 个单元测试、三档 Lint、R8、资源压缩、zipalign 和 v2 签名门禁通过。

### 下载校验

- APK：`CustoMIUIzer-A13-r13.7.0.apk`
- 大小：`2,781,130` bytes（`2.652 MiB`）
- SHA-256：`0A7A07C6A6639DA8890912D6EE145FAB5123F6288D1F98F121AFB1572F75C8A8`
- 证书 SHA-256：`C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70`
