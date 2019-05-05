package com.mrhao.personalutiltest.myclass;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mrhao.personalutiltest.MainActivity;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initBuglyUpdate();//腾讯bugly全量更新

    }


    /**
     * 腾讯bugly应用异常上报+APP全量更新
     */
    private void initBuglyUpdate() {

//      Beta.autoCheckUpgrade = false; //重要设置：true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;如需设置的话放在初始化前
        Beta.canShowUpgradeActs.add(AppUpdateAct.class);//设置只在MainActivity上显示更新弹窗
//        Beta.canShowApkInfo = false;  //设置是否显示更新弹出框中的apk信息

        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog; //设置自定义升级对话框UI布局

        Bugly.init(getApplicationContext(), "9b5829370d", false);//初始化（最好放在最后）


    }

}
