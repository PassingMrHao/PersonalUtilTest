package com.mrhao.personalutiltest.myactivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.utils.X5WebViewLoadUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TencentX5WebActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.tencent_web)
    WebView tencentWeb;

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

        X5WebViewLoadUtil.loadurl(TencentX5WebActivity.this,tencentWeb,"https://weibo.com/");

        tencentWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {

            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                titleName.setText(webView.getTitle());
            }
        });


    }

}
