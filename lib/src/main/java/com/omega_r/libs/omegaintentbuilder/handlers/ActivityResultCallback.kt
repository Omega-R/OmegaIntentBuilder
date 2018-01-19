package com.omega_r.libs.omegaintentbuilder.handlers

import android.content.Intent

interface ActivityResultCallback {

  fun onActivityResult(resultCode: Int, data: Intent)

}