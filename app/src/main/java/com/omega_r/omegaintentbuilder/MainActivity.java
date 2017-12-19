package com.omega_r.omegaintentbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private OmegaIntentBuilder intentBuilder;

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
        intentBuilder = new OmegaIntentBuilder(this);
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
        }
    }

    private void startShareFilesActivity() {
        String url = "https://developer.android.com/studio/images/hero_image_studio.png";
        startActivity(ShareFilesActivity.createIntent(this, url));
    }

    private void startCallIntent() {
        intentBuilder.call()
                    .phoneNumber("88000000008")
                    .createHandler(this)
                    .tryStartActivity("Sorry, you don't have app for making call phone");
    }

    private void startEmailIntent() {
        intentBuilder.email()
                .text("Hello world")
                .emailTo("develop@omega-r.com")
                .subject("Great library")
                .createHandler(this)
                .tryStartActivity("Sorry, you don't have app for sending email");
    }

    private void startShareIntent() {
        intentBuilder.share()
                .emailTo("develop@omega-r.com")
                .emailBcc("bcc1@test.com","bcc2@test.com")
                .emailCc("cc1@test.com","cc2@test.com")
                .subject("Great library")
                .createHandler(this)
                .chooserTitle("Choose")
                .startActivity();
    }

    private void openUrl() {
        intentBuilder.web()
                .url("https://omega-r.com/")
                .createHandler()
                .chooserTitle("Omega-R")
                .tryStartActivity("You don't have app for open urls");
    }

    private void openSettings() {
        intentBuilder.settings().createHandler().startActivity();
    }
}
