/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BaseBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.app.Fragment
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.handlers.*
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandler
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandlerBuilder

abstract class BaseActivityBuilder : IntentHandlerBuilder {

    override fun createIntentHandler(context: Context): IntentHandler = ContextIntentHandler(context, createIntent(context))

    override fun createIntentHandler(activity: Activity): IntentHandler = ActivityIntentHandler(activity, createIntent(activity))

    override fun createIntentHandler(fragment: Fragment): IntentHandler =
            FragmentIntentHandler(fragment, createIntent(fragment.activity!!))

    override fun createIntentHandler(fragment: androidx.fragment.app.Fragment): IntentHandler =
            SupportFragmentIntentHandler(fragment, createIntent(fragment.activity!!))

}