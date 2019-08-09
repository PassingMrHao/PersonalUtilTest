package com.mrhao.personalutiltest.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.widget.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBusExerciseAct extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.btn_eventbusTest)
    Button btnEventbusTest;
    @BindView(R.id.bus_tv)
    TextView busTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_exercise);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setBaseClickEvent();
    }


    private void setBaseClickEvent() {

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleName.setText("CardView/EventBus Test");


        btnEventbusTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(EventBusExerciseAct.this, EventBusExerciseAct2.class));
            }
        });


    }


    //接收到消息后进行处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(MessageEvent me) {
        Log.e("TAG", "MessageEvent: " + me.getMessage());
        busTv.setText(me.getMessage());
    }

    @Override
    protected void onStop() {

        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            Toast.makeText(EventBusExerciseAct.this, "生命周期销毁", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().unregister(this);
        }
    }


}
