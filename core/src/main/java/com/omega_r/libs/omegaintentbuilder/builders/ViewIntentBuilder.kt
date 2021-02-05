package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import java.lang.IllegalStateException

class ViewIntentBuilder: BaseUriBuilder() {

    private var mimeType: String? = null

    fun mimeType(mimeType: String): ViewIntentBuilder = also {
        this.mimeType = mimeType
    }

    override fun createIntent(context: Context): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        val uriSet = getUriSet(context)
        if (uriSet.size == 1) intent.setDataAndType(uriSet.first(), mimeType)
        else throw IllegalStateException("Multiple uri not supported")
        return intent
    }

}