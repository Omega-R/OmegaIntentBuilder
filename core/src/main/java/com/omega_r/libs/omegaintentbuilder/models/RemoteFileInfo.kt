package com.omega_r.libs.omegaintentbuilder.models

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.webkit.MimeTypeMap
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes
import com.omega_r.libs.omegatypes.image.Image
import com.omega_r.libs.omegatypes.image.getStream
import kotlinx.coroutines.runBlocking
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class RemoteFileInfo(
        val urlAddress: String? = null,
        val mimeType: String? = null,
        val originalName: String? = null,
        val image: Image? = null
) {

    fun getFileName(index: Int): String {
        return originalName
                ?: urlAddress?.run { FileProvider.getFileName(this, mimeType) }
                ?: "image${index + 1}." + getFileExtension()
    }

    private fun getFileExtension(): String {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType) ?: "jpg"
    }

    fun <R> openStream(context: Context, receiver: (InputStream) -> R): R? {
        return when {
            urlAddress != null -> {
                val url = URL(urlAddress)
                val httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                try {
                    val responseCode = httpConnection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        httpConnection.inputStream.use(receiver)
                    } else {
                        Log.e(RemoteFileInfo::class.java.simpleName, "No file to download. Server replied HTTP code: $responseCode");
                        null
                    }
                } finally {
                    httpConnection.disconnect()
                }
            }
            image != null -> {
                runBlocking { image.getStream(context, getCompressFormat(), 100).use(receiver) }
            }
            else -> null
        }
    }

    private fun getCompressFormat(): Bitmap.CompressFormat {
        return when (mimeType) {
            MimeTypes.IMAGE_PNG -> Bitmap.CompressFormat.PNG
            MimeTypes.IMAGE_JPEG -> Bitmap.CompressFormat.JPEG
            MimeTypes.IMAGE_WEBP -> Bitmap.CompressFormat.WEBP
            else -> Bitmap.CompressFormat.JPEG
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RemoteFileInfo

        if (urlAddress != other.urlAddress) return false
        if (image != other.image) return false

        return true
    }

    override fun hashCode(): Int {
        var result = urlAddress?.hashCode() ?: 0
        result = 31 * result + (image?.hashCode() ?: 0)
        return result
    }

}