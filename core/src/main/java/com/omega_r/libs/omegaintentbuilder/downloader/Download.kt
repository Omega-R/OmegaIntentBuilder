package com.omega_r.libs.omegaintentbuilder.downloader

import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.builders.BaseUriBuilder

interface Download<out T> where T: BaseUriBuilder {

  fun uri(uriCollection: Collection<Uri>): T

}