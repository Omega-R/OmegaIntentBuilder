package com.omega_r.libs.omegaintentbuilder.types

import android.provider.ContactsContract

enum class EmailAddressType(val type: Int) {
    TYPE_HOME(ContactsContract.CommonDataKinds.Email.TYPE_HOME),
    TYPE_WORK(ContactsContract.CommonDataKinds.Email.TYPE_WORK),
    TYPE_OTHER(ContactsContract.CommonDataKinds.Email.TYPE_OTHER),
    TYPE_MOBILE(ContactsContract.CommonDataKinds.Email.TYPE_MOBILE)
}