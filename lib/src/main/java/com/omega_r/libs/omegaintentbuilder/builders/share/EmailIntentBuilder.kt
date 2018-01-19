/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * EmailIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders.share

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder

/**
 * EmailIntentBuilder is a helper for constructing {Intent#ACTION_SENDTO}
 */
class EmailIntentBuilder internal constructor(context: Context): BaseShareBuilder<EmailIntentBuilder>(context) {

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for email
   */
  override fun createIntent(): Intent {
    val intent = super.createIntent()
    intent.action = Intent.ACTION_SENDTO
    intent.data = Uri.parse("mailto:")

    return intent
  }

}