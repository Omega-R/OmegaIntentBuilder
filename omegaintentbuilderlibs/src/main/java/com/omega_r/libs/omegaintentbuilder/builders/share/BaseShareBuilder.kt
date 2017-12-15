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
import android.net.Uri
import android.os.Build
import android.support.annotation.NonNull
import android.text.Html
import com.omega_r.libs.omegaintentbuilder.builders.BaseBuilder
import java.util.ArrayList
import java.util.TreeSet

@Suppress("UNCHECKED_CAST")
open class BaseShareBuilder<T>(private val context: Context): BaseBuilder(context) {

  private var toAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var subject: String? = null
  private var text: CharSequence? = null
  private var streamsSet: MutableSet<Uri> = mutableSetOf()
  private val downloadBuilder: DownloadBuilder<T> = DownloadBuilder(context, this)
  /**
   * Set single value of email address as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param address Email address to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun setEmailTo(address: String): T {
    toAddressesSet.clear()
    toAddressesSet.add(address)
    return this as T
  }

  /**
   * Set collection of email addresses as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param addresses Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun setEmailTo(addresses: Collection<String>): T {
    toAddressesSet.clear()
    toAddressesSet.addAll(addresses.toMutableSet())
    return this as T
  }

  /**
   * Set an array of email addresses as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param addresses Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun setEmailTo(addresses: Array<String>): T {
    toAddressesSet.clear()
    toAddressesSet.addAll(addresses.toMutableSet())
    return this as T
  }

  /**
   * Add Collection of email addresses as recipients of this share.
   *
   * @param addresses Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun addEmailTo(addresses: Collection<String>): T {
    toAddressesSet.addAll(addresses)
    return this as T
  }

  /**
   * Add an array of email addresses as recipients of this share.
   *
   * @param addresses Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun addEmailTo(addresses: Array<String>): T {
    toAddressesSet.addAll(addresses)
    return this as T
  }

  /**
   * Add or single value of email address as recipients of this share.
   *
   * @param address Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun addEmailTo(address: String): T {
    toAddressesSet.add(address)
    return this as T
  }

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

  /**
   * Set a stream URI to the data that should be shared.
   *
   * <p>This replaces all currently set stream URIs and will produce a single-stream
   * ACTION_SEND createdIntent.</p>
   *
   * @param streamUri URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setStream(vararg streamUri: Uri): T {
    streamsSet.clear()
    streamsSet.addAll(streamUri.toMutableSet())
    return this as T
  }

  /**
   * Set a stream URI to the data that should be shared.
   *
   * <p>This replaces all currently set stream URIs and will produce a single-stream
   * ACTION_SEND createdIntent.</p>
   *
   * @param streamUriSet URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setStream(streamUriSet: MutableSet<Uri>): T {
    streamsSet.clear()
    streamsSet.addAll(streamUriSet)
    return this as T
  }

  /**
   * Set a stream URI to the data that should be shared.
   *
   * <p>This replaces all currently set stream URIs and will produce a single-stream
   * ACTION_SEND createdIntent.</p>
   *
   * @param streamUriList URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   */
  fun setStream(streamUriList: List<Uri>): T {
    streamsSet.clear()
    streamsSet.addAll(streamUriList.toMutableSet())
    return this as T
  }

  /**
   * Add a stream URI to the data that should be shared. If this is not the first
   * stream URI added the final createdIntent constructed will become an ACTION_SEND_MULTIPLE
   * createdIntent. Not all apps will handle both ACTION_SEND and ACTION_SEND_MULTIPLE.
   *
   * @param streamUri URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_STREAM
   * @see Intent#ACTION_SEND
   * @see Intent#ACTION_SEND_MULTIPLE
   */
  fun addStream(vararg streamUri: Uri): T {
    streamsSet.addAll(streamUri)
    return this as T
  }

  /**
   * Add a stream URI to the data that should be shared. If this is not the first
   * stream URI added the final createdIntent constructed will become an ACTION_SEND_MULTIPLE
   * createdIntent. Not all apps will handle both ACTION_SEND and ACTION_SEND_MULTIPLE.
   *
   * @param streamUriSet URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_STREAM
   * @see Intent#ACTION_SEND
   * @see Intent#ACTION_SEND_MULTIPLE
   */
  fun addStream(streamUriSet: MutableSet<Uri>): T {
    streamsSet.addAll(streamUriSet)
    return this as T
  }

  /**
   * Add a stream URI to the data that should be shared. If this is not the first
   * stream URI added the final createdIntent constructed will become an ACTION_SEND_MULTIPLE
   * createdIntent. Not all apps will handle both ACTION_SEND and ACTION_SEND_MULTIPLE.
   *
   * @param streamUriList URI of the stream to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_STREAM
   * @see Intent#ACTION_SEND
   * @see Intent#ACTION_SEND_MULTIPLE
   */
  fun addStream(streamUriList: List<Uri>): T {
    streamsSet.addAll(streamUriList)
    return this as T
  }

  /**
   * Add a files Url address to the data that should be shared.
   *
   * @param urlAddresses String of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun addFilesUrls(vararg urlAddresses: String): DownloadBuilder<T> {
    return downloadBuilder.addFilesUrls(*urlAddresses)
  }

  /**
   * Add a files Url address to the data that should be shared.
   *
   * @param urlAddresses Collection of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun addFilesUrls(collection: Collection<String>): DownloadBuilder<T> {
    return downloadBuilder.addFilesUrls(collection)
  }

  /**
   * Add a files Url address to the data that should be shared.
   *
   * @param fileSet Set of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun addFilesUrls(fileSet: Set<String>): DownloadBuilder<T> {
    return downloadBuilder.addFilesUrls(fileSet)
  }

  /**
   * Set a files Url address to the data that should be shared.
   *
   * <p>This replaces all currently set stream URIs and will produce a new stream
   * ACTION_SEND createdIntent.</p>
   *
   * @param urlAddresses Array of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun setFilesUrls(vararg urlAddresses: String): DownloadBuilder<T> {
    return downloadBuilder.setFilesUrls(*urlAddresses)
  }

  /**
   * Set a files Url address to the data that should be shared.
   *
   * <p>This replaces all currently set stream URIs and will produce a new stream
   * ACTION_SEND createdIntent.</p>
   *
   * @param collection Collection of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun setFilesUrls(collection: Collection<String>): DownloadBuilder<T> {
    return downloadBuilder.setFilesUrls(collection)
  }

  /**
   * Set a files Url address to the data that should be shared.
   *
   * <p>This replaces all currently set stream URIs and will produce a new stream
   * ACTION_SEND createdIntent.</p>
   *
   * @param fileSet Set of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun setFilesUrls(fileSet: Set<String>): DownloadBuilder<T> {
    return downloadBuilder.setFilesUrls(fileSet)
  }

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for sharing
   */
  override fun createIntent(): Intent {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND

    if (streamsSet.size == 1) {
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
      intent.putExtra(Intent.EXTRA_STREAM, streamsSet.elementAt(0))
    } else if (streamsSet.size > 1) {
      intent.action = Intent.ACTION_SEND_MULTIPLE
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
      intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, convertSetToArrayList(streamsSet))
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