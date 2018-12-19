package com.omega_r.libs.omegaintentbuilder.providers;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.webkit.URLUtil;

import com.omega_r.libs.omegaintentbuilder.BuildConfig;

import java.io.File;

public class FileProvider extends androidx.core.content.FileProvider {

    public static Uri getLocalFileUri(Context context, File file) {
        return getUriForFile(context, context.getPackageName() + "." + BuildConfig.SUFFIX_AUTHORITY, file);
    }

    public static String getFileName(@NonNull String urlAddress) {
        return getFileName(urlAddress, null);
    }

    public static String getFileName(@NonNull String urlAddress, @Nullable String mimeType) {
        return URLUtil.guessFileName(urlAddress, null, mimeType);
    }
}
