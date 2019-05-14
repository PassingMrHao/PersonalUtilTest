package com.mrhao.personalutiltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.mrhao.personalutiltest.myactivity.BottomNavActivity;
import com.mrhao.personalutiltest.myactivity.DrawerLayoutActivity;
import com.mrhao.personalutiltest.myactivity.FunctionDescAct;
import com.mrhao.personalutiltest.myactivity.JavaExeActivity;
import com.mrhao.personalutiltest.myactivity.TencentX5WebActivity;
import com.mrhao.personalutiltest.myclass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_functionDesc)
    Button btnFunctionDesc;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.Tencent_X5Webview)
    Button TencentX5Webview;
    @BindView(R.id.btn_java)
    Button btnJava;
    @BindView(R.id.btn_drawerLayout)
    Button btnDrawerLayout;
    @BindView(R.id.btn_bootommenu)
    Button btnBootommenu;

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


        //<Head First Java>阅读随练
        btnJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JavaExeActivity.class));
            }
        });


        //侧滑菜单：DrawerLayout抽屉布局实现侧滑菜单+第三方框架侧滑菜单

        btnDrawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
            }
        });


        //MD设计风格控件，BottomNavigationView 的底部菜单+导航栏
        btnBootommenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BottomNavActivity.class));
            }
        });


    }


}
