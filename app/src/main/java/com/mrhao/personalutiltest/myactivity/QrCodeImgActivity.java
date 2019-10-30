package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title: 生成二维码图片：带logo和不带logo
 * @Description:
 * @author: MrHao
 * @data: 2019\10\30 0030  10:46
 */

public class QrCodeImgActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.creat_qrcode)
    Button creatQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_img);
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

        //生成二维码
        creatQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}
