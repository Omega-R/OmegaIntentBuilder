package com.omega_r.libs.omegaintentbuilder.downloader

import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.BaseFileBuilder

interface Download<out T> where T: BaseFileBuilder {

  fun stream (streamUriList: List<Uri>): T

}