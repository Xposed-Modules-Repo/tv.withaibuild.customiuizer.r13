# 更新日志

## r13.9.2（2026-08-01）

- 锁屏专辑图 View 脱离后释放背景、单帧缓存和静态处理结果，降低 SystemUI 大 Bitmap 驻留；
- 设置页切换动画恢复为 350ms，修正过快的页面节奏；
- 开关按下即显示反馈，并移除每次点击创建的透明度动画；
- 模块列表使用独立简洁摘要，不再展开完整 README；
- 未修改 MIUI 14 / Android 13 或 HyperOS 1 / Android 13 的 ROM Hook 目标与 fallback。

APK SHA-256：`0542E87E5FED06A1BBEC5509C7F5412555D8677850BE939032CD15A2F439BD80`

签名证书 SHA-256：`15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

## 历代核心实现总结

A13 版本线已完成独立包名与 A13 签名、libxposed API 101/102、System/SystemUI/Launcher 拆分、Kotlin 化、资源与偏好 Hook 加固、生命周期治理、有界缓存、OOM 边界和 Contract/Resolver 兼容诊断。详细历史保留在源码仓库的 Git tag 与提交记录中。
