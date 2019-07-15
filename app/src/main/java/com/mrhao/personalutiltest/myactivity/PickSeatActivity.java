package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.utils.X5WebViewLoadUtil;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickSeatActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.web_svg)
    WebView webSvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_seat);
        ButterKnife.bind(this);
        setBaseEvent();
    }

    private void setBaseEvent() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleName.setText("掘金仿淘票票svg选座");

        X5WebViewLoadUtil.loadurl(PickSeatActivity.this,webSvg,"https://hecoffee.github.io/TicketMap/");


    }

}
