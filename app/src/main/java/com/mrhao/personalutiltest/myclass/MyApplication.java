package com.mrhao.personalutiltest.myclass;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.mrhao.personalutiltest.MainActivity;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.widget.GlideImageLoader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import mrhao.com.imagepicker.ImagePicker;
import mrhao.com.imagepicker.loader.ImageLoader;
import mrhao.com.imagepicker.view.CropImageView;

public class MyApplication extends Application {

    private static Context mContext; //设置个全局变量


    public static Context getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        super.onCreate();

        initBuglyUpdate();//腾讯bugly全量更新

        initImagePicker();//打开相册，相片裁切初始化

        //沉浸式标题栏(Activity)，需要适配全面屏和刘海屏，
        // 在manifest的Application节点中加入  android:resizeableActivity="true"
    }

    /**
     * 腾讯bugly应用异常上报+APP全量更新
     */
    private void initBuglyUpdate() {

        Beta.autoCheckUpgrade = false; //重要设置：true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;如需设置的话放在初始化前
//        Beta.autoCheckUpgrade = true; //重要设置：true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;如需设置的话放在初始化前
        Beta.canShowUpgradeActs.add(MainActivity.class);//初始化时自动检查升级情况下，设置只在MainActivity上显示更新弹窗
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog; //设置自定义升级对话框UI布局
        Bugly.init(getApplicationContext(), "9b5829370d", false);//初始化（最好放在最后）

    }



    private void initImagePicker() {
        ImagePicker instance = ImagePicker.getInstance();
        instance.setImageLoader(new GlideImageLoader());
        instance.setShowCamera(true);
        instance.setCrop(true);
        instance.setSaveRectangle(true);
        instance.setStyle(CropImageView.Style.RECTANGLE);
        instance.setFocusWidth(600);//焦点选取框的宽
        instance.setFocusHeight(600);//焦点选取框的高
        instance.setOutPutX(600);//图片裁剪保存部分的宽
        instance.setOutPutY(600);//图片裁剪保存部分的高
    }


}
