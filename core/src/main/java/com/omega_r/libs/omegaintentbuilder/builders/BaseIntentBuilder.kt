package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
open class BaseIntentBuilder<out S, T> (context: Context, cls: Class<T>) {

  private val intent: Intent = Intent(context, cls)

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The boolean data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Boolean): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The byte data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Byte): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The char data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Char): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The short data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Short): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The integer data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Int): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The long data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Long): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The float data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Float): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The double data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Double): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The String data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: String): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The CharSequence data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: CharSequence): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Parcelable data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Parcelable): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Parcelable[] data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Array<Parcelable>): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<Parcelable> data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun <P: Parcelable> putParcelableArrayListExtra(name: String, value: ArrayList<P>): S {
    intent.putParcelableArrayListExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<Integer> data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putIntegerArrayListExtra(name: String, value: ArrayList<Int>): S {
    intent.putIntegerArrayListExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<String> data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putStringArrayListExtra(name: String, value: ArrayList<String>): S {
    intent.putStringArrayListExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The ArrayList<CharSequence> data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putCharSequenceArrayListExtra(name: String, value: ArrayList<CharSequence>): S {
    intent.putCharSequenceArrayListExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Serializable data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Serializable): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The boolean array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: BooleanArray): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The short array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: ShortArray): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The char array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: CharArray): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The int array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: IntArray): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The byte array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: LongArray): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The float array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: FloatArray): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The double array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: DoubleArray): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The String array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Array<String>): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The CharSequence array data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Array<CharSequence>): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add extended data to the createdIntent.  The name must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param name The name of the extra data, with package prefix.
   * @param value The Bundle data value.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun putExtra(name: String, value: Bundle): S {
    intent.putExtra(name, value)
    return this as S
  }

  /**
   * Add a set of extended data to the createdIntent.  The keys must include a package
   * prefix, for example the app com.android.contacts would use names
   * like "com.android.contacts.ShowAll".
   *
   * @param extras The Bundle of extras to add to this createdIntent.
   */
  fun putExtras(value: Bundle): S {
    intent.putExtras(value)
    return this as S
  }

  /**
   * Copy all extras in 'src' in to this createdIntent.
   *
   * @param src Contains the extras to copy.
   */
  fun putExtras(value: Intent): S {
    intent.putExtras(value)
    return this as S
  }

  /**
   * Completely replace the extras in the Intent with the extras in the
   * given Intent.
   *
   * @param src The exact extras contained in this Intent are copied
   * into the target createdIntent, replacing any that were previously there.
   */
  fun replaceExtras(value: Intent): S {
    intent.replaceExtras(value)
    return this as S
  }

  /**
   * Remove extended data from the createdIntent.
   */
  fun removeExtra(value: String): S {
    intent.removeExtra(value)
    return this as S
  }

  /**
   * Completely replace the extras in the Intent with the given Bundle of
   * extras.
   *
   * @param extras The new set of extras in the Intent, or null to erase
   * all extras.
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun replaceExtras(value: Bundle): S {
    intent.replaceExtras(value)
    return this as S
  }

  /**
   * Add additional flags to the createdIntent (or with existing flags
   * value).
   *
   * @param flags The new flags to set.
   *
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun addFlags(flags: Int): S {
    intent.addFlags(flags)
    return this as S
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
   * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
   * into a single statement.
   */
  fun setFlags(flags: Int): S {
    intent.flags = flags
    return this as S
  }

  fun createIntent(): Intent {
    return intent
  }

}