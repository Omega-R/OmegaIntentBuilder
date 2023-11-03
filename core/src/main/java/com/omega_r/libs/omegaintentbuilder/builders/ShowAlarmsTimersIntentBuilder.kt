package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import com.omega_r.libs.omegaintentbuilder.types.ShowType

class ShowAlarmsTimersIntentBuilder(private val showType: ShowType) : BaseActivityBuilder() {

    constructor(parcel: Parcel) : this(parcel.readSerializable() as ShowType)

    override fun createIntent(context: Context): Intent {
        return Intent(showType.actionType)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(showType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<ShowAlarmsTimersIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): ShowAlarmsTimersIntentBuilder {
            return ShowAlarmsTimersIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<ShowAlarmsTimersIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}