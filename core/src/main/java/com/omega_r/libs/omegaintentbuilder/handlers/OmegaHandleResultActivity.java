package com.omega_r.libs.omegaintentbuilder.handlers;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.util.SparseArray;

import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.Nullable;

public class OmegaHandleResultActivity extends Activity {

    private static final String EXTRA_INDEX = "key_handler";
    private static final int REQUEST_CODE = 101;

    private static final AtomicInteger sCounter = new AtomicInteger();
    private static final SparseArray<ContextIntentHandler> sContextHandlers = new SparseArray<>();

    public static int start(Context context, ContextIntentHandler handler) {
        Intent intent = new Intent(context, OmegaHandleResultActivity.class);
        int index = pushContextIntentHandler(handler);
        intent.putExtra(EXTRA_INDEX, index);
        try {
            context.startActivity(intent);
        } catch (AndroidRuntimeException exc) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        return index;
    }

    private static int pushContextIntentHandler(ContextIntentHandler handler) {
        int index = sCounter.incrementAndGet();
        sContextHandlers.put(index, handler);
        return index;
    }

    private static void popContextIntentHandler(int index) {
        sContextHandlers.delete(index);
    }

    private int getIndex() {
        return getIntent().getIntExtra(EXTRA_INDEX, -1);
    }

    @Nullable
    private ContextIntentHandler getContextIntentHandler() {
        int index = getIndex();
        if (index >= 0) {
            return sContextHandlers.get(index);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            ContextIntentHandler contextIntentHandler = getContextIntentHandler();
            assert contextIntentHandler != null;
            try {
                startActivityForResult(contextIntentHandler.getIntent(), REQUEST_CODE);
            } catch (ActivityNotFoundException exc) {
                contextIntentHandler.handleStartActivityException(exc);
                popContextIntentHandler(getIndex());
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            ContextIntentHandler contextIntentHandler = getContextIntentHandler();
            if (contextIntentHandler != null) {
                contextIntentHandler.onActivityResult(resultCode, data);
            } else {
                Log.e("OmegaIntentBuilder", "Error application was destroyed");
            }
            popContextIntentHandler(getIndex());
            finish();
        }
    }
}
