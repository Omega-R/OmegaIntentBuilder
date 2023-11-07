/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * EmailIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders.share

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable.Creator

/**
 * EmailIntentBuilder is a helper for constructing {Intent#ACTION_SENDTO}
 */
class EmailIntentBuilder : BaseShareBuilder<EmailIntentBuilder> {

    constructor() : super()

    constructor(parcel: Parcel) : super(parcel)

    /**
     * This method could call ActivityNotFoundException
     *
     * @return Intent for email
     */
    override fun createIntent(context: Context): Intent {
        val intent = super.createIntent(context)
        intent.action = Intent.ACTION_SENDTO
        intent.data = Uri.parse("mailto:")

        return intent
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<EmailIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): EmailIntentBuilder {
            return EmailIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<EmailIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}