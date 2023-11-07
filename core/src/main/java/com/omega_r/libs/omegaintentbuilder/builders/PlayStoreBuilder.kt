package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable.Creator
import androidx.annotation.StringRes
import com.omega_r.libs.omegatypes.Text

class PlayStoreBuilder() : BaseActivityBuilder() {

    private var packageName: Text? = null
    private var referrer: Text? = null

    constructor(parcel: Parcel) : this() {
        packageName = parcel.readSerializable() as Text?
        referrer = parcel.readSerializable() as Text?
    }

    fun packageName(packageName: String): PlayStoreBuilder {
        this.packageName = Text.from(packageName)
        return this
    }

    fun packageName(@StringRes packageNameRes: Int): PlayStoreBuilder {
        packageName = Text.from(packageNameRes)
        return this
    }

    fun referrer(referrer: String): PlayStoreBuilder {
        this.referrer = Text.from(referrer)
        return this
    }

    fun referrer(@StringRes referrerRes: Int): PlayStoreBuilder {
        this.referrer = Text.from(referrerRes)
        return this
    }

    override fun createIntent(context: Context): Intent {
        val packageName = packageName?.getString(context) ?: context.packageName

        var referrerAppendix = ""
        referrer?.let {
            referrerAppendix = "&referrer=${it.getString(context)}"
        }

        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=$packageName$referrerAppendix")
        )
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(packageName)
        parcel.writeSerializable(referrer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<PlayStoreBuilder> {

        override fun createFromParcel(parcel: Parcel): PlayStoreBuilder {
            return PlayStoreBuilder(parcel)
        }

        override fun newArray(size: Int): Array<PlayStoreBuilder?> {
            return arrayOfNulls(size)
        }
    }
}