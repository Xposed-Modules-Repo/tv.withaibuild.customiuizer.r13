# Changelog

[简体中文](CHANGELOG.md) | English

## r13.12.2 — 2026-08-19

`versionCode 140`, the final stable release in the r13.12 series. Supersedes `r13.12.0` / `r13.12.1`.

- Fixed custom/gesture actions snapping back to "No action";
- Added Restart Launcher on the Launcher gesture page;
- USB default mode reapplies after unplug/replug;
- Fixed dropped return results in the two-hop path `MultiAction -> AppSelector -> ActivitySelector -> AppSelector -> MultiAction`;
- Added/improved USB default-mode mapping, installer purify, hide app-details report entry, dock height, hide IME dismiss button, folder blur toggle, settings backup V2, battery indicator custom colors, and screen-dim ratio;
- Static gates, Release compile, Lint, R8, and signed APK inspection passed;
- DEVICE_VERIFIED = NO; LOG_VERIFIED = NO.

APK: `CustoMIUIzer-A13-r13.12.2.apk` / SHA-256 `d847b1608f465bd9996b444e824109c9d81cd92ebf946157489ba0795e529b4a`

## r13.11.1 — 2026-08-08

- Hardened SubFragment delayed-scroll lifecycle, canceling pending callbacks when the View is destroyed to avoid stale View operations;
- Hardened AppSelector async app-list loading with application context, input snapshots, and owner cleanup to reduce Activity / View lifecycle coupling;
- Hardened ActivitySelector async loading so query results are only committed within the current valid View lifecycle, while preserving the existing re-query behavior on View recreation;
- Optimized the status-bar clock default-format hot path by caching format-conversion and resource-resolution results, reducing repeated work on every time update;
- Preserved existing system time format, seconds, 12/24-hour mode, AM/PM, leading zero, and custom-format behavior;
- Completed Android 13 Release compilation, Lint, R8, signing, and core on-device loading verification;
- Some HyperOS 1 / Android 13 SystemUI customizations still depend on specific ROM internal classes and system-app versions.

## r13.10.1 — 2026-08-06

- Split SystemUI, Launcher, `system_server`, and regular-app entry points into process-routed Installers. Stable feature identities, process scopes, install phases, and install-once state prevent unrelated loading and duplicate installation;
- Hardened early preference snapshots, empty-snapshot handling, concurrent loading, and failed-install state so preference updates cannot incorrectly reset installed Hooks or trigger duplicate installation;
- Improved MIUI 14 and HyperOS 1 / Android 13 environment detection, Hook Contracts, target resolution, and variant selection. Missing targets skip only the affected feature without mixing candidates;
- Unified Hook, reflection, Receiver, Observer, delayed-callback, and diagnostics boundaries. Ordinary compatibility failures remain isolated, while direct or wrapped `OutOfMemoryError`, `ThreadDeath`, and `VirtualMachineError` continue to propagate;
- Completed owner, replacement, stale-state, and release paths for Receivers, Observers, Views, Handlers, and controllers, and removed blocking waits from selector UI paths;
- Hardened callback paths for call-interruption control, secure-window removal, temporary overlay hiding during screenshots, notification/share floating-window actions, and multi-window restrictions; aligned installation and compatibility boundaries across status bar, control center, volume, lock screen, and settings Hooks;
- Replaced Launcher animation-scale reflection with a direct API, cached HotSeats density, touch thresholds, and gesture state per View, and reused the install-time `BaseRecentsImpl` Class in FSG callbacks;
- Reworked DeviceInfo sampling around fixed buffers and byte-wise sysfs parsing, reducing periodic `Properties`, `RandomAccessFile`, Binder queries, and temporary objects while preserving sampling and failure backoff;
- Added runtime invariant, Hook-contract, source-hazard, ROM-compatibility, Release compilation, unit-test, Lint, R8, and dependency-integrity gates.

Known deployed baseline: Redmi Note 11T Pro (`xaga`), MIUI `V14.0.10.0.TLOINXM`. HyperOS 1 feature availability depends on the ROM and system-app versions.

## Historical Core Implementation Summary

The A13 line established an independent package and Android 13 maintenance path; delivered libxposed API 101/102 compatibility; separated System, SystemUI, and Launcher domains; performed staged Kotlin migrations; hardened resource and preference Hooks; governed lifecycles; bounded caches; added cancellable asynchronous work; defined fatal-error boundaries; and introduced Contract/Resolver compatibility diagnostics. Fine-grained history remains in the source repository's Git commits and historical tags.
