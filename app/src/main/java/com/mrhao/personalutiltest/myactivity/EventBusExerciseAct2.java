package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.widget.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EventBusExerciseAct2 extends BaseActivity {

    @BindView(R.id.btn_testres)
    Button btnTestres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_exercise_act2);
        ButterKnife.bind(this);
        setBaseEvent();

    }


    private void setBaseEvent() {

        btnTestres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EventBusExerciseAct2.this,"666999", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("Hello everyone!"));


            }
        });

    }








}
