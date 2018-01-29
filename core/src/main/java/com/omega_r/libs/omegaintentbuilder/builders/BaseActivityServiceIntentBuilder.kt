package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

open class BaseActivityServiceIntentBuilder<T>(context: Context, cls: Class<T>) {

  private val intent: Intent = Intent(context, cls)

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The boolean data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Boolean): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The byte data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Byte): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The char data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Char): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The short data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Short): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The integer data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Int): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The long data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Long): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The float data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Float): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The double data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Double): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The String data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: String): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The CharSequence data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: CharSequence): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Parcelable data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Parcelable): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Parcelable[] data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Array<Parcelable>): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<Parcelable> data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun <P: Parcelable> putParcelableArrayListExtra(name: String, value: ArrayList<P>): BaseActivityServiceIntentBuilder<T> {
    intent.putParcelableArrayListExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<Integer> data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putIntegerArrayListExtra(name: String, value: ArrayList<Int>): BaseActivityServiceIntentBuilder<T> {
    intent.putIntegerArrayListExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<String> data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putStringArrayListExtra(name: String, value: ArrayList<String>): BaseActivityServiceIntentBuilder<T> {
    intent.putStringArrayListExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<CharSequence> data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putCharSequenceArrayListExtra(name: String, value: ArrayList<CharSequence>): BaseActivityServiceIntentBuilder<T> {
    intent.putCharSequenceArrayListExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Serializable data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Serializable): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The boolean array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: BooleanArray): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The short array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: ShortArray): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The char array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: CharArray): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The int array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: IntArray): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The byte array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: LongArray): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The float array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: FloatArray): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The double array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: DoubleArray): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The String array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Array<String>): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The CharSequence array data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Array<CharSequence>): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Bundle data value.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Bundle): BaseActivityServiceIntentBuilder<T> {
    intent.putExtra(name, value)
    return this
  }

  /**
   * Add a set of extended data to the createdIntent.  The keys must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param extras The Bundle of extras to add to this createdIntent.
   */
  fun putExtras(value: Bundle): BaseActivityServiceIntentBuilder<T> {
    intent.putExtras(value)
    return this
  }

  /**
   * Copy all extras in 'src' in to this createdIntent.
   *
   * @param src Contains the extras to copy.
   */
  fun putExtras(value: Intent): BaseActivityServiceIntentBuilder<T> {
    intent.putExtras(value)
    return this
  }

  /**
   * Completely replace the extras in the Intent with the extras in the
   * given Intent.
   *
   * @param src The exact extras contained in this Intent are copied
   * into the target createdIntent, replacing any that were previously there.
   */
  fun replaceExtras(value: Intent): BaseActivityServiceIntentBuilder<T> {
    intent.replaceExtras(value)
    return this
  }

  /**
   * Remove extended data from the createdIntent.
   */
  fun removeExtra(value: String): BaseActivityServiceIntentBuilder<T> {
    intent.removeExtra(value)
    return this
  }

  /**
   * Completely replace the extras in the Intent with the given Bundle of
   * extras.
   *
   * @param extras The new set of extras in the Intent, or null to erase
   * all extras.
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun replaceExtras(value: Bundle): BaseActivityServiceIntentBuilder<T> {
    intent.replaceExtras(value)
    return this
  }

  /**
   * Add additional flags to the createdIntent (or with existing flags
   * value).
   *
   * @param flags The new flags to set.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun addFlags(flags: Int): BaseActivityServiceIntentBuilder<T> {
    intent.addFlags(flags)
    return this
  }

  /**
   * Set special flags controlling how this createdIntent is handled.  Most values
   * here depend on the mimeType of component being executed by the Intent,
   * specifically the FLAG_ACTIVITY_* flags are all for use with
   * {@link Context#startActivity Context.startActivity()} and the
   * FLAG_RECEIVER_* flags are all for use with
   * {@link Context#sendBroadcast(Intent) Context.sendBroadcast()}.
   *
   * @param flags The desired flags.
   *
   * @return Returns the same BaseActivityServiceIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun setFlags(flags: Int): BaseActivityServiceIntentBuilder<T> {
    intent.flags = flags
    return this
  }

  fun createIntent(): Intent {
    return intent
  }

}