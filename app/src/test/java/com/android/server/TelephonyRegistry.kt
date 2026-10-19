package com.android.server

class TelephonyRegistry {
    fun notifyCallState(phoneId: Int, subId: Int, state: Int, incomingNumber: String) {}

    fun notifyCallStateForAllSubs(state: Int, incomingNumber: String) {}

    class Record {
        @JvmField var callingPackage: String? = null
        @JvmField var callback: Any? = Any()
        @JvmField var eventList: Set<Int> = emptySet()

        fun matchTelephonyCallbackEvent(event: Int): Boolean = callback != null && eventList.contains(event)
    }
}
