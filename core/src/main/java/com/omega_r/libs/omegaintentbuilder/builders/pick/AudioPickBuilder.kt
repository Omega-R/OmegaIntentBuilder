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
import android.os.Parcel
import android.os.Parcelable.Creator
import androidx.annotation.RequiresApi
import com.omega_r.libs.omegaintentbuilder.types.AudioTypes

class AudioPickBuilder : BasePickBuilder {

    constructor() : super(AudioTypes.AUDIO.mimeType)

    constructor(parcel: Parcel) : super(parcel)

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

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<AudioPickBuilder> {

        override fun createFromParcel(parcel: Parcel): AudioPickBuilder {
            return AudioPickBuilder(parcel)
        }

        override fun newArray(size: Int): Array<AudioPickBuilder?> {
            return arrayOfNulls(size)
        }
    }
}