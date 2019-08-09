package com.omega_r.libs.omegaintentbuilder.builders.pick

object PickBuilder {

    /**
     * @return FilePickerBuilder for creating intent to pick files
     */
    fun file() = FilePickerBuilder()

    /**
     * @return ImagePickBuilder for creating intent to pick photo from gallery
     */
    fun image() = ImagePickBuilder()

    /**
     * @return AudioPickBuilder for creating intent to pick audio
     */
    fun audio() = AudioPickBuilder()

    /**
     * @return VideoPickBuilder for creating intent to pick video
     */
    fun video() = VideoPickBuilder()

    /**
     * @return ContactPickBuilder for creating intent to pick contact (doesn't support multiplay)
     */
    fun contact() = ContactPickBuilder()

}