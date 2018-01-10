package com.omega_r.libs.omegaintentbuilder

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.provider.CalendarContract.*
import android.support.annotation.StringRes
import com.omega_r.libs.omegaintentbuilder.builders.BaseBuilder
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes.*
import com.omega_r.libs.omegaintentbuilder.types.CalendarAvailabilityTypes
import java.util.*

class CalendarIntentBuilder(private val context: Context): BaseBuilder(context) {

  private var intent: Intent? = null

  private var actionType = CalendarActionTypes.VIEW_DATE
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

  fun startDate(startDate: Date): CalendarIntentBuilder {
    return startDate(startDate.time)
  }

  fun startDate(startDate: Long): CalendarIntentBuilder {
    if (startDate < 0) {
      throw IllegalStateException("StartDate can't be < 0")
    }
    this.startDate = startDate
    return this
  }

  fun endDate(endDate: Date): CalendarIntentBuilder {
    return endDate(endDate.time)
  }

  fun endDate(endDate: Long): CalendarIntentBuilder {
    if (endDate < 0) {
      throw IllegalStateException("EndDate can't be < 0")
    }
    this.endDate = endDate
    return this
  }

  fun title(title: String): CalendarIntentBuilder {
    this.title = title
    return this
  }

  fun location(location: String): CalendarIntentBuilder {
    this.location = location
    return this
  }

  fun description(description: String): CalendarIntentBuilder {
    this.description = description
    return this
  }

  fun eventId(eventId: Long): CalendarIntentBuilder {
    if (eventId < 0) {
      throw IllegalStateException("EventId can't be < 0")
    }
    this.eventId = eventId
    return this
  }

  fun actionType(action: CalendarActionTypes): CalendarIntentBuilder {
    this.actionType = action
    return this
  }

  fun allDay(allDay: Boolean): CalendarIntentBuilder {
    this.allDay = allDay
    return this
  }

  fun organizer(organizerEmail: String): CalendarIntentBuilder {
    organizer = organizerEmail
    return this
  }

  fun organizer(@StringRes organizerEmail: Int): CalendarIntentBuilder {
    organizer = context.getString(organizerEmail)
    return this
  }

  fun eventTimeZone(eventTimeZone: String): CalendarIntentBuilder {
    this.eventTimeZone = eventTimeZone
    return this
  }

  fun eventEndTimeZone(eventEndTimeZone: String): CalendarIntentBuilder {
    this.eventEndTimeZone = eventEndTimeZone
    return this
  }

  fun availability(availability: CalendarAvailabilityTypes): CalendarIntentBuilder {
    availabilityType = availability
    return this
  }

  fun rRule(rRule: String): CalendarIntentBuilder {
    this.rRule = rRule
    return this
  }

  fun rDate(rDate: String): CalendarIntentBuilder {
    this.rDate = rDate
    return this
  }

  fun duration(duration: String): CalendarIntentBuilder {
    this.duration = duration
    return this
  }

  fun dtStart(dtStart: Date): CalendarIntentBuilder {
    return dtStart(dtStart.time)
  }

  fun dtStart(dtStart: Long): CalendarIntentBuilder {
    if (dtStart < 0) {
      throw IllegalStateException("dtStart can't be < 0")
    }
    this.dtStart = dtStart
    return this
  }

  fun dtEnd(dtEnd: Date): CalendarIntentBuilder {
    return dtEnd(dtEnd.time)
  }

  fun dtEnd(dtEnd: Long): CalendarIntentBuilder {
    if (dtEnd < 0) {
      throw IllegalStateException("dtEnd can't be < 0")
    }
    this.dtEnd = dtEnd
    return this
  }

  fun guestCanModify(guestCanModify: Boolean): CalendarIntentBuilder {
    this.guestCanModify = guestCanModify
    return this
  }

  fun guestCanInviteOthers(guestCanInviteOthers: Boolean): CalendarIntentBuilder {
    this.guestCanInviteOthers = guestCanInviteOthers
    return this
  }

  fun guestCanSeeGuests(guestCanSeeGuests: Boolean): CalendarIntentBuilder {
    this.guestCanSeeGuests = guestCanSeeGuests
    return this
  }

  /**
   * Add Collection of email addresses as recipients of this share.
   *
   * @param addresses Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun emailTo(addresses: Collection<String>): CalendarIntentBuilder {
    toAddressesSet.addAll(addresses)
    return this
  }

  /**
   * Add or single value of email address as recipients of this share.
   *
   * @param address Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun emailTo(vararg address: String): CalendarIntentBuilder {
    toAddressesSet.addAll(address)
    return this
  }

  /**
   * Add or single value of email address as recipients of this share.
   *
   * @param address @StringRes Email addresses to send to
   * @return This BaseShareBuilder for method chaining
   */
  fun emailTo(@StringRes addressRes: Int): CalendarIntentBuilder {
    toAddressesSet.add(context.getString(addressRes))
    return this
  }

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