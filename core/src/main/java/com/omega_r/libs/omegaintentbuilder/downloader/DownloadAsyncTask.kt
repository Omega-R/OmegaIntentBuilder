package com.omega_r.libs.omegaintentbuilder.downloader

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import androidx.annotation.NonNull
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder
import com.omega_r.libs.omegaintentbuilder.models.RemoteFileInfo
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider.getLocalFileUri
import java.io.*

internal class DownloadAsyncTask<T>(
        private val context: Context,
        private val intentBuilder: T,
        private val localDirFile: File,
        private val downloadCallback: DownloadCallback
) : AsyncTask<Set<RemoteFileInfo>, Void, List<Uri>>() where T : BaseUriBuilder {

    companion object {
        private val TAG = DownloadAsyncTask::class.java.simpleName
        private const val EOF = -1
        private const val BUFFER_SIZE = 8192
    }

    override fun doInBackground(vararg params: Set<RemoteFileInfo>): List<Uri> {
        val fileSet: MutableSet<File> = mutableSetOf()
        val fileInfoSet: MutableSet<RemoteFileInfo> = mutableSetOf()
        params.forEach { fileInfoSet.addAll(it) }

        fileInfoSet.forEachIndexed { index: Int, remoteFileInfo: RemoteFileInfo ->
            try {
                downloadFile(remoteFileInfo, index)?.let { fileSet.add(it) }
            } catch (exc: IOException) {
                Log.e(TAG, exc.toString())
            }
        }

        val listUri = mutableListOf<Uri>();
        fileSet.forEach { it -> listUri.add(getLocalFileUri(context, it)) }
        return listUri
    }


    override fun onPostExecute(result: List<Uri>) {
        super.onPostExecute(result)
        if (result.isEmpty()) {
            downloadCallback.onDownloaded(false, intentBuilder.createIntentHandler(context))
        } else {
            intentBuilder.uri(result)
            downloadCallback.onDownloaded(true, intentBuilder.createIntentHandler(context))
        }
    }

    @Throws(IOException::class)
    private fun downloadFile(@NonNull fileInfo: RemoteFileInfo, index: Int): File? {
        return fileInfo.openStream(context) { inputStream ->
            File(localDirFile, fileInfo.getFileName(index)).also {
                FileOutputStream(it)
                        .copy(inputStream)
                        .flushAndClose()
            }
        }
    }

    private fun OutputStream.copy(inputStream: InputStream) = also {
        val byteArray = ByteArray(BUFFER_SIZE)
        while (true) {
            val len = inputStream.read(byteArray)
            if (len == -1)
                break
            write(byteArray, 0, len)
        }

    }

    private fun OutputStream.flushAndClose() {
        flush()
        close()
    }

}