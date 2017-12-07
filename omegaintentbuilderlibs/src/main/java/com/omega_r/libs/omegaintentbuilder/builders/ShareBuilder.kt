package com.omega_r.libs.omegaintentbuilder.builders

import android.app.Activity
import android.content.Intent
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import java.util.TreeSet

/**
 * IntentBuilder is a helper for constructing {@link Intent#ACTION_SEND} and
 * {@link Intent#ACTION_SEND_MULTIPLE} sharing intents and starting activities
 * to share content. The ComponentName and package name of the calling activity
 * will be included.
 */
class ShareBuilder internal constructor(private val intentBuilder: OmegaIntentBuilder): IntentBuilder {

  companion object {
    private const val EXTRA_CALLING_PACKAGE = "com.omega_r.libs.omegaintentbuilder.EXTRA_CALLING_PACKAGE";
    private const val EXTRA_CALLING_ACTIVITY = "com.omega_r.libs.omegaintentbuilder.EXTRA_CALLING_ACTIVITY";
  }

  private var activity: Activity? = null
  private lateinit var intent: Intent
  private var chooserTitle: CharSequence? = null
  private var toAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var ccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var bccAddressesSet: MutableSet<String> = TreeSet(String.CASE_INSENSITIVE_ORDER)
  private var streamsList: List<String>? = null
  private var subject: String? = null
  private var mimeType: String? = null


  /**
   * Create a new IntentBuilder for launching a sharing action from launchingActivity.
   *
   * @param launchingActivity Activity that the share will be launched from
   * @return This ShareBuilder for method chaining
   */
  fun from(activity: Activity): ShareBuilder {
    this.activity = activity
    return this
  }

  /**
   * Set an array of email addresses as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param addresses Email addresses to send to
   * @return This ShareBuilder for method chaining
   */
  fun setEmailTo(addresses: Collection<String>): ShareBuilder {
    toAddressesSet = addresses.toMutableSet()
    return this
  }

  fun setEmailTo(addresses: Array<String>) {
    toAddressesSet = addresses.toMutableSet()
  }

  /**
   * Add an array (or single value) of email addresses as recipients of this share.
   * This replaces all current "to" recipients that have been set so far.
   *
   * @param addresses Email addresses to send to
   * @return This ShareBuilder for method chaining
   */
  fun addEmailTo(addresses: Collection<String>): ShareBuilder {
    toAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailTo(addresses: Array<String>): ShareBuilder {
    toAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailTo(addresses: String): ShareBuilder {
    toAddressesSet.add(addresses)
    return this
  }

  /**
   * Set an array of email addresses to CC on this share.
   * This replaces all current "CC" recipients that have been set so far.
   *
   * @param addresses Email addresses to CC on the share
   * @return This ShareBuilder for method chaining
   */
  fun setEmailCc(addresses: Collection<String>): ShareBuilder {
    ccAddressesSet = addresses.toMutableSet()
    return this
  }

  fun setEmailCc(addresses: Array<String>): ShareBuilder {
    ccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Add an array (or single value)  of email addresses to be used in the "cc" field of the final Intent.
   *
   * @param addresses Email addresses to CC
   * @return This ShareBuilder for method chaining
   */
  fun addEmailCc(addresses: Collection<String>): ShareBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailCc(addresses: Array<String>): ShareBuilder {
    ccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailCc(addresses: String): ShareBuilder {
    ccAddressesSet.add(addresses)
    return this
  }

  /**
   * Set an array of email addresses to BCC on this share.
   * This replaces all current "BCC" recipients that have been set so far.
   *
   * @param addresses Email addresses to BCC on the share
   * @return This ShareBuilder for method chaining
   */
  fun setEmailBcc(addresses: Collection<String>): ShareBuilder {
    bccAddressesSet = addresses.toMutableSet()
    return this
  }

  fun setEmailBcc(addresses: Array<String>): ShareBuilder {
    bccAddressesSet = addresses.toMutableSet()
    return this
  }

  /**
   * Add an email address to be used in the "bcc" field of the final Intent.
   *
   * @param address Email address to BCC
   * @return This ShareBuilder for method chaining
   * @see Intent#EXTRA_BCC
   */
  fun addEmailBcc(addresses: Collection<String>): ShareBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailBcc(addresses: Array<String>): ShareBuilder {
    bccAddressesSet.addAll(addresses)
    return this
  }

  fun addEmailBcc(addresses: String): ShareBuilder {
    bccAddressesSet.add(addresses)
    return this
  }

  /**
   * Set a subject heading for this share; useful for sharing via email.
   *
   * @param subject Subject heading for this share
   * @return This ShareBuilder for method chaining
   */
  fun setSubject(subject: String): ShareBuilder {
    this.subject = subject
    return this
  }

  /**
   * Set the type of data being shared
   *
   * @param mimeType mimetype of the shared data
   * @return This ShareBuilder for method chaining
   */
  fun setType(mimeType: String) {
    this.mimeType = mimeType
  }

  override fun createIntent(): Intent {
    intent = Intent().setAction(Intent.ACTION_SEND)

    activity?.let {
      intent.putExtra(EXTRA_CALLING_PACKAGE, activity!!.packageName)
      intent.putExtra(EXTRA_CALLING_ACTIVITY, activity!!.componentName)
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
    }
    if (toAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_EMAIL, toAddressesSet.toTypedArray())
    }
    if (ccAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_CC, ccAddressesSet.toTypedArray())
    }
    if (bccAddressesSet.isNotEmpty()) {
      intent.putExtra(Intent.EXTRA_BCC, bccAddressesSet.toTypedArray())
    }
    if (!subject.isNullOrEmpty()) {
      intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    }
    if (!mimeType.isNullOrEmpty()) {
      intent.setType(mimeType)
    }

    return intent
  }


}