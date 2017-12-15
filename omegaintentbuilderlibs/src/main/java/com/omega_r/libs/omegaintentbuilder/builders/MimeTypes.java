/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * MimeTypes.java
 *
 * @author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders;

import com.omega_r.libs.omegaintentbuilder.builders.share.ShareIntentBuilder;

/**
 * MimeTypes is a helper for constructing {@link ShareIntentBuilder#mimeType(String)}
 * It's most common mime types.
 */
public interface MimeTypes {

    String IMAGE = "image/*";
    String IMAGE_PNG = "image/png";
    String IMAGE_JPEG = "image/jpeg";
    String IMAGE_GIF = "image/gif";
    String IMAGE_PJPEG = "image/pjpeg";
    String IMAGE_SVG_XML = "image/svg+xml";
    String IMAGE_TIFF = "image/tiff";
    String IMAGE_WEBP = "image/webp";

    String VIDEO = "video/*";
    String VIDEO_MPEG = "video/mpeg";
    String VIDEO_MP4 = "video/mp4";
    String VIDEO_OGG = "video/ogg";
    String VIDEO_WEBM = "video/webm";
    String VIDEO_FLV = "video/x-flv";
    String VIDEO_3GPP = "video/3gpp";
    String VIDEO_3GPP2 = "video/3gpp2";

    String AUDIO = "audio/*";
    String AUDIO_MP4 = "audio/mp4";
    String AUDIO_AAC = "audio/aac";
    String AUDIO_MPEG = "audio/mpeg";
    String AUDIO_OGG = "audio/ogg";
    String AUDIO_WEBM = "audio/webm";

    String TEXT_PLAIN = "text/plain";
    String TEXT_HTML = "text/html";
    String TEXT_CSS = "text/css";
    String TEXT_PHP = "text/php";
    String TEXT_XML = "text/xml";

    String APPLICATION = "application/*";
    String APPLICATION_PDF = "application/pdf";
    String APPLICATION_OGG = "application/ogg";
    String APPLICATION_XHTML = "application/xhtml+xml";
    String APPLICATION_ZIP = "application/zip";
    String APPLICATION_GZIP = "application/gzip";
    String APPLICATION_XML = "application/xml";

}
