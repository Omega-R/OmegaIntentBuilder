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

import com.omega_r.libs.omegaintentbuilder.types.VideoTypes

class VideoPickBuilder : BasePickBuilder() {

    init {
        super.mimeType = VideoTypes.VIDEO.mimeType
    }

    /**
     * Set video mime type
     *
     * @param videoType VideoTypes
     * @return This VideoPickBuilder for method chaining
     */
    fun videoType(videoType: VideoTypes): VideoPickBuilder {
        super.mimeType = videoType.mimeType
        return this
    }

}