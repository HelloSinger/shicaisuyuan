package com.uuzuche.lib_zxing.activity;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.uuzuche.lib_zxing.LoadingProgress;
import com.uuzuche.lib_zxing.R;
import com.uuzuche.lib_zxing.view.X5WebView;

public class WebViewActivityX5 extends AppCompatActivity {
    private static final String TAG = "WebViewActivityX5";
    private X5WebView x5WebView;
    private long start;
    private String url;
    private ImageView imageView;
    private ImageView iv_bg;
    private LoadingProgress loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_acctivityx5);
        x5WebView = (X5WebView) findViewById(R.id.x5webView);
        imageView = (ImageView) findViewById(R.id.iv_back);
        loading = (LoadingProgress) findViewById(R.id.loadingprogress);
        iv_bg = (ImageView) findViewById(R.id.iv_bg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        loading.setVisibility(View.VISIBLE);
        iv_bg.setVisibility(View.VISIBLE);
        x5WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // mTestHandler.sendEmptyMessage(MSG_OPEN_TEST_URL);
                /* mWebView.showLog("test Log"); */
                loading.setVisibility(View.GONE);
                iv_bg.setVisibility(View.GONE);
                Log.d(TAG, "onCreate end: " + (System.currentTimeMillis()-start));

            }
        });
        start = System.currentTimeMillis();
//        Log.d(TAG, "onCreate: " + start);
        x5WebView.loadUrl(url);


    }

}
