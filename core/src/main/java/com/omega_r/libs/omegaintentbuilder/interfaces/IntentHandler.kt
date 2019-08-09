package com.omega_r.libs.omegaintentbuilder.interfaces

import android.app.Activity
import android.app.Fragment
import android.content.ActivityNotFoundException
import android.content.Context
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FragmentIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.SupportFragmentIntentHandler

interface IntentHandler : IntentBuilder {

  /**
   * Returns ContextIntentHandler for control Intent.
   * Support startActivity, start Chooser.
   */
  fun createIntentHandler(context: Context): ContextIntentHandler

  /**
   * Returns ActivityIntentHandler (extends ContextIntentHandler) for control Intent.
   * Support startActivity, startActivityForResult, start Chooser.
   */
  fun createIntentHandler(activity: Activity): ActivityIntentHandler

  /**
   * Returns FragmentIntentHandler (extends ContextIntentHandler) for control Intent.
   * Support startActivity, startActivityForResult, start Chooser.
   */
  fun createIntentHandler(fragment: Fragment): FragmentIntentHandler

  /**
   * Returns FragmentIntentHandler (extends ContextIntentHandler) for control Intent.
   * Support startActivity, startActivityForResult, start Chooser.
   */
  fun createIntentHandler(fragment: androidx.fragment.app.Fragment): SupportFragmentIntentHandler

  /**
   * Same as {@link #startActivity(Intent, Bundle)} with no options specified.
   *
   * @throws ActivityNotFoundException &nbsp;
   */
  fun startActivity(context: Context)
}