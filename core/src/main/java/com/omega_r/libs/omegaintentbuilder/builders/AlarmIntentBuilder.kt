package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import android.provider.AlarmClock.*

class AlarmIntentBuilder() : BaseActivityBuilder() {
    private var message: String? = null
    private var hour: Int? = null
    private var minutes: Int? = null
    private var ringtone: String? = null
    private var skipUI: Boolean = false
    private var vibrate: Boolean? = null
    private var ringtoneSilent: Boolean = false

    constructor(parcel: Parcel) : this() {
        message = parcel.readString()
        hour = parcel.readValue(Int::class.java.classLoader) as? Int
        minutes = parcel.readValue(Int::class.java.classLoader) as? Int
        ringtone = parcel.readString()
        skipUI = parcel.readByte() != 0.toByte()
        vibrate = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        ringtoneSilent = parcel.readByte() != 0.toByte()
    }

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
     * <p>
     * The value is an {@link Integer} and ranges from 1 to 86400 (24 hours).
     * </p>
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
     * Set a ringtone to be played with this alarm.
     *
     * This value is a {@link String} and can either be set to {@link #VALUE_RINGTONE_SILENT} or
     * to a content URI of the media to be played. If not specified or the URI doesn't exist,
     * {@code "content://settings/system/alarm_alert} will be used.
     *
     * @param ringtone String
     * @return This AlarmIntentBuilder for method chaining
     */
    fun ringtone(ringtone: String): AlarmIntentBuilder {
        this.ringtone = ringtone
        return this
    }

    /**
     * If true, the application is asked to bypass any intermediate UI. If false, the application
     * may display intermediate UI like a confirmation dialog or settings.
     *
     * @return This AlarmIntentBuilder for method chaining
     */
    fun skipUI(): AlarmIntentBuilder {
        skipUI = true
        return this
    }

    /**
     *  Set whether or not to activate the device vibrator.
     *
     * The value is a {@link Boolean}. The default is {@code true}.
     *
     * @return This AlarmIntentBuilder for method chaining
     */
    @JvmOverloads
    fun vibrate(vibrate: Boolean = true): AlarmIntentBuilder {
        this.vibrate = vibrate
        return this
    }

    /**
     *  Bundle extra value: Indicates no ringtone should be played.
     *
     *  @return This AlarmIntentBuilder for method chaining
     */
    fun ringtoneSilent(): AlarmIntentBuilder {
        ringtoneSilent = true
        return this
    }

    override fun createIntent(context: Context): Intent {
        return Intent(ACTION_SET_ALARM).apply {
            message?.let {
                putExtra(EXTRA_MESSAGE, it)
            }

            hour?.let {
                putExtra(EXTRA_HOUR, it)
            }

            minutes?.let {
                putExtra(EXTRA_MINUTES, it)
            }

            ringtone?.let {
                putExtra(EXTRA_RINGTONE, it)
            }

            if (skipUI) {
                putExtra(EXTRA_SKIP_UI, skipUI)
            }

            vibrate?.let {
                putExtra(EXTRA_VIBRATE, it)
            }

            if (ringtoneSilent) {
                putExtra(VALUE_RINGTONE_SILENT, ringtoneSilent)
            }

        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
        parcel.writeValue(hour)
        parcel.writeValue(minutes)
        parcel.writeString(ringtone)
        parcel.writeByte(if (skipUI) 1 else 0)
        parcel.writeValue(vibrate)
        parcel.writeByte(if (ringtoneSilent) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<AlarmIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): AlarmIntentBuilder {
            return AlarmIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<AlarmIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}