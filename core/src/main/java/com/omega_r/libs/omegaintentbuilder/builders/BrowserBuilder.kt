/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BrowserBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 19, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable.Creator
import com.omega_r.libs.omegatypes.Text
import com.omega_r.libs.omegatypes.toText

/**
 * BrowserBuilder class for starting web browser
 */
class BrowserBuilder(private val uri: Text) : BaseActivityBuilder() {

    constructor(parcel: Parcel) : this(parcel.readSerializable() as Text)

    constructor(urlAddress: String) : this(urlAddress.toText())

    constructor(uri: Uri) : this(uri.toString())

    override fun createIntent(context: Context) =  Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(uri.getString(context))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<BrowserBuilder> {

        override fun createFromParcel(parcel: Parcel): BrowserBuilder {
            return BrowserBuilder(parcel)
        }

        override fun newArray(size: Int): Array<BrowserBuilder?> {
            return arrayOfNulls(size)
        }
    }
}