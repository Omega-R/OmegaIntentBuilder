package com.omega_r.omegaintentbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.handlers.ActivityResultCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.FailCallback;

import org.jetbrains.annotations.NotNull;

public class PhotoCaptureActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 101;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_capture);
        imageView = findViewById(R.id.imageview);
        findViewById(R.id.button_open_camera).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        OmegaIntentBuilder
                .photoCapture()
                .createIntentHandler(this)
                .failCallback(new FailCallback() {
                    @Override
                    public void onActivityStartError(@NotNull Exception exc) {
                        Toast.makeText(PhotoCaptureActivity.this, exc.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .startActivityForResult(new ActivityResultCallback() {
                    @Override
                    public void onActivityResult(int resultCode, @NotNull Intent data) {
                        if (resultCode == RESULT_OK) {
                            Bundle extras = data.getExtras();
                            if (extras != null) {
                                Bitmap imageBitmap = (Bitmap) extras.get("data");
                                imageView.setImageBitmap(imageBitmap);
                            }
                        }
                    }
                });
    }

}
