package com.omega_r.libs.omegaintentbuilder

import com.omega_r.libs.omegaintentbuilder.builders.CallIntentBuilder

class OmegaIntentBuilder {

  fun call(): CallIntentBuilder {
    return CallIntentBuilder(this);
  }

}