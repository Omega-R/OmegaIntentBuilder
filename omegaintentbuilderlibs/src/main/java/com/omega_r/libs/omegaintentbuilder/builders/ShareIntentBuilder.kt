/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * ShareIntentBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import java.util.ArrayList
import java.util.TreeSet

/**
 * ShareIntentBuilder is a helper for constructing {@link Intent#ACTION_SEND} and
 * {@link Intent#ACTION_SEND_MULTIPLE} sharing intents and starting activities
 * to share content. The ComponentName and package name of the calling activity
 * will be included.
 */
class ShareIntentBuilder internal constructor(private val intentBuilder: OmegaIntentBuilder): IntentBuilder {

  private lateinit var intent: Intent
  private var toAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var ccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var bccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var streamsSet: MutableSet<Uri> = mutableSetOf()
  private var subject: String? = null
  private var mimeType: String? = null
  private var text: CharSequence? = null
  private var htmlText: String? = null

  /**
   * Set an array of email addresses as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param addresses Email addresses to send to
   * @return This ShareIntentBuilder for method chaining
   */
  fun setEmailTo(addresses: Collection<String>): ShareIntentBuilder {
    toAddressesSet = addresses.toMutableSet()
    return this
  }

  fun setEmailTo(addresses: Array<String>): ShareIntentBuilder {
    toAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Add an array (or single value) of email addresses as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param addresses Email addresses to send to
   * @return This ShareIntentBuilder for method chaining
   */
  fun addEmailTo(addresses: Collection<String>): ShareIntentBuilder {
    toAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailTo(addresses: Array<String>): ShareIntentBuilder {
    toAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailTo(addresses: String): ShareIntentBuilder {
    toAddressesSet.add(addresses)
    return this
  }

  /**
   * Set an array of email addresses to CC on this share.
   * This replaces all current "CC" recipients that have been set so far.
   *
   * @param addresses Email addresses to CC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setEmailCc(addresses: Collection<String>): ShareIntentBuilder {
    ccAddressesSet = addresses.toMutableSet()
    return this
  }

  fun setEmailCc(addresses: Array<String>): ShareIntentBuilder {
    ccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Add an array (or single value)  of email addresses to be used in the "cc" field of the final Intent.
   *
   * @param addresses Email addresses to CC
   * @return This ShareIntentBuilder for method chaining
   */
  fun addEmailCc(addresses: Collection<String>): ShareIntentBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailCc(addresses: Array<String>): ShareIntentBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailCc(addresses: String): ShareIntentBuilder {
    ccAddressesSet.add(addresses)
    return this
  }

  /**
   * Set an array of email addresses to BCC on this share.
   * This replaces all current "BCC" recipients that have been set so far.
   *
   * @param addresses Email addresses to BCC on the share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setEmailBcc(addresses: Collection<String>): ShareIntentBuilder {
    bccAddressesSet = addresses.toMutableSet()
    return this
  }

  fun setEmailBcc(addresses: Array<String>): ShareIntentBuilder {
    bccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Add an email address to be used in the "bcc" field of the final Intent.
   *
   * @param address Email address to BCC
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_BCC
   */
  fun addEmailBcc(addresses: Collection<String>): ShareIntentBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailBcc(addresses: Array<String>): ShareIntentBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailBcc(addresses: String): ShareIntentBuilder {
    bccAddressesSet.add(addresses)
    return this
  }

  /**
   * Set a subject heading for this share; useful for sharing via email.
   *
   * @param subject Subject heading for this share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setSubject(subject: String): ShareIntentBuilder {
    this.subject = subject
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
   * Set the literal text data to be sent as part of the share.
   * This may be a styled CharSequence.
   *
   * @param text Text to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_TEXT
   */
  fun setText(text: CharSequence): ShareIntentBuilder {
    this.text = text
    return this
  }

  /**
   * Set an HTML string to be sent as part of the share.
   * If {@link Intent#EXTRA_TEXT EXTRA_TEXT} has not already been supplied,
   * a styled version of the supplied HTML text will be added as EXTRA_TEXT as
   * parsed by {@link android.text.Html#fromHtml(String) Html.fromHtml}.
   *
   * @param htmlText A string containing HTML markup as a richer version of the text
   *                 provided by EXTRA_TEXT.
   * @return This ShareIntentBuilder for method chaining
   * @see #setText(CharSequence)
   */
  fun setHtmlText(htmlText: String): ShareIntentBuilder {
    this.htmlText = htmlText
    text?.let {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
      } else {
        text = Html.fromHtml(htmlText)
      }
    }

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
    intent = Intent().setAction(Intent.ACTION_SEND)

    if (toAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_EMAIL, toAddressesSet.toTypedArray())
    }
    if (ccAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_CC, ccAddressesSet.toTypedArray())
    }
    if (bccAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_BCC, bccAddressesSet.toTypedArray())
    }
    if (!subject.isNullOrEmpty()) {
      intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    }
    if (!mimeType.isNullOrEmpty()) {
      intent.setType(mimeType)
    } else {
      intent.setType(MimeTypes.TEXT_PLAIN)
    }
    if (!text.isNullOrEmpty()) {
      intent.putExtra(Intent.EXTRA_TEXT, text);
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