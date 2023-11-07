package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Service
import android.content.Context
import android.os.Parcel
import android.os.Parcelable.Creator
import com.omega_r.libs.omegaintentbuilder.interfaces.ServiceHandler

class ServiceIntentBuilder<T : Service> : BaseIntentBuilder<ServiceIntentBuilder<T>, T>, ServiceHandler {

    constructor(service: Class<T>) : super(service)

    constructor(parcel: Parcel) : super(parcel)

    override fun startService(context: Context) {
        context.startService(createIntent(context))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<ServiceIntentBuilder<*>> {

        override fun createFromParcel(parcel: Parcel): ServiceIntentBuilder<*> {
            return ServiceIntentBuilder<Service>(parcel)
        }

        override fun newArray(size: Int): Array<ServiceIntentBuilder<*>?> {
            return arrayOfNulls(size)
        }
    }
}