/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BrowserBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 19, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * BrowserBuilder class for starting web browser
 */
class BrowserBuilder: BaseBuilder {

  private val uri: Uri

  constructor(context: Context, urlAddress: String) : this(context, Uri.parse(urlAddress))

  constructor(context: Context, uri: Uri) : super(context) {
    this.uri = uri
  }

  override fun createIntent(): Intent {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = uri

    return intent
  }

}