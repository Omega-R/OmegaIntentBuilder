/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * CropImageIntentBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 11, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable.Creator
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes
import com.omega_r.libs.omegaintentbuilder.utils.ExtensionUtils.Companion.isNullOrLessZero

/**
 * CropImageIntentBuilder builder for creating crop image intent
 */
class CropImageIntentBuilder() : BaseUriBuilder() {

    private var outputX: Int? = null
    private var outputY: Int? = null
    private var aspectX: Int = 1
    private var aspectY: Int = 1
    private var scale: Boolean = true
    private var returnData: Boolean = true

    constructor(parcel: Parcel) : this() {
        outputX = parcel.readValue(Int::class.java.classLoader) as? Int
        outputY = parcel.readValue(Int::class.java.classLoader) as? Int
        aspectX = parcel.readInt()
        aspectY = parcel.readInt()
        scale = parcel.readByte() != 0.toByte()
        returnData = parcel.readByte() != 0.toByte()
    }

    /**
     * Set Output image width
     *
     * @param outputX Int
     * @return This CropImageIntentBuilder for method chaining
     */
    fun outputX(outputX: Int): CropImageIntentBuilder {
        if (outputX.isNullOrLessZero()) {
            throw IllegalStateException("outputX can't be less than zero")
        }
        this.outputX = outputX
        return this
    }

    /**
     * Set Output image height
     *
     * @param outputY Int
     * @return This CropImageIntentBuilder for method chaining
     */
    fun outputY(outputY: Int): CropImageIntentBuilder {
        if (outputY.isNullOrLessZero()) {
            throw IllegalStateException("outputY can't be less than zero")
        }
        this.outputY = outputY
        return this
    }

    /**
     * Crop frame aspect X
     *
     * @param aspectX Int
     * @return This CropImageIntentBuilder for method chaining
     */
    fun aspectX(aspectX: Int): CropImageIntentBuilder {
        if (aspectX.isNullOrLessZero()) {
            throw IllegalStateException("aspectX can't be less than zero")
        }
        this.aspectX = aspectX
        return this
    }

    /**
     * Crop frame aspect Y
     *
     * @param aspectY Int
     * @return This CropImageIntentBuilder for method chaining
     */
    fun aspectY(aspectY: Int): CropImageIntentBuilder {
        if (aspectY.isNullOrLessZero()) {
            throw IllegalStateException("aspectY can't be less than zero")
        }
        this.aspectY = aspectY
        return this
    }

    /**
     * Scale or not cropped image if output image and cropImage frame sizes differs
     *
     * @param scale Boolean
     * @return This CropImageIntentBuilder for method chaining
     */
    fun scale(scale: Boolean): CropImageIntentBuilder {
        this.scale = scale
        return this
    }

    /**
     * @param outputX Output image width
     * @param outputY Output image height
     * @param aspectX Crop frame aspect X
     * @param aspectY Crop frame aspect Y
     * @param scale   Scale or not cropped image if output image and cropImage frame sizes differs
     * @return This CropImageIntentBuilder for method chaining
     */
    @JvmOverloads
    fun property(outputX: Int, outputY: Int, aspectX: Int = 1, aspectY: Int = 1, scale: Boolean = true): CropImageIntentBuilder {
        outputX(outputX)
        outputY(outputY)
        aspectX(aspectX)
        aspectY(aspectY)
        scale(scale)

        return this
    }

    /**
     * Set returnData onActivityResult.
     *
     * @param returnData Boolean
     * @return This CropImageIntentBuilder for method chaining
     */
    fun returnData(returnData: Boolean): CropImageIntentBuilder {
        this.returnData = returnData
        return this
    }

    override fun createIntent(context: Context): Intent {
        if (outputX.isNullOrLessZero()) {
            throw IllegalStateException("You can't call createIntent before outputX")
        }
        if (outputY.isNullOrLessZero()) {
            throw IllegalStateException("You can't call createIntent before outputY")
        }

        val intent = Intent("com.android.camera.action.CROP")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        outputX?.let { intent.putExtra("outputX", it) }
        outputY?.let { intent.putExtra("outputY", it) }
        intent.putExtra("aspectX", aspectX)
        intent.putExtra("aspectY", aspectY)
        intent.putExtra("scale", scale)
        intent.putExtra("return-data", returnData)

        intent.setDataAndType(getFirstUri(context), MimeTypes.IMAGE)

        return intent
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(outputX)
        parcel.writeValue(outputY)
        parcel.writeInt(aspectX)
        parcel.writeInt(aspectY)
        parcel.writeByte(if (scale) 1 else 0)
        parcel.writeByte(if (returnData) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<CropImageIntentBuilder> {

        override fun createFromParcel(parcel: Parcel): CropImageIntentBuilder {
            return CropImageIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<CropImageIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }
}