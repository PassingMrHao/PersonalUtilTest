package com.mrhao.personalutiltest.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;;
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


        //WebViewClient
        tencentWeb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                WebView.HitTestResult hit = view.getHitTestResult();
                //hit.getExtra()为null或者hit.getType() == 0都表示即将加载的URL会发生重定向，需要做拦截处理
                if (TextUtils.isEmpty(hit.getExtra()) || hit.getType() == 0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(view.getUrl()));
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    view.loadUrl(url); //此处不再调用该方法，避免页面报错: net::ERR_UNKNOWN_URL_SCHEME
                    return true;//返回true表示当前url已经加载完成，即使url还会重定向，webview也不会再进行加载

                } else {
                    if (url.startsWith("http://") || url.startsWith("https://")) {
                        //加载的url是http/https协议地址
                        view.loadUrl(url);
                        return false; //返回false表示此url默认由系统处理,url未加载完成，会继续往下走
                    } else {
                        //加载的url是自定义协议地址
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(view.getUrl()));
                            context.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
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
