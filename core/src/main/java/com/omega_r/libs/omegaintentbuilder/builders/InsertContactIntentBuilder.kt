package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import com.omega_r.libs.omegaintentbuilder.types.EmailAddressType
import com.omega_r.libs.omegaintentbuilder.types.PhoneType

class InsertContactIntentBuilder : BaseActivityBuilder() {
    private var name: String? = null
    private var fullMode = false
    private var phoneticName: String? = null
    private var company: String? = null
    private var jobTitle: String? = null
    private var notes: String? = null
    private var phone: String? = null
    private var phoneType: PhoneType? = null
    private var customPhoneType: String? = null
    private var phoneIsPrimary = false
    private var secondaryPhone: String? = null
    private var secondaryPhoneType: PhoneType? = null
    private var customSecondaryPhoneType: String? = null
    private var tertiaryPhone: String? = null
    private var tertiaryPhoneType: PhoneType? = null
    private var customTertiaryPhoneType: String? = null
    private var email: String? = null
    private var emailType: EmailAddressType? = null
    private var customEmailType: String? = null
    private var emailIsPrimary = false
    private var secondaryEmail: String? = null
    private var secondaryEmailType: EmailAddressType? = null
    private var customSecondaryEmailType: String? = null
    private var tertiaryEmail: String? = null
    private var tertiaryEmailType: EmailAddressType? = null
    private var customTertiaryEmailType: String? = null
    private var postal: String? = null
    private var postalType: String? = null
    private var postalIsPrimary = false

