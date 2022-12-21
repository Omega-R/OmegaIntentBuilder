/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * ImagePickerBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 25, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.os.Build
import android.os.Parcel
import android.os.Parcelable.Creator
import androidx.annotation.RequiresApi
import com.omega_r.libs.omegaintentbuilder.types.ImageTypes

/**
 * ImagePickBuilder is a helper for creating pick image intent
 */
class ImagePickBuilder : BasePickBuilder {

    constructor() : super(ImageTypes.IMAGE.mimeType)

    constructor(parcel: Parcel) : super(parcel)

    /**
     * Set image mime type
     *
     * @param imageType ImageTypes
     * @return This ImagePickBuilder for method chaining
     */
    fun imageType(imageType: ImageTypes): ImagePickBuilder {
        mimeType(imageType.mimeType)
        return this
    }

    /**
     * Set image mime types
     *
     * @param imageType ImageTypes
     * @return This ImagePickBuilder for method chaining
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun imageTypes(vararg imageType: ImageTypes): ImagePickBuilder {
        mimeTypes(imageType.map { it.mimeType })
        return this
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<ImagePickBuilder> {

        override fun createFromParcel(parcel: Parcel): ImagePickBuilder {
            return ImagePickBuilder(parcel)
        }

        override fun newArray(size: Int): Array<ImagePickBuilder?> {
            return arrayOfNulls(size)
        }
    }
}