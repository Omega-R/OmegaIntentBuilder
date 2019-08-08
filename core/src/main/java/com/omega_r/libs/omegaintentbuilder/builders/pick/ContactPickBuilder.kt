/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * ContactPickBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 26, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import com.omega_r.libs.omegaintentbuilder.builders.BaseActivityBuilder

class ContactPickBuilder : BaseActivityBuilder() {

    override fun createIntent(context: Context): Intent {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        return intent
    }


}