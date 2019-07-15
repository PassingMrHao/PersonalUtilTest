package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBusExerciseAct extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_exercise);
        ButterKnife.bind(this);
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


    }


}
