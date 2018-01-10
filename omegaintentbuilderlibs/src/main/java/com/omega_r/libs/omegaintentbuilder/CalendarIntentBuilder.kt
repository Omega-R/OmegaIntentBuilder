package com.omega_r.libs.omegaintentbuilder

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import com.omega_r.libs.omegaintentbuilder.builders.BaseBuilder
import java.util.*

class CalendarIntentBuilder(context: Context): BaseBuilder(context) {

  private var intent: Intent? = null
  private var startDate: Date? = null
  private var endDate: Date? = null
  private var title: String? = null
  private var location: String? = null
  private var description: String? = null

  fun startDate(startDate: Date): CalendarIntentBuilder {
    this.startDate = startDate
    return this
  }

  fun endDate(endDate: Date): CalendarIntentBuilder {
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

  override fun createIntent(): Intent {
    val date = Date()
    val uriBuilder: Uri.Builder = CalendarContract.CONTENT_URI.buildUpon()
    uriBuilder.appendPath("time")
    ContentUris.appendId(uriBuilder, date.time)
    intent = Intent(Intent.ACTION_VIEW)
    intent!!.setData(uriBuilder.build())
    return intent as Intent
  }

}