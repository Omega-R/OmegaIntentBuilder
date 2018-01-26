/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * IntentBuilder.java
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler

/**
 * A generic Intent; ContextIntentHandler,  ActivityIntentHandler that supports control created createdIntent.
 */
interface IntentBuilder {

  /**
   * Returns created Intent.
   */
  fun createIntent(): Intent

  /**
   * Returns ContextIntentHandler for control Intent.
   * Support startActivity, start Chooser.
   */
  fun createIntentHandler(): ContextIntentHandler

  /**
   * Returns ActivityIntentHandler (extends ContextIntentHandler) for control Intent.
   * Support startActivity, startActivityForResult, start Chooser.
   */
  fun createIntentHandler(activity: Activity): ActivityIntentHandler

  /**
   * Same as {@link #startActivity(Intent, Bundle)} with no options specified.
   *
   * @throws ActivityNotFoundException &nbsp;
   */
  fun startActivity();

}