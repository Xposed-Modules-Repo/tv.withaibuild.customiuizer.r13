# CustoMIUIzer A13 | MIUI 14 / Android 13

[简体中文](README.md) | English

CustoMIUIzer A13 is a system UI and interaction customization module for MIUI 14 / Android 13, with capability-based compatibility paths for HyperOS 1 / Android 13.

## Current Version

| Item | Value |
| --- | --- |
| Version | `r13.10.1` |
| versionCode | `135` |
| Application ID | `tv.withaibuild.customiuizer.r13` |
| ABI | `arm64-v8a` |
| Deployed framework | LSPosed / Vector |
| Source | <https://github.com/tomthenpc/customiuizer-a13> |

## Compatibility and Requirements

- MIUI 14 / Android 13 is the primary compatibility target;
- HyperOS 1 / Android 13 selects compatibility paths through ROM Contracts and target capability detection; feature availability depends on the ROM and system-app versions;
- The device must be rooted and run a LSPosed / Vector framework;
- Module metadata: `minApiVersion=101`, `targetApiVersion=102`, `staticScope=false`;
- Android 14 and later are not supported;
- Do not enable this module together with upstream or another CustoMIUIzer-derived module.

Known deployed baseline: Redmi Note 11T Pro (`xaga`), MIUI `V14.0.10.0.TLOINXM`.

## Main Features

- Status-bar clock, date, temperature, network speed, battery, signal, and icon layout;
- Control center, notifications, volume, brightness, lock screen, media, and charging UI;
- Launcher icons, folders, Dock, recents, gestures, and animations;
- Navigation keys, button actions, power menu, floating windows, multi-window behavior, installer, sharing, and app permissions.

`r13.11.1` hardens the async lifecycle of settings and app selectors and optimizes the hot path for the status-bar clock default format; it also continues process-routed installation, ROM capability detection, and error-boundary governance. See [CHANGELOG_EN.md](CHANGELOG_EN.md) for details.

## Installation

1. Install a LSPosed / Vector framework;
2. Download and install the APK from the latest Release in this repository;
3. Enable the module in the LSPosed / Vector framework and confirm the recommended scope;
4. Open module settings once, then fully reboot the device.

## Issue Reporting

Feature compatibility depends on the ROM and system-app versions. If a problem occurs, disable the related feature first and provide the device, ROM, framework version, scope, reproduction steps, and relevant logs.

Source and issue reporting: <https://github.com/tomthenpc/customiuizer-a13>
