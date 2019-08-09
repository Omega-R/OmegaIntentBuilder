/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * DownloadBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 14, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders.share

import android.content.Context
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.Download
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadAsyncTask
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback
import com.omega_r.libs.omegaintentbuilder.models.RemoteFileInfo
import com.omega_r.libs.omegatypes.Image

/**
 * DownloadBuilder is a helper for download files from internet and add it to createdIntent
 * to share content.
 */
class DownloadBuilder<T>(private val intentBuilder: T) where T : BaseUriBuilder, T : Download<BaseUriBuilder> {

    private val fileInfoSet = mutableSetOf<RemoteFileInfo>()


    /**
     * Add a array of url addresses to download.
     *
     * @param urlAddresses Array of String addresses to download and share
     * @return This DownloadBuilder for method chaining
     */
    fun filesUrls(vararg urlAddresses: String) = apply {
        fileInfoSet += urlAddresses.map { RemoteFileInfo(it) }
    }

    /**
     * Add a String url address for downloading.
     *
     * @param urlAddress String address for downloading and share
     * @param mimeType MimeType
     * @return This DownloadBuilder for method chaining
     */
    @JvmOverloads
    fun fileUrlWithMimeType(urlAddress: String, mimeType: String? = null): DownloadBuilder<T> {
        fileInfoSet += RemoteFileInfo(urlAddress, mimeType)
        return this
    }

    fun images(images: Array<out Image>) = apply {
        fileInfoSet += images.map { RemoteFileInfo(image = it) }
    }

    fun images(image: Image) = apply {
        fileInfoSet += RemoteFileInfo(image = image)
    }

    fun image(image: Image, name: String? = null, mimeType: String? = null) = apply {
        fileInfoSet += RemoteFileInfo(image = image, mimeType = mimeType, originalName = name)
    }

    /**
     * Add a String url address for downloading.
     *
     * @param urlAddress String address for downloading and share
     * @param name String - Your own file name with type ("example.mp3")
     * @return This DownloadBuilder for method chaining
     */
    fun fileUrlWithName(urlAddress: String, name: String) = apply {
        fileInfoSet += RemoteFileInfo(urlAddress, null, name)
    }

    /**
     * Add a collection of url addresses to download.
     *
     * @param collection Collection of String addresses to download and share
     * @return This DownloadBuilder for method chaining
     */
    fun filesUrls(collection: Collection<String>) = apply {
        fileInfoSet += collection.map { RemoteFileInfo(it) }
    }

    /**
     * Download files from urls.
     *
     * @param callback DownloadCallback to control loading status
     * @return This ContextIntentHandler for method chaining
     */
    fun download(context: Context, callback: DownloadCallback) {
        if (fileInfoSet.isEmpty()) {
            callback.onDownloaded(true, intentBuilder.createIntentHandler(context))
            return
        }

        DownloadAsyncTask(context, intentBuilder, intentBuilder.getLocalFilesDir(context), callback)
                .execute(fileInfoSet)
    }


}