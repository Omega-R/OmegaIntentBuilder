/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * CalendarIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 10, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import androidx.annotation.StringRes
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes.*
import com.omega_r.libs.omegaintentbuilder.types.CalendarAvailabilityTypes
import com.omega_r.libs.omegatypes.Text
import com.omega_r.libs.omegatypes.toText
import java.util.*
import kotlin.collections.ArrayList

/**
 * CalendarIntentBuilder is a helper for starting calendar
 */
class CalendarIntentBuilder(private val actionType: CalendarActionTypes) : BaseActivityBuilder() {

    private var startDate: Long? = null
    private var endDate: Long? = null
    private var title: Text? = null
    private var location: Text? = null
    private var description: Text? = null
    private var eventId: Long? = null
    private var allDay: Boolean = false
    private var organizer: Text? = null
    private var eventTimeZone: Text? = null
    private var eventEndTimeZone: Text? = null
    private var availabilityType: CalendarAvailabilityTypes? = null
    private var rRule: Text? = null
    private var rDate: Text? = null
    private var duration: Text? = null
    private var dtStart: Long? = null
    private var dtEnd: Long? = null
    private var guestCanModify: Boolean? = null
    private var guestCanInviteOthers: Boolean? = null
    private var guestCanSeeGuests: Boolean? = null
    private var hasAlarm: Boolean? = null
    private var toAddressesSet = mutableListOf<Text>()

    private constructor(parcel: Parcel) : this(parcel.readSerializable() as CalendarActionTypes) {
        startDate = parcel.readValue(Long::class.java.classLoader) as? Long
        endDate = parcel.readValue(Long::class.java.classLoader) as? Long
        title = parcel.readSerializable() as Text?
        location = parcel.readSerializable() as Text?
        description = parcel.readSerializable() as Text?
        eventId = parcel.readValue(Long::class.java.classLoader) as? Long
        allDay = parcel.readByte() != 0.toByte()
        organizer = parcel.readSerializable() as Text?
        eventTimeZone = parcel.readSerializable() as Text?
        availabilityType = parcel.readSerializable() as CalendarAvailabilityTypes?
        rRule = parcel.readSerializable() as Text?
        rDate = parcel.readSerializable() as Text?
        duration = parcel.readSerializable() as Text?
        dtStart = parcel.readValue(Long::class.java.classLoader) as? Long
        dtEnd = parcel.readValue(Long::class.java.classLoader) as? Long
        guestCanModify = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        guestCanInviteOthers = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        guestCanSeeGuests = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        hasAlarm = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        toAddressesSet = parcel.readSerializable() as MutableList<Text>
    }

    /**
     * Set a start date.
     * The Julian start day of the instance, relative to the Calendar's time zone.
     *
     * @param startDate Date
     * @return This CalendarIntentBuilder for method chaining
     */
    fun startDate(startDate: Date): CalendarIntentBuilder {
        return startDate(startDate.time)
    }

    /**
     * Set a start date.
     * The Julian start day of the instance, relative to the Calendar's time zone.
     *
     * @param startDate Long
     * @return This CalendarIntentBuilder for method chaining
     */
    fun startDate(startDate: Long): CalendarIntentBuilder {
        if (startDate < 0) {
            throw IllegalStateException("StartDate can't be < 0")
        }
        this.startDate = startDate
        return this
    }

    /**
     * Set a endDate date.
     * The Julian end day of the instance, relative to the Calendar's time zone.
     *
     * @param endDate Date
     * @return This CalendarIntentBuilder for method chaining
     */
    fun endDate(endDate: Date): CalendarIntentBuilder {
        return endDate(endDate.time)
    }

    /**
     * Set a endDate date.
     * The Julian end day of the instance, relative to the Calendar's time zone.
     *
     * @param endDate Long
     * @return This CalendarIntentBuilder for method chaining
     */
    fun endDate(endDate: Long): CalendarIntentBuilder {
        if (endDate < 0) {
            throw IllegalStateException("EndDate can't be < 0")
        }
        this.endDate = endDate
        return this
    }

