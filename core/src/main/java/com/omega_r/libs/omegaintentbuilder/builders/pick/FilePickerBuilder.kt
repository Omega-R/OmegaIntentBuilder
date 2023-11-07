/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * FilePickerBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 26, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.os.Parcel
import android.os.Parcelable.Creator

open class FilePickerBuilder : BasePickBuilder {

    constructor() : super()

    constructor(parcel: Parcel) : super(parcel) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<FilePickerBuilder> {

        override fun createFromParcel(parcel: Parcel): FilePickerBuilder {
            return FilePickerBuilder(parcel)
        }

        override fun newArray(size: Int): Array<FilePickerBuilder?> {
            return arrayOfNulls(size)
        }
    }
}