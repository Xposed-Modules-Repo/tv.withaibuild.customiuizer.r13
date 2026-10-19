# 米客 A13｜CustoMIUIzer A13

[简体中文](README.md) | English

System UI and interaction customization for **MIUI 14 / Android 13**, with ongoing maintenance of the Android 13 line. **HyperOS 1 / Android 13 compatibility is experimental**.

[Download the latest release](https://github.com/Xposed-Modules-Repo/tv.withaibuild.customiuizer.r13/releases/latest) · [Releases](https://github.com/Xposed-Modules-Repo/tv.withaibuild.customiuizer.r13/releases) · [Source and issues](https://github.com/thetvplus/customiuizer-a13)

> **The signing certificate changed; older builds cannot be updated in place.** The previous A13 key is currently unavailable. Successfully export settings to Downloads or Documents before uninstalling, then install this build, restore settings, re-enable the module and reboot. Keep the old app if backup fails.

## Current version

| Item | Value |
| --- | --- |
| Version | `r13.13.0` (versionCode `141`) |
| Application ID | `tv.withaibuild.customiuizer.r13` |
| ABI | `arm64-v8a` |
| Framework | LSPosed / Vector providing libxposed API 102 |
| Source tag | [r13.13.0](https://github.com/thetvplus/customiuizer-a13/tree/r13.13.0) |
| APK | `CustoMIUIzer-A13-r13.13.0.apk`, identical in both repositories |

This release fixes settings backup, call filtering and notification settings, and unifies hook installation, service binding and preference-listener lifecycles. See [CHANGELOG_EN.md](CHANGELOG_EN.md).

## Compatibility

- MIUI 14 / Android 13 is the primary target; HyperOS 1 / Android 13 feature availability depends on the ROM and system-app versions.
- Requires root, an `arm64-v8a` device and libxposed API 102. API 101-only frameworks are no longer supported.
- Module metadata: `minApiVersion=102`, `targetApiVersion=102`, `staticScope=true`.
- Android 14+ is unsupported. Do not enable alongside upstream or other CustoMIUIzer variants.

## Main features

- Status-bar clock, date, temperature, network speed, battery, signal and icon layout;
- Control center, notifications, volume, brightness, lock screen, media and charging UI;
- Launcher icons, folders, Dock, recents, gestures and animations;
- Navigation keys, button actions, power menu, floating windows, multi-window, installer, sharing and app permissions.

## Installation

1. Confirm API 102 framework support and download the APK from this repository's Release. Existing users must back up and uninstall as described above.
2. Install, enable the module and confirm the recommended scope.
3. Open module settings, restore settings if upgrading, then fully reboot the device.

Static checks, unit tests, lint, R8 and CI passed. This version has not completed A13 device acceptance. If a feature fails, disable it and report your device, ROM, framework version, scope, reproduction steps and relevant logs.
