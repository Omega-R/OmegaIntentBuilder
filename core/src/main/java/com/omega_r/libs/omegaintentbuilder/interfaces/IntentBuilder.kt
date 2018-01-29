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

import android.content.Intent

/**
 * A generic Intent; ContextIntentHandler,  ActivityIntentHandler that supports control created createdIntent.
 */
interface IntentBuilder {

  /**
   * Returns created Intent.
   */
  fun createIntent(): Intent


}