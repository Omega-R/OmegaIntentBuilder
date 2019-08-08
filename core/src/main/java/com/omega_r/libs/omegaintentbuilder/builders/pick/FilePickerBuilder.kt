/*
 * Copyright (c) 2018 Omega-r
 *
 * OmegaIntentBuilder
 * FilePickerBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   January 26, 2018
 */
package com.omega_r.libs.omegaintentbuilder.builders.pick

open class FilePickerBuilder : BasePickBuilder() {

    /**
     * Set mime type
     *
     * @param mimeType String
     * @return This FilePickerBuilder for method chaining
     */
    fun mimeType(mimeType: String): FilePickerBuilder {
        super.mimeType = mimeType
        return this
    }


}