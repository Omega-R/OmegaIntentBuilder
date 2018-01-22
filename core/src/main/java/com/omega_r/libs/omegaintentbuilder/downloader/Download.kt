package com.omega_r.libs.omegaintentbuilder.downloader

import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.BaseFileBuilder
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder

interface Download<out T> where T: BaseFileBuilder {

  fun uri(uriList: List<Uri>): T

}