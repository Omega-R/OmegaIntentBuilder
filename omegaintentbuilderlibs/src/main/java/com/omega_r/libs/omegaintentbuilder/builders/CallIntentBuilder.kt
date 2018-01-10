/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * CallIntentBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder

/**
 * CallIntentBuilder is a helper for constructing {@link Intent#ACTION_DIAL}
 */
class CallIntentBuilder internal constructor(context: Context,
                                             private var phoneNumber: String): BaseBuilder(context) {

  companion object {
    private const val PHONE_SCHEME = "tel:";
    private val regex = Regex("[^0-9]")
  }

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for calling
   */
  override fun createIntent(): Intent {
    phoneNumber = phoneNumber.replace(regex, "")
    if (phoneNumber.isEmpty()) throw IllegalStateException("Empty phone number")

    return Intent(Intent.ACTION_DIAL, Uri.parse(PHONE_SCHEME + phoneNumber))
  }

}