/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * OmegaIntentBuilder.java
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder

import com.omega_r.libs.omegaintentbuilder.builders.CallIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.ShareIntentBuilder

/**
 * OmegaIntentBuilder class for creating supports intent builders.
 */
class OmegaIntentBuilder {

  /**
   * Return CallIntentBuilder for creating call intent
   */
  fun call(): CallIntentBuilder {
    return CallIntentBuilder(this);
  }

  /**
   * Return ShareIntentBuilder for creating share intent
   */
  fun share(): ShareIntentBuilder {
    return ShareIntentBuilder(this)
  }

  /**
   * Return ShareIntentBuilder for creating email intent
   */
  fun email(): EmailIntentBuilder {
    return EmailIntentBuilder(this)
  }

}