    /**
     * Set the extra field for the contact name.
     *
     * @param name String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun name(name: String): InsertContactIntentBuilder {
        this.name = name
        return this
    }

    /**
     * If present, forces a bypass of quick insert mode.
     *
     * change fullMode = true
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun fullMode(): InsertContactIntentBuilder {
        this.fullMode = true
        return this
    }

    /**
     * Set the field for the contact phonetic name.
     *
     * @param phoneticName String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun phoneticName(phoneticName: String): InsertContactIntentBuilder {
        this.phoneticName = phoneticName
        return this
    }

    /**
     *  Set the field for the contact company.
     *
     * @param company String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun company(company: String): InsertContactIntentBuilder {
        this.company = company
        return this
    }

    /**
     *  Set the field for the contact job title.
     *
     * @param jobTitle String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun jobTitle(jobTitle: String): InsertContactIntentBuilder {
        this.jobTitle = jobTitle
        return this
    }

    /**
     * Set the field for the contact notes.
     *
     * @param notes String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun notes(notes: String): InsertContactIntentBuilder {
        this.notes = notes
        return this
    }

    /**
     * Set the field for the contact phone number.
     *
     * @param phone String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun phone(phone: String): InsertContactIntentBuilder {
        this.phone = phone
        return this
    }

    /**
     * Set the field for the contact phone number type.
     * <P>Type: Either an integer value from
     * @link CommonDataKinds.Phone,
     *  or a string specifying a custom label.</P>
     *
     * @param phoneType PhoneType
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun phoneType(phoneType: PhoneType): InsertContactIntentBuilder {
        this.phoneType = phoneType
        return this
    }

    /**
     * Set the field for the contact phone number type.
     * <P>Type: Either an String value to create custom type
     *
     * @param customPhoneType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun phoneType(customPhoneType: String): InsertContactIntentBuilder {
        this.customPhoneType = customPhoneType
        return this
    }

    /**
     * Set the field for the phone isprimary flag.
     * <P>Type: boolean</P>
     *
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun phoneIsPrimary(): InsertContactIntentBuilder {
        this.phoneIsPrimary = true
        return this
    }

    /**
     * Set the field for the secondary contact phone number.
     *
     * @param secondaryPhone String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun secondaryPhone(secondaryPhone: String): InsertContactIntentBuilder {
        this.secondaryPhone = secondaryPhone
        return this
    }

    /**
     * Set the field for the secondary contact phone number type.
     * <P>Type: Either an integer value from
     * @link CommonDataKinds.Phone,
     *  or a string specifying a custom label.</P>
     *
     * @param secondaryPhoneType PhoneType
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun secondaryPhoneType(secondaryPhoneType: PhoneType): InsertContactIntentBuilder {
        this.secondaryPhoneType = secondaryPhoneType
        return this
    }

    /**
     * Set the field for the contact phone number type.
     * Type: Either an String value to create custom type
     *
     * @param customSecondaryPhoneType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun secondaryPhoneType(customSecondaryPhoneType: String): InsertContactIntentBuilder {
        this.customSecondaryPhoneType = customSecondaryPhoneType
        return this
    }

    /**
     * Set the field for the tertiary contact phone number.
     *
     * @param tertiaryPhone String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun tertiaryPhone(tertiaryPhone: String): InsertContactIntentBuilder {
        this.tertiaryPhone = tertiaryPhone
        return this
    }

    /**
     * Set the field for the tertiary contact phone number type.
     * <P>Type: Either an integer value from
     * @link CommonDataKinds.Phone,
     *  or a string specifying a custom label.</P>
     *
     * @param tertiaryPhoneType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun tertiaryPhoneType(tertiaryPhoneType: PhoneType): InsertContactIntentBuilder {
        this.tertiaryPhoneType = tertiaryPhoneType
        return this
    }

    /**
     * Set the field for the contact phone number type.
     * Type: Either an String value to create custom type
     *
     * @param customTertiaryPhoneType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun tertiaryPhoneType(customTertiaryPhoneType: String): InsertContactIntentBuilder {
        this.customTertiaryPhoneType = customTertiaryPhoneType
        return this
    }

    /**
     * Set the field for the contact email address.
     *
     * @param email String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun email(email: String): InsertContactIntentBuilder {
        this.email = email
        return this
    }

    /**
     * Set the field for the contact email type.
     * <P>Type: Either an integer value from
     * {@link CommonDataKinds.Email}
     *  or a string specifying a custom label.</P>
     *
     * @param emailType EmailAddressType
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun emailType(emailType: EmailAddressType): InsertContactIntentBuilder {
        this.emailType = emailType
        return this
    }

    /**
     * Set the field for the contact email type.
     * Type: Either a String for custom type
     *
     * @param customEmailType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun emailType(customEmailType: String): InsertContactIntentBuilder {
        this.customEmailType = customEmailType
        return this
    }

    /**
     * Set the field for the email isprimary flag.
     *
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun emailIsPrimary(): InsertContactIntentBuilder {
        this.emailIsPrimary = true
        return this
    }

    /**
     * Set the field for the contact secondary email address.
     *
     * @param secondaryEmail String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun secondaryEmail(secondaryEmail: String): InsertContactIntentBuilder {
        this.secondaryEmail = secondaryEmail
        return this
    }

    /**
     * Set the field for the contact secondary email type.
     * <P>Type: Either an integer value from
     * {@link CommonDataKinds.Email}
     *  or a string specifying a custom label.</P>
     *
     * @param secondaryEmailType EmailAddressType
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun secondaryEmailType(secondaryEmailType: EmailAddressType): InsertContactIntentBuilder {
        this.secondaryEmailType = secondaryEmailType
        return this
    }

    /**
     * Set the field for the contact email type.
     * Type: Either a String for custom type
     *
     * @param customSecondaryEmailType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun secondaryEmailType(customSecondaryEmailType: String): InsertContactIntentBuilder {
        this.customSecondaryEmailType = customSecondaryEmailType
        return this
    }

    /**
     * Set the field for the contact tertiary email address.
     *
     * @param tertiaryEmail String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun tertiaryEmail(tertiaryEmail: String): InsertContactIntentBuilder {
        this.tertiaryEmail = tertiaryEmail
        return this
    }

    /**
     * Set the field for the contact tertiary email type.
     * <P>Type: Either an integer value from
     * {@link CommonDataKinds.Email}
     *  or a string specifying a custom label.</P>
     *
     * @param tertiaryEmailType EmailAddressType
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun tertiaryEmailType(tertiaryEmailType: EmailAddressType): InsertContactIntentBuilder {
        this.tertiaryEmailType = tertiaryEmailType
        return this
    }

    /**
     * Set the field for the contact email type.
     * Type: Either a String for custom type
     *
     * @param customSecondaryEmailType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun tertiaryEmailType(customTertiaryEmailType: String): InsertContactIntentBuilder {
        this.customTertiaryEmailType = customTertiaryEmailType
        return this
    }

    /**
     * Set the field for the contact postal address.
     *
     * @param postal String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun postal(postal: String): InsertContactIntentBuilder {
        this.postal = postal
        return this
    }

    /**
     * Set the field for the contact postal address type.
     * <P>Type: Either an integer value from
     *
     * @param postalType String
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun postalType(postalType: String): InsertContactIntentBuilder {
        this.postalType = postalType
        return this
    }

    /**
     * Set the extra field for the postal isprimary flag.
     *
     * @return This InsertContactIntentBuilder for method chaining
     */
    fun postalIsPrimary(): InsertContactIntentBuilder {
        this.postalIsPrimary = true
        return this
    }

