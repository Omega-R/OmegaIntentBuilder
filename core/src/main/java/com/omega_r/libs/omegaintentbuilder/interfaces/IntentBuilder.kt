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

package com.omega_r.libs.omegaintentbuilder.interfaces

import android.content.Context
import android.content.Intent
import com.omega_r.libs.omegaintentbuilder.IntentBuilderLauncher
import com.omegar.libs.omegalaunchers.Launcher

/**
 * A generic Intent; ContextIntentHandler,  ActivityIntentHandler that supports control created createdIntent.
 */
interface IntentBuilder {

    /**
     * Returns created Intent.
     */
    fun createIntent(context: Context): Intent

    /**
     * Returns created Launcher.
     */
    fun createLauncher(): Launcher = IntentBuilderLauncher(this)

}