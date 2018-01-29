/*
 * Copyright (c) 2017 Omega-r
 *
 * ActivityIntentBuilder
 * ActivityIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 29, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler

class ActivityIntentBuilder<T: Activity> (private val context: Context,
                                          activity: Class<T>): BaseActivityServiceIntentBuilder<T>(context, activity), IntentHandler {

  override fun createIntentHandler(): ContextIntentHandler {
    return ContextIntentHandler(context, createIntent())
  }

  override fun createIntentHandler(activity: Activity): ActivityIntentHandler {
    return ActivityIntentHandler(activity, createIntent())
  }

  override fun startActivity() {
    createIntentHandler().startActivity()
  }

}