package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.utils.X5WebViewLoadUtil;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TencentX5WebActivity extends BaseActivity {

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

    //    String baseurl = "http://47.52.169.82/public/index.php/api//index/show_xq?id=8";
//    String baseurl = "http://47.52.169.82/public/index.php/api/Zixun/zx_detail?id=16";
//    String baseurl = "https://m.9665299.com/";
//    String baseurl = "https://hecoffee.github.io/TicketMap/";
    String baseurl = "http://47.52.169.82/public/index.php/api/show/xz?user_id=38&id=6";
    @BindView(R.id.catch_jsmethod)
    TextView catchJsmethod;//调用jsf方法

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

        catchJsmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tencentWeb.post(new Runnable() {
                    @Override
                    public void run() {

//                        tencentWeb.loadUrl("javascript:submit()");

                        tencentWeb.evaluateJavascript("javascript:submit()", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                //value即为js返回值

                                Toast.makeText(TencentX5WebActivity.this,value,Toast.LENGTH_SHORT).show();
                                Log.e("sdahklaksj", "onReceiveValue: "+value );

                            }
                        });

                    }
                });
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
