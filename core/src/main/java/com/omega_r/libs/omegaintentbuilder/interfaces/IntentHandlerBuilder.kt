package com.omega_r.libs.omegaintentbuilder.interfaces

import android.app.Activity
import android.app.Fragment
import android.content.ActivityNotFoundException
import android.content.Context

interface IntentHandlerBuilder : IntentBuilder {

    /**
     * Returns ContextIntentHandler for control Intent.
     * Support startActivity, start Chooser.
     */
    fun createIntentHandler(context: Context): IntentHandler

    /**
     * Returns ActivityIntentHandler (extends ContextIntentHandler) for control Intent.
     * Support startActivity, startActivityForResult, start Chooser.
     */
    fun createIntentHandler(activity: Activity): IntentHandler

    /**
     * Returns FragmentIntentHandler (extends ContextIntentHandler) for control Intent.
     * Support startActivity, startActivityForResult, start Chooser.
     */
    fun createIntentHandler(fragment: Fragment): IntentHandler

    /**
     * Returns FragmentIntentHandler (extends ContextIntentHandler) for control Intent.
     * Support startActivity, startActivityForResult, start Chooser.
     */
    fun createIntentHandler(fragment: androidx.fragment.app.Fragment): IntentHandler

    /**
     * Same as {@link #startActivity(Intent, Bundle)} with no options specified.
     *
     * @throws ActivityNotFoundException &nbsp;
     */
    fun startActivity(context: Context) {
        createIntentHandler(context).startActivity()
    }

    fun startActivity(activity: Activity) {
        createIntentHandler(activity).startActivity()
    }

    fun startActivity(fragment: Fragment) {
        createIntentHandler(fragment).startActivity()
    }

    fun startActivity(fragment: androidx.fragment.app.Fragment) {
        createIntentHandler(fragment).startActivity()
    }

}