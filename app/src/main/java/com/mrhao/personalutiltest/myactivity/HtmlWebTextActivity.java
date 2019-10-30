package com.mrhao.personalutiltest.myactivity;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mrhao.personalutiltest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HtmlWebTextActivity extends AppCompatActivity {

    @BindView(R.id.loadhtmltext_web)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_web_text);
        ButterKnife.bind(this);
        setBaseEvent();
    }

    private void setBaseEvent() {

        webView.setVerticalScrollBarEnabled(false);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.setWebChromeClient(new WebChromeClient() {
        });
        webView.getSettings().setSupportZoom(true);//支持缩放
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);//不显示缩放按钮
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.getSettings().setDomStorageEnabled(false);
        if (Build.VERSION.SDK_INT >= 21) {//同时允许HTTP和HTTPS
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        webView.loadUrl("http://47.52.169.82/public/index.php/api/index/show_xq?id=8");
    }
}
