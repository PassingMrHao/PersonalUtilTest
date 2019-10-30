package com.mrhao.personalutiltest.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * 自定义Toast
 * 郝玉龙
 * 2018/11/07
 */
public class MyToastUtils {
    Context context;
    public MyToastUtils(Context context){
        this.context=context;
    }

    /**
     * (1)只Toast一个图片
     *
     * @param pic_id eg:R.mipmap.ic_launcher
     */
    public void ToastImg(int pic_id){
        //获取一个Toast对象，为下面操作准备
        Toast toast = new Toast(context);
        ImageView img = new ImageView(context);
        //用系统提供的图片

        img.setImageResource(pic_id);
        //设置图片
        toast.setView(img);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        toast.setDuration(Toast.LENGTH_LONG);

    }


    /**
     *
     * @param lay_id 自定义布局文件 eg:R.layout.main_activity
     */
    public void showMyTosat(int lay_id){
        //把一个布局变成一个View对象
        LayoutInflater inflater = LayoutInflater.from(context);
        View toast_layout = inflater.inflate(lay_id, null);
        Toast toast = new Toast(context);
        //把获取到的View对象作为setView的参数
        toast.setView(toast_layout);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        toast.setDuration(Toast.LENGTH_LONG);

    }
}

