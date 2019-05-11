package com.mrhao.personalutiltest.myactivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.utils.MyUtils;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JavaExeActivity extends AppCompatActivity {

    String[] str1 = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    List<String> list_str = new ArrayList<>();
    List<String> list_str2 = new ArrayList<>();

    int[] week = {1, 2, 3, 4, 5, 6, 7};
    List<Integer> list_week = new ArrayList<>();

    @BindView(R.id.x5_webview)
    WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_exe);
        ButterKnife.bind(this);
        ExerciseJava();
        DailyExercise();
        LoadX5Webview();
    }


    private void LoadX5Webview() {
        // 不显示滚动条
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        WebSettings settings = mWebView.getSettings();
        // 允许文件访问
        settings.setAllowFileAccess(true);
        // 支持javaScript
        settings.setJavaScriptEnabled(true);
        // 允许网页定位
        settings.setGeolocationEnabled(true);
        // 允许保存密码
        settings.setSavePassword(true);


        // 加快HTML网页加载完成的速度，等页面finish再加载图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }


        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());

//        String url = "https://github.com/getActivity/";
        String url = "https://www.csdn.net/";
        mWebView.loadUrl(url);
    }


    private class MyWebViewClient extends WebViewClient {

        // 网页加载错误时回调，这个方法会在 onPageFinished 之前调用
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, final String failingUrl) {
//            showError();
            Log.e("hhhh696969", "加载错误回调显示日志 " + description);
        }

        // 开始加载网页
        @Override
        public void onPageStarted(final WebView view, final String url, Bitmap favicon) {
//            mProgressBar.setVisibility(View.VISIBLE);
        }

        // 完成加载网页
        @Override
        public void onPageFinished(WebView view, String url) {

//            mProgressBar.setVisibility(View.GONE);
//            showComplete();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //super.onReceivedSslError(view, handler, error);注意一定要去除这行代码，否则设置无效。
            // handler.cancel();// Android默认的处理方式
            handler.proceed();// 接受所有网站的证书
            // handleMessage(Message msg);// 进行其他处理
        }

        // 跳转到其他链接
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, final String url) {

            String scheme = Uri.parse(url).getScheme();
            if (scheme != null) {
                scheme = scheme.toLowerCase();
            }
            if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                mWebView.loadUrl(url);
            }
            // 已经处理该链接请求
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        // 收到网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title != null) {
                setTitle(title);
            }
        }

        // 收到加载进度变化
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.e("hhhh696969", "加载网页进度展示: " + newProgress);
        }

    }


    private void DailyExercise() {
        //获取当前时间
        String aaa = MyUtils.getTimeNow(null);
        //获取当前手机屏幕宽度、高度和密度

    }


    //《HeadFirst》阅读随练
    private void ExerciseJava() {
        //循环遍历

        //for循环
        for (int i = 0; i < str1.length; i++) {
            list_str.add(str1[i]);

        }

        //for超级遍历
        for (String a : str1) {
            list_str2.add(a);

        }

        Log.e("TAGTAG", "List_Str1And2_toString: " + list_str.toString() + "\n" + list_str2.toString());

        //调用indexOf(int)可以查找具体元素在ArrayList集合中的位置(从0开始)，查询的元素在ArrayList集合中必须包含


        Log.e("HHHHYYYYLLLL", "IndexOf(): " + list_str.indexOf("周五"));

    }


}
