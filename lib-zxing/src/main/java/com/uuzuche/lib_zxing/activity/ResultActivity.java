package com.uuzuche.lib_zxing.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;

import com.uuzuche.lib_zxing.R;
import com.uuzuche.lib_zxing.dialog.ScanDialog;
import com.uuzuche.lib_zxing.dialog.WifiDialog;

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
