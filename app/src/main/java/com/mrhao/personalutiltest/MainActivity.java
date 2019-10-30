package com.mrhao.personalutiltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.mrhao.personalutiltest.myactivity.BitMapCompressActivity;
import com.mrhao.personalutiltest.myactivity.BottomNavActivity;
import com.mrhao.personalutiltest.myactivity.ChangeSkinActivity;
import com.mrhao.personalutiltest.myactivity.CopyBiliSearchBarAct;
import com.mrhao.personalutiltest.myactivity.DrawerLayoutActivity;
import com.mrhao.personalutiltest.myactivity.EventBusExerciseAct;
import com.mrhao.personalutiltest.myactivity.FunctionDescAct;
import com.mrhao.personalutiltest.myactivity.FunctionTestActivity;
import com.mrhao.personalutiltest.myactivity.GradientDrawableActivity;
import com.mrhao.personalutiltest.myactivity.JZVideoPlayerActivity;
import com.mrhao.personalutiltest.myactivity.JavaExeActivity;
import com.mrhao.personalutiltest.myactivity.PhotoCropActivity;
import com.mrhao.personalutiltest.myactivity.QrCodeImgActivity;
import com.mrhao.personalutiltest.myactivity.RadioButtonActivity;
import com.mrhao.personalutiltest.myactivity.TencentX5WebActivity;
import com.mrhao.personalutiltest.myactivity.WaterRippleActivity;
import com.mrhao.personalutiltest.myactivity.WebViewJsPassMsgActivity;
import com.mrhao.personalutiltest.myactivity.ZfbPayTestActivity;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.myinterface.FinishActIne;
import com.mrhao.personalutiltest.utils.CopyIOSDialog;

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
    @BindView(R.id.btn_waterripple)
    Button btnWaterripple;
    @BindView(R.id.btn_app_skin)
    Button btnAppSkin;
    @BindView(R.id.btn_gradraw)
    Button btnGradraw;
    @BindView(R.id.btn_eventbus)
    Button btnEventbus;
    @BindView(R.id.btn_CopybiliSearch)
    Button btnCopybiliSearch;
    @BindView(R.id.btn_bitmap_compress)
    Button btnBitmapCompress;
    @BindView(R.id.btn_crop_photoImg)
    Button btnCropPhotoImg;
    @BindView(R.id.btn_videoPlay)
    Button btnVideoPlay;
    @BindView(R.id.btn_functionTest)
    Button btnFunctionTest;
    @BindView(R.id.btn_zhifubaoPay)
    Button btnZhifubaoPay;//支付宝支付
    @BindView(R.id.btn_danfuchoose)
    Button btnDanfuchoose; //单复选框
    @BindView(R.id.web_test_h5)
    Button webTestH5;
    @BindView(R.id.btn_creatEwm)
    Button btnCreatEwm;//生成带logo的二维码图片

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


        //贝塞尔曲线实现水波纹效果，用于流量预警监控、花费预警、账单花费预警等
        btnWaterripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WaterRippleActivity.class));
            }
        });


        //App更换主题、换肤
        btnAppSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChangeSkinActivity.class));
            }
        });


        //GradientDrawable常规用法，动态设置控件背景颜色
        btnGradraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GradientDrawableActivity.class));
            }
        });


        //Eventbus用法探究+Cardview
        btnEventbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventBusExerciseAct.class));
            }
        });


        //仿bilibili搜索框
        btnCopybiliSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CopyBiliSearchBarAct.class));
            }
        });

        //图片压缩
        btnBitmapCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BitMapCompressActivity.class));
            }
        });

        //打开相册选取相片进行裁剪，获得file图片文件
        btnCropPhotoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhotoCropActivity.class));
            }
        });

        //节操/饺子播放器
        btnVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JZVideoPlayerActivity.class));
            }
        });


        //小功能练习：点赞动画效果等
        btnFunctionTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FunctionTestActivity.class));

            }
        });


        //支付宝支付
        btnZhifubaoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZfbPayTestActivity.class));
            }
        });

        //单选框+多选框
        btnDanfuchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RadioButtonActivity.class));
            }
        });


        //webview和js交互：相互传值
        webTestH5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebViewJsPassMsgActivity.class));
            }
        });

        //生成带logo的二维码图片
        btnCreatEwm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, QrCodeImgActivity.class));

            }
        });


    }


    @Override
    public void onBackPressed() {
        CopyIOSDialog.showDialog(this, new FinishActIne() {
            @Override
            public void finishAct() {
                finish();
            }
        });
    }


}
