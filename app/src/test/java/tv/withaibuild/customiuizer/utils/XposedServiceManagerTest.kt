package tv.withaibuild.customiuizer.utils

import android.os.Bundle
import android.os.RemoteException
import io.github.libxposed.service.XposedService
import io.github.libxposed.service.XposedServiceHelper
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Proxy
import java.util.concurrent.Executor

/** Exercises the real API 102 service/cache and a deterministic process-owned worker. */
class XposedServiceManagerTest {
    private val local = FakeSharedPreferences()
    private val tasks = ArrayDeque<Runnable>()
    private val executor = Executor { tasks.add(it) }
    private var registrations = 0
    private lateinit var listener: XposedServiceHelper.OnServiceListener
    private lateinit var manager: XposedServiceManager

    @Before fun prepare() {
        AppHelper.appPrefs = local
        AppHelper.moduleActive = false
        AppHelper.moduleConnectionObserved = false
        AppHelper.resetMirrorState()
        manager = XposedServiceManager(local, executor) { listener = it; registrations++ }
        manager.start()
    }

    @After fun restore() {
        AppHelper.appPrefs = null
        AppHelper.moduleActive = false
        AppHelper.moduleConnectionObserved = false
        AppHelper.resetMirrorState()
    }

    private fun drain() {
        var count = 0
        while (tasks.isNotEmpty()) {
            assertTrue("worker must remain bounded", count++ < 10)
            tasks.removeFirst().run()
        }
    }

    @Test fun processRegistersEachListenerOnceAndCoalescesChangesOffCaller() {
        manager.start()
        assertEquals(1, registrations)
        assertEquals(1, local.registerCount)
        assertFalse("a pending connection is not proof of inactivity", AppHelper.shouldReportModuleInactive())
        val framework = Framework()
        listener.onServiceBind(framework.service)
        repeat(100) { local.put("pref_key_value", it) }
        assertEquals(1, tasks.size)
        assertEquals(0, framework.commits)
        drain()
        assertEquals(99, framework.persisted["pref_key_value"])
        assertEquals(1, framework.commits)
        assertTrue(AppHelper.moduleActive)
    }

    @Test fun staleServiceDeathCannotClearReplacementConnection() {
        val old = Framework()
        val current = Framework()
        listener.onServiceBind(old.service)
        listener.onServiceBind(current.service)
        listener.onServiceDied(old.service)
        local.put("pref_key_value", "latest")
        drain()
        assertTrue(AppHelper.moduleActive)
        assertEquals(0, old.commits)
        assertEquals("latest", current.persisted["pref_key_value"])
        listener.onServiceDied(current.service)
        drain()
        assertTrue(AppHelper.shouldReportModuleInactive())
        assertTrue(AppHelper.mirrorDirty)
    }

    @Test fun failedSdkCacheWriteIsResentEvenWhenCacheEqualsLocal() {
        val framework = Framework().apply { rejectCommit = true }
        listener.onServiceBind(framework.service)
        local.put("pref_key_value", "saved")
        drain()
        assertTrue(AppHelper.mirrorDirty)
        assertFalse(framework.persisted.containsKey("pref_key_value"))
        assertTrue("failed writes are not retried in a loop", tasks.isEmpty())
        framework.rejectCommit = false
        listener.onServiceBind(framework.service)
        drain()
        assertEquals(2, framework.commits)
        assertEquals("saved", framework.persisted["pref_key_value"])
        assertFalse(AppHelper.mirrorDirty)
    }

    @Test fun failedSdkDeletionIsRetriedWithoutClearingRemoteOnlyKeys() {
        val framework = Framework().apply {
            persisted["pref_key_old"] = "remove"
            persisted["remote_only"] = "keep"
            rejectCommit = true
        }
        listener.onServiceBind(framework.service)
        drain()
        assertTrue(AppHelper.mirrorDirty)
        assertEquals("remove", framework.persisted["pref_key_old"])
        framework.rejectCommit = false
        listener.onServiceBind(framework.service)
        drain()
        assertFalse(framework.persisted.containsKey("pref_key_old"))
        assertEquals("keep", framework.persisted["remote_only"])
        assertFalse(AppHelper.mirrorDirty)
    }

    @Test fun scopeUsesOneSnapshotAndRequestsOnlyMissingActiveSelections() {
        val framework = Framework().apply { granted = listOf("com.granted") }
        local.put("pref_key_system_nooverscroll", true)
        local.put("pref_key_system_nooverscroll_apps", setOf("com.granted", "com.new"))
        listener.onServiceBind(framework.service)
        drain()
        assertEquals(1, framework.scopeReads)
        assertEquals(listOf("com.new"), framework.requests.single())
        local.put("pref_key_other", true)
        drain()
        assertEquals("unchanged selection does not re-prompt", 1, framework.requests.size)
        assertEquals(1, framework.scopeReads)
    }

