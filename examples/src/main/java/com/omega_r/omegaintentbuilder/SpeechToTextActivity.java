package com.omega_r.omegaintentbuilder;

import android.content.Intent;
import android.speech.RecognizerIntent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityResultCallback;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpeechToTextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        textView = findViewById(R.id.textview);
        findViewById(R.id.button_speech_to_text).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        OmegaIntentBuilder
                .speechToText()
                .prompt("Say something")
                .createIntentHandler(this)
                .failToast("You don't have app for \"Speech to text\"")
                .startActivityForResult(new ActivityResultCallback() {
                    @Override
                    public void onActivityResult(int resultCode, @Nullable Intent data) {
                        if (resultCode == RESULT_OK && data != null) {
                            textView.setText("");
                            List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            for (String msg : result) {
                                textView.append("\n" + msg);
                            }
                        }
                    }
                });
    }
}
