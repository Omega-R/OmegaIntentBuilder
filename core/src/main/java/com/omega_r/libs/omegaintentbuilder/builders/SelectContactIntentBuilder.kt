package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract

class SelectContactIntentBuilder : BaseActivityBuilder() {
    override fun createIntent(context: Context): Intent {
        return Intent(Intent.ACTION_PICK).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE
        }
    }
}