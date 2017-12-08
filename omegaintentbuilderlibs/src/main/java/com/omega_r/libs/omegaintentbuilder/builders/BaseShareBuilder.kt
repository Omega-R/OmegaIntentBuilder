/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BaseShareBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Intent
import android.os.Build
import android.text.Html
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import java.util.TreeSet

@Suppress("UNCHECKED_CAST")
open class BaseShareBuilder<T>: IntentBuilder {

  private var toAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var subject: String? = null
  private var text: CharSequence? = null

  /**
   * Set an array of email addresses as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param addresses Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun setEmailTo(address: String): T {
    toAddressesSet = mutableSetOf()
    toAddressesSet.add(address)
    return this as T
  }

  fun setEmailTo(addresses: Collection<String>): T {
    toAddressesSet = addresses.toMutableSet()
    return this as T
  }

  fun setEmailTo(addresses: Array<String>): T {
    toAddressesSet = addresses.toMutableSet()
    return this as T
  }

  //  /**
  //   * Add an array (or single value) of email addresses as recipients of this share.
  //   * This replaces all current "to" recipients that have been set so far.
  //   *
  //   * @param addresses Email addresses to send to
  //   * @return This BaseShareBuilder for method chaining
  //   */
  fun addEmailTo(addresses: Collection<String>): T {
    toAddressesSet.addAll(addresses)
    return this as T
  }

  fun addEmailTo(addresses: Array<String>): T {
    toAddressesSet.addAll(addresses)
    return this as T
  }

  fun addEmailTo(addresses: String): T {
    toAddressesSet.add(addresses)
    return this as T
  }

  fun getEmailsTo(): MutableSet<String> = toAddressesSet

  /**
   * Set a subject heading for this share; useful for sharing via email.
   *
   * @param subject Subject heading for this share
   * @return This BaseShareBuilder for method chaining
   */
  fun setSubject(subject: String): T {
    this.subject = subject
    return this as T
  }

  fun getSubject(): String? = subject

  /**
   * Set the literal text data to be sent as part of the share.
   * This may be a styled CharSequence.
   *
   * @param text Text to share
   * @return This BaseShareBuilder for method chaining
   * @see Intent#EXTRA_TEXT
   */
  fun setText(text: CharSequence): T {
    this.text = text.toString()
    return this as T
  }

  /**
   * Set an HTML string to be sent as part of the share.
   * If {@link Intent#EXTRA_TEXT EXTRA_TEXT} has not already been supplied,
   * a styled version of the supplied HTML text will be added as EXTRA_TEXT as
   * parsed by {@link android.text.Html#fromHtml(String) Html.fromHtml}.
   *
   * @param htmlText A string containing HTML markup as a richer version of the text
   *                 provided by EXTRA_TEXT.
   * @return This BaseShareBuilder for method chaining
   * @see #setText(CharSequence)
   */
  fun setHtmlText(htmlText: String): T {
    text?.let {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
      } else {
        text = Html.fromHtml(htmlText)
      }
    }

    return this as T
  }

  fun getText(): String? = text.toString()

  override fun createIntent(): Intent {
    val intent = Intent()
    if (toAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_EMAIL, getEmailsTo().toTypedArray())
    }
    if (!getSubject().isNullOrEmpty()) {
      intent.putExtra(Intent.EXTRA_SUBJECT, getSubject());
    }
    if (!getText().isNullOrEmpty()) {
      intent.putExtra(Intent.EXTRA_TEXT, getText());
    }

    return intent
  }

}