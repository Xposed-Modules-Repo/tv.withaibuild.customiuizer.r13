# Changelog

[简体中文](CHANGELOG.md) | English

## r13.12.0 — 2026-08-19

- Fixed custom/gesture actions snapping back to "No action";
- Added Restart Launcher on the Launcher gesture page;
- Added/improved USB default-mode mapping, installer purify, hide app-details report entry, dock height, hide IME dismiss button, folder blur toggle, settings backup V2, battery indicator custom colors, and screen-dim ratio correction;
- Static gates, Release compile, Lint, R8, and signed APK inspection passed;
- DEVICE_VERIFIED = NO; LOG_VERIFIED = NO.

APK: `CustoMIUIzer-A13-r13.12.0.apk` / SHA-256 `643e93834c7028a4355f9915efbfe3aa49393ff18577331a76a485c6d9382e29`

## r13.11.1 — 2026-08-08

- Hardened SubFragment delayed-scroll lifecycle, canceling pending callbacks when the View is destroyed to avoid stale View operations;
- Hardened AppSelector async app-list loading with application context, input snapshots, and owner cleanup to reduce Activity / View lifecycle coupling;
- Hardened ActivitySelector async loading so query results are only committed within the current valid View lifecycle, while preserving the existing behavior of re-querying on each UI creation;
- Optimized the hot path for the status-bar clock default format, caching format conversion results and stable resource IDs to reduce repeated work on every time update;
- Preserved the original system time format, seconds, 12/24-hour mode, AM/PM, leading zeros, and custom format behavior;
- Completed Android 13 Release compilation, Lint, R8, signing, and core device-load verification;
- Some HyperOS 1 / Android 13 SystemUI customizations still depend on specific ROM internal classes and system-app versions.

## r13.10.1 — 2026-08-06

- Split SystemUI, Launcher, `system_server`, and regular-app installation into process-specific entry points, using stable feature identities and install state to reduce unrelated loading and duplicate installation;
- Hardened early preference snapshots, concurrent loading, empty-snapshot handling, and failed state so preference updates do not duplicate Hooks or incorrectly roll back state;
- Improved MIUI 14 and HyperOS 1 / Android 13 ROM Contracts, target resolution, and variant selection, skipping only the affected feature when a target is missing;
- Ordinary ROM, reflection, and callback failures remain isolated, while `OutOfMemoryError`, `ThreadDeath`, and `VirtualMachineError` continue to propagate;
- Completed replacement, stale-state, and release paths for Receivers, Observers, Views, Handlers, and controllers, and removed blocking waits from selector UI paths;
- Hardened callback paths for call-interruption control, secure-window removal, temporary overlay hiding during screenshots, notification/share floating-window actions, and multi-window restrictions;
- Optimized Launcher animation scaling, HotSeats gesture state, and FSG Class resolution to reduce repeated reflection, configuration reads, and touch-event overhead;
- Reworked DeviceInfo around fixed buffers and byte-wise sysfs parsing, reducing periodic I/O, Binder queries, and temporary objects;
- Added Release compilation, tests, Lint, R8, dependency-integrity, Manifest, and Xposed metadata gates, while removing unused dependencies and invalid metadata.

Known deployed baseline: Redmi Note 11T Pro (`xaga`), MIUI `V14.0.10.0.TLOINXM`, . HyperOS 1 feature availability depends on the ROM and system-app versions.

## r13.9.2 — 2026-08-01

- Released lock-screen album-art backgrounds, one-frame caches, and static processed results after the owner View detached;
- Set settings-page transitions to `350ms`;
- Gave switches immediate pressed-state feedback without per-tap alpha animators;
- Added a dedicated concise module summary.

## Historical Core Implementation Summary

The A13 line established an independent package and Android 13 maintenance path; delivered libxposed API 101/102 compatibility; separated System, SystemUI, and Launcher domains; performed staged Kotlin migrations; hardened resource and preference Hooks; governed lifecycles; bounded caches; defined fatal-error boundaries; and introduced Contract/Resolver compatibility diagnostics. Detailed history remains in the source repository's Git commits and historical tags.
