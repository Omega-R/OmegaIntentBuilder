package com.omega_r.omegaintentbuilder;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback;
import com.omega_r.libs.omegaintentbuilder.types.CalendarActionTypes;
import com.omega_r.libs.omegaintentbuilder.types.EmailAddressType;
import com.omega_r.libs.omegaintentbuilder.types.MapTypes;
import com.omega_r.libs.omegaintentbuilder.types.PhoneType;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//import com.omega_r.libs.omegaintentbuilder.AppOmegaIntentBuilder;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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
        findViewById(R.id.button_calendar).setOnClickListener(this);
        findViewById(R.id.button_sms).setOnClickListener(this);
        findViewById(R.id.button_photo_capture).setOnClickListener(this);
        findViewById(R.id.button_crop_image).setOnClickListener(this);
        findViewById(R.id.button_pick_image).setOnClickListener(this);
        findViewById(R.id.button_speech_to_text).setOnClickListener(this);
        findViewById(R.id.button_service_extra).setOnClickListener(this);
        findViewById(R.id.button_fragment_extra).setOnClickListener(this);
        findViewById(R.id.button_insert_contact).setOnClickListener(this);
        findViewById(R.id.button_search_web).setOnClickListener(this);
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
            case R.id.button_calendar:
                onCalendarClicked();
                break;
            case R.id.button_sms:
                onSmsClicked();
                break;
            case R.id.button_photo_capture:
                onPhotoCaptureClicked();
                break;
            case R.id.button_crop_image:
                onCropImageClicked();
                break;
            case R.id.button_pick_image:
                onPickImageClicked();
                break;
            case R.id.button_speech_to_text:
                onSpeechToTextClicked();
                break;
            case R.id.button_service_extra:
                onExtrasToServiceClicked();
                break;
            case R.id.button_fragment_extra:
                onExtrasToFragmentClicked();
                break;
            case R.id.button_insert_contact:
                onInsertContactClicked();
                break;
            case R.id.button_search_web:
                onSearchWebClicked();
                break;
        }
    }

    private void startShareFilesActivity() {
        OmegaIntentBuilder.activity(ShareFilesActivity.class)
                .startActivity(this);
//        AppOmegaIntentBuilder
//                .appActivities()
//                .shareFilesActivity()
//                .url1("https://developer.android.com/studio/images/hero_image_studio.png")
//                .modelVar2("https://avatars1.githubusercontent.com/u/28600571?s=200&v=4")
//                .startActivity();
    }

    private void startCallIntent() {
        OmegaIntentBuilder
                .call("88000000008")
                .createIntentHandler(this)
                .failToast("Sorry, you don't have app for making call phone")
                .startActivity();
    }

    private void startEmailIntent() {
        OmegaIntentBuilder
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
        OmegaIntentBuilder
                .share()
                .emailTo("develop@omega-r.com")
                .emailBcc("bcc1@test.com", "bcc2@test.com")
                .emailCc("cc1@test.com", "cc2@test.com")
                .subject("Great library")
                .createIntentHandler(this)
                .chooserTitle("Choose")
                .startActivity();
    }

    private void openUrl() {
        OmegaIntentBuilder
                .web("https://omega-r.com/")
                .createIntentHandler(this)
                .chooserTitle("Omega-R")
                .failToast("You don't have app for open urls")
                .startActivity();
    }

    private void openSettings() {
        OmegaIntentBuilder
                .settings()
                .startActivity(this);
    }

    private void openPlayStore() {
        OmegaIntentBuilder
                .playStore()
                .packageName("com.omegar.coloring")
                .startActivity(this);
    }

    private void openGoogleMap() {
        OmegaIntentBuilder
                .map(MapTypes.YANDEX_MAP, MapTypes.GOOGLE_MAP)
                .latitude(56.6327622)
                .longitude(47.910693)
                .address("Omega-R")
                .createIntentHandler(this)
                .failToast("You don't have Map application")
                .startActivity();
    }

    private void onCalendarClicked() {
        Date startDate = new Date();
        long endDate = startDate.getTime() + TimeUnit.DAYS.toMillis(7);
        OmegaIntentBuilder
                .calendar(CalendarActionTypes.INSERT_EVENT)
                .startDate(startDate)
                .endDate(endDate)
                .title("Omega-R")
                .description("Great library")
                .location("New York")
                .allDay(false)
                .organizer("develop@omega-r.com")
                .hasAlarm(false)
                .startActivity(this);
    }

    private void onSmsClicked() {
        OmegaIntentBuilder
                .sms("88000000008", "88888888888")
                .message("Great library")
                .createIntentHandler(this)
                .startActivity();
    }

    private void onPhotoCaptureClicked() {
        OmegaIntentBuilder
                .activity(PhotoCaptureActivity.class)
                .startActivity(this);
    }

    private void onCropImageClicked() {
        OmegaIntentBuilder
                .activity(CropImageActivity.class)
                .startActivity(this);
    }

    private void onPickImageClicked() {
        OmegaIntentBuilder
                .activity(PickImageActivity.class)
                .startActivity(this);
    }

    private void onSpeechToTextClicked() {
        OmegaIntentBuilder
                .activity(SpeechToTextActivity.class)
                .createIntentHandler(this)
                .startActivity();
    }

    private void onExtrasToServiceClicked() {
//        AppOmegaIntentBuilder.from(this)
//                .appServices()
//                .testService()
//                .value("Great library")
//                .modelVar2("Omega-R")
//                .startService();
    }


    private void onExtrasToFragmentClicked() {
//        AppOmegaIntentBuilder.from(this)
//                .appActivities()
//                .tabActivity()
//                .startActivity();
    }

    private void onInsertContactClicked() {
        OmegaIntentBuilder
                .insertContact()
                .name("John")
                .fullMode()
                .phoneticName("phoneticName")
                .company("company")
                .jobTitle("jobTitle")
                .notes("notes")
                .phone("88000000008")
                .phoneType(PhoneType.TYPE_HOME)
                .phoneIsPrimary(true)
                .secondaryPhone("88000001008")
                .secondaryPhoneType("YOUR_CUSTOM_TYPE")
                .tertiaryPhone("888888888")
                .tertiaryPhoneType(PhoneType.TYPE_WORK_MOBILE)
                .email("develop@omega-r.com")
                .emailType(EmailAddressType.TYPE_HOME)
                .emailIsPrimary(false)
                .secondaryEmail("secondaryEmail")
                .secondaryEmailType(EmailAddressType.TYPE_WORK)
                .tertiaryEmail("tertiaryEmail")
                .tertiaryEmailType("YOUR_CUSTOM_EMAIL_TYPE")
                .postal("postal")
                .postalType("Home")
                .postalIsPrimary(true)
                .createIntentHandler(this)
                .startActivity();
    }

    private void onSearchWebClicked() {
        OmegaIntentBuilder
                .searchWeb()
                .query("How much does an elephant weigh")
                .startActivity(this);
    }

    private void onCreateTimerClicked() {
        OmegaIntentBuilder
                .createTimer()
                .message("It's your timer")
                .seconds(5)
                .startActivity(this);
    }

}
