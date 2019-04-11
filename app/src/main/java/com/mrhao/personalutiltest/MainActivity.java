package com.mrhao.personalutiltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.mrhao.personalutiltest.myactivity.FunctionDescAct;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_functionDesc)
    Button btnFunctionDesc;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBaseEvent();
    }


    private void setBaseEvent() {
        //包含框架、工具类和功能说明
        btnFunctionDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FunctionDescAct.class));
            }
        });

        //版本更新
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AppUpdateAct.class));
            }
        });

    }

}
