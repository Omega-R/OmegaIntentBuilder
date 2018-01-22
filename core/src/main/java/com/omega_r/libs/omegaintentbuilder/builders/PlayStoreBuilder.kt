package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.annotation.StringRes

class PlayStoreBuilder(private val context: Context): BaseBuilder(context) {

  private var packageName: String? = null

  fun packageName(packageName: String): PlayStoreBuilder {
    this.packageName = packageName
    return this
  }

  fun packageName(@StringRes packageNameRes: Int): PlayStoreBuilder {
    packageName = context.getString(packageNameRes)
    return this
  }

  override fun createIntent(): Intent {
    if (packageName.isNullOrBlank()) {
      packageName = context.packageName
    }

    return Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName))
  }

}