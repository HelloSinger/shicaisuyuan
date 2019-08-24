package com.haier.recognition.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.haier.recognition.R;


public class WifiDialog extends Dialog {
    Context mContext;
    Activity activity;

    public WifiDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        mContext = context;
        activity = (Activity) context;
        init();
    }

    private void init() {
        setContentView(R.layout.wifi_dialog);
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
                activity.finish();
            }
        });

    }

    private void close() {
        this.dismiss();
    }

}
