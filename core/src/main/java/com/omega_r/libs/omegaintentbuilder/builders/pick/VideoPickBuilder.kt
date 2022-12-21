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
import android.os.Parcel
import android.os.Parcelable.Creator
import androidx.annotation.RequiresApi
import com.omega_r.libs.omegaintentbuilder.types.VideoTypes

class VideoPickBuilder : BasePickBuilder {

    constructor() : super(VideoTypes.VIDEO.mimeType)

    constructor(parcel: Parcel) : super(parcel)

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<VideoPickBuilder> {

        override fun createFromParcel(parcel: Parcel): VideoPickBuilder {
            return VideoPickBuilder(parcel)
        }

        override fun newArray(size: Int): Array<VideoPickBuilder?> {
            return arrayOfNulls(size)
        }
    }
}