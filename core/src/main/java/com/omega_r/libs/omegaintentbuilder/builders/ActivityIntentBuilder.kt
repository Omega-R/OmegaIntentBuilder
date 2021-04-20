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
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FragmentIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.SupportFragmentIntentHandler
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandlerBuilder

class ActivityIntentBuilder<T : Activity>(
        activity: Class<T>
) : BaseIntentBuilder<ActivityIntentBuilder<T>, T>(activity), IntentHandlerBuilder {

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

}