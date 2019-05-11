package com.mrhao.personalutiltest.utils;


import android.view.ViewGroup;

/**
 * 工具类说明: WebView 生命周期优化工具，方法重载对应webview和腾讯X5webview
 * How to use:
 *
 * @CreatData: 2019\5\10 0010  16:15
 */
public class WebViewLifecycleUtils {

    public static void onResume(android.webkit.WebView webView) {
        webView.onResume();
        webView.resumeTimers();
    }


    public static void onPause(android.webkit.WebView webView) {
        webView.onPause();
        webView.pauseTimers();
    }

    public static void onDestroy(android.webkit.WebView webView) {
        ((ViewGroup) webView.getParent()).removeView(webView);

        //清除历史记录
        webView.clearHistory();
        //停止加载
        webView.stopLoading();
        //加载一个空白网页
        webView.loadUrl("about:blank");
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        //移除WebView所有的view对象
        webView.removeAllViews();
        //销毁此WebView的内部状态
        webView.destroy();

    }


    //方法重载，参数对应腾讯x5webview
    public static void onResume(com.tencent.smtt.sdk.WebView webView) {
        webView.onResume();
        webView.resumeTimers();
    }


    public static void onPause(com.tencent.smtt.sdk.WebView webView) {
        webView.onPause();
        webView.pauseTimers();
    }


    public static void onDestroy(com.tencent.smtt.sdk.WebView webView) {
        ((ViewGroup) webView.getParent()).removeView(webView);
        //清除历史记录
        webView.clearHistory();
        //停止加载网页
        webView.stopLoading();
        //加载一个空白页
        webView.loadUrl("about:blank");
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);

        //移除WebView中所有的View对象
        webView.removeAllViews();
        //销毁此Webview内部所有状态，destroy（）
        webView.destroy();

    }


}
