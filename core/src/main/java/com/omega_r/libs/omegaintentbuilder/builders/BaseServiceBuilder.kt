package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.os.Parcelable
import com.omega_r.libs.omegaintentbuilder.interfaces.ServiceHandler

abstract class BaseServiceBuilder : ServiceHandler, Parcelable {

    override fun startService(context: Context) {
        context.startService(createIntent(context))
    }

}