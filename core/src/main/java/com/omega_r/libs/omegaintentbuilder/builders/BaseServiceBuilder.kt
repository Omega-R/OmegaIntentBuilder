package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import com.omega_r.libs.omegaintentbuilder.interfaces.ServiceHandler

abstract class BaseServiceBuilder : ServiceHandler {

    override fun startService(context: Context) {
        context.startService(createIntent(context))
    }

}