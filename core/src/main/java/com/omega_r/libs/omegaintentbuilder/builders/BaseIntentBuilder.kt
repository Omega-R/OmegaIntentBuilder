package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
abstract class BaseIntentBuilder<out S, T>(
    private val classT: Class<T>,
    private val extras: Bundle = Bundle(),
    private var flags: Int = 0,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readSerializable() as Class<T>,
        parcel.readBundle(Bundle::class.java.classLoader) ?: Bundle(),
        parcel.readInt()
    )

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The boolean data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Boolean): S {
        extras.putBoolean(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The byte data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Byte): S {
        extras.putByte(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The char data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Char): S {
        extras.putChar(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The short data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Short): S {
        extras.putShort(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The integer data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Int): S {
        extras.putInt(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The long data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Long): S {
        extras.putLong(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The float data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Float): S {
        extras.putFloat(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The double data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Double): S {
        extras.putDouble(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The String data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: String): S {
        extras.putString(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The CharSequence data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: CharSequence): S {
        extras.putCharSequence(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The Parcelable data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Parcelable): S {
        extras.putParcelable(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The Parcelable[] data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Array<Parcelable>): S {
        extras.putParcelableArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The ArrayList<Parcelable> data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun <P : Parcelable> putParcelableArrayListExtra(name: String, value: ArrayList<P>): S {
        extras.putParcelableArrayList(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The ArrayList<Integer> data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putIntegerArrayListExtra(name: String, value: ArrayList<Int>): S {
        extras.putIntegerArrayList(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The ArrayList<String> data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putStringArrayListExtra(name: String, value: ArrayList<String>): S {
        extras.putStringArrayList(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The ArrayList<CharSequence> data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putCharSequenceArrayListExtra(name: String, value: ArrayList<CharSequence>): S {
        extras.putCharSequenceArrayList(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The Serializable data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Serializable): S {
        extras.putSerializable(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The boolean array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: BooleanArray): S {
        extras.putBooleanArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The short array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: ShortArray): S {
        extras.putShortArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The char array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: CharArray): S {
        extras.putCharArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The int array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: IntArray): S {
        extras.putIntArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The byte array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: LongArray): S {
        extras.putLongArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The float array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: FloatArray): S {
        extras.putFloatArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The double array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: DoubleArray): S {
        extras.putDoubleArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The String array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Array<String>): S {
        extras.putStringArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The CharSequence array data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Array<CharSequence>): S {
        extras.putCharSequenceArray(name, value)
        return this as S
    }

    /**
     * Add extended data to the createdIntent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name The name of the extras data, with package prefix.
     * @param value The Bundle data value.
     *
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun putExtra(name: String, value: Bundle): S {
        extras.putBundle(name, value)
        return this as S
    }

    /**
     * Add a set of extended data to the createdIntent.  The keys must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param value The Bundle of extras to add to this createdIntent.
     */
    fun putExtras(value: Bundle): S {
        extras.putAll(value)
        return this as S
    }

    /**
     * Copy all extras in 'src' in to this createdIntent.
     *
     * @param value Contains the extras to copy.
     */
    fun putExtras(value: Intent): S {
        extras.putAll(value.extras)
        return this as S
    }

    /**
     * Completely replace the extras in the Intent with the extras in the
     * given Intent.
     *
     * @param value The exact extras contained in this Intent are copied
     * into the target createdIntent, replacing any that were previously there.
     */
    fun replaceExtras(value: Intent): S {
        extras.clear()
        extras.putAll(value.extras)
        return this as S
    }

    /**
     * Remove extended data from the createdIntent.
     */
    fun removeExtra(value: String): S {
        extras.remove(value)
        return this as S
    }

    /**
     * Completely replace the extras in the Intent with the given Bundle of
     * extras.
     *
     * @param value The new set of extras in the Intent, or null to erase
     * all extras.
     * @return Returns the same BaseIntentBuilder<T> object, for chaining multiple calls
     * into a single statement.
     */
    fun replaceExtras(value: Bundle): S {
        extras.clear()
        extras.putAll(value)
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
        this.flags = this.flags or flags
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
        this.flags = flags
        return this as S
    }

    fun createIntent(context: Context) = Intent(context, classT).apply {
        putExtras(this@BaseIntentBuilder.extras)
        flags = this@BaseIntentBuilder.flags
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(classT)
        parcel.writeBundle(extras)
        parcel.writeInt(flags)
    }

}