    /**
     * Set a title. The title of the event.
     *
     * @param title String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun title(title: String): CalendarIntentBuilder {
        this.title = Text.from(title)
        return this
    }

    /**
     * Set a title. The title of the event.
     *
     * @param title Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun title(@StringRes title: Int): CalendarIntentBuilder {
        this.title = Text.from(title)
        return this
    }

    /**
     * Set a location. Where the event takes place.
     *
     * @param location String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun location(location: String): CalendarIntentBuilder {
        this.location = Text.from(location)
        return this
    }

    /**
     * Set a location. Where the event takes place.
     *
     * @param location Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun location(@StringRes location: Int): CalendarIntentBuilder {
        this.location = Text.from(location)
        return this
    }

    /**
     * Set a description. The description of the event.
     *
     * @param description String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun description(description: String): CalendarIntentBuilder {
        this.description = Text.from(description)
        return this
    }

    /**
     * Set a description. The description of the event.
     *
     * @param description Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun description(@StringRes description: Int): CalendarIntentBuilder {
        this.description = Text.from(description)
        return this
    }

    /**
     * Set a event id.
     *
     * @param eventId Long
     * @return This CalendarIntentBuilder for method chaining
     */
    fun eventId(eventId: Long): CalendarIntentBuilder {
        if (eventId < 0) {
            throw IllegalStateException("EventId can't be < 0")
        }
        this.eventId = eventId
        return this
    }

    /**
     * Set allDay. A value of true indicates this event occupies the entire day,
     * as defined by the local time zone.
     * A value of false indicates it is a regular event that may start and end at any time during a day.
     *
     * @param allDay Boolean
     * @return This CalendarIntentBuilder for method chaining
     */
    fun allDay(allDay: Boolean): CalendarIntentBuilder {
        this.allDay = allDay
        return this
    }

    /**
     * Set a organizer email. Email of the organizer (owner) of the event.
     *
     * @param organizer String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun organizer(organizerEmail: String): CalendarIntentBuilder {
        organizer = Text.from(organizerEmail)
        return this
    }

    /**
     * Set a organizer email. Email of the organizer (owner) of the event.
     *
     * @param organizer Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun organizer(@StringRes organizerEmail: Int): CalendarIntentBuilder {
        organizer = Text.from(organizerEmail)
        return this
    }

    /**
     * Set a event TimeZone.
     *
     * @param eventTimeZone String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun eventTimeZone(eventTimeZone: String): CalendarIntentBuilder {
        this.eventTimeZone = Text.from(eventTimeZone)
        return this
    }

    /**
     * Set a event TimeZone.
     *
     * @param eventTimeZone String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun eventTimeZone(@StringRes eventTimeZone: Int): CalendarIntentBuilder {
        this.eventTimeZone = Text.from(eventTimeZone)
        return this
    }

    /**
     * Set a event end TimeZone. The time zone for the end time of the event.
     *
     * @param eventEndTimeZone String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun eventEndTimeZone(eventEndTimeZone: String): CalendarIntentBuilder {
        this.eventEndTimeZone = Text.from(eventEndTimeZone)
        return this
    }

    /**
     * Set a event end TimeZone. The time zone for the end time of the event.
     *
     * @param eventEndTimeZone Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun eventEndTimeZone(@StringRes eventEndTimeZone: Int): CalendarIntentBuilder {
        this.eventEndTimeZone = Text.from(eventEndTimeZone)
        return this
    }

    /**
     * Set a availability. If this event counts as busy time or is free time that can be scheduled over.
     *
     * @param availability CalendarAvailabilityTypes
     * @return This CalendarIntentBuilder for method chaining
     */
    fun availability(availability: CalendarAvailabilityTypes): CalendarIntentBuilder {
        availabilityType = availability
        return this
    }

