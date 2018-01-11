package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes
import java.io.File
import android.content.ComponentName
import android.graphics.Bitmap
import com.omega_r.libs.omegaintentbuilder.BuildConfig
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadAsyncTask
import com.omega_r.libs.omegaintentbuilder.providers.FileProvider
import com.omega_r.libs.omegaintentbuilder.utils.ExtensionUtils.Companion.isNullOrLessZero
import java.io.FileOutputStream

private const val DEFAULT_FILE_NAME = "omegaOutput.jpg"

class CropImageIntentBuilder(context: Context): BaseBuilder(context) {

  private var outputX: Int? = null
  private var outputY: Int? = null
  private var aspectX: Int = 1
  private var aspectY: Int = 1
  private var scale: Boolean = false

  private var imageFile: File? = null
  private var bitmap: Bitmap? = null
  private var fileUri: Uri? = null

  private val localDirFile: File

  init {
    localDirFile = File(context.cacheDir, DownloadAsyncTask.FILE_DIR)
    localDirFile.mkdirs()
  }

  fun outputX(outputX: Int): CropImageIntentBuilder {
    if (outputX.isNullOrLessZero()) {
      throw IllegalStateException("outputX can't be less than zero")
    }
    this.outputX = outputX
    return this
  }

  fun outputY(outputY: Int): CropImageIntentBuilder {
    if (outputY.isNullOrLessZero()) {
      throw IllegalStateException("outputY can't be less than zero")
    }
    this.outputY = outputY
    return this
  }

  fun aspectX(aspectX: Int): CropImageIntentBuilder {
    if (aspectX.isNullOrLessZero()) {
      throw IllegalStateException("aspectX can't be less than zero")
    }
    this.aspectX = aspectX
    return this
  }

  fun aspectY(aspectY: Int): CropImageIntentBuilder {
    if (aspectY.isNullOrLessZero()) {
      throw IllegalStateException("aspectY can't be less than zero")
    }
    this.aspectY = aspectY
    return this
  }

  fun scale(scale: Boolean): CropImageIntentBuilder {
    this.scale = scale
    return this
  }

  @JvmOverloads
  fun property(outputX: Int, outputY: Int, aspectX: Int, aspectY: Int, scale: Boolean = false): CropImageIntentBuilder {
    outputX(outputX)
    outputY(outputY)
    aspectX(aspectX)
    aspectY(aspectY)
    scale(scale)

    return this
  }

  fun file(image: File): CropImageIntentBuilder {
    imageFile = image
    return this
  }

  fun bitmap(bitmap: Bitmap): CropImageIntentBuilder {
    this.bitmap = bitmap
    return this
  }

  fun fileUri(uri: Uri): CropImageIntentBuilder {
    fileUri = uri
    return this
  }

  override fun createIntent(): Intent {
    if (outputX.isNullOrLessZero()) {
      throw IllegalStateException("You can't call createIntent before outputX")
    }
    if (outputY.isNullOrLessZero()) {
      throw IllegalStateException("You can't call createIntent before outputY")
    }
    if (aspectX.isNullOrLessZero()) {
      throw IllegalStateException("You can't call createIntent before aspectX")
    }
    if (aspectY.isNullOrLessZero()) {
      throw IllegalStateException("You can't call createIntent before aspectY")
    }

    val intent = Intent("com.android.camera.action.CROP")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.type = MimeTypes.IMAGE
    intent.putExtra("outputX", outputX)
    intent.putExtra("outputY", outputY)
    intent.putExtra("aspectX", aspectX)
    intent.putExtra("aspectY", aspectY)
    intent.putExtra("scale", scale)
    intent.putExtra("return-data", true)

    if (imageFile != null) {
      intent.data = Uri.fromFile(imageFile)
    } else if (bitmap != null) {
      val file = File(localDirFile, DEFAULT_FILE_NAME)
      val fileOutputStream = FileOutputStream(file)
      bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
      fileOutputStream.close()

    }

     // FileProvider.getUriForFile(context, context.packageName + "." + BuildConfig.SUFFIX_AUTHORITY, image)


    return intent
  }

}