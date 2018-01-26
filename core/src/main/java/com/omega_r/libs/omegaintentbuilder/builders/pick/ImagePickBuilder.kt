/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * ImagePickerBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 25, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.content.Context
import android.content.Intent
import com.omega_r.libs.omegaintentbuilder.types.ImageTypes
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes

/**
 * ImagePickBuilder is a helper for creating pick image intent
 */
class ImagePickBuilder(context: Context): BasePickBuilder(context) {

  init {
    super.mimeType = ImageTypes.IMAGE.mimeType
  }

  /**
   * Set image mime type
   *
   * @param imageType ImageTypes
   * @return This ImagePickBuilder for method chaining
   */
  fun imageType(imageType: ImageTypes): ImagePickBuilder {
    super.mimeType = imageType.mimeType
    return this
  }

}