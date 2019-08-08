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
class BrowserBuilder (private val uri: Uri) : BaseActivityBuilder() {

    constructor(urlAddress: String) : this(Uri.parse(urlAddress))

    override fun createIntent(context: Context) =  Intent(Intent.ACTION_VIEW).apply {
        data = uri
    }

}