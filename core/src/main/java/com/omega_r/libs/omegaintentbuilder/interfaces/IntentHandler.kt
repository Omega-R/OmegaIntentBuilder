package com.omega_r.libs.omegaintentbuilder.interfaces

import android.content.Intent
import androidx.annotation.StringRes
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback

interface IntentHandler : ActivityStarter {

    fun chooserTitle(chooserTitle: CharSequence): IntentHandler

    fun chooserTitle(chooserTitle: String): IntentHandler

    fun chooserTitle(chooserTitle: Int): IntentHandler

    fun failToast(message: String): IntentHandler

    fun failToast(@StringRes message: Int): IntentHandler

    fun failIntent(failIntent: Intent): IntentHandler

    fun failCallback(failCallback: FailCallback): IntentHandler

    fun failIntentHandler(failIntentHandler: IntentHandler?): IntentHandler

    fun getIntent(): Intent

    fun addFlagsClearBackStack(): IntentHandler

    fun addFlags(flags: Int): IntentHandler

    fun setFlags(flags: Int): IntentHandler

}