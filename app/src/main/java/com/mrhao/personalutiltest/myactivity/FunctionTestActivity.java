package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title: Android点赞动画实现
 * @Description: 重要参考：https://www.jianshu.com/p/91c5c598ddf2
 * @author: MrHao
 * @data: 2019\8\31   14:13
 */

public class FunctionTestActivity extends BaseActivity {

    @BindView(R.id.img_zan)
    ImageView imgZan;

    boolean isZan=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_test);
        ButterKnife.bind(this);
        setBaseEvent();
    }

    private void setBaseEvent() {

        imgZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isZan==false){
                    Toast.makeText(FunctionTestActivity.this,"点赞成功",Toast.LENGTH_SHORT).show();
                    imgZan.startAnimation(AnimationUtils.loadAnimation(FunctionTestActivity.this,R.anim.like_animation));
                    Glide.with(FunctionTestActivity.this).load(R.drawable.dianzan_color).into(imgZan);
                    isZan=true;

                }else {
                    Glide.with(FunctionTestActivity.this).load(R.drawable.dianzan_gray).into(imgZan);

                    isZan=false;
                }

            }
        });






    }
}
