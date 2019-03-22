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

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback
import com.omega_r.libs.omegaintentbuilder.types.MapTypes
import com.omega_r.libs.omegaintentbuilder.types.MapTypes.*

/**
 * MapIntentBuilder is a helper for open Maps applications
 */
class MapIntentBuilder(val context: Context, private vararg var types: MapTypes) : BaseActivityBuilder(context) {

    private var latitude: Double? = null
    private var longitude: Double? = null
    private var address: String? = null
    private var failtype: MapTypes? = null

    init {
        if (types.isEmpty()) types = MapTypes.values()
    }

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

    fun openPlayStoreIfFail(type: MapTypes): MapIntentBuilder {
        failtype = type
        return this
    }

    override fun createIntent(): Intent {
        val uri: Uri = getFormattedUri(0)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        when (types[0]) {
            GOOGLE_MAP -> intent.setPackage(GOOGLE_MAP.packageName)
            YANDEX_MAP -> intent.setPackage(YANDEX_MAP.packageName)
            KAKAO_MAP -> intent.setPackage(KAKAO_MAP.packageName)
            NAVER_MAP -> intent.setPackage(NAVER_MAP.packageName)
        }

        return intent
    }

    private fun getFormattedUri(index: Int): Uri {
        val sb = StringBuilder()
        when (types[index]) {
            GOOGLE_MAP -> {
                sb.append("geo:")
                if (latitude != null && longitude != null) {
                    sb.append(latitude, ",", longitude)
                }
                if (address != null) {
                    sb.append("?q=", Uri.encode(address))
                }
            }
            YANDEX_MAP -> {
                sb.append("yandexmaps://", YANDEX_MAP.packageName, "/?pt=")
                if (latitude != null && longitude != null) {
                    sb.append(longitude, ",", latitude)
                }
                if (address != null) {
                    sb.append("&text=", address)
                }
            }
            KAKAO_MAP -> {
                sb.append("daummaps://look?p=")
                if (latitude != null && longitude != null) {
                    sb.append(latitude, ",", longitude)
                }
            }
            NAVER_MAP -> {
                sb.append("geo:")
                if (latitude != null && longitude != null) {
                    sb.append(latitude, ",", longitude)
                }
                if (address != null) {
                    sb.append("?q=", Uri.encode(address))
                }
            }
        }
        return Uri.parse(sb.toString())
    }

    override fun createIntentHandler(): ContextIntentHandler {
        val failIntentHandlers = createFailIntentHandler()
        if (failIntentHandlers.isEmpty()) {
            return super.createIntentHandler()
        } else {
            return WrapperIntentHandler(context, createIntent(), failIntentHandlers.last(), failIntentHandlers.first())
        }

    }

    private fun createFailIntentHandler(): List<ContextIntentHandler> {
        val result = ArrayList<ContextIntentHandler>(types.size)

        var prevIntentHandler : ContextIntentHandler? = null

        for (index in 1 until types.size) {
            val intentHandler = OmegaIntentBuilder.from(context)
                    .map(types[index])
                    .createIntentHandler()
            result += intentHandler
            prevIntentHandler?.failIntentHandler(intentHandler)
            prevIntentHandler = intentHandler
        }

        if (failtype != null) {
            val lastHandler =  OmegaIntentBuilder.from(context)
                    .playStore()
                    .packageName(failtype!!.packageName)
                    .createIntentHandler()
            result += lastHandler
            prevIntentHandler?.failIntentHandler(lastHandler)

        }

        return result
    }

    private class WrapperIntentHandler(context: Context, createdIntent: Intent,
                               private val handler: ContextIntentHandler,
                               failIntentHandler: ContextIntentHandler?
    ): ContextIntentHandler(context, createdIntent) {

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

        override fun failIntentHandler(failIntentHandler: ContextIntentHandler?): ContextIntentHandler {
            handler.failIntentHandler(failIntentHandler)
            return this
        }

    }

}