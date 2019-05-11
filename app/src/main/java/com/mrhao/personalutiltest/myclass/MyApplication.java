package com.mrhao.personalutiltest.myclass;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.mrhao.personalutiltest.MainActivity;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;

import java.util.zip.Inflater;


public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        initBuglyUpdate();//腾讯bugly全量更新

        //沉浸式标题栏(Activity)，需要适配全面屏和刘海屏，
        // 在manifest的Application节点中加入  android:resizeableActivity="true"

    }


    /**
     * 腾讯bugly应用异常上报+APP全量更新
     */
    private void initBuglyUpdate() {

//        Beta.autoCheckUpgrade = false; //重要设置：true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;如需设置的话放在初始化前
        Beta.canShowUpgradeActs.add(MainActivity.class);//设置只在MainActivity上显示更新弹窗
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog; //设置自定义升级对话框UI布局

        Bugly.init(getApplicationContext(), "9b5829370d", false);//初始化（最好放在最后）

    }

}
