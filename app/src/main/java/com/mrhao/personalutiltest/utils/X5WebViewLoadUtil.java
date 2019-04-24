package com.mrhao.personalutiltest.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebViewLoadUtil {

    public static void loadurl(final Context context, WebView tencentWeb, String baseurl) {

        tencentWeb.onResume();
        WebSettings webSetting = tencentWeb.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
//         webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);


        tencentWeb.getSettings().setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        tencentWeb.getSettings().setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        tencentWeb.getSettings().setDisplayZoomControls(true); //隐藏原生的缩放控件
        tencentWeb.getSettings().setBlockNetworkImage(false);//解决图片不显示
        tencentWeb.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
        // 使用localStorage则必须打开


        //该界面打开更多链接
        tencentWeb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (url == null) return false;
                try {
                    if (url.startsWith("alipay://") || url.startsWith("alipays://") ||
                            url.startsWith("mqqapi://") || url.startsWith("mqqapis://") ||
                            url.startsWith("weixin://") || url.startsWith("weixins://") ||
                            url.startsWith("mqq://") || url.startsWith("mqqs://")
                    ) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        context.startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }

                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
                //接收网站安全证书
            }
        });
        //监听网页的加载进度
        tencentWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView w, int i) {

            }
        });

        //设置 缓存模式
        tencentWeb.getSettings().setAppCacheEnabled(true);
        tencentWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 开启 DOM storage API 功能
        tencentWeb.getSettings().setDomStorageEnabled(true);

        //解决android6.0以下手机webview加载网页不显示图片的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tencentWeb.getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        tencentWeb.loadUrl(baseurl);


    }
}
