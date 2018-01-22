/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BaseBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import java.io.File

abstract class BaseBuilder(private val context: Context): IntentBuilder {

  private var localFilesDirectory: File? = null

  companion object {
    const val FILE_DIR = "intent_files"
    const val DEFAULT_IMAGE_FILE_NAME = "omegaOutput.jpg"
  }

  override fun createIntentHandler(): ContextIntentHandler {
    return ContextIntentHandler(context, createIntent())
  }

  override fun createIntentHandler(activity: Activity): ActivityIntentHandler {
    return ActivityIntentHandler(activity, createIntent())
  }

  internal fun localFilesDirectory(): File {
    if (localFilesDirectory == null) {
      localFilesDirectory = File(context.cacheDir, FILE_DIR)
      localFilesDirectory!!.mkdirs()
    }
    return localFilesDirectory!!
  }

}