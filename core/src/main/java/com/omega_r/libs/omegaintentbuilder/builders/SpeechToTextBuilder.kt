package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.support.annotation.StringRes
import java.util.*

class SpeechToTextBuilder(private val context: Context): BaseBuilder(context) {

  private var prompt: String? = null
  private var locale: Locale = Locale.getDefault()

  fun prompt(promptMessage: String): SpeechToTextBuilder {
    prompt = promptMessage
    return this
  }

  fun prompt(@StringRes promptMessage: Int): SpeechToTextBuilder {
    return prompt(context.getString(promptMessage))
  }

  fun language(locale: Locale): SpeechToTextBuilder {
    this.locale = locale
    return this
  }

  override fun createIntent(): Intent {
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale)
    prompt?.let { intent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt) }

    return intent
  }

}