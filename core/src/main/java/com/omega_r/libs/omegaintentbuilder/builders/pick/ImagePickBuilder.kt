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
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes

/**
 * ImagePickBuilder is a helper for creating pick image intent
 */
class ImagePickBuilder(context: Context): BasePickBuilder(context) {

  override fun createIntent(): Intent {
    val intent = super.createIntent()
    intent.type = MimeTypes.IMAGE

    return intent
  }

}