package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import java.lang.IllegalStateException

class ViewIntentBuilder : BaseUriBuilder {

    private var mimeType: String? = null

    constructor() : super()

    constructor(parcel: Parcel) : super(parcel) {
        mimeType = parcel.readString()
    }

    fun mimeType(mimeType: String): ViewIntentBuilder = also {
        this.mimeType = mimeType
    }

    override fun createIntent(context: Context): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        val uriSet = getUriSet(context)
        if (uriSet.size == 1) intent.setDataAndType(uriSet.first(), mimeType)
        else throw IllegalStateException("Multiple uri not supported")
        return intent
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeString(mimeType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<ViewIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): ViewIntentBuilder {
            return ViewIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<ViewIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}