package com.omega_r.omegaintentbuilder;

import android.content.ActivityNotFoundException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final OmegaIntentBuilder intentBuilder = new OmegaIntentBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_call).setOnClickListener(this);
        findViewById(R.id.button_send_email).setOnClickListener(this);
        findViewById(R.id.button_share).setOnClickListener(this);
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
        }
    }

    private void startCallIntent() {
        try {
            intentBuilder.call().phoneNumber("88000000008").handler(this).startActivity();
        } catch (ActivityNotFoundException exc) {
            Toast.makeText(this, "Sorry, you don't have app for making call phone", Toast.LENGTH_SHORT).show();
        }
    }

    private void startEmailIntent() {
        try {
            intentBuilder.email()
                    .setText("Hello world")
                    .setEmailTo("develop@omega-r.com")
                    .setSubject("Great library")
                    .handler(this)
                    .startActivity();
        } catch (ActivityNotFoundException exc) {
            Toast.makeText(this, "Sorry, you don't have app for sending email", Toast.LENGTH_SHORT).show();
        }
    }

    private void startShareIntent() {
        intentBuilder.share()
                .setEmailTo("develop@omega-r.com")
                .setEmailBcc(Arrays.asList("bcc1@test.com","bcc2@test.com"))
                .setEmailCc(Arrays.asList("cc1@test.com","cc2@test.com"))
                .setSubject("Great library")
                .handler(this)
                .setChooserTitle("Choose")
                .startActivity();
    }
}
