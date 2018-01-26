package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Service
import android.content.Context

class ServiceIntentBuilder<T: Service> (private val context: Context,
                                        service: Class<T>): BaseIntentBuilder<T>(context, service) {

  fun startService() {
    context.startService(createIntent())
  }

}