package com.omega_r.libs.omegaintentbuilder.bundle

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.RequiresApi
import java.io.Serializable
import java.util.ArrayList

class BundleBuilder {

    private val bundle = Bundle()

    /**
     * Inserts all mappings from the given Bundle into this Bundle.
     *
     * @param bundle a Bundle
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, bundle: Bundle): BundleBuilder {
        bundle.putBundle(name, bundle)
        return this
    }

    /**
     * Inserts all mappings from the given PersistableBundle into this BaseBundle.
     *
     * @param bundle a PersistableBundle
     * @return This BundleBuilder for method chaining
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun putExtra(name: String, bundle: PersistableBundle): BundleBuilder {
        bundle.putPersistableBundle(name, bundle)
        return this
    }

    /**
     * Inserts a Boolean value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a boolean
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Boolean): BundleBuilder {
        bundle.putBoolean(name, value)
        return this
    }

    /**
     * Inserts a byte value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key a String, or null
     * @param value a byte
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Byte): BundleBuilder {
        bundle.putByte(name, value)
        return this
    }

    /**
     * Inserts a char value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key a String, or null
     * @param value a char
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Char): BundleBuilder {
        bundle.putChar(name, value)
        return this
    }

    /**
     * Inserts a short value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key a String, or null
     * @param value a short
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Short): BundleBuilder {
        bundle.putShort(name, value)
        return this
    }

    /**
     * Inserts an int value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key a String, or null
     * @param value an int
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Int): BundleBuilder {
        bundle.putInt(name, value)
        return this
    }

    /**
     * Inserts a long value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key a String, or null
     * @param value a long
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Long): BundleBuilder {
        bundle.putLong(name, value)
        return this
    }

    /**
     * Inserts a float value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key a String, or null
     * @param value a float
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Float): BundleBuilder {
        bundle.putFloat(name, value)
        return this
    }

    /**
     * Inserts a double value into the mapping of this Bundle, replacing
     * any existing value for the given key.
     *
     * @param key a String, or null
     * @param value a double
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Double): BundleBuilder {
        bundle.putDouble(name, value)
        return this
    }

    /**
     * Inserts a String value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a String, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: String): BundleBuilder {
        bundle.putString(name, value)
        return this
    }

    /**
     * Inserts a CharSequence value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a CharSequence, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: CharSequence): BundleBuilder {
        bundle.putCharSequence(name, value)
        return this
    }

    /**
     * Inserts an ArrayList<Any> value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value an ArrayList<Any> object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: ArrayList<Any>): BundleBuilder {
        bundle.putSerializable(name, value)
        return this
    }

    /**
     * Inserts a Serializable value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a Serializable object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Serializable): BundleBuilder {
        bundle.putSerializable(name, value)
        return this
    }

    /**
     * Inserts a boolean array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a boolean array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: BooleanArray): BundleBuilder {
        bundle.putBooleanArray(name, value)
        return this
    }

    /**
     * Inserts a byte array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a byte array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: ByteArray): BundleBuilder {
        bundle.putByteArray(name, value)
        return this
    }

    /**
     * Inserts a short array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a short array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: ShortArray): BundleBuilder {
        bundle.putShortArray(name, value)
        return this
    }

    /**
     * Inserts a char array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a char array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: CharArray): BundleBuilder {
        bundle.putCharArray(name, value)
        return this
    }

    /**
     * Inserts an int array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value an int array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: IntArray): BundleBuilder {
        bundle.putIntArray(name, value)
        return this
    }

    /**
     * Inserts an long array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value an long array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: LongArray): BundleBuilder {
        bundle.putLongArray(name, value)
        return this
    }

    /**
     * Inserts an float array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value an float array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: FloatArray): BundleBuilder {
        bundle.putFloatArray(name, value)
        return this
    }

    /**
     * Inserts a double array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a double array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: DoubleArray): BundleBuilder {
        bundle.putDoubleArray(name, value)
        return this
    }

    /**
     * Inserts a String array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a String array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Array<String>): BundleBuilder {
        bundle.putStringArray(name, value)
        return this
    }

    /**
     * Inserts a double array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key a String, or null
     * @param value a double array object, or null
     * @return This BundleBuilder for method chaining
     */
    fun putExtra(name: String, value: Array<CharSequence>): BundleBuilder {
        bundle.putCharSequenceArray(name, value)
        return this
    }

    /**
     * @return Bundle
     */
    fun create(): Bundle = bundle

}