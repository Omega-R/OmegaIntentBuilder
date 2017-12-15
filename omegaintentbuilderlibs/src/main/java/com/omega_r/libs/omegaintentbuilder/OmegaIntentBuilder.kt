/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * OmegaIntentBuilder.java
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder

import android.app.Activity
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.builders.CallIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.ActivityIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.ShareIntentBuilder

/**
 * OmegaIntentBuilder class for creating supports createdIntent builders.
 */
class OmegaIntentBuilder(private val context: Context) {

  /**
   * Return CallIntentBuilder for creating call createdIntent
   */
  fun call(): CallIntentBuilder {
    return CallIntentBuilder(context,this)
  }

  /**
   * Return ShareIntentBuilder for creating share createdIntent
   */
  fun share(): ShareIntentBuilder {
    return ShareIntentBuilder(context, this)
  }

  /**
   * Return ShareIntentBuilder for creating email createdIntent
   */
  fun email(): EmailIntentBuilder {
    return EmailIntentBuilder(context, this)
  }

  fun <T: Activity> activity(activity: Class<T>): ActivityIntentBuilder<T> {
    return ActivityIntentBuilder(context, activity)
  }

}