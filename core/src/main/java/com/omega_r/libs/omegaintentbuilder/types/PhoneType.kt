package com.omega_r.libs.omegaintentbuilder.types

import android.provider.ContactsContract

enum class PhoneType(val type: Int) {
    TYPE_HOME(ContactsContract.CommonDataKinds.Phone.TYPE_HOME),
    TYPE_ISDN(ContactsContract.CommonDataKinds.Phone.TYPE_ISDN),
    TYPE_MAIN(ContactsContract.CommonDataKinds.Phone.TYPE_MAIN),
    TYPE_MMS(ContactsContract.CommonDataKinds.Phone.TYPE_MMS),
    TYPE_MOBILE(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE),
    TYPE_OTHER(ContactsContract.CommonDataKinds.Phone.TYPE_OTHER),
    TYPE_OTHER_FAX(ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX),
    TYPE_PAGER(ContactsContract.CommonDataKinds.Phone.TYPE_PAGER),
    TYPE_RADIO(ContactsContract.CommonDataKinds.Phone.TYPE_RADIO),
    TYPE_TELEX(ContactsContract.CommonDataKinds.Phone.TYPE_TELEX),
    TYPE_TTY_TDD(ContactsContract.CommonDataKinds.Phone.TYPE_TTY_TDD),
    TYPE_WORK(ContactsContract.CommonDataKinds.Phone.TYPE_WORK),
    TYPE_WORK_MOBILE(ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE),
    TYPE_WORK_PAGER(ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER)
}