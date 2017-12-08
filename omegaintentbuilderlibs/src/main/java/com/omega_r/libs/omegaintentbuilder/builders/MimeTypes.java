/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * MimeTypes.java
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders;

/**
 * MimeTypes is a helper for constructing {@link ShareIntentBuilder#setType(String)}
 * It's most common mime types.
 */
public interface MimeTypes {

    String IMAGE = "image/*";

    String VIDEO = "video/*";

    String AUDIO = "audio/*";

    // Text
    String TEXT_PLAIN = "text/plain";
    String TEXT_HTML = "text/html";

}
