/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BaseShareBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders.share

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.support.annotation.StringRes
import android.text.Html
import com.omega_r.libs.omegaintentbuilder.builders.BaseFileBuilder
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.Download
import java.util.ArrayList
import java.util.TreeSet

@Suppress("UNCHECKED_CAST")
open class BaseShareBuilder<T>(private val context: Context): BaseUriBuilder<T>(context), Download<T> where T: BaseUriBuilder<T>, T: Download<T>  {

  private var toAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var subject: String? = null
  private var text: CharSequence? = null
  private val downloadBuilder = DownloadBuilder(context, this)

  /**
   * Add Collection of email addresses as recipients of this share.
   *
   * @param addresses Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun emailTo(addresses: Collection<String>): T {
    toAddressesSet.addAll(addresses)
    return this as T
  }

  /**
   * Add or single value of email address as recipients of this share.
   *
   * @param address Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun emailTo(vararg address: String): T {
    toAddressesSet.addAll(address)
    return this as T
  }

  /**
   * Add or single value of email address as recipients of this share.
   *
   * @param address @StringRes Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun emailTo(@StringRes addressRes: Int): T {
    toAddressesSet.add(context.getString(addressRes))
    return this as T
  }

  /**
   * Set a subject heading for this share; useful for sharing via email.
   *
   * @param subject Subject heading for this share
   * @return This BaseShareBuilder for method chaining
   */
  fun subject(subject: String): T {
    this.subject = subject
    return this as T
  }

  /**
   * Set a subject heading for this share; useful for sharing via email.
   *
   * @param subject Resource id for Subject heading for this share
   * @return This BaseShareBuilder for method chaining
   */
  fun subject(@StringRes subjectRes: Int): T {
    this.subject = context.getString(subjectRes)
    return this as T
  }

  /**
   * Set a subject heading for this share; useful for sharing via email.
   *
   * @param subjectRes Resource id for the format string
   * @param formatArgs The format arguments that will be used for substitution.
   * @return This BaseShareBuilder for method chaining
   */
  fun subject(@StringRes subjectRes: Int, vararg formatArgs: Any): T {
    this.subject = context.getString(subjectRes, *formatArgs)
    return this as T
  }

  /**
   * Set the literal text data to be sent as part of the share.
   * This may be a styled CharSequence.
   *
   * @param text Text to share
   * @return This BaseShareBuilder for method chaining
   * @see Intent#EXTRA_TEXT
   */
  fun text(text: CharSequence): T {
    this.text = text.toString()
    return this as T
  }

  /**
   * Set the literal text data to be sent as part of the share.
   * This may be a styled CharSequence.
   *
   * @param @StringRes textRes Text to share
   * @return This BaseShareBuilder for method chaining
   * @see Intent#EXTRA_TEXT
   */
  fun text(@StringRes textRes: Int): T {
    this.text = context.getString(textRes)
    return this as T
  }

  /**
   * Set the literal text data to be sent as part of the share.
   * This may be a styled CharSequence.
   *
   * @param subjectRes Resource id for the format string
   * @param formatArgs The format arguments that will be used for substitution.
   * @return This BaseShareBuilder for method chaining
   * @see Intent#EXTRA_TEXT
   */
  fun text(@StringRes textRes: Int, vararg formatArgs: Any): T {
    this.text = context.getString(textRes, *formatArgs)
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
   * @see #text(CharSequence)
   */
  fun htmlText(htmlText: String): T {
    text?.let {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
      } else {
        text = Html.fromHtml(htmlText)
      }
    }

    return this as T
  }

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for sharing
   */
  override fun createIntent(): Intent {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    val uriSet = getUriSet()

    if (uriSet.size == 1) {
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
      intent.putExtra(Intent.EXTRA_STREAM, uriSet.elementAt(0))
    } else if (uriSet.size > 1) {
      intent.action = Intent.ACTION_SEND_MULTIPLE
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
      intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, convertSetToArrayList(uriSet))
    }
    if (toAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_EMAIL, toAddressesSet.toTypedArray())
    }
    if (!subject.isNullOrEmpty()) {
      intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    if (!text.isNullOrEmpty()) {
      intent.putExtra(Intent.EXTRA_TEXT, text)
    }

    return intent
  }

  private fun <T> convertSetToArrayList(sets: MutableSet<T>): ArrayList<T> {
    val list: ArrayList<T> = arrayListOf()
    sets.forEach { it -> list.add(it) }
    return list
  }

}