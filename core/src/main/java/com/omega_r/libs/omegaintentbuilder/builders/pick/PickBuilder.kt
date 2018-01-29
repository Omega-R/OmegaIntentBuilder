package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.content.Context

class PickBuilder(private val context: Context) {

  /**
   * @return FilePickerBuilder for creating intent to pick files
   */
  fun file(): FilePickerBuilder {
    return FilePickerBuilder(context)
  }

  /**
   * @return ImagePickBuilder for creating intent to pick photo from gallery
   */
  fun image(): ImagePickBuilder {
    return ImagePickBuilder(context)
  }

  /**
   * @return AudioPickBuilder for creating intent to pick audio
   */
  fun audio(): AudioPickBuilder {
    return AudioPickBuilder(context)
  }

  /**
   * @return VideoPickBuilder for creating intent to pick video
   */
  fun video(): VideoPickBuilder {
    return VideoPickBuilder(context)
  }

  /**
   * @return ContactPickBuilder for creating intent to pick contact (doesn't support multiplay)
   */
  fun contact(): ContactPickBuilder {
    return ContactPickBuilder(context)
  }

}