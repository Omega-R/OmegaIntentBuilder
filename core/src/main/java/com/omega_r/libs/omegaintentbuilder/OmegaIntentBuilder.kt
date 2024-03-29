/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * OmegaIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */

package com.omega_r.libs.omegaintentbuilder

import android.app.Activity
import android.app.Service
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.omega_r.libs.omegaintentbuilder.builders.*
import com.omega_r.libs.omegaintentbuilder.builders.pick.PickBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.EmailIntentBuilder
import com.omega_r.libs.omegaintentbuilder.builders.share.ShareIntentBuilder
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes
import com.omega_r.libs.omegaintentbuilder.types.MapTypes
import com.omega_r.libs.omegaintentbuilder.types.ShowType
import com.omega_r.libs.omegatypes.Text

/**
 * OmegaIntentBuilder class for creating supports createdIntent builders.
 */
object OmegaIntentBuilder {

    /**
     * @param phoneNumber String for calling
     * @return CallIntentBuilder for creating call Intent
     */
    @JvmStatic
    fun call(phoneNumber: String = "") = CallIntentBuilder(phoneNumber)

    /**
     * @return ShareIntentBuilder for creating share Intent
     */
    @JvmStatic
    fun share() = ShareIntentBuilder()

    @JvmStatic
    fun view() = ViewIntentBuilder()

    /**
     * @return ShareIntentBuilder for creating email Intent
     */
    @JvmStatic
    fun email() = EmailIntentBuilder()

    /**
     * @param urlAddress String
     * @return BrowserBuilder for creating intent to start web browser
     */
    @JvmStatic
    fun web(urlAddress: String) = BrowserBuilder(urlAddress)

    /**
     * @param urlAddress Text
     * @return BrowserBuilder for creating intent to start web browser
     */
    @JvmStatic
    fun web(urlAddress: Text) = BrowserBuilder(urlAddress)

    /**
     * @param uri Uri
     * @return BrowserBuilder for creating intent to start web browser
     */
    @JvmStatic
    fun web(uri: Uri) = BrowserBuilder(uri)

    /**
     * @return SettingsIntentBuilder for creating intent to start settings
     */
    @JvmStatic
    fun settings() = SettingsIntentBuilder()

    /**
     * @return PlayStoreBuilder for creating intent to open PlayStore
     */
    @JvmStatic
    fun playStore() = PlayStoreBuilder()

    /**
     * @param actionType CalendarActionTypes
     * @return CalendarIntentBuilder for method chaining
     */
    @JvmOverloads
    @JvmStatic
    fun calendar(actionType: CalendarActionTypes = CalendarActionTypes.VIEW_DATE) = CalendarIntentBuilder(actionType)

    /**
     * @param addresses Array
     * @return SmsIntentBuilder for method chaining
     */
    @JvmStatic
    fun sms(vararg addresses: String) = SmsIntentBuilder(addresses.asList())

    /**
     * @param addresses Collection
     * @return SmsIntentBuilder for method chaining
     */
    @JvmStatic
    fun sms(addresses: Collection<String>) = SmsIntentBuilder(addresses)

    /**
     * @return SmsIntentBuilder for method chaining
     */
    @JvmStatic
    fun photoCapture() = PhotoCaptureBuilder()

    /**
     * @return CropImageIntentBuilder for method chaining
     */
    @JvmStatic
    fun cropImage() = CropImageIntentBuilder()

    /**
     * @return ActivityIntentBuilder for creating activity intent
     */
    @JvmStatic
    fun <T : Activity> activity(activity: Class<T>) = ActivityIntentBuilder(activity)

    /**
     * @return ServiceIntentBuilder for creating service intent
     */
    @JvmStatic
    fun <T : Service> service(service: Class<T>) = ServiceIntentBuilder(service)

    /**
     * @return MapIntentBuilder for creating intent to open Map application
     */
    @JvmStatic
    fun map(vararg types: MapTypes) = MapIntentBuilder(*types)

    /**
     * @return PickBuilder for pick different files
     */
    @JvmStatic
    fun pick() = PickBuilder

    /**
     * @return SpeechToTextBuilder
     */
    @JvmStatic
    fun speechToText() = SpeechToTextBuilder()

    /**
     * @return InsertContactIntentBuilder
     */
    @JvmStatic
    fun insertContact() = InsertContactIntentBuilder()

    /**
     * @return AlarmIntentBuilder
     */
    @JvmStatic
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun createTimer() = TimerIntentBuilder()


    /**
     * @return AlarmIntentBuilder
     */
    @JvmStatic
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun createAlarm() = AlarmIntentBuilder()

    /**
     * @return ShowAlarmsTimersIntentBuilder
     */
    @JvmStatic
    fun showTimers() = ShowAlarmsTimersIntentBuilder(ShowType.TIMERS)

    /**
     * @return ShowAlarmsTimersIntentBuilder
     */
    @JvmStatic
    fun showAlarms() = ShowAlarmsTimersIntentBuilder(ShowType.ALARMS)

    /**
     * @return VideoRecordBuilder
     */
    @JvmStatic
    fun recordVideo() = VideoRecordBuilder()

}