package com.omega_r.libs.omegaintentbuilder.types

import android.provider.AlarmClock.ACTION_SHOW_ALARMS
import android.provider.AlarmClock.ACTION_SHOW_TIMERS

enum class ShowType(val actionType: String) {
    ALARMS(ACTION_SHOW_ALARMS),
    TIMERS(ACTION_SHOW_TIMERS)
}