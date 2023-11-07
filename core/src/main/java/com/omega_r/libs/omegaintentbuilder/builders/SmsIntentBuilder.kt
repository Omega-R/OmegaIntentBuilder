/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * SmsIntentBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 10, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcel
import android.os.Parcelable.Creator
import android.provider.Telephony
import androidx.annotation.StringRes
import com.omega_r.libs.omegatypes.Text
import java.util.*
import kotlin.collections.ArrayList

/**
 * SmsIntentBuilder is a helper for constructing sms intent
 */
class SmsIntentBuilder(addresses: Collection<String>) : BaseActivityBuilder() {

    private var phoneNumberSet = TreeSet(String.CASE_INSENSITIVE_ORDER)
    private var message: Text? = null

    constructor(parcel: Parcel) : this(addresses = parcel.readSerializable() as Collection<String>) {
        message = parcel.readSerializable() as Text?
    }

    init {
        addresses.forEach {
            val phone = it.replace(CallIntentBuilder.regex, "")
            if (!phone.isEmpty()) {
                phoneNumberSet.add(phone)
            }
        }
    }

    /**
     * Add a message.
     *
     * @param message String
     * @return This SmsIntentBuilder for method chaining
     */
    fun message(message: String): SmsIntentBuilder {
        this.message = Text.from(message)
        return this
    }

    /**
     * Add a message.
     *
     * @param message Int
     * @return This SmsIntentBuilder for method chaining
     */
    fun message(@StringRes message: Int): SmsIntentBuilder {
        this.message = Text.from(message)
        return this
    }

    @SuppressLint("NewApi")
    override fun createIntent(context: Context): Intent {

        val intent: Intent
        val phones = phoneNumberSet.joinToString(separator = ";")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val smsPackage = Telephony.Sms.getDefaultSmsPackage(context)
            intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phones"))
            smsPackage?.let { intent.setPackage(smsPackage) }
        } else {
            val smsUri = Uri.parse("tel:$phones")
            intent = Intent(Intent.ACTION_VIEW, smsUri)
            intent.putExtra("address", phones)
            intent.type = "vnd.android-dir/mms-sms"
        }

        intent.putExtra("sms_body", message?.getString(context) ?: "")

        return intent
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(phoneNumberSet)
        parcel.writeSerializable(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<SmsIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): SmsIntentBuilder {
            return SmsIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<SmsIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}