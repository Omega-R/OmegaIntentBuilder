/*
 * Copyright (c) 2017 Omega-r
 *
 * ActivityIntentBuilder
 * ActivityIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 29, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Parcel
import android.os.Parcelable.Creator
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FragmentIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.SupportFragmentIntentHandler
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandlerBuilder

class ActivityIntentBuilder<T : Activity> : BaseIntentBuilder<ActivityIntentBuilder<T>, T>, IntentHandlerBuilder {

    constructor(activity: Class<T>) : super(activity)

    constructor(parcel: Parcel) : super(parcel)

    override fun createIntentHandler(context: Context): ContextIntentHandler {
        return ContextIntentHandler(context, createIntent(context))
    }

    override fun createIntentHandler(activity: Activity): ActivityIntentHandler {
        return ActivityIntentHandler(activity, createIntent(activity))
    }

    override fun createIntentHandler(fragment: Fragment): FragmentIntentHandler {
        return FragmentIntentHandler(fragment, createIntent(fragment.activity))
    }

    override fun createIntentHandler(fragment: androidx.fragment.app.Fragment): SupportFragmentIntentHandler {
        return SupportFragmentIntentHandler(fragment, createIntent(fragment.activity!!))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<ActivityIntentBuilder<*>> {

        override fun createFromParcel(parcel: Parcel): ActivityIntentBuilder<*> {
            return ActivityIntentBuilder<Activity>(parcel)
        }

        override fun newArray(size: Int): Array<ActivityIntentBuilder<*>?> {
            return arrayOfNulls(size)
        }
    }
}