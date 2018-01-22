/*
 * Copyright (c) 2018 Omega-r
 *
 * UriShareBuilder
 * UriShareBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 22, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.share.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.ShareIntentBuilder

/**
 * UriShareBuilder it's helper for create other builder with file uri
 */
class UriShareBuilder(private val context: Context) {

  /**
   * @param fileUri Uri for cropping image
   * @return CropImageIntentBuilder for method chaining
   */
  fun cropImage(fileUri: Uri): CropImageIntentBuilder {
    return CropImageIntentBuilder(context).fileUri(fileUri)
  }

  /**
   * @param fileUri Uri for capturing image
   * @return PhotoCaptureBuilder for method chaining
   */
  fun photoCapture(fileUri: Uri): PhotoCaptureBuilder {
    return PhotoCaptureBuilder(context).file(fileUri)
  }

  /**
   * @param streamUri URI of the uri to share
   * @return ShareIntentBuilder for method chaining
   */
  fun share(vararg streamUri: Uri): ShareIntentBuilder {
    return ShareIntentBuilder(context).uri(*streamUri)
  }

  /**
   * @param streamUriSet Set<Uri> of the uri to share
   * @return ShareIntentBuilder for method chaining
   */
  fun share(streamUriSet: MutableSet<Uri>): ShareIntentBuilder {
    return ShareIntentBuilder(context).uri(streamUriSet)
  }

  /**
   * @param streamUriList List<Uri> of the uri to share
   * @return ShareIntentBuilder for method chaining
   */
  fun share(streamUriList: List<Uri>): ShareIntentBuilder {
    return ShareIntentBuilder(context).uri(streamUriList)
  }

  /**
   * @param streamUri URI of the uri to share
   * @return EmailIntentBuilder for method chaining
   */
  fun email(vararg streamUri: Uri): EmailIntentBuilder {
    return EmailIntentBuilder(context).uri(*streamUri)
  }

  /**
   * @param streamUriSet Set<Uri> of the uri to share
   * @return EmailIntentBuilder for method chaining
   */
  fun email(streamUriSet: MutableSet<Uri>): EmailIntentBuilder {
    return EmailIntentBuilder(context).uri(streamUriSet)
  }

  /**
   * @param streamUriList List<Uri> of the uri to share
   * @return EmailIntentBuilder for method chaining
   */
  fun email(streamUriList: List<Uri>): EmailIntentBuilder {
    return EmailIntentBuilder(context).uri(streamUriList)
  }


}