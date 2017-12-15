package com.omega_r.omegaintentbuilder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.builders.MimeTypes;
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShareFilesActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_FILE = "file";

    private OmegaIntentBuilder intentBuilder;
    @Nullable
    private ProgressDialog progressDialog;

    public static Intent createIntent(Context context, String file) {
        return new OmegaIntentBuilder(context)
                .activity(ShareFilesActivity.class)
                .putExtra(KEY_FILE, file)
                .createIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_files);
        intentBuilder = new OmegaIntentBuilder(this);
        findViewById(R.id.button_share).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_share:
                downlodFiles();
                break;
        }
    }

    private void downlodFiles() {
        showProgress();
        String url1 = getIntent().getStringExtra(KEY_FILE);
        String url2 = "https://avatars1.githubusercontent.com/u/28600571?s=200&v=4";

        intentBuilder.share()
                .emailTo("your_email_here@gmail.com")
                .subject("Great library")
                .filesUrls(url1)
                .filesUrlWithMimeType(url2, MimeTypes.IMAGE_PNG)
                .download(new DownloadCallback() {
                    @Override
                    public void onDownloaded(boolean success, @NotNull ContextIntentHandler contextIntentHandler) {
                        hideProgress();
                        contextIntentHandler.startActivity();
                    }
                });
    }

    private void showProgress() {
        if (progressDialog == null) {
            progressDialog = createProgressDialog();
        }
        assert progressDialog != null;
        progressDialog.show();
    }

    private ProgressDialog createProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
