package com.omega_r.libs.omegaintentbuilder

import com.omega_r.libs.omegaintentbuilder.builders.CallIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.ShareIntentBuilder

class OmegaIntentBuilder {

  fun call(): CallIntentBuilder {
    return CallIntentBuilder(this);
  }

  fun share(): ShareIntentBuilder {
    return ShareIntentBuilder(this)
  }

  fun email(): EmailIntentBuilder {
    return EmailIntentBuilder(this)
  }

}