package com.omega_r.libs.omegaintentbuilder.downloader

import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.BaseBuilder

interface Download<out T> where T: BaseBuilder {

  fun stream (streamUriList: List<Uri>): T

}