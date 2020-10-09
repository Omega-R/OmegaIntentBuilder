package com.omega_r.omegaintentbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.types.ImageTypes;

import java.io.IOException;

public class PickImageActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE = 141;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);
        imageView = findViewById(R.id.imageview);
        findViewById(R.id.button_pick_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        OmegaIntentBuilder
                .pick()
                .image()
                .imageTypes(ImageTypes.IMAGE_JPEG, ImageTypes.IMAGE_PNG)
                .multiply(false)
                .createIntentHandler(this)
                .startActivityForResult(REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imageView.setImageBitmap(bitmap);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Pick cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
