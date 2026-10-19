# 米客 A13｜CustoMIUIzer A13 架构

## 入口与进程

```text
LSPosed / libxposed
  → MainModule → PreferenceBootstrap → ProcessScopes
  → 目标进程 Installer → Catalog Contract / Resolver 或 Legacy Hook
  → 有所有者的 Receiver / Observer / Controller
```

`MainModule` 只接受 Android 13。`onPackageReady` 先检查首包与可安装 scope，
再读取目标偏好；`system_server` 通过独立的 `onSystemServerStarting` 入口安装。
NetworkStack、SystemUI 插件进程、Settings / SecurityCenter 的非主进程不进入安装路径。

两个入口各自使用一次原子占用，覆盖 Catalog 与 Legacy Direct Hook；已开始的安装不会因
偏好变更、重复回调或部分失败重新开始。Launcher / 普通应用的 `Application.attach` 和
SystemUI 的 `onCreate` 回调还在目标验证后单独占用，失败的原始生命周期调用不触发安装。
关闭功能不创建业务 Hook、Receiver、Observer 或任务；开启需重启的功能仍遵循原有重启语义。

Installer 按进程分工：SystemServer、SystemUi、Launcher、AndroidPackage、GenericApp、
InputMethod、Settings、SecurityCenter、PowerKeeper、Wallpaper、Media、Phone、PackageInstallerRouter。
入口只做分发；ROM 行为留在现有 Hook 与合同中，不为了统一外观迁移所有 Legacy Hook。

## 功能身份与安装

Catalog 路径为 `FeatureDispatcher → FeatureInstallRegistry → FeatureSpec`。
稳定身份为 canonical feature id；别名只用于查找。安装键为进程身份、canonical id 与
ClassLoader 的对象身份，不能跨 ClassLoader 共用安装状态。

Registry 原子占用 `INSTALLING`，兼容探测与安装完成前不释放占用。
条件未启用、进程不匹配或阶段错误不安装；兼容探测失败可重试；已经产生 Hook 的部分失败、
无法证明无副作用的 installer 异常进入终端失败。明确无 Hook 副作用的失败仍可返回
`FailedTransient`。成功与部分失败的 Hook 计数由同一转换函数解释。

`HookTargetResolver` 属于各 `FeatureRuntime` 的 ClassLoader；按需、安全发布，
冷路径缓存访问串行化且有界。`HookInstaller.withSession` 在当前线程内绑定选中的
variant，`finally` 清除会话，避免 probe 与实际 installer 选择不同目标。

## 偏好与所有权

`PreferenceBootstrap` 在监听器注册后再次读取偏好，再原子发布稳定快照。
二次读取失败可以复用已注册监听器恢复；部分注册失败会先撤销同一监听器。
全量通知（null key）会替换整个快照，并通知被删除或恢复的键。
快照构建使用一次可变 Map 填充和一次原子发布，不逐键复制整个不可变快照。
偏好变化只更新数据，不重置安装状态。

`ModuleHelper` 的 Receiver 注册在框架注册成功后替换旧所有者；注册后抛错会回滚新注册，
保留旧注册。释放失败进入现有有界 stale 队列。owned Receiver 不允许注册结果落到已移除的桶。
Observer、View 和短生命周期 controller 保持原有 owner / 弱引用 / 释放规则。

普通异常局部隔离；`RuntimeFatality.throwIfFatal` 传播致命错误及其包装原因。
不静态强持有 Activity、View 或短生命周期 controller。

设置端 `XposedServiceManager` 由 Application 初始化一次，统一持有 Service 和本地偏好监听。
同步在单个串行 worker 上合并请求，空闲线程自动退出；不随 Activity 重建注册或释放。
连接按对象身份交接，旧连接死亡不清除新连接；失败提交的写入和删除保留待确认状态，
不把 SDK 内存缓存等同于框架持久化，也不循环重试。动态应用沿用 `PreferenceLoadRegistry`
的四组开关与选择键，仅对已启用、未授权的包发起 API 102 作用域请求。

## API 102 原生安装

`XposedHelpers` 的同一原生安装函数处理方法和构造器，统一 priority、`PASSTHROUGH`、handle
及 unhook。`HookerClassHelper` 保留 A13 的参数改写、提前返回和 after 异常恢复行为，
不是 API 101 兼容层。固定 ID 只用于 `application.attach` 与 `systemui.application.create`；
对应 API 33 签名已由 xaga framework / SystemUI 验证。ID 赋值失败不安装，安装绝不重试；
回读失败仅影响诊断，保留 handle。ID 不取代入口原子占用，也不扩展到无交接设计的业务 Hook。

不启用热重载、显式 `replaceHook` 或 detach。Service SDK 随 APK 打包，Hook API 保持
`compileOnly`；R8 验证两者的设置进程类加载边界。

## 平台与构建

- MIUI 14 / Android 13 主支持；HyperOS 1 / Android 13 实验兼容。
- `applicationId=tv.withaibuild.customiuizer.r13`，ABI `arm64-v8a`。
- `minSdk=33`、`targetSdk=34`；`compileSdk=37` 不改变设备支持范围。
- libxposed API / Service 102.0.0，最低和目标均为 API 102，`staticScope=true`。
- AGP 9.4.1 / Gradle 9.8.1 / Kotlin 2.4.21 / JDK 25；Java/Kotlin 字节码目标 25。
- 热路径只读已准备好的状态；反射、Binder、I/O 与兼容探测留在冷路径。
- DexSQL 仅为开发工具，见 [查询入口](tools/dexsql/README.md)。

A13 / A14 共享产品意图与纯逻辑验证思路，不共享 ROM 合同、ClassLoader 假设、设备结论、
生产分支、APK 或签名。功能分类与历史证据入口见 [A13_PARITY](docs/A13_PARITY.md)。
