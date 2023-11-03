package com.omega_r.libs.omegaintentbuilder

import android.content.Context
import android.os.Parcel
import android.os.Parcelable.Creator
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandlerBuilder
import com.omegar.libs.omegalaunchers.BaseIntentLauncher

/**
 * Created by Anton Knyazev on 2019-08-08.
 */
class IntentBuilderLauncher(private val intentBuilder: IntentBuilder) : BaseIntentLauncher() {

    constructor(parcel: Parcel) : this(parcel.readParcelable(IntentBuilder::class.java.classLoader)!!)

    override fun getIntent(context: Context) = intentBuilder.createIntent(context)

    override fun launch(context: Context) {
        if (intentBuilder is IntentHandlerBuilder) {
            intentBuilder.startActivity(context)
        } else super.launch(context)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(intentBuilder, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<IntentBuilderLauncher> {

        override fun createFromParcel(parcel: Parcel): IntentBuilderLauncher {
            return IntentBuilderLauncher(parcel)
        }

        override fun newArray(size: Int): Array<IntentBuilderLauncher?> {
            return arrayOfNulls(size)
        }
    }
}