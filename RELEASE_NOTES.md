# r13.7.0 正式版 / Formal Release

米客 A13 面向 MIUI 14 / Android 13 的正式稳定版本。

本版本完成 System、SystemUI、Launcher 工程拆分与迁移审计，补齐异步回调异常隔离和生命周期所有权，并优化设备监控、应用图标、设置搜索、AudioVisualizer 与锁屏专辑图的队列和缓存边界。

LSPosed 2.1.1（7790）实机日志未发现模块因果 crash、ANR、Fatal、Hook/反射失败或异常刷屏。发布门禁覆盖 678 个单元测试、invariants、迁移审计、三档 Lint、Debug/Release、R8、资源压缩、zipalign 和 v2 签名。

> 适用范围仅为 MIUI 14 / Android 13。不同 ROM 仍可能需要单项兼容；日志与静态门禁不能证明所有功能在所有系统变体上的视觉和行为完全一致。

## 下载校验

- `CustoMIUIzer-A13-r13.7.0.apk`
- `2,781,130` bytes（`2.652 MiB`）
- SHA-256：`0A7A07C6A6639DA8890912D6EE145FAB5123F6288D1F98F121AFB1572F75C8A8`
- 证书 SHA-256：`C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70`

证书 DN 的历史名称为 `CN=CustoMIUIzer A14`；本次未更换签名者，以保持已测试 A13 构建的升级兼容。该名称不表示支持 Android 14。

---

CustoMIUIzer A13 formal stable release for MIUI 14 / Android 13.

This release completes the System, SystemUI, and Launcher engineering split and migration audit, adds deferred-callback isolation and lifecycle ownership, and bounds queues and caches for device monitoring, application icons, settings search, AudioVisualizer, and lock-screen album art.

The LSPosed 2.1.1 (7790) device evidence contains no module-causal crash, ANR, Fatal, Hook/reflection failure, or repeated exception. Release gates cover 678 unit tests, runtime invariants, migration audit, all three Lint variants, Debug/Release, R8, resource shrinking, zip alignment, and v2 signing.

> This release supports MIUI 14 / Android 13 only. Individual features may still require ROM-specific compatibility work; logs and static gates cannot prove every visual and behavioral permutation on every system build.
