package com.omega_r.libs.omegaintentbuilder.interfaces

import android.content.Context

interface ServiceHandler : IntentBuilder {

    fun startService(context: Context)

}