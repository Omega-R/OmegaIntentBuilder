/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BaseBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler


abstract class BaseBuilder: IntentBuilder {

  override fun handler(context: Context): ContextIntentHandler {
    return ContextIntentHandler(context, createIntent())
  }

  override fun handler(activity: Activity): ActivityIntentHandler {
    return ActivityIntentHandler(activity, createIntent())
  }

}