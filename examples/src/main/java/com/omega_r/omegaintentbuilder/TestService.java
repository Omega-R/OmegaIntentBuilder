package com.omega_r.omegaintentbuilder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import omega.com.annotations.OmegaExtra;
import omega.com.annotations.OmegaService;

@OmegaService
public class TestService extends Service {

    @OmegaExtra
    String value;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
