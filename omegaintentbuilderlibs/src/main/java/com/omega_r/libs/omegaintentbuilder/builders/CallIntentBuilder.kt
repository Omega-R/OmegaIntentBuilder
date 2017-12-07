package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder

class CallIntentBuilder(private val intentBuilder: OmegaIntentBuilder) : IntentBuilder {

  companion object {
    private const val PHONE_SCHEME = "tel:";
    private val regex = Regex("[^0-9]")
  }

  private var intent: Intent? = null
  private var phoneNumber: String? = null;

  fun phoneNumber(phone: String): CallIntentBuilder {
    phoneNumber = phone.replace(regex, "")
    if (phoneNumber!!.isEmpty()) throw IllegalStateException("Empty phone number")

    return this
  }

  override fun createIntent(): Intent {
    if (phoneNumber == null) throw IllegalStateException("You can't call createIntent before phoneNumber method")

    intent = Intent(Intent.ACTION_DIAL, Uri.parse(PHONE_SCHEME + phoneNumber))
    return intent as Intent
  }

}