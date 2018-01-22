/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * DownloadBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 14, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders.share

import android.content.Context
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.Download
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadAsyncTask
import java.util.*

/**
 * DownloadBuilder is a helper for download files from internet and add it to createdIntent
 * to share content.
 */
class DownloadBuilder<T>(private val context: Context,
                         private val intentBuilder: T) where T : BaseUriBuilder, T: Download<BaseUriBuilder> {

  var urlsMap: MutableMap<String, String?> = TreeMap(String.CASE_INSENSITIVE_ORDER)

  /**
   * Add a array of url addresses to download.
   *
   * @param urlAddresses Array of String addresses to download and share
   * @return This DownloadBuilder for method chaining
   */
  fun filesUrls(vararg urlAddresses: String): DownloadBuilder<T> {
    urlAddresses.forEach { it -> urlsMap.put(it) }
    return this
  }

  /**
   * Add a String url address for downloading.
   *
   * @param urlAddress String address for downloading and share
   * @param mimeType MimeType
   * @return This DownloadBuilder for method chaining
   */
  @JvmOverloads
  fun fileUrlWithMimeType(urlAddress: String, mimeType: String? = null): DownloadBuilder<T> {
    urlsMap.put(urlAddress, mimeType)
    return this
  }

  /**
   * Add a collection of url addresses to download.
   *
   * @param collection Collection of String addresses to download and share
   * @return This DownloadBuilder for method chaining
   */
  fun filesUrls(collection: Collection<String>): DownloadBuilder<T> {
    collection.forEach { it -> urlsMap.put(it) }
    return this
  }

  private fun MutableMap<String, String?>.put(key: String, value: String? = null) {
    this.put(key, value)
  }

  /**
   * Download files from urls.
   *
   * @param callback DownloadCallback to control loading status
   * @return This ContextIntentHandler for method chaining
   */
  fun download(callback: DownloadCallback) {
    if (urlsMap.isEmpty()) {
      callback.onDownloaded(true, intentBuilder.createIntentHandler())
      return
    }
    val downloader = DownloadAsyncTask(context, intentBuilder, intentBuilder.localFilesDir, callback)
    downloader.execute(urlsMap)
  }


}