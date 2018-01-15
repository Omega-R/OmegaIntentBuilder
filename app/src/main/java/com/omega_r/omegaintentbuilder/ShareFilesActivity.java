package com.omega_r.omegaintentbuilder;

import android.os.Bundle;
import android.view.View;

import com.omega_r.libs.omegaintentbuilder.AppOmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler;
import com.omega_r.libs.omegaintentbuilder.types.MimeTypes;
import com.omega_r.omegaintentbuilder.models.Model;

import org.jetbrains.annotations.NotNull;

import omega.com.annotations.OmegaActivity;
import omega.com.annotations.OmegaExtra;
import omega.com.annotations.OmegaExtraModel;

@OmegaActivity
public class ShareFilesActivity extends BaseActivity implements View.OnClickListener {

    @OmegaExtra
    protected String url1;

    @OmegaExtraModel(prefix = "model")
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_files);
        AppOmegaIntentBuilder.inject(this);
        findViewById(R.id.button_share).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_share:
                downloadFiles();
                break;
        }
    }

    private void downloadFiles() {
        showProgress();
        OmegaIntentBuilder.from(this).share()
                .emailTo("your_email_here@gmail.com")
                .subject("Great library")
                .filesUrls(url1)
                .fileUrlWithMimeType(model.getUrl(), MimeTypes.IMAGE_PNG)
                .download(new DownloadCallback() {
                    @Override
                    public void onDownloaded(boolean success, @NotNull ContextIntentHandler contextIntentHandler) {
                        hideProgress();
                        contextIntentHandler.startActivity();
                    }
                });
    }

}
