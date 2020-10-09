/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * VideoPickBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 26, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.os.Build
import androidx.annotation.RequiresApi
import com.omega_r.libs.omegaintentbuilder.types.VideoTypes

class VideoPickBuilder : BasePickBuilder(VideoTypes.VIDEO.mimeType) {

    /**
     * Set video mime type
     *
     * @param videoType VideoTypes
     * @return This VideoPickBuilder for method chaining
     */
    fun videoType(videoType: VideoTypes): VideoPickBuilder {
        mimeType(videoType.mimeType)
        return this
    }

    /**
     * Set video mime types
     *
     * @param videoType VideoTypes
     * @return This VideoPickBuilder for method chaining
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun videoTypes(vararg videoType: VideoTypes): VideoPickBuilder {
        mimeTypes(videoType.map { it.mimeType })
        return this
    }

}