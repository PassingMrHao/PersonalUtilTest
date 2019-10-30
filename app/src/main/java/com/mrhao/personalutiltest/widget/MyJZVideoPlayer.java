package com.mrhao.personalutiltest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class MyJZVideoPlayer extends JzvdStd {


    public MyJZVideoPlayer(Context context) {
        super(context);

    }

    public MyJZVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setUp(JZDataSource jzDataSource, int screen, Class mediaInterface) {
        super.setUp(jzDataSource, screen, mediaInterface);
    }


    //播放完成后的回调事件
    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        Toast.makeText(getContext(), "播放完成", Toast.LENGTH_SHORT).show();
    }

}
