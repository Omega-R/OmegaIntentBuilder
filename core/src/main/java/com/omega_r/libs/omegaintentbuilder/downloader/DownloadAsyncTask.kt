package com.omega_r.libs.omegaintentbuilder.downloader

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.support.annotation.NonNull
import android.util.Log
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder
import com.omega_r.libs.omegaintentbuilder.models.FileInfo
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider.getFileName
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider.getLocalFileUri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

internal class DownloadAsyncTask<T>(
        private val context: Context,
        private val intentBuilder: T,
        private val localDirFile: File,
        private val downloadCallback: DownloadCallback
) : AsyncTask<Set<FileInfo>, Void, List<Uri>>() where T : BaseUriBuilder {

  companion object {
    private val TAG = DownloadAsyncTask::class.java.simpleName
    private const val BUFFER_SIZE = 8192
  }

  override fun doInBackground(vararg params: Set<FileInfo>): List<Uri> {
    val fileSet: MutableSet<File> = mutableSetOf()
    val fileInfoSet: MutableSet<FileInfo> = mutableSetOf()
    params.forEach { fileInfoSet.addAll(it) }

    fileInfoSet.forEach {
      try {
        downloadFile(it)?.let { fileSet.add(it) }
      } catch (exc: IOException)  {
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
        downloadCallback.onDownloaded(false, intentBuilder.createIntentHandler())
      } else {
        intentBuilder.uri(result)
        downloadCallback.onDownloaded(true, intentBuilder.createIntentHandler())
      }
  }

  @Throws(IOException::class)
  private fun downloadFile(@NonNull fileInfo: FileInfo): File? {
    val url = URL(fileInfo.urlAddress)
    val httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

    val responseCode = httpConnection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
      val inputStream: InputStream = httpConnection.inputStream;
      val file: File

      if (fileInfo.originalName.isNullOrEmpty()) {
        if (fileInfo.mimeType.isNullOrEmpty()) {
          file = File(localDirFile, getFileName(fileInfo.urlAddress))
        } else {
          file = File(localDirFile, getFileName(fileInfo.urlAddress, fileInfo.mimeType))
        }
      } else {
          file = File(localDirFile, fileInfo.originalName)
      }

      val fileOutputStream = FileOutputStream(file)
      var bytesRead = 0;
      val buffer = ByteArray(BUFFER_SIZE)

      do {
        fileOutputStream.write(buffer, 0, bytesRead)
        bytesRead = inputStream.read(buffer)
      } while (bytesRead > 0)

      fileOutputStream.close()
      inputStream.close()
      httpConnection.disconnect()

      return file
    } else {
      httpConnection.disconnect()
      Log.e(TAG, "No file to download. Server replied HTTP code: " + responseCode);
      return null
    }
  }

}