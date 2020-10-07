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
    private var phoneIsPrimary = false
    private var secondaryPhone: String? = null
    private var secondaryPhoneType: PhoneType? = null
    private var tertiaryPhone: String? = null
    private var tertiaryPhoneType: PhoneType? = null
    private var email: String? = null
    private var emailType: EmailAddressType? = null
    private var emailIsPrimary = false
    private var secondaryEmail: String? = null
    private var secondaryEmailType: EmailAddressType? = null
    private var tertiaryEmail: String? = null
    private var tertiaryEmailType: EmailAddressType? = null
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
                putExtra(ContactsContract.Intents.Insert.NAME, name)
            }

            putExtra(ContactsContract.Intents.Insert.FULL_MODE, fullMode)

            phoneticName?.let {
                putExtra(ContactsContract.Intents.Insert.PHONETIC_NAME, phoneticName)
            }

            company?.let {
                putExtra(ContactsContract.Intents.Insert.COMPANY, company)
            }

            jobTitle?.let {
                putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle)
            }

            notes?.let {
                putExtra(ContactsContract.Intents.Insert.NOTES, notes)
            }

            phone?.let {
                putExtra(ContactsContract.Intents.Insert.PHONE, phone)
            }

            phoneType?.let {
                putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, phoneType!!.type)
            }

            putExtra(ContactsContract.Intents.Insert.PHONE_ISPRIMARY, phoneIsPrimary)


            secondaryPhone?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, secondaryPhone)
            }

            secondaryPhoneType?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE, secondaryPhoneType!!.type)
            }

            tertiaryPhone?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE, tertiaryPhone)
            }

            tertiaryPhoneType?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_PHONE_TYPE, tertiaryPhoneType!!.type)
            }

            email?.let {
                putExtra(ContactsContract.Intents.Insert.EMAIL, email)
            }

            emailType?.let {
                putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, emailType!!.type)
            }

            putExtra(ContactsContract.Intents.Insert.EMAIL_ISPRIMARY, emailIsPrimary)

            secondaryEmail?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_EMAIL, secondaryEmail)
            }

            secondaryEmailType?.let {
                putExtra(ContactsContract.Intents.Insert.SECONDARY_EMAIL_TYPE, secondaryEmailType!!.type)
            }

            tertiaryEmail?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_EMAIL, tertiaryEmail)
            }

            tertiaryEmailType?.let {
                putExtra(ContactsContract.Intents.Insert.TERTIARY_EMAIL_TYPE, tertiaryEmailType!!.type)
            }

            postal?.let {
                putExtra(ContactsContract.Intents.Insert.POSTAL, postal)
            }

            postalType?.let {
                putExtra(ContactsContract.Intents.Insert.POSTAL_TYPE, postalType)
            }

            putExtra(ContactsContract.Intents.Insert.POSTAL_ISPRIMARY, postalIsPrimary)

        }
    }

}