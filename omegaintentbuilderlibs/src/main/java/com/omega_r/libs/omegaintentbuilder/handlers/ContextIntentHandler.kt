/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * ContextIntentHandler.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder.handlers

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_CHOOSER
import android.content.Intent.EXTRA_INTENT
import android.content.Intent.EXTRA_TITLE
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * ContextIntentHandler is a helper for start intents
 */
open class ContextIntentHandler(private val context: Context, private val createdIntent: Intent) {

  companion object {
    private const val FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 0x00000040
    private const val FLAG_GRANT_PREFIX_URI_PERMISSION = 0x00000080
  }

  private var chooserTitle: String? = null

  /**
   * Set the title that will be used for the activity chooser for this share.
   *
   * @param title Title CharSequence
   * @return This ContextIntentHandler for method chaining
   */
  fun chooserTitle(chooserTitle: CharSequence): ContextIntentHandler {
    this.chooserTitle = chooserTitle.toString()
    return this
  }

  /**
   * Set the title that will be used for the activity chooser for this share.
   *
   * @param title Title String
   * @return This ContextIntentHandler for method chaining
   */
  fun chooserTitle(chooserTitle: String): ContextIntentHandler {
    this.chooserTitle = chooserTitle
    return this
  }

  /**
   * Set the title that will be used for the activity chooser for this share.
   *
   * @param title Title @StringRes Int
   * @return This ContextIntentHandler for method chaining
   */
  fun chooserTitle(chooserTitle: Int): ContextIntentHandler {
    this.chooserTitle = context.getText(chooserTitle).toString()
    return this
  }

  /**
   *
   * @return This Chooser title
   */
  protected fun getChooserTitle(): String? = chooserTitle

  /**
   * Create an Intent that will launch the standard Android activity chooser,
   * allowing the user to pick what activity/app on the system should handle
   * the share.
   *
   * @return A chooser Intent for the currently configured sharing action
   */
  protected fun createChooserIntent(): Intent {
    val chooserIntent = Intent(ACTION_CHOOSER)
    chooserIntent.putExtra(EXTRA_INTENT, createdIntent)
    chooserTitle?.let {
      chooserIntent.putExtra(EXTRA_TITLE, chooserTitle)
    }
    var permFlags = createdIntent.flags
    permFlags = permFlags and (FLAG_GRANT_READ_URI_PERMISSION
        or FLAG_GRANT_WRITE_URI_PERMISSION or FLAG_GRANT_PERSISTABLE_URI_PERMISSION
        or FLAG_GRANT_PREFIX_URI_PERMISSION)
    if (permFlags != 0) {
      var clipData: ClipData? = createdIntent.clipData
      if (clipData == null && createdIntent.data != null) {
        val item: ClipData.Item = ClipData.Item(createdIntent.data)
        var mimeTypes: Array<String> = arrayOf()
        createdIntent.type?.let {
          mimeTypes = arrayOf(createdIntent.type)
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

  /**
   * Same as {@link #android.content.Context.startActivity(Intent, Bundle)} with no options
   * specified.
   *
   * @throws ActivityNotFoundException
   */
  fun startActivity() {
    context.startActivity(getIntent())
  }

  fun tryStartActivity(): Boolean {
    try {
      startActivity()
      return true
    } catch (exc: ActivityNotFoundException) {
      return false
    }
  }

  fun tryStartActivity(errorToastMessage: String): Boolean {
    val attempt = tryStartActivity()
    if (!attempt) {
      Toast.makeText(context, errorToastMessage, Toast.LENGTH_SHORT).show()
    }
    return attempt
  }

  fun tryStartActivity(@StringRes errorToastRes: Int): Boolean {
    return tryStartActivity(context.getString(errorToastRes))
  }

  fun getIntent(): Intent {
    if (chooserTitle.isNullOrEmpty()) {
      return createdIntent
    } else {
      return createChooserIntent()
    }
  }

}