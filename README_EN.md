# CustoMIUIzer A13

[简体中文](README.md) | [English](README_EN.md)

This is the LSPosed listing and download repository for CustoMIUIzer A13. Complete source, build instructions, engineering documentation, and issue tracking are hosted at [tomthenpc/customiuizer-a13](https://github.com/tomthenpc/customiuizer-a13).

## Current formal release

| Item | Value |
|---|---|
| Version | `r13.7.0` (versionCode `124`) |
| System | MIUI 14 / Android 13 |
| ABI | `arm64-v8a` |
| Application ID | `tv.withaibuild.customiuizer.r13` |
| libxposed | API 101–102, `staticScope=false` |
| APK | `CustoMIUIzer-A13-r13.7.0.apk` |
| Size | `2,781,130` bytes (`2.652 MiB`) |
| APK SHA-256 | `0A7A07C6A6639DA8890912D6EE145FAB5123F6288D1F98F121AFB1572F75C8A8` |
| Certificate SHA-256 | `C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70` |

[Download r13.7.0](https://github.com/Xposed-Modules-Repo/tv.withaibuild.customiuizer.r13/releases/tag/124-r13.7.0)

## Compatibility

- MIUI 14 / Android 13 (API 33);
- primary device: Redmi Note 11T Pro / Pro+ (`xaga`);
- reference ROMs: `V14.0.10.0.TLOINXM`, `V14.0.7.0.TLOCNXM`;
- recommended framework: LSPosed 2.x / Vector 2.x.

Android 14 and later are outside this module's support scope. SystemUI, Launcher, and system-app signatures differ between MIUI 14 ROMs, so individual features may require ROM-specific compatibility work.

## Feature overview

- status bar, battery, signal, network speed, clock, date, and temperature;
- control center, volume, brightness, notifications, and system animations;
- lock screen, charging information, media UI, shortcuts, and album art;
- launcher, recents, folders, icons, dock, drawer, and launcher gestures;
- navigation, buttons, custom actions, power menu, freeform, and Tasker;
- permissions, installer, sharing, hidden applications, app lock, and other MIUI behaviors.

## r13.7.0 highlights

- completed the System, SystemUI, and Launcher engineering split and migration audit;
- isolated deferred callback failures and completed Receiver / Observer / Listener ownership and cleanup;
- fixed preference initialization, weak-reference cleanup, and Hook-instance lifetime handling;
- bounded queues and caches for device monitoring, application icons, settings search, AudioVisualizer, and lock-screen album art;
- retained libxposed API 101 as the minimum runtime baseline with API 102 metadata;
- found no module-causal crash, ANR, Fatal, Hook/reflection failure, or repeated exception in the LSPosed 2.1.1 (7790) evidence.

See [CHANGELOG_EN.md](CHANGELOG_EN.md) for details.

## Installation

1. Download the APK from this repository's Release and verify its SHA-256.
2. Install it and enable the recommended scope in LSPosed / Vector.
3. Open module settings once and fully reboot the device.
4. Enable and verify features by group.

Early builds signed with a different certificate cannot be upgraded in place. Back up settings and uninstall the old build if Android reports a signature mismatch.

The certificate DN retains the historical label `CN=CustoMIUIzer A14`, while the signer is unchanged from the tested A13 builds. It is retained for upgrade compatibility and does not imply Android 14 support.

## Feedback

Report issues in the [source repository](https://github.com/tomthenpc/customiuizer-a13/issues) with the module version, device and ROM, system-app versions, LSPosed/Vector version, actual scope, reproduction steps, and complete logs.

A package-name mention alone is not module causality. Include a Hook failure, module stack, crash, or ANR context.
