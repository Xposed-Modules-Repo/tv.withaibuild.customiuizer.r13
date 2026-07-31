# CustoMIUIzer A13 Kotlin Refactor｜MIUI 14 / Android 13｜API 101/102

[简体中文](README.md) | English

A system UI and interaction customization module for **MIUI 14 / Android 13**.

## Current release

| Item | Value |
|---|---|
| Version | `r13.8.6` |
| versionCode | `131` |
| System | MIUI 14 / Android 13 |
| ABI | `arm64-v8a` |
| Application ID | `tv.withaibuild.customiuizer.r13` |
| libxposed | API 101–102 |
| APK | `CustoMIUIzer-A13-r13.8.6.apk` |
| Size | `2836582 bytes` |
| APK SHA-256 | `ABF31CE311253AE863F7B2CEB87BF95140EE706EFF39ADA219033552B6FA7287` |
| Signing certificate SHA-256 | `C0EFF2DC4E662717195490DA78B12A984C6F2E6BD38ACF4EDAD14D53E3D22E70` |

## r13.8.6 highlights

* Merged the latest A13 maintenance, compatibility diagnostics, scope and UI improvements;
* Improved hook target resolution, install result recording and compatibility fallback;
* Strengthened lifecycles for receivers, observers, step counter, device monitor and lock-screen album art;
* Optimized hot paths in status bar, notifications, network speed, battery, clock and launcher;
* Preserved the system typeface for status-bar network speed and added dual-row line spacing adjustment;
* Fixed settings text style and About page wrapping.

## Scope

* MIUI 14 / Android 13 (API 33);
* `arm64-v8a`;
* LSPosed / Vector implementing libxposed API 101 or 102;
* Android 14 and later are not supported.

Different ROM implementations of SystemUI, Launcher and system apps may vary; some features may need ROM-specific adaptation.

## Installation

1. Download the APK from this repository's Release;
2. Verify the APK SHA-256;
3. Install and enable the module in LSPosed / Vector;
4. Confirm the recommended scope;
5. Open module settings once and fully reboot.

Early builds signed with a different certificate cannot be upgraded in place. If a signature mismatch appears, back up your settings and uninstall the old build first.

## Verification status

This release completed Release APK build, signing, zipalign, package info and Xposed metadata basic checks.

Full unit tests, Lint, engineering audit or complete functional regression on a real device were not run for this release.

## Source code and feedback

Source code, full changelog and engineering notes:

`https://github.com/tomthenpc/customiuizer-a13`

When submitting an issue, include module version, device, ROM, framework version, actual scope, reproduction steps and complete logs.
