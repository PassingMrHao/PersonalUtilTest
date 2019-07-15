package com.mrhao.personalutiltest.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;


/**
 * @Title: 自定义带一键清除的 EditText 输入框
 * @Description: 重要参考：https://www.cnblogs.com/luhan/p/5956708.html
 * @author: MrHao
 * @data: 2019\6\13  11:11
 */

@SuppressLint("AppCompatCustomView")
public class ClearEditext extends EditText {

    Drawable rightdraw;
    Context mcontext;

    public ClearEditext(Context context) {
        super(context);
        this.mcontext = context;
        initMyEdiText();//在此进行初始化操作，比如将清除按钮画上去
    }

    public ClearEditext(Context context, AttributeSet attrs) {
        //此构造必要，不添加的话很多属性不能在xml里定义
        super(context, attrs);
        this.mcontext = context;
        initMyEdiText();//在此进行初始化操作，比如将清除按钮画上去
    }

    public ClearEditext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontext = context;
        initMyEdiText();//在此进行初始化操作，比如将清除按钮画上去
    }


    private void initMyEdiText() {

        if (rightdraw == null) {
            rightdraw = getCompoundDrawables()[2];
            rightdraw = getResources().getDrawable(R.drawable.editext_delete);//添加默认后缀删除图片
        }
        int picwd = dip2px(26, mcontext);//设置删除图标宽高均为 22 dp
        rightdraw.setBounds(0, 0, picwd, picwd);


    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        //监听输入动态，当输入框内有字符时显示删除图标 ; setCompoundDrawables(left,top,right,bottom)-->：调用此方法设置文本输入框上、下、左、右内置图
        // 三目运算符：(start>0)?rightdraw:null ----> start>0时赋值rightdraw，start<=0时赋null
        setCompoundDrawables(null, null, (start == 0 && TextUtils.isEmpty(text)) ? null : rightdraw, null);

    }


    //重要方法：在onTouch触摸事件中获取手指抬起时的位置，判断用户是否点击了删除图标
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                int deleteW = dip2px(26, mcontext);
                int area = (int) (getWidth() - event.getX()); // 当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
                if (rightdraw != null) {
                    if (area <= deleteW) {
                        setText("");
                    }
                }
//                Log.e("TouchArea_Point", "onTouchEvent:    X: " + event.getX() + "-------" + deleteW + "-----------W: " + getWidth());
                break;
        }
        return super.onTouchEvent(event);
    }


    //获取最终想要得到的dp值对应的int型基本函数
    public int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
