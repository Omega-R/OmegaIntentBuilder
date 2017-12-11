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
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import java.util.ArrayList
import java.util.TreeSet

/**
 * ShareIntentBuilder is a helper for constructing {Intent#ACTION_SEND} and
 * {Intent#ACTION_SEND_MULTIPLE} sharing intents and starting activities
 * to share content.
 */
class ShareIntentBuilder internal constructor(private val intentBuilder: OmegaIntentBuilder) : BaseShareBuilder<ShareIntentBuilder>() {

  private lateinit var intent: Intent
  private var ccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var bccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var streamsSet: MutableSet<Uri> = mutableSetOf()
  private var mimeType: String? = null

  /**
   * Set a collection of email addresses to CC on this share.
   * This replaces all current "CC" recipients that have been set so far.
   *
   * @param addresses Email addresses to CC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setEmailCc(addresses: Collection<String>): ShareIntentBuilder {
    ccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Set an array of email addresses to CC on this share.
   * This replaces all current "CC" recipients that have been set so far.
   *
   * @param addresses Email addresses to CC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setEmailCc(addresses: Array<String>): ShareIntentBuilder {
    ccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Add a collection of email addresses to be used in the "cc" field of the final Intent.
   *
   * @param addresses Email addresses to CC
   * @return This ShareIntentBuilder for method chaining
   */
  fun addEmailCc(addresses: Collection<String>): ShareIntentBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add an array of email addresses to be used in the "cc" field of the final Intent.
   *
   * @param addresses Email addresses to CC
   * @return This ShareIntentBuilder for method chaining
   */
  fun addEmailCc(addresses: Array<String>): ShareIntentBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add single value of email address to be used in the "cc" field of the final Intent.
   *
   * @param address Email address to CC
   * @return This ShareIntentBuilder for method chaining
   */
  fun addEmailCc(address: String): ShareIntentBuilder {
    ccAddressesSet.add(address)
    return this
  }

  /**
   * Set a Collection of email addresses to BCC on this share.
   * This replaces all current "BCC" recipients that have been set so far.
   *
   * @param addresses Email addresses to BCC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setEmailBcc(addresses: Collection<String>): ShareIntentBuilder {
    bccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Set an Array of email addresses to BCC on this share.
   * This replaces all current "BCC" recipients that have been set so far.
   *
   * @param addresses Email addresses to BCC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setEmailBcc(addresses: Array<String>): ShareIntentBuilder {
    bccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Add a Collection of email address to be used in the "bcc" field of the final Intent.
   *
   * @param address Email address to BCC
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_BCC
   */
  fun addEmailBcc(addresses: Collection<String>): ShareIntentBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add an Array of email addresses to BCC on this share.
   *
   * @param addresses Email addresses to BCC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun addEmailBcc(addresses: Array<String>): ShareIntentBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add single value of email address to BCC on this share.
   *
   * @param address Email address to BCC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun addEmailBcc(address: String): ShareIntentBuilder {
    bccAddressesSet.add(address)
    return this
  }

  /**
   * Set the type of data being shared
   *
   * @param mimeType mimetype of the shared data
   * @return This ShareIntentBuilder for method chaining
   */
  fun setType(mimeType: String): ShareIntentBuilder {
    this.mimeType = mimeType
    return this
  }

  /**
   * Set a stream URI to the data that should be shared.
   *
   * <p>This replaces all currently set stream URIs and will produce a single-stream
   * ACTION_SEND intent.</p>
   *
   * @param streamUri URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setStream(streamUri: Uri): ShareIntentBuilder {
    streamsSet = mutableSetOf();
    streamsSet.add(streamUri)
    return this
  }

  /**
   * Add a stream URI to the data that should be shared. If this is not the first
   * stream URI added the final intent constructed will become an ACTION_SEND_MULTIPLE
   * intent. Not all apps will handle both ACTION_SEND and ACTION_SEND_MULTIPLE.
   *
   * @param streamUri URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_STREAM
   * @see Intent#ACTION_SEND
   * @see Intent#ACTION_SEND_MULTIPLE
   */
  fun addStream(streamUri: Uri): ShareIntentBuilder {
    streamsSet.add(streamUri)
    return this
  }

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for sharing
   */
  override fun createIntent(): Intent {
    intent = super.createIntent()
    intent.action = Intent.ACTION_SEND
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
    if (streamsSet.size == 1) {
      intent.putExtra(Intent.EXTRA_STREAM, streamsSet.elementAt(0))
    } else if (streamsSet.size > 1) {
      intent.setAction(Intent.ACTION_SEND_MULTIPLE)
      intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, convertSetToArrayList(streamsSet))
    }

    return intent
  }

  private fun <T> convertSetToArrayList(sets: MutableSet<T>): ArrayList<T> {
    val list: ArrayList<T> = arrayListOf()
    sets.forEach { it -> list.add(it) }
    return list
  }

}