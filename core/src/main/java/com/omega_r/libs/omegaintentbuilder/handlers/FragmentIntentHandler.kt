/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * FragmentIntentHandler.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   October 28, 2018
 */

package com.omega_r.libs.omegaintentbuilder.handlers

import android.app.Fragment
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle

/**
 * FragmentIntentHandler is a helper for start intents
 * Support startActivityForResult
 */
class FragmentIntentHandler(private val fragment: Fragment, private val createdIntent: Intent) : ContextIntentHandler(fragment.activity, createdIntent) {

    /**
     * Launch an activity for which you would like a result when it finished.
     * When this activity exits, your
     * onActivityResult() method will be called with the given requestCode.
     * Using a negative requestCode is the same as calling
     * {@link #android.app.Activity.startActivity} (the activity is not launched as a sub-activity).
     *
     * <p>Note that this method should only be used with Intent protocols
     * that are defined to return a result.  In other protocols (such as
     * {Intent#ACTION_MAIN} or {@link Intent#ACTION_VIEW}), you may
     * not get the result when you expect.  For example, if the activity you
     * are launching uses {Intent#FLAG_ACTIVITY_NEW_TASK}, it will not
     * run in your task and thus you will immediately receive a cancel result.
     *
     * <p>As a special case, if you call startActivityForResult() with a requestCode
     * >= 0 during the initial onCreate(Bundle savedInstanceState)/onResume() of your
     * activity, then your window will not be displayed until a result is
     * returned back from the started activity.  This is to avoid visible
     * flickering when redirecting to another activity.
     *
     * <p>This method throws {@throws android.content.ActivityNotFoundException}
     * if there was no Activity found to run the given Intent.
     *
     * @param createdIntent The createdIntent to start.
     * @param requestCode If >= 0, this code will be returned in
     *                    onActivityResult() when the activity exits.
     * @param options Additional options for how the Activity should be started.
     * See {@link android.content.Context#startActivity(Intent, Bundle)}
     * Context.startActivity(Intent, Bundle)} for more details.
     *
     * @throws android.content.ActivityNotFoundException
     *
     * @see #startActivity
     */
    @JvmOverloads
    fun startActivityForResult(requestCode: Int, options: Bundle? = null) {
        try {
            if (getChooserTitle()?.isEmpty() != false) {
                if (options == null) {
                    fragment.startActivityForResult(createdIntent, requestCode)
                } else {
                    fragment.startActivityForResult(createdIntent, requestCode, options)
                }
            } else {
                if (options == null) {
                    fragment.startActivityForResult(createChooserIntent(), requestCode)
                } else {
                    fragment.startActivityForResult(createChooserIntent(), requestCode, options)
                }
            }
        } catch (exc: ActivityNotFoundException) {
            handleStartActivityException(exc)
        }
    }

    override fun failToast(message: String): FragmentIntentHandler {
        super.failToast(message)
        return this
    }

    override fun failToast(message: Int): FragmentIntentHandler {
        super.failToast(message)
        return this
    }

    override fun failCallback(failCallback: FailCallback): FragmentIntentHandler {
        super.failCallback(failCallback)
        return this
    }

    override fun failIntent(failIntent: Intent): FragmentIntentHandler {
        super.failIntent(failIntent)
        return this
    }

    override fun failIntentHandler(failIntentHandler: ContextIntentHandler?): FragmentIntentHandler {
        super.failIntentHandler(failIntentHandler)
        return this
    }
}