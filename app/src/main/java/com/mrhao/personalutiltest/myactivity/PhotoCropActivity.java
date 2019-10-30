package com.mrhao.personalutiltest.myactivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mrhao.personalutiltest.ChooseDateDialog.DateDialog;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.utils.MyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrhao.com.imagepicker.ImagePicker;
import mrhao.com.imagepicker.bean.ImageItem;
import mrhao.com.imagepicker.ui.ImageGridActivity;
import okhttp3.Call;

public class PhotoCropActivity extends BaseActivity {

    @BindView(R.id.photo_imgcrop)
    Button photoImgcrop;
    @BindView(R.id.photo_img)
    ImageView photoImg;
    @BindView(R.id.change_head)
    Button changeHead;
    @BindView(R.id.choose_date)
    Button chooseDate;
    @BindView(R.id.choose_date_tv)
    TextView chooseDateTv;
    private String imagePath;//图片裁剪完后返回的路径
    File ImgFile = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_crop);

        //关键设置：设置打开相册进入图片裁切模式(单选)还是图片选择模式(多选),默认为图片选择模式
        ImagePicker instance = ImagePicker.getInstance();
        instance.setMultiMode(false);
//        instance.setCrop(true);

        ButterKnife.bind(this);
        setBaseEvent();
    }

    private void setBaseEvent() {
        photoImgcrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PhotoCropActivity.this, ImageGridActivity.class), 100);
            }
        });

        //修改头像接口Test
        changeHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> map = new HashMap<>();
                map.put("id", "30");
                map.put("nickname", "珈百璃");//昵称
                map.put("gender", "1");//性别
                map.put("birthday", MyUtils.TimesTamp("2000-10-10") + "");//生日：1992-09-21 08:53:02
                OkHttpUtils.post()
                        .addFile("avatar", "mrhao.png", ImgFile)//注意：filename命名格式需要加上后缀.png、.jpg，否则上传的图片文件没有格式
                        .url("http://47.52.169.82/public/index.php/api/Register/info").params(map).build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("sadhjak164654", "请求失败: " + e.getMessage());
                            }
                            @Override
                            public void onResponse(String response, int id) {
                                // 图片拼接：http://47.52.169.82/public
                                Log.w("6969696969asdasd", "onResponse: " + response.toString());
                            }
                        });
            }
        });

        //日期选择
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateDialog.Builder(PhotoCropActivity.this)
                        .setTitle("请选择日期")
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(Dialog dialog, int year, int month, int day) {
                                Toast.makeText(PhotoCropActivity.this,year + "年" + month + "月" + day + "日",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCancel(Dialog dialog) {
                                Toast.makeText(PhotoCropActivity.this,"取消了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


    }


    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imagePath = images.get(0).path;
                ImgFile = new File(imagePath);
                Glide.with(this).load(imagePath).into(photoImg);
                Log.e("6969696969asdasd", "onActivityResult: " + imagePath);


            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
