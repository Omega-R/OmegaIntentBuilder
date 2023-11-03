/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * ContactPickBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 26, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import android.provider.ContactsContract
import com.omega_r.libs.omegaintentbuilder.builders.BaseActivityBuilder

class ContactPickBuilder : BaseActivityBuilder {

    constructor() : super()

    constructor(parcel: Parcel) : this() {
    }

    override fun createIntent(context: Context): Intent {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        )
        return intent
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // nothing
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<ContactPickBuilder> {

        override fun createFromParcel(parcel: Parcel): ContactPickBuilder {
            return ContactPickBuilder(parcel)
        }

        override fun newArray(size: Int): Array<ContactPickBuilder?> {
            return arrayOfNulls(size)
        }
    }
}