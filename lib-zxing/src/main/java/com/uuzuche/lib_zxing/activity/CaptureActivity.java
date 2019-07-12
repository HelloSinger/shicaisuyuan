package com.uuzuche.lib_zxing.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.uuzuche.lib_zxing.BMToastUtil;
import com.uuzuche.lib_zxing.R;

import java.io.IOException;
import java.util.List;

/**
 * Initial the camera
 * <p>
 * 默认的二维码扫描Activity
 */
public class CaptureActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView iv_arrow1;
    private ImageView iv_arrow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

        }else{
            //否则去请求相机权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},100);

        }
        imageView = (ImageView) findViewById(R.id.iv_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_arrow1 = (ImageView) findViewById(R.id.iv_arrow1);

        if (!isNetworkAvailable()) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("from", "wifi");
            this.startActivity(intent);
            finish();
        }
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();
        Log.e("TAG", "是否有摄像头: " + checkCamera() + "");

        captureFragment.setCameraInitCallBack(new CaptureFragment.CameraInitCallBack() {
            @Override
            public void callBack(Exception e) {
                if (e == null) {

                } else {
                    Log.e("TAG", "callBack: ", e);
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void exitAPP1() {
        ActivityManager activityManager = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        for (ActivityManager.AppTask appTask : appTaskList) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                appTask.finishAndRemoveTask();
            }
        }
    }

    /**
     * @return 摄像头是否存在
     */
    private boolean checkCamera() {
        return CaptureActivity.this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {

            if (result.contains("http://hotc.haier.net") || result.contains("https://hotc.haier.net")) {
                Intent intent = new Intent(CaptureActivity.this, WebViewActivity.class);
                intent.putExtra("url", result);
                Log.e("TAG", "二维码: " + result);
                startActivity(intent);
                finish();
            } else {

                Intent intent = new Intent(CaptureActivity.this, ResultActivity.class);
                intent.putExtra("from", "error");
                startActivity(intent);
                finish();
            }


        }

        @Override
        public void onAnalyzeFailed() {
            BMToastUtil.showWashToast(CaptureActivity.this);
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }
    };

    private boolean isNetworkAvailable() {
        // 得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            return manager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}