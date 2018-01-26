package com.omega_r.libs.omegaintentbuilder.types

import android.speech.RecognizerIntent

enum class LanguageModelTypes(val type: String) {

  LANGUAGE_MODEL_FREE_FORM(RecognizerIntent.LANGUAGE_MODEL_FREE_FORM),
  LANGUAGE_MODEL_WEB_SEARCH(RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)

}