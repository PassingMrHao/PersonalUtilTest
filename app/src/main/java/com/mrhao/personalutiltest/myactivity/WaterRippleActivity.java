package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.utils.WaterRippleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title:  水波纹效果，贝塞尔曲线
 * @Description: CSDN地址：https://blog.csdn.net/itrenj/article/details/53874219
 *               GitHub项目地址：https://github.com/itrenjunhua/WaveView
 * @author: MrHao
 * @data: 2019\5\25   15:39
 */

public class WaterRippleActivity extends BaseActivity {

    @BindView(R.id.water_ripple)
    WaterRippleView waterRipple;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_stop)
    Button btStop;
    @BindView(R.id.bt_reset)
    Button btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_ripple);
        ButterKnife.bind(this);
        setClickEvent();
    }


    private void setClickEvent() {

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterRipple.start();
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterRipple.stop();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterRipple.reset();
            }
        });


    }


}
