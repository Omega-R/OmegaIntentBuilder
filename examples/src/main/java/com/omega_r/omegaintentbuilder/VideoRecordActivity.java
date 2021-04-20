package com.omega_r.omegaintentbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityResultCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VideoRecordActivity extends BaseActivity implements View.OnClickListener {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        videoView = findViewById(R.id.video_view);
        findViewById(R.id.button_open_camera_to_record).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        OmegaIntentBuilder
                .recordVideo()
                .createIntentHandler(this)
                .failCallback(new FailCallback() {
                    @Override
                    public void onActivityStartError(@NotNull Exception exc) {
                        Toast.makeText(VideoRecordActivity.this, exc.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .startActivityForResult(new ActivityResultCallback() {
                    @Override
                    public void onActivityResult(int resultCode, @Nullable Intent data) {
                        if (resultCode == RESULT_OK) {
                            Uri videoUri = data.getData();
                            if (videoUri != null) {
                                videoView.setVisibility(View.VISIBLE);
                                videoView.setVideoURI(videoUri);
                                videoView.start();
                            }
                        }
                    }
                });
    }

}
