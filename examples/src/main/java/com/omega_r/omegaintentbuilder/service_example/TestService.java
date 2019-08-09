package com.omega_r.omegaintentbuilder.service_example;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import android.util.Log;

//import com.omega_r.libs.omegaintentbuilder.AppOmegaIntentBuilder;
import com.omega_r.omegaintentbuilder.models.Model;

import omega.com.annotations.OmegaExtra;
import omega.com.annotations.OmegaExtraModel;
import omega.com.annotations.OmegaService;

@OmegaService
public class TestService extends IntentService {

    private static final String TAG = TestService.class.getSimpleName();

    @OmegaExtra
    String value;

    @OmegaExtraModel(prefix = "model")
    Model model = new Model();

    public TestService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        AppOmegaIntentBuilder.inject(this, intent);
        Log.d(TAG, value);
        Log.d(TAG, model.getUrl());
    }
}
