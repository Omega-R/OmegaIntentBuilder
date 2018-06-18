package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.File

class OpenFileBuilder(private val context: Context): BaseActivityBuilder(context) {

    private var uri: Uri? = null
    private var mimeType: String? = null

    fun file(file: File, mimeType: String? = null): OpenFileBuilder {
        uri = Uri.fromFile(file)

        if (mimeType == null) {
            this.mimeType = uri?.getMimeType()
        } else {
            this.mimeType = mimeType
        }

        return this
    }

    fun fileUri(uri: Uri, mimeType: String? = null): OpenFileBuilder {
        this.uri = uri

        if (mimeType == null) {
            this.mimeType = uri.getMimeType()
        } else {
            this.mimeType = mimeType
        }

        return this
    }

    override fun createIntent(): Intent {
        val intent = Intent(Intent.ACTION_VIEW)

        if (uri == null) return intent

        if (mimeType == null) {
            intent.data = uri
        } else {
            intent.setDataAndType(uri, mimeType)
        }

        return intent
    }

}

fun Uri.getMimeType(): String? {
    val extension = MimeTypeMap.getFileExtensionFromUrl(this.toString())
    extension?.let {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(it)
    }
    return null
}