    /**
     * Set a rRule.
     * The recurrence rule for the event format. For example, "FREQ=WEEKLY;COUNT=10;WKST=SU".
     * You can find more examples here (http://tools.ietf.org/html/rfc5545#section-3.8.5.3)
     *
     * @param rRule String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun rRule(rRule: String): CalendarIntentBuilder {
        this.rRule = Text.from(rRule)
        return this
    }

    /**
     * Set a rRule.
     * The recurrence rule for the event format. For example, "FREQ=WEEKLY;COUNT=10;WKST=SU".
     * You can find more examples here (http://tools.ietf.org/html/rfc5545#section-3.8.5.3)
     *
     * @param rRule Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun rRule(@StringRes rRule: Int): CalendarIntentBuilder {
        this.rRule = Text.from(rRule)
        return this
    }

    /**
     * Set a rDate.
     * The recurrence dates for the event.
     * You typically use RDATE in conjunction with RRULE (https://developer.android.com/reference/android/provider/CalendarContract.EventsColumns.html#RDATE)
     * to define an aggregate set of repeating occurrences.
     * For more discussion, see the RFC5545 spec. (http://tools.ietf.org/html/rfc5545#section-3.8.5.2)
     *
     * @param rDate String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun rDate(rDate: String): CalendarIntentBuilder {
        this.rDate = Text.from(rDate)
        return this
    }

    /**
     * Set a rDate.
     * The recurrence dates for the event.
     * You typically use RDATE in conjunction with RRULE (https://developer.android.com/reference/android/provider/CalendarContract.EventsColumns.html#RDATE)
     * to define an aggregate set of repeating occurrences.
     * For more discussion, see the RFC5545 spec. (http://tools.ietf.org/html/rfc5545#section-3.8.5.2)
     *
     * @param rDate Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun rDate(@StringRes rDate: Int): CalendarIntentBuilder {
        this.rDate = Text.from(rDate)
        return this
    }

    /**
     * Set a duration.
     *
     * The duration of the event in RFC5545 format.
     * For example, a value of "PT1H" states that the event should last one hour,
     * and a value of "P2W" indicates a duration of 2 weeks.
     *
     * @param duration String
     * @return This CalendarIntentBuilder for method chaining
     */
    fun duration(duration: String): CalendarIntentBuilder {
        this.duration = Text.from(duration)
        return this
    }

    /**
     * Set a duration.
     *
     * The duration of the event in RFC5545 format.
     * For example, a value of "PT1H" states that the event should last one hour,
     * and a value of "P2W" indicates a duration of 2 weeks.
     *
     * @param duration Int
     * @return This CalendarIntentBuilder for method chaining
     */
    fun duration(@StringRes duration: Int): CalendarIntentBuilder {
        this.duration = Text.from(duration)
        return this
    }

    /**
     * Set a DTSTART. The time the event starts in UTC milliseconds since the epoch.
     *
     * @param dtStart Date
     * @return This CalendarIntentBuilder for method chaining
     */
    fun dtStart(dtStart: Date): CalendarIntentBuilder {
        return dtStart(dtStart.time)
    }

    /**
     * Set a DTSTART. The time the event starts in UTC milliseconds since the epoch.
     *
     * @param dtStart Long
     * @return This CalendarIntentBuilder for method chaining
     */
    fun dtStart(dtStart: Long): CalendarIntentBuilder {
        if (dtStart < 0) {
            throw IllegalStateException("dtStart can't be < 0")
        }
        this.dtStart = dtStart
        return this
    }

    /**
     * Set a DTEND. The time the event ends in UTC milliseconds since the epoch.
     *
     * @param dtEnd Date
     * @return This CalendarIntentBuilder for method chaining
     */
    fun dtEnd(dtEnd: Date): CalendarIntentBuilder {
        return dtEnd(dtEnd.time)
    }

