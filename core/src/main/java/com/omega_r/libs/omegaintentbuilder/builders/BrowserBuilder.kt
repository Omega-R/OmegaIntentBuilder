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
import com.omega_r.libs.omegatypes.Text
import com.omega_r.libs.omegatypes.toText

/**
 * BrowserBuilder class for starting web browser
 */
class BrowserBuilder (private val uri: Text) : BaseActivityBuilder() {

    constructor(urlAddress: String) : this(urlAddress.toText())

    constructor(uri: Uri) : this(uri.toString())

    override fun createIntent(context: Context) =  Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(uri.getString(context))
    }

}