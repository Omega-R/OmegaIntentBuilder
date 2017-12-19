/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BrowserBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * BrowserBuilder class for starting web browser
 */
class BrowserBuilder(private val context: Context): BaseBuilder(context) {

  private var uri: Uri? = null

  fun url(urlAddress: String): BrowserBuilder {
    uri = Uri.parse(urlAddress)
    return this
  }

  fun url(uri: Uri): BrowserBuilder {
    this.uri = uri
    return this
  }

  override fun createIntent(): Intent {
    if (uri == null) {
      throw RuntimeException("You can't call createIntent with empty url address")
    }
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = uri

    return intent
  }



}