    override fun createIntent(context: Context): Intent {
        return Intent(Intent.ACTION_INSERT).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE

            name?.let {
                putExtra(ContactsContract.Intents.Insert.NAME, it)
            }

            if (fullMode) {
                putExtra(ContactsContract.Intents.Insert.FULL_MODE, fullMode)
            }

            phoneticName?.let {
                putExtra(ContactsContract.Intents.Insert.PHONETIC_NAME, it)
            }

            company?.let {
                putExtra(ContactsContract.Intents.Insert.COMPANY, it)
            }

            jobTitle?.let {
                putExtra(ContactsContract.Intents.Insert.JOB_TITLE, it)
            }

            notes?.let {
                putExtra(ContactsContract.Intents.Insert.NOTES, it)
            }

            phone?.let {
                putExtra(ContactsContract.Intents.Insert.PHONE, it)
            }

            phoneType?.let {
                putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, it.type)
            } ?: run {
                customPhoneType?.let {
                    putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, it)
                }
            }

            if (phoneIsPrimary) {
                putExtra(ContactsContract.Intents.Insert.PHONE_ISPRIMARY, phoneIsPrimary)
            }

            secondaryPhone?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, it)
            }

            secondaryPhoneType?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE, it.type)
            } ?: run {
                customSecondaryPhoneType?.let {
                    putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE, it)
                }
            }

            tertiaryPhone?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE, it)
            }

            tertiaryPhoneType?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE_TYPE, it.type)
            } ?: run {
                customTertiaryPhoneType?.let {
                    putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE_TYPE, it)
                }
            }

            email?.let {
                putExtra(ContactsContract.Intents.Insert.EMAIL, it)
            }

            emailType?.let {
                putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, it.type)
            } ?: run {
                customEmailType?.let {
                    putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, it)
                }
            }

            if (emailIsPrimary) {
                putExtra(ContactsContract.Intents.Insert.EMAIL_ISPRIMARY, emailIsPrimary)
            }

            secondaryEmail?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_EMAIL, it)
            }

            secondaryEmailType?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_EMAIL_TYPE, it.type)
            } ?: run {
                customSecondaryEmailType?.let {
                    putExtra(ContactsContract.Intents.Insert.SECONDARY_EMAIL_TYPE, it)
                }
            }

            tertiaryEmail?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_EMAIL, it)
            }

            tertiaryEmailType?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_EMAIL_TYPE, it.type)
            } ?: run {
                customTertiaryEmailType?.let {
                    putExtra(ContactsContract.Intents.Insert.TERTIARY_EMAIL_TYPE, it)
                }
            }

            postal?.let {
                putExtra(ContactsContract.Intents.Insert.POSTAL, it)
            }

            postalType?.let {
                putExtra(ContactsContract.Intents.Insert.POSTAL_TYPE, it)
            }

            if (postalIsPrimary) {
                putExtra(ContactsContract.Intents.Insert.POSTAL_ISPRIMARY, postalIsPrimary)
            }

        }
    }

}