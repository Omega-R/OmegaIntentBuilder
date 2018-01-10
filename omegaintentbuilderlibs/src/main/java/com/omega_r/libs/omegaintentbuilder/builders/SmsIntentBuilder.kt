/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * SmsIntentBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 10, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Telephony
import android.support.annotation.StringRes
import java.util.TreeSet

/**
 * SmsIntentBuilder is a helper for constructing sms intent
 */
class SmsIntentBuilder(private val context: Context) : BaseBuilder(context) {

  private var phoneNumberSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var message: String? = null

  /**
   * Add a collection of phone numbers.
   *
   * @param addresses Collection
   * @return This SmsIntentBuilder for method chaining
   */
  fun phoneNumbers(addresses: Collection<String>): SmsIntentBuilder {
    addresses.forEach { addPhoneNumber(it) }
    return this
  }

  /**
   * Add a Array of phone numbers.
   *
   * @param addresses Array
   * @return This SmsIntentBuilder for method chaining
   */
  fun phoneNumbers(vararg address: String): SmsIntentBuilder {
    address.forEach { addPhoneNumber(it) }
    return this
  }

  /**
   * Add a phone number.
   *
   * @param addressRes Int
   * @return This SmsIntentBuilder for method chaining
   */
  fun phoneNumber(@StringRes addressRes: Int): SmsIntentBuilder {
    addPhoneNumber(context.getString(addressRes))
    return this
  }

  private fun addPhoneNumber(phoneNumber: String) {
    val phone = phoneNumber.replace(CallIntentBuilder.regex, "")
    if (!phone.isEmpty()) {
      phoneNumberSet.add(phone)
    }
  }

  /**
   * Add a message.
   *
   * @param message String
   * @return This SmsIntentBuilder for method chaining
   */
  fun message(message: String): SmsIntentBuilder {
    this.message = message
    return this
  }

  /**
   * Add a message.
   *
   * @param message Int
   * @return This SmsIntentBuilder for method chaining
   */
  fun message(@StringRes message: Int): SmsIntentBuilder {
    this.message = context.getString(message)
    return this
  }

  @SuppressLint("NewApi")
  override fun createIntent(): Intent {
    if (phoneNumberSet.isEmpty()) {
      throw IllegalStateException("You can't call createIntent with empty phone number")
    }
    if (message == null) {
      message = ""
    }
    val intent: Intent
    val phones = phoneNumberSet.joinToString(separator = ";")

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      val smsPackage = Telephony.Sms.getDefaultSmsPackage(context)
      intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phones))
      smsPackage?.let { intent.setPackage(smsPackage) }
    } else {
      val smsUri = Uri.parse("tel:" + phones)
      intent = Intent(Intent.ACTION_VIEW, smsUri)
      intent.putExtra("address", phones)
      intent.setType("vnd.android-dir/mms-sms")
    }
    intent.putExtra("sms_body", message)

    return intent
  }

}