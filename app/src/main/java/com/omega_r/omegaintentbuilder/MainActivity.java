package com.omega_r.omegaintentbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omega_r.libs.omegaintentbuilder.AppOmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback;

import org.jetbrains.annotations.NotNull;
import com.omega_r.libs.omegaintentbuilder.types.MapTypes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_call).setOnClickListener(this);
        findViewById(R.id.button_send_email).setOnClickListener(this);
        findViewById(R.id.button_share).setOnClickListener(this);
        findViewById(R.id.button_share_files).setOnClickListener(this);
        findViewById(R.id.button_web).setOnClickListener(this);
        findViewById(R.id.button_settings).setOnClickListener(this);
        findViewById(R.id.button_playstore).setOnClickListener(this);
        findViewById(R.id.button_navigation).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_call:
                startCallIntent();
                break;
            case R.id.button_send_email:
                startEmailIntent();
                break;
            case R.id.button_share:
                startShareIntent();
                break;
            case R.id.button_share_files:
                startShareFilesActivity();
                break;
            case R.id.button_web:
                openUrl();
                break;
            case R.id.button_settings:
                openSettings();
                break;
            case R.id.button_playstore:
                openPlayStore();
                break;
            case R.id.button_navigation:
                openGoogleMap();
                break;
        }
    }

    private void startShareFilesActivity() {
        AppOmegaIntentBuilder.from(this)
                .appActivity()
                .shareFilesActivity()
                .url1("https://developer.android.com/studio/images/hero_image_studio.png")
                .var2("https://avatars1.githubusercontent.com/u/28600571?s=200&v=4")
                .createIntentHandler()
                .startActivity();
    }

    private void startCallIntent() {
        OmegaIntentBuilder.from(this)
                    .call()
                    .phoneNumber("88000000008")
                    .createIntentHandler(this)
                    .failToast("Sorry, you don't have app for making call phone")
                    .startActivity();
    }

    private void startEmailIntent() {
        OmegaIntentBuilder.from(this)
                .email()
                .text("Hello world")
                .emailTo("develop@omega-r.com")
                .subject("Great library")
                .createIntentHandler(this)
                .failCallback(new FailCallback() {
                    @Override
                    public void onActivityStartError(@NotNull Exception exc) {
                        Toast.makeText(getApplicationContext(), "Sorry, you don't have app for sending email", Toast.LENGTH_SHORT).show();
                    }
                })
                .startActivity();
    }

    private void startShareIntent() {
        OmegaIntentBuilder.from(this)
                .share()
                .emailTo("develop@omega-r.com")
                .emailBcc("bcc1@test.com","bcc2@test.com")
                .emailCc("cc1@test.com","cc2@test.com")
                .subject("Great library")
                .createIntentHandler(this)
                .chooserTitle("Choose")
                .startActivity();
    }

    private void openUrl() {
        OmegaIntentBuilder.from(this)
                .web()
                .url("https://omega-r.com/")
                .createIntentHandler()
                .chooserTitle("Omega-R")
                .failToast("You don't have app for open urls")
                .startActivity();
    }

    private void openSettings() {
        OmegaIntentBuilder.from(this)
                .settings()
                .createIntentHandler()
                .startActivity();
    }

    private void openPlayStore() {
        OmegaIntentBuilder.from(this)
                .playStore()
                .packageName("com.omegar.coloring")
                .createIntentHandler()
                .startActivity();
    }

    private void openGoogleMap() {
        OmegaIntentBuilder.from(this)
                .map()
                .type(MapTypes.GOOGLE_MAP)
                .latitude(56.6327622)
                .longitude(47.910693)
                .address("Omega-R")
                .createIntentHandler()
                .failToast("You don't have Google Map application")
                .startActivity();
    }

}
