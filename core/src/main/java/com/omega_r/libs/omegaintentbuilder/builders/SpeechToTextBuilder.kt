/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * SpeechToTextBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 26, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Build
import android.speech.RecognizerIntent
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.omega_r.libs.omegaintentbuilder.types.LanguageModelTypes
import com.omega_r.libs.omegatypes.Text
import java.util.*

class SpeechToTextBuilder() : BaseActivityBuilder() {

    private var prompt: Text? = null
    private var language: String = Locale.getDefault().toString()
    private var languageModel: LanguageModelTypes = LanguageModelTypes.LANGUAGE_MODEL_FREE_FORM
    private var maxResult: Int? = null
    private var preferOffline: Boolean = false
    private var secure: Boolean = false
    private var partialResults: Boolean = false
    private var onlyReturnLanguagePreference: String? = null

    /**
     * Optional text prompt to show to the user when asking them to speak.
     *
     * @param promptMessage String
     * @return This SpeechToTextBuilder for method chaining
     */
    fun prompt(promptMessage: String): SpeechToTextBuilder {
        prompt = Text.from(promptMessage)
        return this
    }

    /**
     * Optional text prompt to show to the user when asking them to speak.
     *
     * @param promptMessage String
     * @return This SpeechToTextBuilder for method chaining
     */
    fun prompt(promptMessage: Text): SpeechToTextBuilder {
        prompt = promptMessage
        return this
    }

    /**
     * Optional text prompt to show to the user when asking them to speak.
     *
     * @param promptMessage @StringRes integer
     * @return This SpeechToTextBuilder for method chaining
     */
    fun prompt(@StringRes promptMessage: Int): SpeechToTextBuilder {
        return prompt(Text.from(promptMessage))
    }

    /*
     * Optional IETF language tag (as defined by BCP 47), for example "en-US".
     * This tag informs the recognizer to perform speech recognition in a language different than the one set in the getDefault().
     *
     * @param language Locale
     * @return This SpeechToTextBuilder for method chaining
     */
    fun language(locale: Locale): SpeechToTextBuilder {
        this.language = locale.toString()
        return this
    }

    /*
   * Optional IETF language tag (as defined by BCP 47), for example "en-US".
   * This tag informs the recognizer to perform speech recognition in a language different than the one set in the getDefault().
   *
   * @param language Locale
   * @return This SpeechToTextBuilder for method chaining
   */
    fun language(locale: String): SpeechToTextBuilder {
        this.language = locale
        return this
    }

    /**
     * Informs the recognizer which speech model to prefer when performing ACTION_RECOGNIZE_SPEECH.
     * The recognizer uses this information to fine tune the results. This extra is required.
     * Activities implementing ACTION_RECOGNIZE_SPEECH may interpret the values as they see fit.
     *
     * @param type LanguageModelTypes
     * @return This SpeechToTextBuilder for method chaining
     */
    fun languageModel(type: LanguageModelTypes): SpeechToTextBuilder {
        this.languageModel = type
        return this
    }

    /*
     * Optional limit on the maximum number of results to return.
     * If omitted the recognizer will choose how many results to return. Must be an integer.
     *
     * @param max integer
     * @return This SpeechToTextBuilder for method chaining
     */
    fun maxResult(max: Int): SpeechToTextBuilder {
        maxResult = max
        return this
    }

    /*
     * Optional boolean, to be used with ACTION_RECOGNIZE_SPEECH, ACTION_VOICE_SEARCH_HANDS_FREE,
     * ACTION_WEB_SEARCH to indicate whether to only use an offline speech recognition engine.
     * The default is false, meaning that either network or offline recognition engines may be used.
     *
     * @param offline Boolean
     * @return This SpeechToTextBuilder for method chaining
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun preferOffline(offline: Boolean): SpeechToTextBuilder {
        preferOffline = offline
        return this
    }

    /*
     * Optional boolean to indicate that a "hands free" voice search was performed while the device was in a secure mode.
     * An example of secure mode is when the device's screen lock is active, and it requires some form
     * of authentication to be unlocked. When the device is securely locked,
     * the voice search activity should either restrict the set of voice actions that are permitted,
     * or require some form of secure authentication before proceeding.
     *
     * @param secure Boolean
     * @return This SpeechToTextBuilder for method chaining
     */
    fun secure(secure: Boolean): SpeechToTextBuilder {
        this.secure = secure
        return this
    }

    /*
     * Optional boolean to indicate whether partial results should be returned by the recognizer as the user speaks (default is false).
     * The server may ignore a request for partial results in some or all cases.
     *
     * @param partialResults Boolean
     * @return This SpeechToTextBuilder for method chaining
     */
    fun partialResults(partialResults: Boolean): SpeechToTextBuilder {
        this.partialResults = partialResults
        return this
    }

    /*
     * Specify this boolean extra in a broadcast of ACTION_GET_LANGUAGE_DETAILS to indicate that only
     * the current language preference is needed in the response.
     * This avoids any additional computation if all you need is EXTRA_LANGUAGE_PREFERENCE in the response.
     *
     * @param partialResults String
     * @return This SpeechToTextBuilder for method chaining
     */
    fun onlyReturnLanguagePreference(onlyReturnLanguagePreference: String): SpeechToTextBuilder {
        this.onlyReturnLanguagePreference = onlyReturnLanguagePreference
        return this
    }

    /*
     * Specify this boolean extra in a broadcast of ACTION_GET_LANGUAGE_DETAILS to indicate that only
     * the current language preference is needed in the response.
     * This avoids any additional computation if all you need is EXTRA_LANGUAGE_PREFERENCE in the response.
     *
     * @param partialResults Locale
     * @return This SpeechToTextBuilder for method chaining
     */
    fun onlyReturnLanguagePreference(onlyReturnLanguagePreference: Locale): SpeechToTextBuilder {
        this.onlyReturnLanguagePreference = onlyReturnLanguagePreference.toString()
        return this
    }

    override fun createIntent(context: Context): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, languageModel.type)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
        prompt?.let { intent.putExtra(RecognizerIntent.EXTRA_PROMPT, it.getString(context)) }
        maxResult?.let { intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, it) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, preferOffline)
        }
        intent.putExtra(RecognizerIntent.EXTRA_SECURE, secure)
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, partialResults)
        onlyReturnLanguagePreference?.let { intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, it) }

        return intent
    }

}