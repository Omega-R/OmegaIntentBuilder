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
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FragmentIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.SupportFragmentIntentHandler

abstract class BaseActivityBuilder(private val context: Context): IntentHandler {

  override fun createIntentHandler(): ContextIntentHandler {
    return ContextIntentHandler(context, createIntent())
  }

  override fun createIntentHandler(activity: Activity): ActivityIntentHandler {
    return ActivityIntentHandler(activity, createIntent())
  }

  override fun createIntentHandler(fragment: Fragment): FragmentIntentHandler {
    return FragmentIntentHandler(fragment, createIntent())
  }

  override fun createIntentHandler(fragment: android.support.v4.app.Fragment): SupportFragmentIntentHandler {
    return SupportFragmentIntentHandler(fragment, createIntent())
  }

  override fun startActivity() {
    createIntentHandler().startActivity()
  }

}