    @Test fun disabledSelectionsDoNotRequestScope() {
        val framework = Framework()
        local.put("pref_key_system_nooverscroll_apps", setOf("com.unused"))
        listener.onServiceBind(framework.service)
        drain()
        assertEquals(0, framework.scopeReads)
        assertTrue(framework.requests.isEmpty())
    }

    @Test fun serviceBelowApi102IsRejectedWithoutMirroringOrScopeRequests() {
        val framework = Framework().apply { apiVersion = 101 }
        local.put("pref_key_value", true)
        listener.onServiceBind(framework.service)
        assertFalse("service validation is still pending", AppHelper.shouldReportModuleInactive())
        drain()
        assertTrue(AppHelper.shouldReportModuleInactive())
        assertTrue(AppHelper.mirrorDirty)
        assertEquals(0, framework.commits)
        assertEquals(0, framework.scopeReads)
    }

    @Test fun editsDuringCommitAreDrainedWithoutLosingTheLatestValue() {
        val framework = Framework()
        framework.duringCommit = { local.put("pref_key_value", "newer") }
        local.put("pref_key_value", "first")
        listener.onServiceBind(framework.service)
        drain()
        assertEquals("newer", framework.persisted["pref_key_value"])
        assertEquals(2, framework.commits)
        assertTrue(tasks.isEmpty())
    }

    @Test fun scopeFailureDoesNotTurnAConfirmedPreferenceCommitIntoAnUnconfirmedOne() {
        val framework = Framework().apply { scopeFailure = IllegalStateException("scope unavailable") }
        local.put("pref_key_system_nooverscroll", true)
        local.put("pref_key_system_nooverscroll_apps", setOf("com.selected"))
        listener.onServiceBind(framework.service)
        drain()
        assertFalse(AppHelper.mirrorDirty)
        local.put("pref_key_other", true)
        drain()
        assertEquals("uncertain scope requests are not retried", 1, framework.scopeReads)
        assertTrue(framework.requests.isEmpty())
    }

    @Test fun partiallyRegisteredLocalListenerIsRolledBack() {
        val source = FakeSharedPreferences().apply {
            registerFailureAfterSideEffect = IllegalStateException("registered then failed")
        }
        val connection = XposedServiceManager(source, executor) {}
        connection.start()
        assertEquals(1, source.unregisterCount)
        assertTrue(source.activeListenerIdentities.isEmpty())
        assertTrue(AppHelper.mirrorDirty)
    }

    @Test fun wrappedFatalRemoteWriteEscapesTheWorker() {
        val fatal = InternalError("remote write")
        val framework = Framework().apply { commitFailure = RuntimeException(fatal) }
        local.put("pref_key_value", true)
        listener.onServiceBind(framework.service)
        try {
            drain()
            fail("fatal must escape")
        } catch (actual: InternalError) {
            assertSame(fatal, actual)
        }
        assertEquals(1, framework.commits)
    }

    private class Framework {
        val persisted = HashMap<String, Any>()
        var rejectCommit = false
        var commits = 0
        var apiVersion = 102
        var granted = emptyList<String>()
        var scopeReads = 0
        val requests = ArrayList<List<String>>()
        var duringCommit: (() -> Unit)? = null
        var scopeFailure: Throwable? = null
        var commitFailure: Throwable? = null
        private val ipcClass = Class.forName("io.github.libxposed.service.IXposedService")
        private val ipc = Proxy.newProxyInstance(ipcClass.classLoader, arrayOf(ipcClass)) { _, method, args ->
            when (method.name) {
                "getApiVersion" -> apiVersion
                "requestRemotePreferences" -> Bundle().apply { putSerializable("map", HashMap(persisted)) }
                "updateRemotePreferences" -> {
                    commits++
                    commitFailure?.let { throw it }
                    if (rejectCommit) throw RemoteException("not delivered")
                    val bundle = args!![1] as Bundle
                    assertFalse("mirror never clears unrelated remote data", bundle.getBoolean("clear"))
                    @Suppress("UNCHECKED_CAST")
                    val deleted = bundle.getSerializable("delete") as Set<String>
                    deleted.forEach(persisted::remove)
                    @Suppress("UNCHECKED_CAST")
                    persisted.putAll(bundle.getSerializable("put") as Map<String, Any>)
                    val callback = duringCommit
                    duringCommit = null
                    callback?.invoke()
                    null
                }
                "getScope" -> { scopeReads++; scopeFailure?.let { throw it }; granted }
                "requestScope" -> {
                    @Suppress("UNCHECKED_CAST")
                    requests.add((args!![0] as List<String>).toList())
                    null
                }
                else -> null
            }
        }
        val service: XposedService = XposedService::class.java.getDeclaredConstructor(ipcClass).apply {
            isAccessible = true
        }.newInstance(ipc)
    }
}
