/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * CallIntentBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable.Creator
import com.omega_r.libs.omegaintentbuilder.types.CallTypes

/**
 * CallIntentBuilder is a helper for constructing {@link Intent#ACTION_DIAL}
 */
class CallIntentBuilder(phoneNumber: String) : BaseActivityBuilder() {

    private val phoneNumber: String = phoneNumber.replace(regex, "")

    private var callType = CallTypes.SYSTEM_CALL

    constructor(parcel: Parcel) : this(parcel.readString().orEmpty()) {
        callType = parcel.readSerializable() as CallTypes
    }

    fun type(callType: CallTypes): CallIntentBuilder {
        this.callType = callType
        return this
    }

    /**
     * This method could call ActivityNotFoundException
     *
     * @return Intent for calling
     */
    override fun createIntent(context: Context) = Intent().apply {
        data = Uri.parse(PHONE_SCHEME + phoneNumber)
        action = when (callType) {
            CallTypes.SYSTEM_CALL -> Intent.ACTION_DIAL
            CallTypes.VIBER -> {
                setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity")
                Intent.ACTION_VIEW
            }
            CallTypes.SKYPE -> {
                data = Uri.parse("$SKYPE_SCHEME$phoneNumber?call")
                Intent.ACTION_VIEW
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(phoneNumber)
        parcel.writeSerializable(callType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<CallIntentBuilder> {

        private const val PHONE_SCHEME = "tel:";
        private const val SKYPE_SCHEME = "skype:";
        val regex = Regex("[^0-9]")

        override fun createFromParcel(parcel: Parcel): CallIntentBuilder {
            return CallIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<CallIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}