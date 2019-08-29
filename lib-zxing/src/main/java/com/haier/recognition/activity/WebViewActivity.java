package com.haier.recognition.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.haier.recognition.LoadingProgress;
import com.haier.recognition.R;


public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "WebViewActivity";
    private WebView mWebView;
    private ImageView imageView;
    private ImageView iv_bg;
    private LoadingProgress loading;
    private  Boolean isFirst =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_acctivity2);
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
        String url = intent.getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.setWebViewClient(new WebViewClient() {
            // 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "shouldOverrideUrlLoading: "+url);
                view.loadUrl(url);
                return true;
                //开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
            }

            // 当每一个页面加载出来时的动作 可以获取当前页面的信息 如URL 如标题等

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
                // 如可以得到当前的URL
                //current_url=view.getUrl();
                if(isFirst){
                    loading.setVisibility(View.VISIBLE);
                    iv_bg.setVisibility(View.VISIBLE);
                    isFirst= false;
                }

                Log.d("web", "开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定加载结束的操作
                loading.setVisibility(View.GONE);
                iv_bg.setVisibility(View.GONE);
                Log.d("web", "加载结束");
            }

            //加载页面的服务器出现错误时（如404）调用 使用自定义的错误界面 更符合软件的整体设计风格
            //步骤1：写一个html文件（error_handle.html），用于出错时展示给用户看的提示页面
//步骤2：将该html文件放置到代码根目录的assets文件夹下
//步骤3：复写WebViewClient的onRecievedError方法
//该方法传回了错误码，根据错误类型可以进行不同的错误分类处理
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.d("web", "加载失败");
                switch (errorCode) {

                }
            }

            //处理https请求
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
//                super.onReceivedSslError(view, handler, error);
            }

        });

        WebSettings webSettings = mWebView.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setUseWideViewPort(true);
        mWebView.loadUrl(url);
    }

}
