package com.omega_r.libs.omegaintentbuilder

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler

interface IntentBuilder {

  fun createIntent(): Intent
  fun handler(context: Context): ContextIntentHandler
  fun handler(activity: Activity): ActivityIntentHandler

}