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
import android.provider.CalendarContract
import android.provider.CalendarContract.*
import androidx.annotation.StringRes
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes.*
import com.omega_r.libs.omegaintentbuilder.types.CalendarAvailabilityTypes
import java.util.*

/**
 * CalendarIntentBuilder is a helper for starting calendar
 */
class CalendarIntentBuilder(private val context: Context,
                            private val actionType: CalendarActionTypes): BaseActivityBuilder(context) {

  private var intent: Intent? = null

  private var startDate: Long? = null
  private var endDate: Long? = null
  private var title: String? = null
  private var location: String? = null
  private var description: String? = null
  private var eventId: Long? = null
  private var allDay: Boolean = false
  private var organizer: String? = null
  private var eventTimeZone: String? = null
  private var eventEndTimeZone: String? = null
  private var availabilityType: CalendarAvailabilityTypes? = null
  private var rRule: String? = null
  private var rDate: String? = null
  private var duration: String? = null
  private var dtStart: Long? = null
  private var dtEnd: Long? = null
  private var guestCanModify: Boolean? = null
  private var guestCanInviteOthers: Boolean? = null
  private var guestCanSeeGuests: Boolean? = null
  private var hasAlarm: Boolean? = null
  private var toAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)

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
    this.title = title
    return this
  }

  /**
   * Set a title. The title of the event.
   *
   * @param title Int
   * @return This CalendarIntentBuilder for method chaining
   */
  fun title(@StringRes title: Int): CalendarIntentBuilder {
    this.title = context.getString(title)
    return this
  }

  /**
   * Set a location. Where the event takes place.
   *
   * @param location String
   * @return This CalendarIntentBuilder for method chaining
   */
  fun location(location: String): CalendarIntentBuilder {
    this.location = location
    return this
  }

  /**
   * Set a location. Where the event takes place.
   *
   * @param location Int
   * @return This CalendarIntentBuilder for method chaining
   */
  fun location(@StringRes location: Int): CalendarIntentBuilder {
    this.location = context.getString(location)
    return this
  }

  /**
   * Set a description. The description of the event.
   *
   * @param description String
   * @return This CalendarIntentBuilder for method chaining
   */
  fun description(description: String): CalendarIntentBuilder {
    this.description = description
    return this
  }

  /**
   * Set a description. The description of the event.
   *
   * @param description Int
   * @return This CalendarIntentBuilder for method chaining
   */
  fun description(@StringRes description: Int): CalendarIntentBuilder {
    this.description = context.getString(description)
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
    organizer = organizerEmail
    return this
  }

  /**
   * Set a organizer email. Email of the organizer (owner) of the event.
   *
   * @param organizer Int
   * @return This CalendarIntentBuilder for method chaining
   */
  fun organizer(@StringRes organizerEmail: Int): CalendarIntentBuilder {
    organizer = context.getString(organizerEmail)
    return this
  }

  /**
   * Set a event TimeZone.
   *
   * @param eventTimeZone String
   * @return This CalendarIntentBuilder for method chaining
   */
  fun eventTimeZone(eventTimeZone: String): CalendarIntentBuilder {
    this.eventTimeZone = eventTimeZone
    return this
  }

  /**
   * Set a event TimeZone.
   *
   * @param eventTimeZone String
   * @return This CalendarIntentBuilder for method chaining
   */
  fun eventTimeZone(@StringRes eventTimeZone: Int): CalendarIntentBuilder {
    this.eventTimeZone = context.getString(eventTimeZone)
    return this
  }

  /**
   * Set a event end TimeZone. The time zone for the end time of the event.
   *
   * @param eventEndTimeZone String
   * @return This CalendarIntentBuilder for method chaining
   */
  fun eventEndTimeZone(eventEndTimeZone: String): CalendarIntentBuilder {
    this.eventEndTimeZone = eventEndTimeZone
    return this
  }

  /**
   * Set a event end TimeZone. The time zone for the end time of the event.
   *
   * @param eventEndTimeZone Int
   * @return This CalendarIntentBuilder for method chaining
   */
  fun eventEndTimeZone(@StringRes eventEndTimeZone: Int): CalendarIntentBuilder {
    this.eventEndTimeZone = context.getString(eventEndTimeZone)
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
    this.rRule = rRule
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
    this.rRule = context.getString(rRule)
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
    this.rDate = rDate
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
    this.rDate = context.getString(rDate)
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
    this.duration = duration
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
    this.duration = context.getString(duration)
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
    toAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add Collection of email addresses as recipients of this share.
   *
   * @param addresses Email addresses to share to
   * @return This CalendarIntentBuilder for method chaining
   */
  fun emailTo(vararg address: String): CalendarIntentBuilder {
    toAddressesSet.addAll(address)
    return this
  }

  /**
   * Add Collection of email addresses as recipients of this share.
   *
   * @param addresses Email addresses to share to
   * @return This CalendarIntentBuilder for method chaining
   */
  fun emailTo(@StringRes addressRes: Int): CalendarIntentBuilder {
    toAddressesSet.add(context.getString(addressRes))
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

  override fun createIntent(): Intent {
    when(actionType) {
      VIEW_DATE -> intent = createViewIntent()
      VIEW_EVENT -> intent = createEventIntent()
      EDIT_EVENT -> intent = createEventIntent()
      INSERT_EVENT -> intent = createInsertIntent()
    }

    return intent!!
  }

  private fun createViewIntent(): Intent {
    if (startDate == null) {
      startDate = Date().time
    }
    val intent = Intent(actionType.intentAction)
    val builder = CalendarContract.CONTENT_URI.buildUpon()
    builder.appendPath("time")
    ContentUris.appendId(builder, startDate!!)
    intent.setData(builder.build())

    return intent
  }

  private fun createEventIntent(): Intent {
    if (eventId == null) {
      throw IllegalStateException("You can't call createIntent with CalendarActionType == $actionType and null event id")
    }
    val uri = ContentUris.withAppendedId(Events.CONTENT_URI, eventId!!)
    val intent = Intent(actionType.intentAction).setData(uri)
    if (actionType == EDIT_EVENT && !title.isNullOrEmpty()) {
      intent.putExtra(Events.TITLE, title)
    }

    return intent
  }

  private fun createInsertIntent(): Intent {
    if (startDate == null) {
      throw IllegalStateException("You can't call createIntent with CalendarActionType == INSERT_EVENT and empty startDate")
    }
    val intent = Intent(actionType.intentAction)
    intent.setData(Events.CONTENT_URI)

    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate!!)
    endDate?.let {
      if (endDate!! <= startDate!!) {
        throw IllegalStateException("EndDate can't be <= StartDate")
      }
      intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate!!)
    }
    title?.let { intent.putExtra(Events.TITLE, title) }
    location?.let { intent.putExtra(Events.EVENT_LOCATION, location) }
    description?.let { intent.putExtra(Events.DESCRIPTION, description) }
    organizer?.let { intent.putExtra(Events.ORGANIZER, organizer) }

    eventTimeZone?.let { intent.putExtra(Events.EVENT_TIMEZONE, eventTimeZone) }
    eventEndTimeZone?.let { intent.putExtra(Events.EVENT_END_TIMEZONE, eventEndTimeZone) }

    intent.putExtra(Events.ALL_DAY, allDay)
    availabilityType?.let { intent.putExtra(Events.AVAILABILITY, availabilityType!!.availability) }

    duration?.let { intent.putExtra(Events.DURATION, duration) }
    rRule?.let { intent.putExtra(Events.RRULE, rRule) }
    rDate?.let { intent.putExtra(Events.RDATE, rDate) }
    if (dtStart != null && dtEnd != null && dtEnd!! <= dtStart!!) {
      throw IllegalStateException("dtEnd can't be <= dtStart")
    }
    dtStart?.let { intent.putExtra(Events.DTSTART, dtStart!!) }
    dtEnd?.let { intent.putExtra(Events.DTEND, dtEnd!!) }

    guestCanModify?.let { intent.putExtra(Events.GUESTS_CAN_MODIFY, guestCanModify!!) }
    guestCanInviteOthers?.let { intent.putExtra(Events.GUESTS_CAN_INVITE_OTHERS, guestCanInviteOthers!!) }
    guestCanSeeGuests?.let { intent.putExtra(Events.GUESTS_CAN_SEE_GUESTS, guestCanSeeGuests!!) }

    if (toAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_EMAIL, toAddressesSet.toTypedArray())
    }

    hasAlarm?.let { intent.putExtra(Events.HAS_ALARM, hasAlarm!!) }

    return intent
  }

}