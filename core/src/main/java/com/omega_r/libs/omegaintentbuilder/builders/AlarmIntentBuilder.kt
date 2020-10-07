package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock

class AlarmIntentBuilder : BaseActivityBuilder() {
    private var message: String? = null
    private var hour: Int? = null
    private var minutes: Int? = null
    private var vibrate: Boolean? = null

    /**
     * Set custom message for the alarm.
     * <p>
     * The value is a {@link String}.
     * </p>
     *
     * @param message String
     * @return This AlarmIntentBuilder for method chaining
     */
    fun message(message: String): AlarmIntentBuilder {
        this.message = message
        return this
    }

    /**
     *  Set the hour of the alarm.
     *
     * @param hour String
     * @return This AlarmIntentBuilder for method chaining
     */
    fun hour(hour: Int): AlarmIntentBuilder {
        this.hour = hour
        return this
    }

    /**
     *  Set the minutes of the alarm.
     *
     * @param minutes String
     * @return This AlarmIntentBuilder for method chaining
     */
    fun minutes(minutes: Int): AlarmIntentBuilder {
        this.minutes = minutes
        return this
    }

    /**
     *  Set whether or not to activate the device vibrator.
     *
     * The value is a {@link Boolean}. The default is {@code true}.
     *
     * @param vibrate Boolean
     * @return This AlarmIntentBuilder for method chaining
     */
    fun vibrate(vibrate: Boolean): AlarmIntentBuilder {
        this.vibrate = vibrate
        return this
    }

    override fun createIntent(context: Context): Intent {
        return Intent(AlarmClock.ACTION_SET_ALARM).apply {
            message?.let {
                putExtra(AlarmClock.EXTRA_MESSAGE, message)
            }

            hour?.let {
                putExtra(AlarmClock.EXTRA_HOUR, hour!!)
            }

            minutes?.let {
                putExtra(AlarmClock.EXTRA_MINUTES, minutes!!)
            }

            vibrate?.let {
                putExtra(AlarmClock.EXTRA_VIBRATE, vibrate!!)
            }
        }
    }

}