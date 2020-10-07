package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock

class TimerIntentBuilder : BaseActivityBuilder() {
    private var message: String? = null
    private var seconds: Int? = null

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

    override fun createIntent(context: Context): Intent {
        return Intent(AlarmClock.ACTION_SET_TIMER).apply {
            message?.let {
                putExtra(AlarmClock.EXTRA_MESSAGE, message)
            }

            seconds?.let {
                putExtra(AlarmClock.EXTRA_LENGTH, seconds!!)
            }

            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
    }

}