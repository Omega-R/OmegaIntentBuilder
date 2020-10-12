package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock.*

class DismissAlarmIntentBuilder : BaseActivityBuilder() {

    /**
     * <li>If exactly one active alarm exists, it is dismissed.
     * <li>If more than one active alarm exists, the user is prompted to choose the alarm to
     * dismiss.
     */
    override fun createIntent(context: Context): Intent {
        return Intent(ACTION_DISMISS_ALARM).apply {
            putExtra(EXTRA_ALARM_SEARCH_MODE, ALARM_SEARCH_MODE_ALL);
        }
    }

}