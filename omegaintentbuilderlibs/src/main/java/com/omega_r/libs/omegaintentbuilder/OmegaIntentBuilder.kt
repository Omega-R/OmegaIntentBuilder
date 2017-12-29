/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * OmegaIntentBuilder.kt
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
import com.omega_r.libs.omegaintentbuilder.builders.BrowserBuilder
import com.omega_r.libs.omegaintentbuilder.builders.SettingsIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.PlayStoreBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.ShareIntentBuilder

/**
 * OmegaIntentBuilder class for creating supports createdIntent builders.
 */
open class OmegaIntentBuilder(private val context: Context) {

  companion object {
    @JvmStatic
    @Suppress("NON_FINAL_MEMBER_IN_OBJECT")
    open fun from(context: Context): OmegaIntentBuilder = OmegaIntentBuilder(context)
  }

  /**
   * Return CallIntentBuilder for creating call Intent
   */
  fun call(): CallIntentBuilder {
    return CallIntentBuilder(context,this)
  }

  /**
   * Return ShareIntentBuilder for creating share Intent
   */
  fun share(): ShareIntentBuilder {
    return ShareIntentBuilder(context, this)
  }

  /**
   * Return ShareIntentBuilder for creating email Intent
   */
  fun email(): EmailIntentBuilder {
    return EmailIntentBuilder(context, this)
  }

  /**
   * Return BrowserBuilder for creating intent to start web browser
   */
  fun web(): BrowserBuilder {
    return BrowserBuilder(context)
  }

  /**
   * Return SettingsIntentBuilder for creating intent to start settings
   */
  fun settings(): SettingsIntentBuilder {
    return SettingsIntentBuilder(context)
  }

  fun playStore(): PlayStoreBuilder {
    return PlayStoreBuilder(context)
  }

  /**
   * Return ActivityIntentBuilder for creating activity intent
   */
  fun <T: Activity> activity(activity: Class<T>): ActivityIntentBuilder<T> {
    return ActivityIntentBuilder(context, activity)
  }

}