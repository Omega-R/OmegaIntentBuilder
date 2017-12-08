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

import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder

/**
 * CallIntentBuilder is a helper for constructing {@link Intent#ACTION_DIAL}
 */
class CallIntentBuilder internal constructor(private val intentBuilder: OmegaIntentBuilder): IntentBuilder {

  companion object {
    private const val PHONE_SCHEME = "tel:";
    private val regex = Regex("[^0-9]")
  }

  private var intent: Intent? = null
  private var phoneNumber: String? = null;

  /**
   * Set a phone number.
   * This replaces all current "phones" recipients that have been set so far.
   *
   * @param phone Phone number to call to
   * @return This CallIntentBuilder for method chaining
   */
  fun phoneNumber(phone: String): CallIntentBuilder {
    phoneNumber = phone.replace(regex, "")
    if (phoneNumber!!.isEmpty()) throw IllegalStateException("Empty phone number")

    return this
  }

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for calling
   */
  override fun createIntent(): Intent {
    if (phoneNumber == null) throw IllegalStateException("You can't call createIntent before phoneNumber method")

    intent = Intent(Intent.ACTION_DIAL, Uri.parse(PHONE_SCHEME + phoneNumber))
    return intent as Intent
  }

}