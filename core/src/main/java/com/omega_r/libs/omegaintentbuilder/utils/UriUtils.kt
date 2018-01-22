package com.omega_r.libs.omegaintentbuilder.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.BaseBuilder
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider
import java.io.File
import java.io.FileOutputStream

class UriUtils {

  companion object {

    fun bitmapToUri(context: Context, fileDirectory : File, bitmap: Bitmap): Uri {
      val file = File(fileDirectory, BaseBuilder.DEFAULT_IMAGE_FILE_NAME)
      val fileOutputStream = FileOutputStream(file)
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
      fileOutputStream.close()

      return FileProvider.getLocalFileUri(context, file)
    }

  }

}