package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Service
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.interfaces.ServiceHandler

class ServiceIntentBuilder<T : Service>(
        private val context: Context,
        service: Class<T>
) : BaseIntentBuilder<ServiceIntentBuilder<T>, T>(context, service), ServiceHandler {

    override fun startService() {
        context.startService(createIntent())
    }

}