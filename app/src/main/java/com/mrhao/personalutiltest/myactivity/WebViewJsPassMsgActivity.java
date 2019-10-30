package com.mrhao.personalutiltest.myactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhao.personalutiltest.MainActivity;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.utils.WebviewLoadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewJsPassMsgActivity extends BaseActivity {

    @BindView(R.id.web_sdaklj)
    WebView webSdaklj;
    @BindView(R.id.sure_btn)
    TextView sureBtn;
    String baseurl = "http://47.52.169.82/public/index.php/api/show/xz?user_id=58&id=9";
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;

    @SuppressLint("AddJavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_js_pass_msg);
        ButterKnife.bind(this);

        //给web提供调用接口
        webSdaklj.addJavascriptInterface(new WebAppInterface(), "Android");
        webSdaklj.getSettings().setDomStorageEnabled(true);

        setBaseEvent();

    }

    private void setBaseEvent() {

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleName.setText("剧场选座");

        new WebviewLoadUtil().loadUrl(this, webSdaklj, baseurl);

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                webSdaklj.evaluateJavascript("javascript:submit()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {

                        Toast.makeText(WebViewJsPassMsgActivity.this, value, Toast.LENGTH_SHORT).show();
                        Log.e("6a6sd9asd66", "onReceiveValue: " + value);

                    }
                });
            }
        });
    }



    //Js调用android端代码完成需求，重要参考：https://blog.csdn.net/fenggering/article/details/79415730#commentBox
    public class WebAppInterface {
        @SuppressLint("NewApi")
        @JavascriptInterface //加上此部分表示这个方法是提供给JS调用的, JS在input标签中添加onclick()：window.Android.BuyTicketEnsure()
        public void BuyTicketEnsure(String aa) {

            Toast.makeText(WebViewJsPassMsgActivity.this, aa, Toast.LENGTH_SHORT).show();
            Log.e("6a6sd9asd66", "onReceiveValue: " + aa);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WebViewJsPassMsgActivity.this, ZfbPayTestActivity.class));
//                    finish();
                }
            },2800);

        }
    }



}
