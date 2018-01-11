package com.omega_r.omegaintentbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CropImageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = CropImageActivity.class.getName();

    private static final int CROP_REQUEST_CODE = 101;
    private static final int QUALITY = 100;

    private static final int DEFAULT_OUTPUT_X = 200;
    private static final int DEFAULT_OUTPUT_Y = 200;
    private static final int DEFAULT_ASPECT_X = 1;
    private static final int DEFAULT_ASPECT_Y = 1;
    private static final boolean DEFAULT_SCALE = true;

    private ImageView imageView;
    private File outputFile;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        imageView = findViewById(R.id.imageview);
        imageView.setImageResource(R.drawable.crop_image);
        findViewById(R.id.button_crop).setOnClickListener(this);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.crop_image);
        try {
            outputFile = new File(this.getCacheDir(), "intent_files/output.jpg");
            bmp.compress(Bitmap.CompressFormat.JPEG, QUALITY, new FileOutputStream(outputFile));
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Cannot write image to SDCARD", e);
        }
    }

    @Override
    public void onClick(View v) {
        OmegaIntentBuilder.from(this)
                .cropImage(outputFile)
                .property(DEFAULT_OUTPUT_X, DEFAULT_OUTPUT_Y, DEFAULT_ASPECT_X, DEFAULT_ASPECT_Y, DEFAULT_SCALE)
                .createIntentHandler(this)
                .failToast("You don't have app for cropping image")
                .startActivityForResult(CROP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CROP_REQUEST_CODE && resultCode == RESULT_OK) {
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
