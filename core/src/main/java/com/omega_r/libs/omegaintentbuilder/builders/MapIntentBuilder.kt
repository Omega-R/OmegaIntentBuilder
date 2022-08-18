/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * MapIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 29, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.IntentBuilderLauncher
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandler
import com.omega_r.libs.omegaintentbuilder.types.MapTypes
import com.omega_r.libs.omegaintentbuilder.types.MapTypes.*
import com.omega_r.libs.omegaintentbuilder.types.MapViewTypes
import com.omega_r.libs.omegaintentbuilder.types.MapViewTypes.*

/**
 * MapIntentBuilder is a helper for open Maps applications
 */
class MapIntentBuilder(private vararg var types: MapTypes) : BaseActivityBuilder() {

    private var latitude: Double? = null
    private var longitude: Double? = null
    private var address: String? = null
    private var failtype: MapTypes? = null
    private var viewType: MapViewTypes? = null
    private var zoom: Int? = null
    private var startLatitude: Double? = null
    private var startLongitude: Double? = null
    private var isDrivingModeEnabled: Boolean = false

    /**
     * Set a latitude.
     *
     * @param latitude Double
     * @return This MapIntentBuilder for method chaining
     */
    fun latitude(latitude: Double): MapIntentBuilder {
        this.latitude = latitude
        return this
    }

    /**
     * Set a longitude.
     *
     * @param longitude Double
     * @return This MapIntentBuilder for method chaining
     */
    fun longitude(longitude: Double): MapIntentBuilder {
        this.longitude = longitude
        return this
    }

    /**
     * Set latitude and longitude.
     *
     * @param latitude Double
     * @param longitude Double
     * @return This MapIntentBuilder for method chaining
     */
    fun latitudeLongitude(latitude: Double, longitude: Double): MapIntentBuilder {
        this.latitude = latitude
        this.longitude = longitude
        return this
    }

    /**
     * Set a searching address.
     *
     * @param address String
     * @return This MapIntentBuilder for method chaining
     */
    fun address(address: String): MapIntentBuilder {
        this.address = address
        return this
    }

    /**
     * Set that app should navigate to certain location, for some of map apps should pass current location
     *
     * @param address String
     * @return This MapIntentBuilder for method chaining
     */
    fun navigate(startLatitude: Double?, startLongitude: Double?, latitude: Double?, longitude: Double?): MapIntentBuilder {
        this.latitude = latitude
        this.longitude = longitude
        this.startLatitude = startLatitude
        this.startLongitude = startLongitude
        return this
    }

    fun openPlayStoreIfFail(type: MapTypes): MapIntentBuilder {
        failtype = type
        return this
    }

    fun zoom(zoom: Int): MapIntentBuilder {
        if (zoom >= 0) this.zoom = zoom
        return this
    }

    fun viewType(viewType: MapViewTypes): MapIntentBuilder {
        this.viewType = viewType
        return this
    }

    fun enableDrivingMode() {
        isDrivingModeEnabled = true
    }

    override fun createIntent(context: Context) =
        if (types.isEmpty()) {
            Intent(Intent.ACTION_VIEW, undefinedMapUri())
        } else {
            // We take first because next type will be handled in WrapperIntentHandler
            val mapType = types.first()
            val uri = getFormattedUri(mapType)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            when (mapType) {
                GOOGLE_MAP -> GOOGLE_MAP.packageName
                YANDEX_MAP -> YANDEX_MAP.packageName
                KAKAO_MAP -> KAKAO_MAP.packageName
                NAVER_MAP -> NAVER_MAP.packageName
            }.also { intent.setPackage(it) }
            intent
        }

    private fun undefinedMapUri(): Uri {
        val sb = StringBuilder()
        if (startLatitude != null && startLongitude != null) {
            sb.append("geo:${startLatitude},${startLongitude}")
            if (latitude != null && longitude != null) {
                sb.append("?q=${latitude},${longitude}")
            }
            address?.let {
                sb.append(" (${Uri.encode(it)})")
            }
        } else if (latitude != null && longitude != null) {
            sb.append("geo:${latitude},${longitude}")
            address?.let {
                sb.append("?q=${Uri.encode(it)}")
            }
        }
        return Uri.parse(sb.toString())
    }

    private fun getFormattedUri(mapType: MapTypes): Uri {
        val sb = StringBuilder()
        when (mapType) {
            GOOGLE_MAP -> formulateGoogleUri(sb)
            YANDEX_MAP -> formulateYandexMapUri(sb)
            KAKAO_MAP -> formulateKakaoMapUri(sb)
            NAVER_MAP -> formulateNaverMapUri(sb)
        }
        return Uri.parse(sb.toString())
    }

    private fun formulateGoogleUri(sb: StringBuilder) {
        val viewType = when (viewType) {
            MAP -> "m"
            SATELLITE -> "k"
            HYBRID -> "h"
            TERRAIN -> "p"
            GOOGLE_EARTH -> "e"
            else -> null
        }

        if (startLatitude != null && startLongitude != null) {
            if (isDrivingModeEnabled) sb.append("google.navigation:q=", latitude, ",", longitude)
            else sb.append("http://maps.google.com/maps?saddr=${startLatitude},${startLongitude}&daddr=${latitude},${longitude}")
        } else {
            if (viewType == null) {
                sb.append("geo:")
                if (latitude != null && longitude != null) {
                    sb.append(latitude, ",", longitude)
                }
                address?.let {
                    sb.append("?q=", Uri.encode(address))
                } ?: if (latitude != null && longitude != null) {
                    sb.append("?q=", latitude, ",", longitude)
                }
            } else {
                sb.append("http://maps.google.com/maps?")
                    .append("t=$viewType")
                address?.let {
                    sb.append("?q=", Uri.encode(address))
                } ?: if (latitude != null && longitude != null) {
                    sb.append("?q=", latitude, ",", longitude)
                }
            }
        }
    }

