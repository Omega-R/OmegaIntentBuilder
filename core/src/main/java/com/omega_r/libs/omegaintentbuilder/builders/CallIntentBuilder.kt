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
import com.omega_r.libs.omegaintentbuilder.types.CallTypes

/**
 * CallIntentBuilder is a helper for constructing {@link Intent#ACTION_DIAL}
 */
class CallIntentBuilder (private val context: Context,
                         private var phoneNumber: String): BaseActivityBuilder(context) {

  private var callType = CallTypes.SYSTEM_CALL

  companion object {
    private const val PHONE_SCHEME = "tel:";
    private const val SKYPE_SCHEME = "skype:";
    val regex = Regex("[^0-9]")
  }

  init {
    phoneNumber = phoneNumber.replace(regex, "")
    if (phoneNumber.isEmpty()) {
      throw IllegalStateException("Empty phone number")
    }
  }

  fun type(callType: CallTypes): CallIntentBuilder {
    this.callType = callType
    return this
  }

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for calling
   */
  override fun createIntent(): Intent {
    val intent = Intent()
    intent.data = Uri.parse(PHONE_SCHEME + phoneNumber)

    if (callType == CallTypes.SYSTEM_CALL) {
      intent.action = Intent.ACTION_DIAL
    } else {
      intent.action = Intent.ACTION_VIEW
      when(callType) {
        CallTypes.VIBER -> {
          intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity")
        }
        CallTypes.SKYPE -> {
          intent.data = Uri.parse(SKYPE_SCHEME + phoneNumber + "?call")
        }
      }
    }
    return intent
  }

}