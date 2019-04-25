package com.mrhao.personalutiltest.myclass;

import android.app.Application;

import com.mrhao.personalutiltest.MainActivity;
import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;


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

        Bugly.init(getApplicationContext(), "9b5829370d", false);//初始化
        Beta.autoCheckUpgrade = false; //重要设置：true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
    }

}
