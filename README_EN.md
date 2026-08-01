# CustoMIUIzer A13

[简体中文](README.md) | English

An Android 13 system UI and interaction customization module for MIUI and HyperOS.

## Current release

- Version: `r13.9.1` (versionCode `132`)
- APK: `CustoMIUIzer-A13-r13.9.1.apk`
- Size: `2,860,194 bytes`
- SHA-256: `98F03BFB1FA29E776C3A638E771CCE6D1672F5C94F91B39B7D7D4362DB6EF96C`
- Signing certificate SHA-256: `15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

## Compatibility and requirements

- MIUI 14 / Android 13;
- HyperOS 1 / Android 13;
- `arm64-v8a`;
- Root access and LSPosed / Vector supporting libxposed API 101 or 102;
- Android 14 and later are not supported.

The known device baseline is Redmi Note 11T Pro (`xaga`), MIUI `V14.0.10.0.TLOINXM`, and LSPosed 2.1.1. HyperOS 1 / Android 13 is a formal compatibility target, but SystemUI, Launcher, and system_server internals vary between ROMs, so individual features still require ROM-specific log confirmation.

## Main features

- Status-bar clock, date, temperature, network speed, battery, signal, and icon layout;
- Control center, notifications, volume, brightness, lock screen, media, and charging information;
- Launcher icons, folders, Dock, recents, gestures, and animations;
- Navigation keys, button actions, power menu, floating windows, installer, sharing, and app permissions.

## Installation

1. Download the APK from the latest Release and verify its SHA-256;
2. Back up your current module settings;
3. If r13.8.6 is installed, uninstall it first. That release used a different certificate and cannot be upgraded in place;
4. Install r13.9.1, enable it in LSPosed / Vector, and confirm the recommended scope;
5. Open module settings once, then fully reboot the device.

## Risk notice

This Xposed module modifies system processes and system UI. ROM updates, system-app updates, or a mismatched scope can disable individual features or restart SystemUI/Launcher. Enable features in small batches after first installation. If a problem occurs, disable the related feature and provide the device, ROM, framework version, scope, reproduction steps, and complete LSPosed logs.

Static tests and APK checks cannot replace on-device regression across every ROM. New changes in this release and HyperOS 1 / Android 13 still require broader device-log validation.

Source: <https://github.com/tomthenpc/customiuizer-a13>
