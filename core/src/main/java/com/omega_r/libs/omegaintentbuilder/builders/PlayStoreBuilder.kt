package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes

class PlayStoreBuilder(private val context: Context): BaseActivityBuilder(context) {

  private var packageName: String? = null
  private var referrer: String? = null

  fun packageName(packageName: String): PlayStoreBuilder {
    this.packageName = packageName
    return this
  }

  fun packageName(@StringRes packageNameRes: Int): PlayStoreBuilder {
    packageName = context.getString(packageNameRes)
    return this
  }

  fun referrer(referrer: String): PlayStoreBuilder {
    this.referrer = referrer
    return this
  }

  fun referrer(@StringRes referrerRes: Int): PlayStoreBuilder {
    this.referrer = context.getString(referrerRes)
    return this
  }

  override fun createIntent(): Intent {
    if (packageName.isNullOrBlank()) {
      packageName = context.packageName
    }

    var referrerAppendix = ""
    if (!referrer.isNullOrBlank()) {
        referrerAppendix = "&referrer=$referrer"
    }

    return Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName$referrerAppendix"))
  }

}