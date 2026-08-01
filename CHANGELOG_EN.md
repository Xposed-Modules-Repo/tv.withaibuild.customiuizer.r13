# Changelog

## r13.9.1 (2026-08-01)

- Improved Hook target capability checks and failure diagnostics for MIUI 14 / Android 13 and HyperOS 1 / Android 13;
- Fixed ResourceHooks argument handling and status-bar clock installation diagnostics;
- Hardened lifecycle ownership for receivers, preferences, step counter, device monitor, audio visualization, lock-screen album art, and delayed input;
- Reduced reflection and temporary objects in status-bar, notification, Launcher, and resource-replacement hot paths;
- Shortened settings transitions and made Preference/switch clicks show feedback sooner;
- Isolated ordinary feature failures while preserving `OutOfMemoryError` propagation.

The formal APK is `CustoMIUIzer-A13-r13.9.1.apk`, with SHA-256 `98F03BFB1FA29E776C3A638E771CCE6D1672F5C94F91B39B7D7D4362DB6EF96C` and A13 signing certificate SHA-256 `15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`.

> r13.8.6 used a different historical certificate. Back up settings and uninstall the old release before installing r13.9.1.

## Historical implementation summary

The A13 release line established an independent package and libxposed API 101/102 migration, System/SystemUI/Launcher domain separation, small Kotlin migrations, hardened preferences and resource Hooks, receiver/observer/handler lifecycle ownership, bounded caches and cancellable asynchronous work, explicit OOM boundaries, Contract/Resolver compatibility diagnostics, and continuing performance work across status bar, lock screen, notifications, Launcher, and settings UI. Detailed history remains available through source-repository Git tags and commits.