    /**
     * Set a DTEND. The time the event ends in UTC milliseconds since the epoch.
     *
     * @param dtEnd Long
     * @return This CalendarIntentBuilder for method chaining
     */
    fun dtEnd(dtEnd: Long): CalendarIntentBuilder {
        if (dtEnd < 0) {
            throw IllegalStateException("dtEnd can't be < 0")
        }
        this.dtEnd = dtEnd
        return this
    }

    /**
     * Set a DTEND. Whether guests can modify the event.
     *
     * @param guestCanModify Boolean
     * @return This CalendarIntentBuilder for method chaining
     */
    fun guestCanModify(guestCanModify: Boolean): CalendarIntentBuilder {
        this.guestCanModify = guestCanModify
        return this
    }

    /**
     * Set a DTEND. Whether guests can invite other guests.
     *
     * @param guestCanInviteOthers Boolean
     * @return This CalendarIntentBuilder for method chaining
     */
    fun guestCanInviteOthers(guestCanInviteOthers: Boolean): CalendarIntentBuilder {
        this.guestCanInviteOthers = guestCanInviteOthers
        return this
    }

    /**
     * Set a DTEND. Whether guests can see the list of attendees.
     *
     * @param guestCanSeeGuests Boolean
     * @return This CalendarIntentBuilder for method chaining
     */
    fun guestCanSeeGuests(guestCanSeeGuests: Boolean): CalendarIntentBuilder {
        this.guestCanSeeGuests = guestCanSeeGuests
        return this
    }

    /**
     * Add Collection of email addresses as recipients of this share.
     *
     * @param addresses Email addresses to share to
     * @return This CalendarIntentBuilder for method chaining
     */
    fun emailTo(addresses: Collection<String>): CalendarIntentBuilder {
        toAddressesSet.addAll(addresses.map { it.toText() })
        return this
    }

    /**
     * Add Collection of email addresses as recipients of this share.
     *
     * @param addresses Email addresses to share to
     * @return This CalendarIntentBuilder for method chaining
     */
    fun emailTo(vararg address: String): CalendarIntentBuilder {
        toAddressesSet.addAll(address.map { it.toText() })
        return this
    }

    /**
     * Add Collection of email addresses as recipients of this share.
     *
     * @param addresses Email addresses to share to
     * @return This CalendarIntentBuilder for method chaining
     */
    fun emailTo(@StringRes addressRes: Int): CalendarIntentBuilder {
        toAddressesSet.add(Text.from(addressRes))
        return this
    }

    /**
     * Set a hasAlarm. Whether guests can see the list of attendees.
     * Whether the event has an alarm or not. Column name.
     *
     * @param hasAlarm Boolean
     * @return This CalendarIntentBuilder for method chaining
     */
    fun hasAlarm(hasAlarm: Boolean): CalendarIntentBuilder {
        this.hasAlarm = hasAlarm
        return this
    }

    override fun createIntent(context: Context): Intent {
        return when (actionType) {
            VIEW_DATE -> createViewIntent()
            VIEW_EVENT -> createEventIntent(context)
            EDIT_EVENT -> createEventIntent(context)
            INSERT_EVENT -> createInsertIntent(context)
        }
    }

    private fun createViewIntent(): Intent {
        if (startDate == null) {
            startDate = Date().time
        }
        val intent = Intent(actionType.intentAction)
        val builder = CalendarContract.CONTENT_URI.buildUpon()
        builder.appendPath("time")
        ContentUris.appendId(builder, startDate!!)
        intent.data = builder.build()

        return intent
    }

    private fun createEventIntent(context: Context): Intent {
        if (eventId == null) {
            throw IllegalStateException("You can't call createIntent with CalendarActionType == $actionType and null event id")
        }
        val uri = ContentUris.withAppendedId(Events.CONTENT_URI, eventId!!)
        val intent = Intent(actionType.intentAction).setData(uri)
        if (actionType == EDIT_EVENT && title?.isEmpty() == false) {
            intent.putExtra(Events.TITLE, title!!.getCharSequence(context))
        }

        return intent
    }

