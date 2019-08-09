package com.mrhao.personalutiltest.myactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

//图片压缩,Bitmap格式
public class BitMapCompressActivity extends AppCompatActivity {

    @BindView(R.id.pic_mian)
    ImageView picMian; //原图片
    @BindView(R.id.img_compress)
    ImageView imgCompress; //压缩后的图片
    @BindView(R.id.pic_size)
    TextView picSize; //原图片大小
    @BindView(R.id.compress_size)
    TextView compressSize; //压缩后图片大小

    Bitmap bit ;


    @BindView(R.id.suofang_compress)
    Button suofangCompress;
    @BindView(R.id.rgb565_compress)
    Button rgb565Compress;
    @BindView(R.id.setSize_compress)
    Button setSizeCompress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_map_compress);
        ButterKnife.bind(this);
        setBaseEvent();
    }


    private void setBaseEvent() {
        //测量bitmap图片大小
        bit = BitmapFactory.decodeResource(getResources(), R.mipmap.img_compress_test);
        picSize.setText(bit.getByteCount() / 1024 + " Kb(" + bit.getByteCount() / 1024 / 1024 + " Mb)");


        //缩放压缩法
        suofangCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matrix matrix = new Matrix();
                matrix.setScale(0.3f, 0.3f); //宽高各缩放至原图的1/2
                Bitmap bm=Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);
                imgCompress.setImageBitmap(bm);
                compressSize.setText(bm.getByteCount() / 1024 + " Kb(" + bm.getByteCount() / 1024 / 1024 + " Mb)");
            }
        });


        //RGB_565法压缩图片
        rgb565Compress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //createScaledBitmap：设置bitmap图片宽高，缺点：如果用户期望的长度和宽度和原图长度宽度相差太多的话，图片会很不清晰。
        setSizeCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}
