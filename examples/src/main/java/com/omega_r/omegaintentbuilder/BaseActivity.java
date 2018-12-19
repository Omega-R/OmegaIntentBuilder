package com.omega_r.omegaintentbuilder;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    private ProgressDialog progressDialog;

    protected void showProgress() {
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

    protected void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
