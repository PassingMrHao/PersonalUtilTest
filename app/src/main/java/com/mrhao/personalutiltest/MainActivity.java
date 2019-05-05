package com.mrhao.personalutiltest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.mrhao.personalutiltest.myactivity.FunctionDescAct;
import com.mrhao.personalutiltest.myactivity.JavaExeActivity;
import com.mrhao.personalutiltest.myactivity.TencentX5WebActivity;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_functionDesc)
    Button btnFunctionDesc;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.Tencent_X5Webview)
    Button TencentX5Webview;
    @BindView(R.id.btn_java)
    Button btnJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBaseEvent();

    }


    private void setBaseEvent() {

        //包含框架、工具类和功能说明
        btnFunctionDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FunctionDescAct.class));
            }
        });

        //版本更新
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AppUpdateAct.class));
            }
        });

        //腾讯X5WebView
        TencentX5Webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TencentX5WebActivity.class));
            }
        });


        //《HeadFirst》Java随练
        btnJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, JavaExeActivity.class));
            }
        });








        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，

                // 通过tag方式获取控件，并更改布局内容
                TextView textView = (TextView) view.findViewWithTag("beta_cancel_button");
                TextView textView2 = (TextView) view.findViewWithTag("beta_confirm_button");
                textView.setText("忽略此次提醒");
                textView2.setText("后台更新安装");

            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {

            }
        };
    }


}
