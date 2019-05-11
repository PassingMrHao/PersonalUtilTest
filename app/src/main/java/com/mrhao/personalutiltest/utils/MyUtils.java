package com.mrhao.personalutiltest.utils;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Title:
 * @Description: 个人工具类集合
 * @author: MrHao
 * @data: 2019\5\9   14:13
 */

public class MyUtils {

    /**
     * 方法说明:获取当前时间，可设置返回时间样式
     * yyyy表示年；MM表示月2；dd表示天；
     * hh表示用12小时制；HH表示用24小时制；mm表示分；ss表示秒；
     * 参考示例："yyyy-MM-dd HH：mm"、"yyyy年MM月dd日 HH:mm:ss"
     *
     * @CreatData: 2019\5\9  14:46
     */
    public static String getTimeNow(String dataType) {
        if(TextUtils.isEmpty(dataType)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.format(new Date());
        }
        SimpleDateFormat format = new SimpleDateFormat(dataType);
        return format.format(new Date());//new Date()即获取当前时间
    }



    /**
     * 获得屏幕高度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    /**
     * 获得屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕密度
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }





}
