package com.omega_r.libs.omegaintentbuilder.interfaces

import android.app.Activity
import android.content.ActivityNotFoundException
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler

interface IntentHandler : IntentBuilder {

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