/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * ShareIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders.share

import android.content.Context
import android.content.Intent
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.MimeTypes
import java.util.TreeSet

/**
 * ShareIntentBuilder is a helper for constructing {Intent#ACTION_SEND} and
 * {Intent#ACTION_SEND_MULTIPLE} sharing intents and starting activities
 * to share content.
 */
class ShareIntentBuilder internal constructor(private val context: Context,
                                              private val intentBuilder: OmegaIntentBuilder) : BaseShareBuilder<ShareIntentBuilder>(context) {

  private lateinit var intent: Intent
  private var ccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var bccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var mimeType: String? = null

   /**
   * Add a collection of email addresses to be used in the "cc" field of the final Intent.
   *
   * @param addresses Email addresses to CC
   * @return This ShareIntentBuilder for method chaining
   */
  fun emailCc(addresses: Collection<String>): ShareIntentBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add an array of email addresses to be used in the "cc" field of the final Intent.
   *
   * @param addresses Email addresses to CC
   * @return This ShareIntentBuilder for method chaining
   */
  fun emailCc(addresses: Array<String>): ShareIntentBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add single value of email address to be used in the "cc" field of the final Intent.
   *
   * @param address Email address to CC
   * @return This ShareIntentBuilder for method chaining
   */
  fun emailCc(address: String): ShareIntentBuilder {
    ccAddressesSet.add(address)
    return this
  }

  /**
   * Add a Collection of email address to be used in the "bcc" field of the final Intent.
   *
   * @param address Email address to BCC
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_BCC
   */
  fun emailBcc(addresses: Collection<String>): ShareIntentBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add an Array of email addresses to BCC on this share.
   *
   * @param addresses Email addresses to BCC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun emailBcc(addresses: Array<String>): ShareIntentBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add single value of email address to BCC on this share.
   *
   * @param address Email address to BCC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun emailBcc(address: String): ShareIntentBuilder {
    bccAddressesSet.add(address)
    return this
  }

  /**
   * Set the mimeType of data being shared
   *
   * @param mimeType mimetype of the shared data
   * @return This ShareIntentBuilder for method chaining
   */
  fun mimeType(mimeType: String): ShareIntentBuilder {
    this.mimeType = mimeType
    return this
  }

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for sharing
   */
  override fun createIntent(): Intent {
    intent = super.createIntent()
    if (ccAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_CC, ccAddressesSet.toTypedArray())
    }
    if (bccAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_BCC, bccAddressesSet.toTypedArray())
    }
    if (!mimeType.isNullOrEmpty()) {
      intent.setType(mimeType)
    } else {
      intent.setType(MimeTypes.TEXT_PLAIN)
    }

    return intent
  }

}