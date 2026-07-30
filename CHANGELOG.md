# 更新日志

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
