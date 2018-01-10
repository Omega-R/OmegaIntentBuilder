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
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.*
import com.omega_r.libs.omegaintentbuilder.builders.share.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.PlayStoreBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.ShareIntentBuilder
import com.omega_r.libs.omegaintentbuilder.types.MapTypes

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
  fun call(phoneNumber: String): CallIntentBuilder {
    return CallIntentBuilder(context, phoneNumber)
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
  fun web(urlAddress: String): BrowserBuilder {
    return BrowserBuilder(context, urlAddress)
  }

  /**
   * Return BrowserBuilder for creating intent to start web browser
   */
  fun web(uri: Uri): BrowserBuilder {
    return BrowserBuilder(context, uri)
  }

  /**
   * Return SettingsIntentBuilder for creating intent to start settings
   */
  fun settings(): SettingsIntentBuilder {
    return SettingsIntentBuilder(context)
  }

  /**
   * Return PlayStoreBuilder for creating intent to open PlayStore
   */
  fun playStore(): PlayStoreBuilder {
    return PlayStoreBuilder(context)
  }

  fun calendar(): CalendarIntentBuilder {
    return CalendarIntentBuilder(context)
  }

  /**
   * Return ActivityIntentBuilder for creating activity intent
   */
  fun <T: Activity> activity(activity: Class<T>): ActivityIntentBuilder<T> {
    return ActivityIntentBuilder(context, activity)
  }

  /**
   * Return MapIntentBuilder for creating intent to open Map application
   */
  fun map(type: MapTypes): MapIntentBuilder {
    return MapIntentBuilder(context, type)
  }

}