/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * CropImageIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 11, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes
import java.io.File
import android.graphics.Bitmap
import com.omega_r.libs.omegaintentbuilder.builders.share.DownloadBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.Download
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider.getLocalFileUri
import com.omega_r.libs.omegaintentbuilder.utils.ExtensionUtils.Companion.isNullOrLessZero
import com.omega_r.libs.omegaintentbuilder.utils.UriUtils
import com.omega_r.libs.omegaintentbuilder.utils.UriUtils.Companion.bitmapToUri
import java.io.FileOutputStream

/**
 * CropImageIntentBuilder builder for creating crop image intent
 */
class CropImageIntentBuilder(private val context: Context): BaseBuilder(context), Download<CropImageIntentBuilder> {

  private var outputX: Int? = null
  private var outputY: Int? = null
  private var aspectX: Int = 1
  private var aspectY: Int = 1
  private var scale: Boolean = true
  private var returnData: Boolean = true
  private var fileUri: Uri? = null
  private val downloadBuilder = DownloadBuilder(context, this)

  /**
   * Set Output image width
   *
   * @param outputX Int
   * @return This CropImageIntentBuilder for method chaining
   */
  fun outputX(outputX: Int): CropImageIntentBuilder {
    if (outputX.isNullOrLessZero()) {
      throw IllegalStateException("outputX can't be less than zero")
    }
    this.outputX = outputX
    return this
  }

  /**
   * Set Output image height
   *
   * @param outputY Int
   * @return This CropImageIntentBuilder for method chaining
   */
  fun outputY(outputY: Int): CropImageIntentBuilder {
    if (outputY.isNullOrLessZero()) {
      throw IllegalStateException("outputY can't be less than zero")
    }
    this.outputY = outputY
    return this
  }

  /**
   * Crop frame aspect X
   *
   * @param aspectX Int
   * @return This CropImageIntentBuilder for method chaining
   */
  fun aspectX(aspectX: Int): CropImageIntentBuilder {
    if (aspectX.isNullOrLessZero()) {
      throw IllegalStateException("aspectX can't be less than zero")
    }
    this.aspectX = aspectX
    return this
  }

  /**
   * Crop frame aspect Y
   *
   * @param aspectY Int
   * @return This CropImageIntentBuilder for method chaining
   */
  fun aspectY(aspectY: Int): CropImageIntentBuilder {
    if (aspectY.isNullOrLessZero()) {
      throw IllegalStateException("aspectY can't be less than zero")
    }
    this.aspectY = aspectY
    return this
  }

  /**
   * Scale or not cropped image if output image and cropImage frame sizes differs
   *
   * @param scale Boolean
   * @return This CropImageIntentBuilder for method chaining
   */
  fun scale(scale: Boolean): CropImageIntentBuilder {
    this.scale = scale
    return this
  }

  /**
   * @param outputX Output image width
   * @param outputY Output image height
   * @param aspectX Crop frame aspect X
   * @param aspectY Crop frame aspect Y
   * @param scale   Scale or not cropped image if output image and cropImage frame sizes differs
   * @return This CropImageIntentBuilder for method chaining
   */
  @JvmOverloads
  fun property(outputX: Int, outputY: Int, aspectX: Int = 1, aspectY: Int = 1, scale: Boolean = true): CropImageIntentBuilder {
    outputX(outputX)
    outputY(outputY)
    aspectX(aspectX)
    aspectY(aspectY)
    scale(scale)

    return this
  }

  /**
   * Image that will be used for cropping. This image is not changed during the cropImage
   *
   * @param image File
   * @return This CropImageIntentBuilder for method chaining
   */
  fun file(image: File): CropImageIntentBuilder {
    fileUri = Uri.fromFile(image)
    return this
  }

  /**
   * Bitmap that will be used for cropping.
   *
   * @param bitmap Bitmap
   * @return This CropImageIntentBuilder for method chaining
   */
  fun bitmap(bitmap: Bitmap): CropImageIntentBuilder {
    fileUri = bitmapToUri(context, localFilesDirectory(), bitmap)

    return this
  }

  /**
   * Set returnData onActivityResult.
   *
   * @param returnData Boolean
   * @return This CropImageIntentBuilder for method chaining
   */
  fun returnData(returnData: Boolean): CropImageIntentBuilder {
    this.returnData = returnData
    return this
  }

  /**
   * File uri that will be used for cropping.
   *
   * @param uri Uri
   * @return This CropImageIntentBuilder for method chaining
   */
  fun fileUri(uri: Uri): CropImageIntentBuilder {
    fileUri = uri
    return this
  }

  /**
   * Add a stream URI to the data that should be croped.
   *
   * @param streamUriList URI of the stream to share
   * @return This CropImageIntentBuilder for method chaining
   */
  override fun stream(streamUriList: List<Uri>): CropImageIntentBuilder {
    if (!streamUriList.isEmpty()) {
      fileUri = streamUriList[0]
    }
    return this
  }

  /**
   * Add a String url address for downloading.
   *
   * @param urlAddress String address for downloading and share
   * @param mimeType MimeType
   * @return DownloadBuilder for method chaining
   */
  @JvmOverloads
  fun fileUrlWithMimeType(urlAddress: String,  mimeType: String? = null): DownloadBuilder<CropImageIntentBuilder> {
    return downloadBuilder.fileUrlWithMimeType(urlAddress, mimeType)
  }

  override fun createIntent(): Intent {
    if (outputX.isNullOrLessZero()) {
      throw IllegalStateException("You can't call createIntent before outputX")
    }
    if (outputY.isNullOrLessZero()) {
      throw IllegalStateException("You can't call createIntent before outputY")
    }

    val intent = Intent("com.android.camera.action.CROP")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.type = MimeTypes.IMAGE
    intent.putExtra("outputX", outputX)
    intent.putExtra("outputY", outputY)
    intent.putExtra("aspectX", aspectX)
    intent.putExtra("aspectY", aspectY)
    intent.putExtra("scale", scale)
    intent.putExtra("return-data", returnData)

    if (fileUri == null) {
      throw IllegalStateException("You can't call createIntent with empty image")
    } else {
      intent.data = fileUri
    }

    return intent
  }

}