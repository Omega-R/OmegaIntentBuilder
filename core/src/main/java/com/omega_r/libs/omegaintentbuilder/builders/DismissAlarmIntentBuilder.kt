package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock.*

class DismissAlarmIntentBuilder : BaseActivityBuilder() {

    private var hour: Int? = null
    private var minute: Int? = null
    private var isPM: Boolean? = null
    private var label: String? = null
    private var next: Boolean? = null

    /**
     * </p><p>
     * The value is an {@link Integer} and ranges from 0 to 23.
     * </p>
     *
     * @param hour Integer
     * @return This DismissAlarmIntentBuilder for method chaining
     */
    fun hour(hour: Int): DismissAlarmIntentBuilder {
        label = null
        next = null
        this.hour = hour
        return this
    }

    /**
     * <p>
     * The value is an {@link Integer} and ranges from 0 to 59. If not provided, it defaults to 0.
     * </p>
     *
     * @param minute Integer
     * @return This DismissAlarmIntentBuilder for method chaining
     */
    fun minute(minute: Int): DismissAlarmIntentBuilder {
        label = null
        next = null
        this.minute = minute
        return this
    }

    /**
     * <p>
     * The value is an {@link Integer} and ranges from 0 to 59. If not provided, it defaults to 0.
     * </p>
     *
     * @param isPM Integer
     * @return This DismissAlarmIntentBuilder for method chaining
     */
    fun isPM(isPM: Boolean): DismissAlarmIntentBuilder {
        label = null
        next = null
        this.isPM = isPM
        return this
    }

    /**
     * Search alarm by label
     *
     * @param label String
     * @return This DismissAlarmIntentBuilder for method chaining
     */
    fun label(label: String): DismissAlarmIntentBuilder {
        hour = null
        minute = null
        isPM = null
        next = null
        this.label = label
        return this
    }

    /**
     * For activate search mode NEXT
     * Search next alarm
     *
     * @return This DismissAlarmIntentBuilder for method chaining
     */
    fun next(): DismissAlarmIntentBuilder {
        next = true
        return this
    }

    /**
     * <li>If exactly one active alarm exists, it is dismissed.
     * <li>If more than one active alarm exists, the user is prompted to choose the alarm to
     * dismiss.
     */
    override fun createIntent(context: Context): Intent {
        return Intent(ACTION_DISMISS_ALARM).apply {
            if (hour != null && minute != null && isPM != null) {
                putExtra(EXTRA_ALARM_SEARCH_MODE, ALARM_SEARCH_MODE_TIME)

                hour?.let {
                    putExtra(EXTRA_HOUR, it)
                }

                minute?.let {
                    putExtra(EXTRA_MINUTES, it)
                }

                isPM?.let {
                    putExtra(EXTRA_IS_PM, it)
                }
            } else if (label != null) {
                putExtra(EXTRA_ALARM_SEARCH_MODE, ALARM_SEARCH_MODE_LABEL)
                putExtra(EXTRA_MESSAGE, label)
            } else if (next != null) {
                putExtra(EXTRA_ALARM_SEARCH_MODE, ALARM_SEARCH_MODE_NEXT)
            } else {
                putExtra(EXTRA_ALARM_SEARCH_MODE, ALARM_SEARCH_MODE_ALL)
            }


        }
    }

}