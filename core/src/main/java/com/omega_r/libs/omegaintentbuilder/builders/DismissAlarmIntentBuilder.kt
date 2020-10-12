package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.AlarmClock.*
import androidx.annotation.RequiresApi

class DismissAlarmIntentBuilder : BaseActivityBuilder() {
    private var label: String? = null

    fun label(label: String): DismissAlarmIntentBuilder {
        this.label = label
        return this
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun createIntent(context: Context): Intent {
        return Intent(ACTION_DISMISS_ALARM).apply {
            label?.let {
                putExtra(EXTRA_ALARM_SEARCH_MODE, ALARM_SEARCH_MODE_LABEL)
                putExtra(EXTRA_MESSAGE, it)
            }
        }
    }

}