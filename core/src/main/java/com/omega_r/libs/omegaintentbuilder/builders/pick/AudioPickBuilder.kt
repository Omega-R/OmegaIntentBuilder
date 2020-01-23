/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * AudioPickBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 26, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.os.Build
import androidx.annotation.RequiresApi
import com.omega_r.libs.omegaintentbuilder.types.AudioTypes

class AudioPickBuilder : BasePickBuilder(AudioTypes.AUDIO.mimeType) {

    /**
     * Set audio mime type
     *
     * @param audioType AudioTypes
     * @return This AudioPickBuilder for method chaining
     */
    fun audioType(audioType: AudioTypes): AudioPickBuilder {
        mimeType(audioType.mimeType)
        return this
    }

    /**
     * Set audio mime types
     *
     * @param audioType AudioTypes
     * @return This AudioPickBuilder for method chaining
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun audioTypes(vararg audioType: AudioTypes): AudioPickBuilder {
        mimeTypes(audioType.map { it.mimeType })
        return this
    }

}