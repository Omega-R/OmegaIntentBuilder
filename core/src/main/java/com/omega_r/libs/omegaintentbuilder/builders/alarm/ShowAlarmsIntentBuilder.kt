package com.omega_r.libs.omegaintentbuilder.builders.alarm

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import com.omega_r.libs.omegaintentbuilder.builders.BaseActivityBuilder

class ShowAlarmsIntentBuilder() : BaseActivityBuilder() {

    override fun createIntent(context: Context): Intent {
        return Intent(AlarmClock.ACTION_SHOW_ALARMS)
    }

}