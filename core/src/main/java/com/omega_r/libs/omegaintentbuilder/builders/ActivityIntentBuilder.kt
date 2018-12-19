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
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FragmentIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.SupportFragmentIntentHandler

class ActivityIntentBuilder<T : Activity>(
        private val context: Context,
        activity: Class<T>
) : BaseIntentBuilder<ActivityIntentBuilder<T>, T>(context, activity), IntentHandler {

    override fun createIntentHandler(): ContextIntentHandler {
        return ContextIntentHandler(context, createIntent())
    }

    override fun createIntentHandler(activity: Activity): ActivityIntentHandler {
        return ActivityIntentHandler(activity, createIntent())
    }

    override fun createIntentHandler(fragment: Fragment): FragmentIntentHandler {
        return FragmentIntentHandler(fragment, createIntent())
    }

    override fun createIntentHandler(fragment: androidx.fragment.app.Fragment): SupportFragmentIntentHandler {
        return SupportFragmentIntentHandler(fragment, createIntent())
    }

    override fun startActivity() {
        createIntentHandler().startActivity()
    }

}