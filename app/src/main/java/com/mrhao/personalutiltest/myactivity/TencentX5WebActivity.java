package com.mrhao.personalutiltest.myactivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.utils.X5WebViewLoadUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TencentX5WebActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.tencent_web)
    WebView tencentWeb;
    @BindView(R.id.x5web_pro)
    ProgressBar x5webPro;
    @BindView(R.id.pro_tv)
    TextView proTv;
    @BindView(R.id.relay_progress)
    RelativeLayout relayProgress;

    String baseurl = "https://www.csdn.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_x5_web);
        ButterKnife.bind(this);
        setBaseDate();
    }

    private void setBaseDate() {
        titleName.setText("Loading...");
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        X5WebViewLoadUtil.loadurl(TencentX5WebActivity.this, tencentWeb, baseurl);

        tencentWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {

                if (!TextUtils.isEmpty(webView.getOriginalUrl())) {
                    x5webPro.setProgress(webView.getProgress());
                    proTv.setText(webView.getProgress() + " %");
                    if (webView.getProgress() == 100) {
                        relayProgress.setVisibility(View.GONE);
                    }

                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出浏览器时回收X5webview，避免造成内存泄露
        tencentWeb.destroy();
    }


    @Override
    public void onBackPressed() {
        if (tencentWeb.getUrl().equals(baseurl)) {
            //加载基本baseUrl
            finish();
        }
        tencentWeb.goBack();
    }

}
