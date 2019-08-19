package com.mrhao.personalutiltest.utils;


import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
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
        if (TextUtils.isEmpty(dataType)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.format(new Date());
        }
        SimpleDateFormat format = new SimpleDateFormat(dataType);
        return format.format(new Date());//new Date()即获取当前时间
    }

    //获取指定日期时间戳，日期格式："2018-09-29"
    public static long TimesTamp(String riqi) {
        Date date = null;
        try {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
            String time=riqi;
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }


    //阿拉伯数字转换成汉子
    public static String toChinese(String str) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }


    /**
     * 获得屏幕高度
     *
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
     *
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
     *
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    //获取最终想要得到的dp值对应的int型基本函数
    public static int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //获取最终想要得到的px值对应的int型基本函数
    public static int px2dip(float pxValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    //代码动态弹出软键盘，重要参考：https://blog.csdn.net/lnn368/article/details/51201148
    public static void openSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //代码动态关闭软键盘，重要参考：https://blog.csdn.net/lnn368/article/details/51201148
    public static void closeSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //判断手机是否打开了输入软键盘

    public static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }


}
