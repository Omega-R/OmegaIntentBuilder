package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import com.omega_r.libs.omegaintentbuilder.types.ShowType

class ShowAlarmsTimersIntentBuilder(private val showType: ShowType) : BaseActivityBuilder() {

    override fun createIntent(context: Context): Intent {
        return Intent(showType.actionType)
    }

}