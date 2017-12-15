/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * DownloadCallback.kt
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 14, 2017
 */
package com.omega_r.libs.omegaintentbuilder.downloader

import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler

/**
 * DownloadCallback it is a interface to controll download state
 */
interface DownloadCallback {

  fun onDownloaded(success: Boolean, contextIntentHandler: ContextIntentHandler)

}