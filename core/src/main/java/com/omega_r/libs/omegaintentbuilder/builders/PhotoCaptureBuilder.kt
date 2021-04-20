/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * PhotoCaptureBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 10, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import java.io.File

/**
 * PhotoCaptureBuilder return intent for calling standard camera application for capturing an image
 */
class PhotoCaptureBuilder() : BaseActivityBuilder() {

    private var fileUri: Uri? = null

    /**
     * Set fileUri
     *
     * @param fileUri Uri. Full path to captured file
     * @return This PhotoCaptureBuilder for method chaining
     */
    fun file(fileUri: Uri): PhotoCaptureBuilder {
        this.fileUri = fileUri
        return this
    }

    /**
     * Set fileUri
     *
     * @param fileUri String. Full path to captured file
     * @return This PhotoCaptureBuilder for method chaining
     */
    fun file(fileUri: String): PhotoCaptureBuilder {
        this.fileUri = Uri.fromFile(File(fileUri))
        return this
    }

    override fun createIntent(context: Context) = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
        fileUri?.let { putExtra(MediaStore.EXTRA_OUTPUT, fileUri) }
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
    }

}