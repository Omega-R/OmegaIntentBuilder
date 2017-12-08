/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * ContextIntentHandler.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder.handlers

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_CHOOSER
import android.content.Intent.EXTRA_INTENT
import android.content.Intent.EXTRA_TITLE
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION

/**
 * ContextIntentHandler is a helper for start intents
 */
open class ContextIntentHandler(private val context: Context, private val intent: Intent) {

  companion object {
    private const val FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 0x00000040
    private const val FLAG_GRANT_PREFIX_URI_PERMISSION = 0x00000080
  }

  private var chooserTitle: String? = null

  fun setChooserTitle(chooserTitle: CharSequence): ContextIntentHandler {
    this.chooserTitle = chooserTitle.toString()
    return this
  }

  fun setChooserTitle(chooserTitle: String): ContextIntentHandler {
    this.chooserTitle = chooserTitle
    return this
  }

  fun setChooserTitle(chooserTitle: Int): ContextIntentHandler {
    this.chooserTitle = context.getText(chooserTitle).toString()
    return this
  }

  fun getChooserTitle(): String? = chooserTitle

  fun createChooserIntent(): Intent {
    val chooserIntent = Intent(ACTION_CHOOSER)
    chooserIntent.putExtra(EXTRA_INTENT, intent)
    chooserTitle?.let {
      chooserIntent.putExtra(EXTRA_TITLE, chooserTitle)
    }
    var permFlags = intent.flags
    permFlags = permFlags and (FLAG_GRANT_READ_URI_PERMISSION
        or FLAG_GRANT_WRITE_URI_PERMISSION or FLAG_GRANT_PERSISTABLE_URI_PERMISSION
        or FLAG_GRANT_PREFIX_URI_PERMISSION)
    if (permFlags != 0) {
      var clipData: ClipData? = intent.clipData
      if (clipData == null && intent.data != null) {
        val item: ClipData.Item = ClipData.Item(intent.data)
        var mimeTypes: Array<String> = arrayOf()
        intent.type?.let {
          mimeTypes = arrayOf(intent.type)
        }
        clipData = ClipData(null, mimeTypes, item)
      }
      clipData?.let {
        chooserIntent.clipData = clipData
        chooserIntent.addFlags(permFlags)
      }
    }
    return chooserIntent
  }

  fun startActivity() {
    if (chooserTitle.isNullOrEmpty()) {
      context.startActivity(intent)
    } else {
      context.startActivity(createChooserIntent())
    }
  }

}