package com.omega_r.libs.omegaintentbuilder.models

class FileInfo(
        val urlAddress: String,
        val mimeType: String? = null,
        val originalName: String? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileInfo

        if (urlAddress != other.urlAddress) return false

        return true
    }

    override fun hashCode(): Int {
        return urlAddress.hashCode()
    }

}