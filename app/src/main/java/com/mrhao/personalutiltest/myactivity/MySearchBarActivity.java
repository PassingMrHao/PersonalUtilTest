package com.mrhao.personalutiltest.myactivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.utils.MyUtils;
import com.mrhao.personalutiltest.widget.MySearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySearchBarActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.linlay_out)
    LinearLayout linlayOut;
    @BindView(R.id.mysearchbar)
    MySearchView mysearchbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_search_bar);
        ButterKnife.bind(this);
        linlayOut.setFocusableInTouchMode(true);
        linlayOut.requestFocus();
        setBaseEvent();
    }

    private void setBaseEvent() {

        /**
         * @Title: 实现点击屏幕其他地方让edittext失去焦点
         * @Description: 重要参考：https://www.cnblogs.com/Jingerxin/p/5208939.html
         * @author: MrHao
         * @data: 2019\6\18 0018  17:49
         */
        linlayOut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linlayOut.setFocusable(true);
                linlayOut.setFocusableInTouchMode(true);
                linlayOut.requestFocus();
                //获取当前Activity屏幕高度，判断手机软键盘是否打开，重要参考：https://blog.csdn.net/javazejian/article/details/52126391
                //decorView是window中的最顶层view，可以从window中通过getDecorView获取到decorView。
                //通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕高度
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                //计算软键盘高度
                int softInputHeight = screenHeight - r.bottom;
                if (softInputHeight > (int) (screenHeight / 3)) {
                    MyUtils.closeSoftInput(MySearchBarActivity.this);
                }

                Log.e("软键盘", "软键盘高度: " + softInputHeight + "-----屏幕高度：" + screenHeight);
                return false;
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
