package com.mrhao.personalutiltest.utils;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

/**
 * @Title: GradientDrawable 动态设置view控件背景色工具类
 * @Description: 方法重载，包含边线框和不含边线框两种方法动态设置背景色
 * @author: MrHao
 * @data: 2019\5\27   17:05
 */


public class GradientDrawableUseUtil {
    private static GradientDrawable drawable;

    //不包含边框线
    public static GradientDrawable SimpleGradDrawable(int backcolor, int radious, View view) {
        drawable = new GradientDrawable();
        drawable.setColor(backcolor); //设置控件view背景色
        drawable.setCornerRadius(radious); //设置圆角角度

        //设置背景的形状，默认就是矩形，跟xml文件中类型android:shape的值保持一致，具体有：GradientDrawable.LINE  GradientDrawable.OVAL GradientDrawable.RECTANGLE  GradientDrawable.RING
        drawable.setShape(GradientDrawable.RECTANGLE);
        //判断当前版本号，版本号大于等于16，使用setBackground；版本号小于16，使用setBackgroundDrawable。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }

        return drawable;
    }


    //包含边线框
    public  static GradientDrawable SimpleGradDrawable(int backcolor, int radious, int StrokeWidth, int StrokeColor, View view){
        drawable = new GradientDrawable();
        drawable.setColor(backcolor); //设置控件view背景色
        drawable.setCornerRadius(radious); //设置圆角角度
        drawable.setStroke(StrokeWidth,StrokeColor);//设置边框的厚度以及边框的颜色

        drawable.setShape(GradientDrawable.RECTANGLE);
        //判断当前版本号，版本号大于等于16，使用setBackground；版本号小于16，使用setBackgroundDrawable。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);

        } else {
            view.setBackgroundDrawable(drawable);

        }

        return drawable;
    }



}

