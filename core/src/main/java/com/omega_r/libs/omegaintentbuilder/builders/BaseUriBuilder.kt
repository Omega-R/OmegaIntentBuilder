/*
 * Copyright (c) 2017 Omega-r
 *
 * BaseUriBuilder
 * BaseUriBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 22, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.share.DownloadBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.Download
import com.omega_r.libs.omegaintentbuilder.models.RemoteFileInfo
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider
import com.omega_r.libs.omegatypes.image.Image
import java.io.File
import java.io.FileOutputStream

abstract class BaseUriBuilder() : BaseActivityBuilder(), Download<BaseUriBuilder> {

    private val uriSet: MutableSet<Uri> = mutableSetOf()
    private val fileSet: MutableSet<File> = mutableSetOf()

    private val downloadBuilder by lazy { DownloadBuilder(this) }
    private var bitmapIndex = 0
    private var localFilesDir: File? = null

    companion object {
        private const val FILE_DIR = "intent_files" // this value from xml/file_paths.xml
        private const val DEFAULT_IMAGE_FILE_NAME = "File"
        private const val DEFAULT_IMAGE_FILE_TYPE = ".jpg";
    }


    internal fun getLocalFilesDir(context: Context): File {
        if (localFilesDir == null) {
            localFilesDir = File(context.cacheDir, FILE_DIR)
            localFilesDir!!.mkdirs()
        }
        return localFilesDir!!
    }

    /**
     * @param uri Resource Identifier
     * @return BaseUriBuilder for method chaining
     */
    fun uri(vararg uri: Uri): BaseUriBuilder {
        uriSet.addAll(uri)
        return this
    }

    /**
     * @param uriCollection  Collections of resource identifiers
     * @return BaseUriBuilder for method chaining
     */
    override fun uri(uriCollection: Collection<Uri>): BaseUriBuilder {
        uriSet.addAll(uriCollection)
        return this
    }

    /**
     * @param urlAddresses Url addresses for downloading
     * @return DownloadBuilder for method chaining
     */
    fun filesUrls(vararg urlAddresses: String): DownloadBuilder<BaseUriBuilder> {
        return downloadBuilder.filesUrls(*urlAddresses)
    }

    /**
     * @param collection Collection<String> addresses for downloading
     * @return DownloadBuilder for method chaining
     */
    fun filesUrls(collection: Collection<String>): DownloadBuilder<BaseUriBuilder> {
        return downloadBuilder.filesUrls(collection)
    }

    /**
     * Add a String url address for downloading.
     *
     * @param urlAddress String address for downloading and share
     * @param mimeType MimeType
     * @return DownloadBuilder for method chaining
     */
    @JvmOverloads
    fun fileUrlWithMimeType(urlAddress: String, mimeType: String? = null): DownloadBuilder<BaseUriBuilder> {
        return downloadBuilder.fileUrlWithMimeType(urlAddress, mimeType)
    }

    /**
     * Add a String url address for downloading.
     *
     * @param urlAddress String address for downloading and share
     * @param name String - Your own file name with type ("example.mp3")
     * @return DownloadBuilder for method chaining
     */
    fun fileUrlWithName(urlAddress: String, name: String): DownloadBuilder<BaseUriBuilder> {
        return downloadBuilder.fileUrlWithName(urlAddress, name)
    }

    fun images(vararg image: Image): DownloadBuilder<BaseUriBuilder> {
        return downloadBuilder.images(image)
    }

    @JvmOverloads
    fun image(image: Image, name: String? = null, mimeType: String? = null): DownloadBuilder<BaseUriBuilder> {
        return downloadBuilder.image(image, name, mimeType)
    }

    /**
     * @param file File
     * @return BaseUriBuilder for method chaining
     */
    fun file(vararg file: File): BaseUriBuilder {
        fileSet.addAll(file)
        return this
    }

    /**
     * @param collection Collection<String>
     * or
     * @param collection Collection<File>
     * @return BaseUriBuilder for method chaining
     */
    fun <T> file(collection: Collection<T>): BaseUriBuilder {
        if (collection.all { it is String }) {
            collection.forEach { fileSet.add(File(it as String)) }
        } else if (collection.all { it is File }) {
            collection.forEach { fileSet.add(it as File) }
        } else {
            throw IllegalStateException("Collection type should be String or File")
        }
        return this
    }

    /**
     * @param file String
     * @return BaseUriBuilder for method chaining
     */
    fun file(vararg file: String): BaseUriBuilder {
        file.forEach { fileSet.add(File(it)) }
        return this
    }

    private fun toUri(context: Context, file: File): Uri {
        val cacheFile = File(getLocalFilesDir(context), file.name)
        file.copyTo(cacheFile, overwrite = true)
        return FileProvider.getLocalFileUri(context, cacheFile)
    }

    /**
     * @param bitmaps Bitmap
     * @return BaseUriBuilder for method chaining
     */
    fun bitmap(vararg bitmaps: Bitmap): BaseUriBuilder {
        bitmaps.forEach { bitmap ->
            fileSet.add(bitmap.toFile(bitmapIndex))
            bitmapIndex++
        }
        return this
    }

    private fun Bitmap.toFile(fileIndex: Int): File {
        return File(localFilesDir, DEFAULT_IMAGE_FILE_NAME + fileIndex + DEFAULT_IMAGE_FILE_TYPE).apply {
            val fileOutputStream = FileOutputStream(this)
            compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()
        }
    }

    private fun Bitmap.toUri(context: Context, fileIndex: Int): Uri {
        return FileProvider.getLocalFileUri(context, toFile(fileIndex))
    }

    protected fun getFileOrUriSetSize(): Int = uriSet.size + fileSet.size

    protected fun getFirstUri(context: Context): Uri {
        if (uriSet.isEmpty()) {
            if (fileSet.isEmpty()) {
                throw IllegalStateException("Uri list is empty")
            }
            return toUri(context, fileSet.first())
        }
        return uriSet.first()
    }

    protected fun getUriSet(context: Context): Set<Uri> {
        val uriSet = mutableSetOf<Uri>()
        uriSet.addAll(this.uriSet)
        uriSet.addAll(fileSet.map { toUri(context, it) })
        return uriSet
    }

}