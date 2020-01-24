/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * ContextIntentHandler.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder.handlers

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandler
import com.omega_r.libs.omegatypes.Text

/**
 * ContextIntentHandler is a helper for start intents
 */
open class ContextIntentHandler(
        private val context: Context,
        private val createdIntent: Intent
) : IntentHandler {

    companion object {
        private const val FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 0x00000040
        private const val FLAG_GRANT_PREFIX_URI_PERMISSION = 0x00000080
    }

    private var chooserTitle: Text? = null
    private var toastMessage: Text? = null
    private var failIntent: Intent? = null
    private var failCallback: FailCallback? = null
    private var failIntentHandler: IntentHandler? = null
    private var activityResultCallback: ActivityResultCallback? = null

    /**
     * Set the title that will be used for the activity chooser for this share.
     *
     * @param title Title CharSequence
     * @return This ContextIntentHandler for method chaining
     */
    override fun chooserTitle(chooserTitle: CharSequence): IntentHandler {
        this.chooserTitle = Text.from(chooserTitle)
        return this
    }

    /**
     * Set the title that will be used for the activity chooser for this share.
     *
     * @param title Title String
     * @return This ContextIntentHandler for method chaining
     */
    override fun chooserTitle(chooserTitle: String): IntentHandler {
        this.chooserTitle = Text.from(chooserTitle)
        return this
    }

    /**
     * Set the title that will be used for the activity chooser for this share.
     *
     * @param title Title @StringRes Int
     * @return This ContextIntentHandler for method chaining
     */
    override fun chooserTitle(chooserTitle: Int): IntentHandler {
        this.chooserTitle = Text.from(chooserTitle)
        return this
    }

    /**
     *
     * @return This Chooser title
     */
    protected fun getChooserTitle(): Text? = chooserTitle

    /**
     * Create an Intent that will launch the standard Android activity chooser,
     * allowing the user to pick what activity/app on the system should handle
     * the share.
     *
     * @return A chooser Intent for the currently configured sharing action
     */
    protected fun createChooserIntent(): Intent {
        val chooserIntent = Intent(ACTION_CHOOSER)
        chooserIntent.putExtra(EXTRA_INTENT, createdIntent)
        chooserTitle?.let {
            chooserIntent.putExtra(EXTRA_TITLE, it.getCharSequence(context))
        }
        var permFlags = createdIntent.flags
        permFlags = permFlags and (FLAG_GRANT_READ_URI_PERMISSION
                or FLAG_GRANT_WRITE_URI_PERMISSION or FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                or FLAG_GRANT_PREFIX_URI_PERMISSION)
        if (permFlags != 0) {
            var clipData: ClipData? = createdIntent.clipData
            if (clipData == null && createdIntent.data != null) {
                val item: ClipData.Item = ClipData.Item(createdIntent.data)
                var mimeTypes: Array<String> = arrayOf()
                createdIntent.type?.let {
                    mimeTypes = arrayOf(createdIntent.type)
                }
                clipData = ClipData(null, mimeTypes, item)
            }
            clipData?.let {
                chooserIntent.clipData = clipData
                chooserIntent.addFlags(permFlags)
            }
        }
        return chooserIntent
    }

    /**
     * Set toast message if startActivityMethod throw ActivityNotFoundException
     * @param message String
     *
     * @return Returns the same ContextIntentHandler object, for chaining multiple calls
     * into a single statement.
     */
    override fun failToast(message: String): IntentHandler {
        toastMessage = Text.from(message)
        return this
    }

    /**
     * Set toast message if startActivityMethod throw ActivityNotFoundException
     * @param message @StringRes Int
     *
     * @return Returns the same ContextIntentHandler object, for chaining multiple calls
     * into a single statement.
     */
    override fun failToast(@StringRes message: Int): IntentHandler {
        toastMessage = Text.from(message)
        return this
    }

    /**
     * Set failIntent
     * If startActivityMethod throw ActivityNotFoundException this failIntent will started
     * @param failIntent Intent
     *
     * @return Returns the same ContextIntentHandler object, for chaining multiple calls
     * into a single statement.
     */
    override fun failIntent(failIntent: Intent): IntentHandler {
        this.failIntent = failIntent
        return this
    }

    /**
     * Set failCallback
     * If startActivityMethod throw ActivityNotFoundException this failCallback will emit Error
     * @param failCallback Intent
     *
     * @return Returns the same ContextIntentHandler object, for chaining multiple calls
     * into a single statement.
     */
    override fun failCallback(failCallback: FailCallback): IntentHandler {
        this.failCallback = failCallback
        return this
    }

    /**
     * Set failIntentHandler
     * If startActivityMethod throw ActivityNotFoundException this failIntentHandler will startActivity
     * with other intent
     * @param failIntentHandler ContextIntentHandler
     *
     * @return Returns the same ContextIntentHandler object, for chaining multiple calls
     * into a single statement.
     */
    override fun failIntentHandler(failIntentHandler: IntentHandler?): IntentHandler {
        this.failIntentHandler = failIntentHandler
        return this
    }

    /**
     * Same as {@link #android.content.Context.startActivity(Intent, Bundle)} with no options
     * specified.
     *
     * @throws ActivityNotFoundException
     */
    override fun startActivity() {
        try {
            startActivity(getIntent())
        } catch (exc: ActivityNotFoundException) {
            handleStartActivityException(exc)
        }
    }

    override fun startActivityForResult(requestCode: Int, options: Bundle?) {
        try {
            startActivity(getIntent(), options)
        } catch (exc: ActivityNotFoundException) {
            handleStartActivityException(exc)
        }
    }

    @Throws(ActivityNotFoundException::class)
    private fun startActivity(intent: Intent, options: Bundle? = null) {
        try {
            ContextCompat.startActivity(context, intent, options)
        } catch (exc: AndroidRuntimeException) {
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            ContextCompat.startActivity(context, intent, options)
        }
    }

    override fun startActivityForResult(callback: ActivityResultCallback) {
        activityResultCallback = callback
        OmegaHandleResultActivity.start(context, this)
    }

    protected fun handleStartActivityException(exc: ActivityNotFoundException) {
        if (toastMessage != null) {
            Toast.makeText(context, toastMessage!!.getCharSequence(context), Toast.LENGTH_SHORT).show()
        }
        failCallback?.onActivityStartError(exc)
        failIntent?.let {
            context.startActivity(failIntent)
        }
        failIntentHandler?.startActivity()

        if (toastMessage == null && failCallback == null
                && failIntent == null && failIntentHandler == null) {
            throw RuntimeException(exc)
        }
    }

    protected fun onActivityResult(resultCode: Int, data: Intent?) {
        activityResultCallback?.onActivityResult(resultCode, data)
    }

    override fun getIntent(): Intent {
        return if (chooserTitle == null) createdIntent else createChooserIntent()
    }

    override fun addFlagsClearBackStack(): IntentHandler {
        return addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * Add additional flags to the createdIntent (or with existing flags
     * value).
     *
     * @param flags The new flags to set.
     *
     * @return Returns the same ContextIntentHandler object, for chaining multiple calls
     * into a single statement.
     */
    override fun addFlags(flags: Int): IntentHandler {
        createdIntent.addFlags(flags)
        return this
    }

    /**
     * Set special flags controlling how this createdIntent is handled.  Most values
     * here depend on the mimeType of component being executed by the Intent,
     * specifically the FLAG_ACTIVITY_* flags are all for use with
     * {@link Context#startActivity Context.startActivity()} and the
     * FLAG_RECEIVER_* flags are all for use with
     * {@link Context#sendBroadcast(Intent) Context.sendBroadcast()}.
     *
     * @param flags The desired flags.
     *
     * @return Returns the same ContextIntentHandler object, for chaining multiple calls
     * into a single statement.
     */
    override fun setFlags(flags: Int): IntentHandler {
        createdIntent.flags = flags
        return this
    }

}