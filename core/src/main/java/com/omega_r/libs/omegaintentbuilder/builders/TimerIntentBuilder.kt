package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import android.provider.AlarmClock.*

class TimerIntentBuilder() : BaseActivityBuilder() {
    private var message: String? = null
    private var seconds: Int? = null
    private var skipUI: Boolean? = null

    constructor(parcel: Parcel) : this() {
        message = parcel.readString()
        seconds = parcel.readValue(Int::class.java.classLoader) as? Int
        skipUI = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

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
        return Intent(ACTION_SET_TIMER).apply {
            message?.let {
                putExtra(EXTRA_MESSAGE, it)
            }

            seconds?.let {
                putExtra(EXTRA_LENGTH, it)
            }

            skipUI?.let {
                putExtra(EXTRA_SKIP_UI, it)
            }

        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
        parcel.writeValue(seconds)
        parcel.writeValue(skipUI)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<TimerIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): TimerIntentBuilder {
            return TimerIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<TimerIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}