# CustoMIUIzer A13

[简体中文](README.md) | English

An Android 13 system UI and interaction customization module for MIUI and HyperOS.

## Current release

- `r13.9.2` (versionCode `133`)
- APK: `CustoMIUIzer-A13-r13.9.2.apk` (`2,859,894 bytes`)
- SHA-256: `0542E87E5FED06A1BBEC5509C7F5412555D8677850BE939032CD15A2F439BD80`
- Signing certificate SHA-256: `15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

## Compatibility and requirements

- MIUI 14 / Android 13;
- HyperOS 1 / Android 13;
- `arm64-v8a`;
- Root access and LSPosed / Vector supporting libxposed API 101 or 102;
- Android 14 and later are not supported.

Known baseline: Redmi Note 11T Pro (`xaga`), MIUI `V14.0.10.0.TLOINXM`, and LSPosed 2.1.1. HyperOS 1 internals can differ from MIUI 14, so individual features still require ROM-specific log confirmation.

## Main features

- Status-bar clock, date, temperature, network speed, battery, signal, and icon layout;
- Control center, notifications, volume, brightness, lock screen, media, and charging information;
- Launcher icons, folders, Dock, recents, gestures, and animations;
- Navigation keys, button actions, power menu, floating windows, installer, sharing, and app permissions.

## Installation

1. Download the APK from the latest Release and verify its SHA-256;
2. Back up current module settings;
3. If r13.8.6 is installed, uninstall it first because that release used a different certificate;
4. Install r13.9.2, enable it in LSPosed / Vector, and confirm the recommended scope;
5. Open module settings once, then fully reboot the device.

## Risk notice

This module modifies system processes and system UI. ROM/system-app updates or an incorrect scope can disable features or restart SystemUI/Launcher. Enable features in small batches. If a problem occurs, disable the related feature and provide the device, ROM, framework version, scope, reproduction steps, and complete LSPosed logs. Static tests cannot replace on-device regression across every ROM.

Source: <https://github.com/tomthenpc/customiuizer-a13>
