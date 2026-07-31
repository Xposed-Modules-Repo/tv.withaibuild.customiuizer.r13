# Changelog

## r13.8.6

* Merged the latest A13 maintenance, compatibility diagnostics, scope and UI improvements;
* Improved hook target resolution, install result recording and compatibility fallback;
* Strengthened lifecycles for receivers, observers, step counter, device monitor and lock-screen album art;
* Optimized status bar, notification, network speed, battery, clock and Launcher hot paths;
* Preserved the system typeface for status-bar network speed and added dual-row line spacing adjustment;
* Fixed settings text style inheritance and About page wrapping;
* Remains compatible with MIUI 14 / Android 13, `arm64-v8a` and libxposed API 101/102.

### APK

* File: `CustoMIUIzer-A13-r13.8.6.apk`
* Size: `2836582 bytes`
* SHA-256: `ABF31CE311253AE863F7B2CEB87BF95140EE706EFF39ADA219033552B6FA7287`
* Signing certificate SHA-256: `C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70`
* versionCode / versionName: `131 / r13.8.6`

### Verification notes

This release completed APK build, signature, zipalign, package info and Xposed metadata basic checks; the full test suite and full-device regression were not executed.

## r13.7.0

### Stability

- Added module exception boundaries for receivers, observers, listeners, handlers, and runnables registered by Hooks.
- Managed callback replacement, cleanup, and owner destruction through stable owners and registration keys.
- Fixed RemotePreferences initialization, listener registration, and mirror recovery.
- Fixed weak-reference cleanup, Hook-instance additional fields, and migrated Launcher loop-exit semantics.

### Performance and lifecycle

- Avoided argument-array copies for read-only Hook arguments.
- Suspended device monitoring with the screen off and added bounded backoff.
- Added bounded icon-loading queues, request deduplication, weak View waiters, and a byte-sized LRU.
- Added latest-wins, generation, and explicit cancellation boundaries to settings search, AudioVisualizer, and lock-screen album art.

### Compatibility and verification

- Supports MIUI 14 / Android 13 only, with `arm64-v8a` ABI.
- Retains application ID `tv.withaibuild.customiuizer.r13`.
- Retains libxposed API 101–102 with `staticScope=false`.
- LSPosed 2.1.1 (7790) evidence contains no module-causal crash, ANR, Fatal, Hook/reflection failure, or repeated exception.
- 678 unit tests, all three Lint gates, R8, resource shrinking, zip alignment, and v2 signing passed.

### Download verification

- APK: `CustoMIUIzer-A13-r13.7.0.apk`
- Size: `2,781,130` bytes (`2.652 MiB`)
- SHA-256: `0A7A07C6A6639DA8890912D6EE145FAB5123F6288D1F98F121AFB1572F75C8A8`
- Certificate SHA-256: `C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70`
