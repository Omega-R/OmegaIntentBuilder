package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import android.provider.MediaStore

class VideoRecordBuilder() : BaseActivityBuilder() {
    constructor(parcel: Parcel) : this()

    override fun createIntent(context: Context): Intent {
        return Intent(MediaStore.ACTION_VIDEO_CAPTURE);
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<VideoRecordBuilder> {

        override fun createFromParcel(parcel: Parcel): VideoRecordBuilder {
            return VideoRecordBuilder(parcel)
        }

        override fun newArray(size: Int): Array<VideoRecordBuilder?> {
            return arrayOfNulls(size)
        }
    }
}