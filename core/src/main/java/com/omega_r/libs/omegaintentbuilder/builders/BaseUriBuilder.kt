package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.share.BaseShareBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.DownloadBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.Download

abstract class BaseUriBuilder<T>(private val context: Context): BaseFileBuilder(context), Download<T> where T: BaseUriBuilder<T>, T: Download<T> {

  private var uriSet: MutableSet<Uri> = mutableSetOf()
  private val downloadBuilder = DownloadBuilder(context, this)

  /**
   * Add a uri URI to the data that should be shared. If this is not the first
   * uri URI added the final createdIntent constructed will become an ACTION_SEND_MULTIPLE
   * createdIntent. Not all apps will handle both ACTION_SEND and ACTION_SEND_MULTIPLE.
   *
   * @param uri URI of the uri to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_STREAM
   * @see Intent#ACTION_SEND
   * @see Intent#ACTION_SEND_MULTIPLE
   */
  fun uri(vararg uri: Uri): BaseFileBuilder {
    uriSet.addAll(uri)
    return this
  }

  /**
   * Add a uri URI to the data that should be shared. If this is not the first
   * uri URI added the final createdIntent constructed will become an ACTION_SEND_MULTIPLE
   * createdIntent. Not all apps will handle both ACTION_SEND and ACTION_SEND_MULTIPLE.
   *
   * @param uriSet URI of the uri to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_STREAM
   * @see Intent#ACTION_SEND
   * @see Intent#ACTION_SEND_MULTIPLE
   */
  fun uri(uriSet: MutableSet<Uri>): BaseFileBuilder {
    this.uriSet.addAll(uriSet)
    return this
  }

  /**
   * Add a uri URI to the data that should be shared. If this is not the first
   * uri URI added the final createdIntent constructed will become an ACTION_SEND_MULTIPLE
   * createdIntent. Not all apps will handle both ACTION_SEND and ACTION_SEND_MULTIPLE.
   *
   * @param uriList URI of the uri to share
   * @return This ShareIntentBuilder for method chaining
   * @see Intent#EXTRA_STREAM
   * @see Intent#ACTION_SEND
   * @see Intent#ACTION_SEND_MULTIPLE
   */
  override fun uri(uriList: List<Uri>): T {
    uriSet.addAll(uriList)
    return this as T
  }

  /**
   * Add a files Url address to the data that should be shared.
   *
   * @param urlAddresses String of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun filesUrls(vararg urlAddresses: String): DownloadBuilder<BaseUriBuilder<T>> {
    return downloadBuilder.filesUrls(*urlAddresses)
  }

  /**
   * Add a files Url address to the data that should be shared.
   *
   * @param urlAddresses Collection of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun filesUrls(collection: Collection<String>): DownloadBuilder<BaseUriBuilder<T>> {
    return downloadBuilder.filesUrls(collection)
  }

  /**
   * Add a files Url address to the data that should be shared.
   *
   * @param fileSet Set of the url links to share
   * @return This DownloadBuilder for download call
   */
  fun filesUrls(fileSet: Set<String>): DownloadBuilder<BaseUriBuilder<T>> {
    return downloadBuilder.filesUrls(fileSet)
  }

  /**
   * Add a file Url address to the data that should be shared.
   *
   * @param urlAddress String address for downloading and share
   * @param mimeType MimeType
   *
   * @return This DownloadBuilder for method chaining
   */
  @JvmOverloads
  fun fileUrlWithMimeType(urlAddress: String,  mimeType: String? = null): DownloadBuilder<BaseUriBuilder<T>> {
    return downloadBuilder.fileUrlWithMimeType(urlAddress, mimeType)
  }

  fun bitmap(bitmap: Bitmap): BaseFileBuilder {
    uriSet.add(toUri(bitmap))
    return this
  }




  protected fun getUriSet(): MutableSet<Uri> = uriSet

}