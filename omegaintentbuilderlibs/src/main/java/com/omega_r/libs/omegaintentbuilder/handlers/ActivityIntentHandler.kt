/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * ActivityIntentHandler.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder.handlers

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * ActivityIntentHandler is a helper for start intents
 * Support startActivityForResult
 */
class ActivityIntentHandler(private val activity: Activity,private val intent: Intent): ContextIntentHandler(activity.applicationContext, intent) {

  @JvmOverloads
  fun startActivityForResult(requestCode: Int, options: Bundle? = null) {
    if (getChooserTitle().isNullOrEmpty()) {
      activity.startActivityForResult(intent, requestCode)
    } else {
      if (options != null) {
        activity.startActivityForResult(createChooserIntent(), requestCode, options)
      } else {
        activity.startActivityForResult(createChooserIntent(), requestCode)
      }
    }
  }

}