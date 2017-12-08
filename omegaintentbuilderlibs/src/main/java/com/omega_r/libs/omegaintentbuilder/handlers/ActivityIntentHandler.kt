package com.omega_r.libs.omegaintentbuilder.handlers

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class ActivityIntentHandler(private val activity: Activity,private val intent: Intent): ContextIntentHandler(activity, intent) {

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