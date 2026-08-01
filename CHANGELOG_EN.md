# Changelog

## r13.9.2 (2026-08-01)

- Releases the lock-screen album-art background, one-frame cache, and static processed result after the owner View detaches, reducing large-Bitmap residency in SystemUI;
- Restores 350ms settings transitions to correct the overly fast navigation pace;
- Gives switches immediate press feedback without creating an alpha animator for every tap;
- Uses a dedicated concise module summary instead of expanding the complete README in listings;
- Changes no MIUI 14 / Android 13 or HyperOS 1 / Android 13 ROM Hook target or fallback.

APK SHA-256: `0542E87E5FED06A1BBEC5509C7F5412555D8677850BE939032CD15A2F439BD80`

Signing certificate SHA-256: `15CE32F03E4D8E62DF9390F77431862E59BF2CF95CD5A72F0C7330CDFCCA2934`

## Historical implementation summary

The A13 line established an independent package and A13 signing identity, libxposed API 101/102, System/SystemUI/Launcher separation, Kotlin migrations, hardened resource and preference Hooks, lifecycle ownership, bounded caches, explicit OOM boundaries, and Contract/Resolver compatibility diagnostics. Source-repository Git tags and commits retain the detailed history.
