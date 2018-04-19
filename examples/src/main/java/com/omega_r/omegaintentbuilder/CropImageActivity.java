package com.omega_r.omegaintentbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler;
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes;

import org.jetbrains.annotations.NotNull;

public class CropImageActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE = 111;
    private static final int DEFAULT_OUTPUT_X = 200;
    private static final int DEFAULT_OUTPUT_Y = 200;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        imageView = findViewById(R.id.imageview);
        imageView.setImageResource(R.drawable.crop_image);
        findViewById(R.id.button_crop).setOnClickListener(this);
        findViewById(R.id.button_crop_from_internet).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_crop:
                crop();
                break;
            case R.id.button_crop_from_internet:
                cropFromInternet();
                break;
        }
    }

    private void crop() {
        OmegaIntentBuilder.from(this)
                .cropImage()
                .property(DEFAULT_OUTPUT_X, DEFAULT_OUTPUT_Y)
                .bitmap(BitmapFactory.decodeResource(getResources(), R.drawable.crop_image))
                .createIntentHandler(this)
                .failToast("You don't have app for cropping image")
                .startActivityForResult(REQUEST_CODE);
    }

    private void cropFromInternet() {
        showProgress();
        OmegaIntentBuilder.from(this)
                .cropImage()
                .property(DEFAULT_OUTPUT_X, DEFAULT_OUTPUT_Y)
                .fileUrlWithMimeType("https://avatars1.githubusercontent.com/u/28600571?s=200&v=4", MimeTypes.IMAGE_PNG)
                .download(new DownloadCallback() {
                    @Override
                    public void onDownloaded(boolean success, @NotNull ContextIntentHandler contextIntentHandler) {
                        hideProgress();
                        startActivityForResult(contextIntentHandler.getIntent(), REQUEST_CODE);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap cropped = extras.getParcelable("data");
                imageView.setImageBitmap(cropped);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Crop cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
