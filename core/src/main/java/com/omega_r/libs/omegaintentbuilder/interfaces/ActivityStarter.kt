package com.omega_r.libs.omegaintentbuilder.interfaces

import android.os.Bundle
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityResultCallback

interface ActivityStarter {

    fun startActivity()

    fun startActivityForResult(requestCode: Int) {
        startActivityForResult(requestCode, null)
    }

    fun startActivityForResult(requestCode: Int, options: Bundle?)

    fun startActivityForResult(callback: ActivityResultCallback)

}