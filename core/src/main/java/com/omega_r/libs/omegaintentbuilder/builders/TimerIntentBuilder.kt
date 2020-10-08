package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock

class TimerIntentBuilder : BaseActivityBuilder() {
    private var message: String? = null
    private var seconds: Int? = null
    private var skipUI: Boolean? = null

    /**
     * Set custom message for the timer.
     * <p>
     * The value is a {@link String}.
     * </p>
     *
     * @param message String
     * @return This TimerIntentBuilder for method chaining
     */
    fun message(message: String): TimerIntentBuilder {
        this.message = message
        return this
    }

    /**
     * Set the length of the timer in seconds.
     *
     * @param seconds String
     * @return This TimerIntentBuilder for method chaining
     */
    fun seconds(seconds: Int): TimerIntentBuilder {
        this.seconds = seconds
        return this
    }

    /**
     * Bundle extra: Whether or not to display an activity after performing the action.
     * If true, the application is asked to bypass any intermediate UI. If false, the application
     * may display intermediate UI like a confirmation dialog or settings.
     *
     * @param skipUI Boolean
     * @return This TimerIntentBuilder for method chaining
     */
    fun skipUI(skipUI: Boolean): TimerIntentBuilder {
        this.skipUI = skipUI
        return this
    }

    override fun createIntent(context: Context): Intent {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Intent(AlarmClock.ACTION_SET_TIMER).apply {
                message?.let {
                    putExtra(AlarmClock.EXTRA_MESSAGE, it)
                }

                seconds?.let {
                    putExtra(AlarmClock.EXTRA_LENGTH, it)
                }

                seconds?.let {
                    putExtra(AlarmClock.EXTRA_SKIP_UI, it)
                }

            }
        } else {
            TODO("VERSION.SDK_INT < KITKAT")
        }
    }

}