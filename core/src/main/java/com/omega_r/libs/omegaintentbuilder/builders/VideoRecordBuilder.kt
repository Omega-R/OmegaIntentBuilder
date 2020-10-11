package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Context
import android.content.Intent
import android.provider.MediaStore

class VideoRecordBuilder: BaseActivityBuilder() {
    override fun createIntent(context: Context): Intent {
        return Intent(MediaStore.ACTION_VIDEO_CAPTURE);
    }
}