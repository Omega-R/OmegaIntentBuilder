package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.AlarmClock.ACTION_DISMISS_TIMER
import android.provider.AlarmClock.EXTRA_SKIP_UI
import androidx.annotation.RequiresApi

class DismissTimerIntentBuilder: BaseActivityBuilder() {

    override fun createIntent(context: Context): Intent {
        return Intent(ACTION_DISMISS_TIMER).apply {
            putExtra(EXTRA_SKIP_UI, false)
        }
    }

}