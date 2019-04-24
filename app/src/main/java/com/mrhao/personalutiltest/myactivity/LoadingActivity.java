package com.mrhao.personalutiltest.myactivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mrhao.personalutiltest.MainActivity;
import com.mrhao.personalutiltest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingActivity extends AppCompatActivity {

    @BindView(R.id.start_img)
    ImageView startImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);
        startImg.post(new Runnable() {
            @Override
            public void run() {
                setBaseEvent();
            }
        });
    }


    private void setBaseEvent() {

        //Android属性动画-渐变
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(startImg, "alpha", 0f, 1f);
        alpha.setDuration(2000);
        alpha.start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
                alpha.cancel();
            }
        }, 2800);

    }

}
