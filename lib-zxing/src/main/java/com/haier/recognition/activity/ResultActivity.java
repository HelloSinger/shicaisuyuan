package com.haier.recognition.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;

import com.haier.recognition.R;
import com.haier.recognition.dialog.ScanDialog;
import com.haier.recognition.dialog.WifiDialog;


public class ResultActivity extends Activity {
    ScanDialog scanDialog;
    WifiDialog wifiDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        String from = getIntent().getStringExtra("from");
        if (from.equals("wifi")) {
            if (wifiDialog == null) {
                wifiDialog = new WifiDialog(this);
            }
            wifiDialog.show();
            wifiDialog.setCanceledOnTouchOutside(false);
            setDialogXY(wifiDialog);
        } else {
            if (scanDialog == null) {
                scanDialog = new ScanDialog(this);
            }
            scanDialog.show();
            scanDialog.setCanceledOnTouchOutside(false);
            setDialogXY(scanDialog);
        }

    }


    /**
     * 设置dialog位置
     *
     * @param dialog
     */
    private void setDialogXY(Dialog dialog) {
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
    }
}