    private fun createInsertIntent(context: Context): Intent {
        if (startDate == null) {
            throw IllegalStateException("You can't call createIntent with CalendarActionType == INSERT_EVENT and empty startDate")
        }
        val intent = Intent(actionType.intentAction)
        intent.data = Events.CONTENT_URI

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate!!)
        endDate?.let {
            if (endDate!! <= startDate!!) {
                throw IllegalStateException("EndDate can't be <= StartDate")
            }
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate!!)
        }
        title?.let { intent.putExtra(Events.TITLE, it.getCharSequence(context)) }
        location?.let { intent.putExtra(Events.EVENT_LOCATION, it.getString(context)) }
        description?.let { intent.putExtra(Events.DESCRIPTION, it.getCharSequence(context)) }
        organizer?.let { intent.putExtra(Events.ORGANIZER, it.getString(context)) }

        eventTimeZone?.let { intent.putExtra(Events.EVENT_TIMEZONE, it.getString(context)) }
        eventEndTimeZone?.let { intent.putExtra(Events.EVENT_END_TIMEZONE, it.getString(context)) }

        intent.putExtra(Events.ALL_DAY, allDay)
        availabilityType?.let { intent.putExtra(Events.AVAILABILITY, availabilityType!!.availability) }

        duration?.let { intent.putExtra(Events.DURATION, it.getString(context)) }
        rRule?.let { intent.putExtra(Events.RRULE, it.getString(context)) }
        rDate?.let { intent.putExtra(Events.RDATE, it.getString(context)) }
        if (dtStart != null && dtEnd != null && dtEnd!! <= dtStart!!) {
            throw IllegalStateException("dtEnd can't be <= dtStart")
        }
        dtStart?.let { intent.putExtra(Events.DTSTART, dtStart!!) }
        dtEnd?.let { intent.putExtra(Events.DTEND, dtEnd!!) }

        guestCanModify?.let { intent.putExtra(Events.GUESTS_CAN_MODIFY, guestCanModify!!) }
        guestCanInviteOthers?.let { intent.putExtra(Events.GUESTS_CAN_INVITE_OTHERS, guestCanInviteOthers!!) }
        guestCanSeeGuests?.let { intent.putExtra(Events.GUESTS_CAN_SEE_GUESTS, guestCanSeeGuests!!) }

        if (toAddressesSet.isNotEmpty()) {
            intent.putExtra(Intent.EXTRA_EMAIL, toAddressesSet.map { it.getString(context) }.toTypedArray())
        }

        hasAlarm?.let { intent.putExtra(Events.HAS_ALARM, hasAlarm!!) }

        return intent
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(actionType)
        parcel.writeValue(startDate)
        parcel.writeValue(endDate)
        parcel.writeSerializable(title)
        parcel.writeSerializable(location)
        parcel.writeSerializable(description)
        parcel.writeValue(eventId)
        parcel.writeByte(if (allDay) 1 else 0)
        parcel.writeSerializable(organizer)
        parcel.writeSerializable(eventTimeZone)
        parcel.writeSerializable(eventEndTimeZone)
        parcel.writeSerializable(availabilityType)
        parcel.writeSerializable(rRule)
        parcel.writeSerializable(rDate)
        parcel.writeSerializable(duration)
        parcel.writeValue(dtStart)
        parcel.writeValue(dtEnd)
        parcel.writeValue(guestCanModify)
        parcel.writeValue(guestCanInviteOthers)
        parcel.writeValue(guestCanSeeGuests)
        parcel.writeValue(hasAlarm)
        parcel.writeSerializable(ArrayList(toAddressesSet))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<CalendarIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): CalendarIntentBuilder {
            return CalendarIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<CalendarIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}