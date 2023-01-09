package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.provider.DocumentsContract
import androidx.annotation.RequiresApi

class SaveIntentBuilder() : BaseActivityBuilder() {

    enum class Category(val type: String) {
        OPENABLE(Intent.CATEGORY_OPENABLE)
    }

    private var type: String? = null
    private var category: Category? = null
    private var title: String? = null
    private var initialUri: String? = null

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        category = parcel.readString()?.let { Category.valueOf(it) }
        title = parcel.readString()
        initialUri = parcel.readString()
    }

    fun type(type: String): SaveIntentBuilder {
        this.type = type
        return this
    }

    fun category(category: Category): SaveIntentBuilder {
        this.category = category
        return this
    }

    fun title(title: String): SaveIntentBuilder {
        this.title = title
        return this
    }

    fun initialUri(initialUri: String): SaveIntentBuilder {
        this.initialUri = initialUri
        return this
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(type)
        parcel.writeValue(category?.type)
        parcel.writeValue(title)
        parcel.writeValue(initialUri)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createIntent(context: Context) = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
        addCategory(category?.type)
        type = this@SaveIntentBuilder.type
        putExtra(Intent.EXTRA_TITLE, title)
        putExtra(DocumentsContract.EXTRA_INITIAL_URI, initialUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SaveIntentBuilder> {
        override fun createFromParcel(parcel: Parcel): SaveIntentBuilder {
            return SaveIntentBuilder(parcel)
        }

        override fun newArray(size: Int): Array<SaveIntentBuilder?> {
            return arrayOfNulls(size)
        }
    }

}