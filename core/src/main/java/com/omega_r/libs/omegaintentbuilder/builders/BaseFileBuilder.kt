package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider
import java.io.File
import java.io.FileOutputStream

abstract class BaseFileBuilder(private val context: Context): BaseBuilder(context) {

  internal var localFilesDir: File

  companion object {
    private const val FILE_DIR = "intent_files" // this value from xml/file_paths.xml
    private const val DEFAULT_IMAGE_FILE_NAME = "omegaOutput.jpg"
  }

  init {
    localFilesDir = File(context.cacheDir, FILE_DIR)
    localFilesDir.mkdirs()
  }

  protected fun toUri(bitmap: Bitmap): Uri {
    val file = File(localFilesDir, DEFAULT_IMAGE_FILE_NAME)
    val fileOutputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
    fileOutputStream.close()

    return FileProvider.getLocalFileUri(context, file)
  }

}