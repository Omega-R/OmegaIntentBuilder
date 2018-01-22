package com.omega_r.libs.omegaintentbuilder.downloader

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.support.annotation.NonNull
import android.util.Log
import com.omega_r.libs.omegaintentbuilder.builders.BaseFileBuilder
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

internal class DownloadAsyncTask<T>(private val context: Context,
                                    private val intentBuilder: T,
                                    private val downloadCallback: DownloadCallback) : AsyncTask<Map<String, String?>, Void, List<Uri>>() where T : BaseFileBuilder, T: Download<T> {

  companion object {
    private val TAG = DownloadAsyncTask::class.java.simpleName
    private const val BUFFER_SIZE = 8192
  }

  private val localDirFile: File

  init {
    localDirFile = intentBuilder.localFilesDir
  }

  override fun doInBackground(vararg maps: Map<String, String?>): List<Uri> {
    val fileSet: MutableSet<File> = mutableSetOf()
    val urlsMap: MutableMap<String, String?> = TreeMap(String.CASE_INSENSITIVE_ORDER)
    maps.forEach { it -> urlsMap.putAll(it) }

    for (address in urlsMap) {
      try {
        downloadFile(address.key, address.value)?.let { it -> fileSet.add(it) }
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
        intentBuilder.stream(result)
        downloadCallback.onDownloaded(true, intentBuilder.createIntentHandler())
      }
  }

  @Throws(IOException::class)
  private fun downloadFile(@NonNull urlAddress: String, mimType: String? = null): File? {
    val url = URL(urlAddress)
    val httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

    val responseCode = httpConnection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
      val inputStream: InputStream = httpConnection.inputStream;
      val file: File
      if (mimType.isNullOrEmpty()) {
        file = File(localDirFile, getFileName(urlAddress))
      } else {
        file = File(localDirFile, getFileName(urlAddress, mimType))
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