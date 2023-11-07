/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * BaseShareBuilder.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders.share

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcel
import android.text.Html
import androidx.annotation.StringRes
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder
import com.omega_r.libs.omegatypes.Text
import com.omega_r.libs.omegatypes.toText
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
abstract class BaseShareBuilder<T> : BaseUriBuilder {

    private var toAddressesSet = mutableListOf<Text>()
    private var subject: Text? = null
    private var text: Text? = null

    constructor(): super()

    constructor(parcel: Parcel) : super(parcel) {
        toAddressesSet = parcel.readSerializable() as MutableList<Text>
        subject = parcel.readSerializable() as Text?
        text = parcel.readSerializable() as Text?
    }

    /**
     * Add Collection of email addresses as recipients of this share.
     *
     * @param addresses Email addresses to send to
     * @return This BaseShareBuilder for method chaining
     */
    fun emailTo(addresses: Collection<String>): T {
        toAddressesSet.addAll(addresses.map { it.toText() })
        return this as T
    }

    /**
     * Add or single value of email address as recipients of this share.
     *
     * @param address Email addresses to send to
     * @return This BaseShareBuilder for method chaining
     */
    fun emailTo(address: String): T {
        toAddressesSet.add(address.toText())
        return this as T
    }

    /**
     * Add or single value of email address as recipients of this share.
     *
     * @param address Email addresses to send to
     * @return This BaseShareBuilder for method chaining
     */
    fun emailTo(vararg address: String): T {
        toAddressesSet.addAll(address.map { it.toText() })
        return this as T
    }


    /**
     * Add or single value of email address as recipients of this share.
     *
     * @param address Email addresses to send to
     * @return This BaseShareBuilder for method chaining
     */
    fun emailTo(vararg address: Text): T {
        toAddressesSet.addAll(address)
        return this as T
    }

    /**
     * Add or single value of email address as recipients of this share.
     *
     * @param address Email addresses to send to
     * @return This BaseShareBuilder for method chaining
     */
    fun emailTo(address: Text): T {
        toAddressesSet.add(address)
        return this as T
    }

    /**
     * Add or single value of email address as recipients of this share.
     *
     * @param address @StringRes Email addresses to send to
     * @return This BaseShareBuilder for method chaining
     */
    fun emailTo(@StringRes addressRes: Int): T {
        toAddressesSet.add(Text.from(addressRes))
        return this as T
    }

    /**
     * Set a subject heading for this share; useful for sharing via email.
     *
     * @param subject Subject heading for this share
     * @return This BaseShareBuilder for method chaining
     */
    fun subject(subject: String): T {
        this.subject = Text.from(subject)
        return this as T
    }

    /**
     * Set a subject heading for this share; useful for sharing via email.
     *
     * @param subject Resource id for Subject heading for this share
     * @return This BaseShareBuilder for method chaining
     */
    fun subject(@StringRes subjectRes: Int): T {
        this.subject = Text.from(subjectRes)
        return this as T
    }

    /**
     * Set a subject heading for this share; useful for sharing via email.
     *
     * @param subject Resource id for Subject heading for this share
     * @return This BaseShareBuilder for method chaining
     */
    fun subject(subject: Text): T {
        this.subject = subject
        return this as T
    }

    /**
     * Set a subject heading for this share; useful for sharing via email.
     *
     * @param subjectRes Resource id for the format string
     * @param formatArgs The format arguments that will be used for substitution.
     * @return This BaseShareBuilder for method chaining
     */
    fun subject(@StringRes subjectRes: Int, vararg formatArgs: Serializable): T {
        this.subject = Text.from(stringRes = subjectRes, formatArgs = formatArgs)
        return this as T
    }

    /**
     * Set the literal text data to be sent as part of the share.
     * This may be a styled CharSequence.
     *
     * @param text Text to share
     * @return This BaseShareBuilder for method chaining
     * @see Intent#EXTRA_TEXT
     */
    fun text(text: CharSequence): T {
        this.text = Text.from(text)
        return this as T
    }

    /**
     * Set the literal text data to be sent as part of the share.
     * This may be a styled CharSequence.
     *
     * @param @StringRes textRes Text to share
     * @return This BaseShareBuilder for method chaining
     * @see Intent#EXTRA_TEXT
     */
    fun text(@StringRes textRes: Int): T {
        this.text = Text.from(textRes)
        return this as T
    }

    /**
     * Set the literal text data to be sent as part of the share.
     * This may be a styled CharSequence.
     *
     * @param subjectRes Resource id for the format string
     * @param formatArgs The format arguments that will be used for substitution.
     * @return This BaseShareBuilder for method chaining
     * @see Intent#EXTRA_TEXT
     */
    fun text(@StringRes textRes: Int, vararg formatArgs: Serializable): T {
        this.text = Text.from(stringRes = textRes, formatArgs = formatArgs)
        return this as T
    }

    /**
     * Set an HTML string to be sent as part of the share.
     * If {@link Intent#EXTRA_TEXT EXTRA_TEXT} has not already been supplied,
     * a styled version of the supplied HTML text will be added as EXTRA_TEXT as
     * parsed by {@link android.text.Html#fromHtml(String) Html.fromHtml}.
     *
     * @param htmlText A string containing HTML markup as a richer version of the text
     *                 provided by EXTRA_TEXT.
     * @return This BaseShareBuilder for method chaining
     * @see #text(CharSequence)
     */
    fun htmlText(htmlText: String): T {
        text?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                text = Text.from(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY))
            } else {
                text = Text.from(Html.fromHtml(htmlText))
            }
        }

        return this as T
    }

    /**
     * This method could call ActivityNotFoundException
     *
     * @return Intent for sharing
     */
    override fun createIntent(context: Context): Intent {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        val uriSet = getUriSet(context)

        if (uriSet.isNotEmpty()) intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        if (uriSet.size == 1) intent.putExtra(Intent.EXTRA_STREAM, uriSet.first())
        if (uriSet.size > 1) {
            intent.action = Intent.ACTION_SEND_MULTIPLE
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriSet.toArrayList())
        }
        if (toAddressesSet.isNotEmpty()) {
            intent.putExtra(Intent.EXTRA_EMAIL, toAddressesSet.map { it.getString(context) }.toTypedArray())
        }
        if (subject?.isEmpty() == false) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject!!.getCharSequence(context))
        }
        if (text?.isEmpty() == false) {
            intent.putExtra(Intent.EXTRA_TEXT, text!!.getCharSequence(context))
        }

        return intent
    }

    private fun Set<Uri>.toArrayList(): ArrayList<Uri> = ArrayList(this.toList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeSerializable(ArrayList(toAddressesSet))
        parcel.writeSerializable(subject)
        parcel.writeSerializable(text)
    }

}