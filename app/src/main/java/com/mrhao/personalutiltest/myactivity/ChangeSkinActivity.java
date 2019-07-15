package com.mrhao.personalutiltest.myactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title: Android夜间模式的实现方案
 * @Description: CSDN地址----  https://blog.csdn.net/qq_20521573/article/details/76222085
 * @author: MrHao
 * @data: 2019\5\25   15:42
 */

public class ChangeSkinActivity extends AppCompatActivity {

    @BindView(R.id.img_night)
    ImageView imgNight;
    SharedPreferences.Editor editor;
    boolean isNightTheme;
    @BindView(R.id.img_night_yueliang)
    ImageView imgNightYueliang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_skin);
        ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences("Nightstyle", 0);
        isNightTheme = sp.getBoolean("isNightTheme", false);
        editor = sp.edit();

        if (isNightTheme == false) {
            imgNight.setImageResource(R.mipmap.night_close);
            imgNightYueliang.setImageResource(R.mipmap.set_night_style);
            ImmersionBar.with(this).statusBarDarkFont(true).init();//设置状态栏字体颜色为深色、黑色
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);//关闭夜间模式
        } else {
            imgNight.setImageResource(R.mipmap.night_open);
            imgNightYueliang.setImageResource(R.mipmap.set_night_style_white);
            ImmersionBar.with(this).statusBarDarkFont(false).init();//设置状态栏字体颜色为浅色、白色
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);//开启夜间模式
        }

        setBaseEvent();
    }

    private void setBaseEvent() {

        imgNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNightTheme == false) {
                    //开启夜间模式
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);//开启夜间模式，暗色主题
                    editor.putBoolean("isNightTheme", true);
                    editor.commit();//容易忽略
                } else {
                    //关闭夜间模式

                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);//关闭夜间模式
                    editor.putBoolean("isNightTheme", false);
                    editor.commit();//容易忽略

                }
            }
        });

    }

}
