/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * BasePickBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 25, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.omega_r.libs.omegaintentbuilder.builders.BaseActivityBuilder
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes

/**
 * BasePickBuilder is a helper for creating pick file intent
 */
open class BasePickBuilder(private val defaultMimeType: String = MimeTypes.ANY) : BaseActivityBuilder() {

    private var allowMultiply = false
    private val mimeTypes = mutableSetOf(defaultMimeType)

    /**
     * Extra used to indicate that an intent can allow the user to select and
     * return multiple items. This is a boolean extra; the default is false. If
     * true, an implementation is allowed to present the user with a UI where
     * they can pick multiple items that are all returned to the caller.
     *
     * @see #ACTION_GET_CONTENT
     */
    @JvmOverloads
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun multiply(allowMultiply: Boolean = true): BasePickBuilder {
        this.allowMultiply = allowMultiply
        return this
    }

    open fun mimeType(mimeType: String): BasePickBuilder {
        mimeTypes.clear()
        mimeTypes += mimeType
        return this
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    open fun mimeTypes(vararg mimeType: String): BasePickBuilder {
        mimeTypes.clear()
        mimeTypes.addAll(mimeType)
        return this
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    open fun mimeTypes(mimeTypes: Collection<String>): BasePickBuilder {
        this.mimeTypes.clear()
        this.mimeTypes.addAll(mimeTypes)
        return this
    }

    override fun createIntent(context: Context): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT || mimeTypes.size <= 1) {
            intent.type = mimeTypes.firstOrNull() ?: defaultMimeType
        } else {
            intent.type = defaultMimeType
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes.toTypedArray())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, allowMultiply)
        }

        return intent
    }


}