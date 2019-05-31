package com.uuzuche.lib_zxing;

/**
 * Created by YJ on 2017/1/9.
 */

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Toast管理
 */
public class BMToastUtil {

    public static Toast toast;



    /**
     *
     *
     * @param context
     */
    public static void showWashToast(Context context) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        View layout = View.inflate(context, R.layout.bm_dialog, null);
        // 设置toast文本，把设置好的布局传进来
        toast.setView(layout);
        // 设置土司显示在屏幕的位置
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 864);
        // 显示土司
        toast.show();
    }


}