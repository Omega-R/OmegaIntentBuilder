package com.omega_r.libs.omegaintentbuilder.builders.pick

object PickBuilder {

    /**
     * @return FilePickerBuilder for creating intent to pick files
     */
    @JvmStatic
    fun file() = FilePickerBuilder()

    /**
     * @return ImagePickBuilder for creating intent to pick photo from gallery
     */
    @JvmStatic
    fun image() = ImagePickBuilder()

    /**
     * @return AudioPickBuilder for creating intent to pick audio
     */
    @JvmStatic
    fun audio() = AudioPickBuilder()

    /**
     * @return VideoPickBuilder for creating intent to pick video
     */
    @JvmStatic
    fun video() = VideoPickBuilder()

    /**
     * @return ContactPickBuilder for creating intent to pick contact (doesn't support multiplay)
     */
    @JvmStatic
    fun contact() = ContactPickBuilder()

}