    private fun formulateYandexMapUri(sb: StringBuilder) {
        sb.append("yandexmaps://", "maps.yandex.ru/?")
        if (startLongitude != null && startLatitude != null) {
            sb.append("rtext=", startLatitude, ",", startLongitude, "~", latitude, ",", longitude)
        } else {
            if (latitude != null && longitude != null) {
                sb.append("pt=")
                    .append(longitude, ",", latitude)
            }
            val viewType = when (viewType) {
                MAP -> "map"
                SATELLITE -> "sat"
                HYBRID -> "skl"
                OPEN_MAP -> "pmap"
                else -> null
            }
            viewType?.let { sb.append("&l=", viewType) }
            zoom?.let { sb.append("&z=", zoom) }
            address?.let { sb.append("&text=", Uri.encode(address)) }
        }
    }

    private fun formulateNaverMapUri(sb: StringBuilder) {
        sb.append("nmap://")
        if (address == null) sb.append("map?") else sb.append("place?")
        latitude?.let { sb.append("lat=$latitude") }
        longitude?.let { sb.append("&lng=$longitude") }
        zoom?.let { sb.append("&zoom=$zoom") }
        address?.let { sb.append("&name=", Uri.encode(address)) }
    }

    private fun formulateKakaoMapUri(sb: StringBuilder) {
        sb.append("daummaps://")
        if (address == null) {
            sb.append("look?")
        } else {
            sb.append("search?")
                .append("q=", address, "&")
        }
        if (latitude != null && longitude != null) {
            sb.append("p=")
                .append(latitude, ",", longitude)
        }
    }

    override fun createLauncher(): IntentBuilderLauncher {
        return super.createLauncher()
    }

    override fun createIntentHandler(activity: Activity): IntentHandler {
        return createFailIntentHandler(activity) ?: super.createIntentHandler(activity)
    }

    override fun createIntentHandler(fragment: Fragment): IntentHandler {
        return createFailIntentHandler(fragment.activity) ?: super.createIntentHandler(fragment)
    }

    override fun createIntentHandler(fragment: androidx.fragment.app.Fragment): IntentHandler {
        return fragment.context?.let { createFailIntentHandler(it) } ?: super.createIntentHandler(
            fragment
        )
    }

    override fun createIntentHandler(context: Context): IntentHandler {
        return createFailIntentHandler(context) ?: super.createIntentHandler(context)
    }

    private fun createFailIntentHandler(context: Context): WrapperIntentHandler? {
        val list = createFailIntentHandlers(context)
        return if (list.isEmpty()) null else WrapperIntentHandler(
            context,
            createIntent(context),
            list.last(),
            list.first()
        )
    }

    private fun createFailIntentHandlers(context: Context): List<IntentHandler> {
        val result = ArrayList<IntentHandler>(types.size)

        var prevIntentHandler: IntentHandler? = null

        for (index in 1 until types.size) {
            val intentHandler = OmegaIntentBuilder
                .map(types[index])
                .also {
                    it.address = address
                    it.latitude = latitude
                    it.longitude = longitude
                    it.zoom = zoom
                    it.viewType = viewType
                }
                .createIntentHandler(context)
            result += intentHandler
            prevIntentHandler?.failIntentHandler(intentHandler)
            prevIntentHandler = intentHandler
        }

        if (failtype != null) {
            val lastHandler = OmegaIntentBuilder
                .playStore()
                .packageName(failtype!!.packageName)
                .createIntentHandler(context)
            result += lastHandler
            prevIntentHandler?.failIntentHandler(lastHandler)

        }

        return result
    }

    private class WrapperIntentHandler(
        context: Context,
        createdIntent: Intent,
        private val handler: IntentHandler,
        failIntentHandler: IntentHandler?
    ) : ContextIntentHandler(
        context,
        createdIntent
    ) {

        init {
            super.failIntentHandler(failIntentHandler)
        }

        override fun failToast(message: String): ContextIntentHandler {
            handler.failToast(message)
            return this
        }

        override fun failToast(message: Int): ContextIntentHandler {
            handler.failToast(message)
            return this
        }

        override fun failIntent(failIntent: Intent): ContextIntentHandler {
            handler.failIntent(failIntent)
            return this
        }

        override fun failCallback(failCallback: FailCallback): ContextIntentHandler {
            handler.failCallback(failCallback)
            return this
        }

        override fun failIntentHandler(failIntentHandler: IntentHandler?): IntentHandler {
            handler.failIntentHandler(failIntentHandler)
            return this
        }

        override fun chooserTitle(chooserTitle: CharSequence): IntentHandler {
            handler.chooserTitle(chooserTitle)
            return this
        }

        override fun chooserTitle(chooserTitle: String): IntentHandler {
            handler.chooserTitle(chooserTitle)
            return this
        }

        override fun chooserTitle(chooserTitle: Int): IntentHandler {
            handler.chooserTitle(chooserTitle)
            return this
        }

        override fun addFlagsClearBackStack(): IntentHandler {
            handler.addFlagsClearBackStack()
            return this
        }

        override fun addFlags(flags: Int): IntentHandler {
            handler.addFlags(flags)
            return this
        }

        override fun setFlags(flags: Int): IntentHandler {
            handler.setFlags(flags)
            return this
        }
    }

}