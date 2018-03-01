/*
 * Copyright (c) 2017 Omega-r
 *
 * BaseUriBuilder
 * BaseUriBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 22, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.share.DownloadBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.Download
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider
import java.io.File
import java.io.FileOutputStream

abstract class BaseUriBuilder(private val context: Context): BaseActivityBuilder(context), Download<BaseUriBuilder> {

  private var uriSet: MutableSet<Uri> = mutableSetOf()
  private val downloadBuilder = DownloadBuilder(context, this)
  internal var localFilesDir: File

  companion object {
    private const val FILE_DIR = "intent_files" // this value from xml/file_paths.xml
    private const val DEFAULT_IMAGE_FILE_NAME = "omegaOutput.jpg"
  }

  init {
    localFilesDir = File(context.cacheDir, FILE_DIR)
    localFilesDir.mkdirs()
  }

  /**
   * @param uri Resource Identifier
   * @return BaseUriBuilder for method chaining
   */
  fun uri(vararg uri: Uri): BaseUriBuilder {
    uriSet.addAll(uri)
    return this
  }

  /**
   * @param uriCollection  Collections of resource identifiers
   * @return BaseUriBuilder for method chaining
   */
  override fun uri(uriCollection: Collection<Uri>): BaseUriBuilder {
    uriSet.addAll(uriCollection)
    return this
  }

  /**
   * @param urlAddresses Url addresses for downloading
   * @return DownloadBuilder for method chaining
   */
  fun filesUrls(vararg urlAddresses: String): DownloadBuilder<BaseUriBuilder> {
    return downloadBuilder.filesUrls(*urlAddresses)
  }

  /**
   * @param collection Collection<String> addresses for downloading
   * @return DownloadBuilder for method chaining
   */
  fun filesUrls(collection: Collection<String>): DownloadBuilder<BaseUriBuilder> {
    return downloadBuilder.filesUrls(collection)
  }

  /**
   * Add a String url address for downloading.
   *
   * @param urlAddress String address for downloading and share
   * @param mimeType MimeType
   * @return DownloadBuilder for method chaining
   */
  @JvmOverloads
  fun fileUrlWithMimeType(urlAddress: String,  mimeType: String? = null): DownloadBuilder<BaseUriBuilder> {
    return downloadBuilder.fileUrlWithMimeType(urlAddress, mimeType)
  }

  /**
   * @param file File
   * @return BaseUriBuilder for method chaining
   */
  fun file(vararg file: File): BaseUriBuilder {
    file.forEach { uriSet.add(toUri(it)) }
    return this
  }

  /**
   * @param collection Collection<String>
   * or
   * @param collection Collection<File>
   * @return BaseUriBuilder for method chaining
   */
  fun <T> file(collection: Collection<T>): BaseUriBuilder {
    if (collection.all { it is String }) {
      collection.forEach {  uriSet.add(toUri(File(it as String))) }
    } else if (collection.all { it is File }) {
      collection.forEach { uriSet.add(toUri(it as File)) }
    } else {
      throw IllegalStateException("Collection type should be String or File")
    }
    return this
  }

  /**
   * @param file String
   * @return BaseUriBuilder for method chaining
   */
  fun file(vararg file: String): BaseUriBuilder {
    file.forEach { uriSet.add(toUri(File(it))) }
    return this
  }

  private fun toUri(file:File): Uri {
    val cacheFile = File(localFilesDir, file.name)
    file.copyTo(cacheFile, overwrite = true)
    return FileProvider.getLocalFileUri(context, cacheFile)
  }

  /**
   * @param bitmap Bitmap
   * @return BaseUriBuilder for method chaining
   */
  fun bitmap(bitmap: Bitmap): BaseUriBuilder {
    uriSet.add(toUri(bitmap))
    return this
  }

  private fun toUri(bitmap: Bitmap): Uri {
    val file = File(localFilesDir, DEFAULT_IMAGE_FILE_NAME)
    val fileOutputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
    fileOutputStream.close()

    return FileProvider.getLocalFileUri(context, file)
  }

  protected fun getUriSet(): MutableSet<Uri> = uriSet

  protected fun getFirstUri(): Uri {
    if (uriSet.isEmpty()) {
      throw IllegalStateException("Uri list is empty")
    }
    return uriSet.first()
  }

}