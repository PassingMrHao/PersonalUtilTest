package com.mrhao.personalutiltest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @Description: 复写onMeasure方法，适配ScrollView
 * @author: MrHao
 * @data: 2019\6\18 0018  11:58
 */


public class SearchListView extends ListView {
    public SearchListView(Context context) {
        super(context);
    }

    public SearchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //通过复写其onMeasure方法、达到对ScrollView适配的效果
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
