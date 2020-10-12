package com.omega_r.libs.omegaintentbuilder.builders.alarm

import android.os.Build
import androidx.annotation.RequiresApi

object AlarmBuilder {
    /**
     * @return CreateAlarmIntentBuilder for creating intent to set alarm
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun create() = CreateAlarmIntentBuilder()

    /**
     * @return DismissAlarmIntentBuilder for creating intent to dismiss alarm
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun dismiss() = DismissAlarmIntentBuilder()

    /**
     * @return DismissAlarmIntentBuilder for creating intent to dismiss alarm
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun show() = ShowAlarmsIntentBuilder()
}