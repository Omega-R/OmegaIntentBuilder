package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Service
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.interfaces.ServiceHandler

class ServiceIntentBuilder<T : Service>(
        service: Class<T>
) : BaseIntentBuilder<ServiceIntentBuilder<T>, T>(service), ServiceHandler {

    override fun startService(context: Context) {
        context.startService(createIntent(context))
    }

}