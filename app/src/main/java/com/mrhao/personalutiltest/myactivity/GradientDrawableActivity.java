package com.mrhao.personalutiltest.myactivity;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Title: Android GradientDrawable动态设置背景(静态方法)
 * @Description: 参考：https://blog.csdn.net/RainMcCom/article/details/81197867
 * @author: MrHao
 * @data: 2019\5\23   17:43
 */


public class GradientDrawableActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_drawable);
        ButterKnife.bind(this);
        setBaseEvent();
    }


    private void setBaseEvent() {

        //新建一个Drawable对象
        GradientDrawable drawable = new GradientDrawable();
        //设置背景色
//        drawable.setColor(Color.rgb(255, 203, 87));
        drawable.setColor(getResources().getColor(R.color.juhuang));
        //设置边框的厚度以及边框的颜色
//        drawable.setStroke(2, Color.rgb(221, 79, 66));
        drawable.setStroke(2, getResources().getColor(R.color.anhong));
        //设置圆角的半径  当然也是可以一个个设置圆角的半径
        drawable.setCornerRadius(20);
        //设置背景的形状，默认就是矩形，跟xml文件中类型android:shape的值保持一致，具体有：GradientDrawable.LINE  GradientDrawable.OVAL GradientDrawable.RECTANGLE  GradientDrawable.RING
        drawable.setShape(GradientDrawable.RECTANGLE);
        //判断当前版本号，版本号大于等于16，使用setBackground；版本号小于16，使用setBackgroundDrawable。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tvTitle.setBackground(drawable);
            tvTitle.setTextColor(getResources().getColor(R.color.white));
        } else {
            tvTitle.setBackgroundDrawable(drawable);
            tvTitle.setTextColor(getResources().getColor(R.color.white));
        }


    }


}
