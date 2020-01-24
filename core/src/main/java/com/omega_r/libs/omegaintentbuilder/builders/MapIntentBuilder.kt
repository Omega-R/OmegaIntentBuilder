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
import android.os.Bundle
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityResultCallback
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback
import com.omega_r.libs.omegaintentbuilder.interfaces.IntentHandler
import com.omega_r.libs.omegaintentbuilder.types.MapTypes
import com.omega_r.libs.omegaintentbuilder.types.MapTypes.*

/**
 * MapIntentBuilder is a helper for open Maps applications
 */
class MapIntentBuilder(private vararg var types: MapTypes) : BaseActivityBuilder() {

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

    override fun createIntent(context: Context): Intent {
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

    override fun createIntentHandler(activity: Activity): IntentHandler {
        return createFailIntentHandler(activity) ?: super.createIntentHandler(activity)
    }

    override fun createIntentHandler(fragment: Fragment): IntentHandler {
        return createFailIntentHandler(fragment.activity) ?: super.createIntentHandler(fragment)
    }

    override fun createIntentHandler(fragment: androidx.fragment.app.Fragment): IntentHandler {
        return fragment.context?.let { createFailIntentHandler(it) }  ?: super.createIntentHandler(fragment)
    }

    override fun createIntentHandler(context: Context): IntentHandler {
        return createFailIntentHandler(context) ?: super.createIntentHandler(context)
    }

    private fun createFailIntentHandler(context: Context): WrapperIntentHandler? {
        val list = createFailIntentHandlers(context)
        return if (list.isEmpty()) null else WrapperIntentHandler(context, createIntent(context), list.last(), list.first())
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

        override fun startActivity() {
            handler.startActivity()
        }

        override fun startActivityForResult(requestCode: Int, options: Bundle?) {
            handler.startActivityForResult(requestCode, options)
        }

        override fun startActivityForResult(callback: ActivityResultCallback) {
            handler.startActivityForResult(callback)
        }

        override fun getIntent(): Intent {
            return handler.getIntent()
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