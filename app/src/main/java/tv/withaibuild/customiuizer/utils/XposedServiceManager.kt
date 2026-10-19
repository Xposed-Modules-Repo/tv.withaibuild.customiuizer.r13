package tv.withaibuild.customiuizer.utils

import android.content.SharedPreferences
import io.github.libxposed.service.XposedService
import io.github.libxposed.service.XposedServiceHelper
import tv.withaibuild.customiuizer.mods.utils.RuntimeFatality
import tv.withaibuild.customiuizer.prefs.PreferenceLoadRegistry
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/** Process-owned service and local listener. No Activity, View or Hook API reference. */
internal class XposedServiceManager(
    private val prefs: SharedPreferences,
    private val executor: Executor,
    private val registerService: (XposedServiceHelper.OnServiceListener) -> Unit = XposedServiceHelper::registerListener
) : XposedServiceHelper.OnServiceListener {
    private val lock = Any()
    private var started = false
    private var pending = false
    private var scheduled = false
    @Volatile private var service: XposedService? = null
    @Volatile private var remote: SharedPreferences? = null
    // Accessed only by the serial worker; reset for each service identity.
    private var scopeService: XposedService? = null
    private var scopePackages: Set<String>? = null
    private val changed = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == null || key.startsWith("pref_key_") && key !in AppHelper.mirrorIgnoreKeys) requestMirror()
    }

    fun start() {
        synchronized(lock) {
            if (started) return
            started = true
        }
        try {
            prefs.registerOnSharedPreferenceChangeListener(changed)
        } catch (t: Throwable) {
            RuntimeFatality.throwIfFatal(t)
            try { prefs.unregisterOnSharedPreferenceChangeListener(changed) } catch (cleanup: Throwable) {
                RuntimeFatality.throwIfFatal(cleanup)
            }
            AppHelper.mirrorDirty = true
            AppHelper.log("Service preference listener", t)
        }
        try {
            registerService(this)
        } catch (t: Throwable) {
            RuntimeFatality.throwIfFatal(t)
            synchronized(lock) {
                if (service == null) {
                    AppHelper.moduleConnectionObserved = true
                    AppHelper.moduleActive = false
                }
            }
            AppHelper.mirrorDirty = true
            SettingsDiagnostics.failure("XposedServiceManager.registerService", t)
        }
    }

    override fun onServiceBind(service: XposedService) {
        synchronized(lock) {
            this.service = service
            remote = null
            AppHelper.moduleActive = false
            AppHelper.moduleConnectionObserved = false
        }
        requestMirror()
    }

    override fun onServiceDied(service: XposedService) {
        synchronized(lock) {
            if (this.service !== service) return
            this.service = null
            remote = null
            AppHelper.moduleActive = false
            AppHelper.moduleConnectionObserved = true
            AppHelper.mirrorDirty = true
        }
        requestMirror()
    }

    private fun requestMirror() {
        synchronized(lock) {
            pending = true
            if (scheduled) return
            scheduled = true
        }
        executor.execute(::drain)
    }

    private fun drain() {
        while (true) {
            synchronized(lock) {
                if (!pending) {
                    scheduled = false
                    return
                }
                pending = false
            }
            try {
                mirrorCurrentConnection()
            } catch (t: Throwable) {
                AppHelper.mirrorDirty = true
                RuntimeFatality.throwIfFatal(t)
                AppHelper.log("Service mirror", t)
            }
        }
    }

    private fun mirrorCurrentConnection() {
        val (bound, cached) = synchronized(lock) { service to remote }
        if (bound == null) {
            scopeService = null
            scopePackages = null
            AppHelper.mirrorDirty = true
            return
        }
        if (cached == null && bound.apiVersion < 102) {
            synchronized(lock) {
                if (service !== bound) return
                service = null
                remote = null
                AppHelper.moduleActive = false
                AppHelper.moduleConnectionObserved = true
                AppHelper.mirrorDirty = true
            }
            scopeService = null
            scopePackages = null
            AppHelper.log("Service requires libxposed API 102")
            return
        }
        val target = cached ?: bound.getRemotePreferences(AppHelper.prefsName + "_remote")
        synchronized(lock) {
            if (service !== bound) return
            remote = target
            AppHelper.moduleActive = true
            AppHelper.moduleConnectionObserved = true
        }
        AppHelper.reconcileRemotePreferences(target, prefs)
        if (service !== bound) {
            AppHelper.mirrorDirty = true
            return
        }
        if (!AppHelper.mirrorDirty) {
            try { requestSelectedScope(bound) } catch (t: Throwable) {
                RuntimeFatality.throwIfFatal(t)
                AppHelper.log("Service scope", t)
            }
        }
    }

    private fun requestSelectedScope(bound: XposedService) {
        if (scopeService !== bound) {
            scopeService = bound
            scopePackages = null
        }
        val packages = PreferenceLoadRegistry.selectedScopePackages(prefs)
        if (packages == scopePackages) return
        scopePackages = packages
        if (packages.isEmpty()) return
        // One Binder snapshot per changed selection. Rejection is not retried.
        val granted = bound.scope.toHashSet()
        val missing = packages.filterNot { it in granted }
        if (missing.isEmpty() || service !== bound) return
        bound.requestScope(missing, object : XposedService.OnScopeEventListener {
            override fun onScopeRequestApproved(approved: List<String>) {
                if (!approved.containsAll(missing)) AppHelper.log("Scope request partially approved")
            }
            override fun onScopeRequestFailed(message: String) {
                AppHelper.log("Scope request failed: $message")
            }
        })
    }

    companion object {
        private var instance: XposedServiceManager? = null
        @JvmStatic @Synchronized
        fun init(prefs: SharedPreferences) {
            if (instance != null) return
            // Coalescing keeps at most one queued job; the worker expires when idle.
            val worker = ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, ArrayBlockingQueue(1)) { task ->
                Thread(task, "A13-preferences").apply { isDaemon = true }
            }
            instance = XposedServiceManager(prefs, worker).also { it.start() }
        }
    }
}
