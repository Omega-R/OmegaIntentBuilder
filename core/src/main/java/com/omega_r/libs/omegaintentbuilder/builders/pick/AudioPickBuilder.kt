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

import android.content.Context
import com.omega_r.libs.omegaintentbuilder.types.AudioTypes

class AudioPickBuilder(context: Context): BasePickBuilder(context) {

  init {
    super.mimeType = AudioTypes.AUDIO.mimeType
  }


  /**
   * Set audio mime type
   *
   * @param audioTypes AudioTypes
   * @return This AudioPickBuilder for method chaining
   */
  fun audioType(audioTypes: AudioTypes): AudioPickBuilder {
    super.mimeType = audioTypes.mimeType
    return this
  }

}