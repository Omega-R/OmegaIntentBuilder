package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.AlarmClock
import android.provider.AlarmClock.*
import androidx.annotation.RequiresApi

class DismissAlarmIntentBuilder : BaseActivityBuilder() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun createIntent(context: Context): Intent {
        return Intent(ACTION_DISMISS_ALARM)/*.apply {
            putExtra(EXTRA_ALARM_SEARCH_MODE, ALARM_SEARCH_MODE_TIME)
            putExtra(EXTRA_IS_PM, true);
            putExtra(EXTRA_HOUR, 2);
            putExtra(EXTRA_MINUTES, 19);
        }*/
    }

}