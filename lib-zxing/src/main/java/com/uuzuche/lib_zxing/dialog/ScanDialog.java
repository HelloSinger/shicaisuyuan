package com.uuzuche.lib_zxing.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;


import com.uuzuche.lib_zxing.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

public class ScanDialog extends Dialog {
    Context mContext;
    Activity activity;

    public ScanDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        mContext = context;
        activity = (Activity) context;
        init();
    }

    private void init() {
        setContentView(R.layout.scan_dialog);

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
