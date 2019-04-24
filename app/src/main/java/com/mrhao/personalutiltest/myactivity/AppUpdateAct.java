package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myinterface.UpDateIne;
import com.mrhao.personalutiltest.utils.DownLoadConfig;
import com.mrhao.personalutiltest.utils.DownLoadManagerUtil;
import com.mrhao.personalutiltest.utils.PublicDateValue;
import com.mrhao.personalutiltest.utils.UpdateDialog;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppUpdateAct extends AppCompatActivity {

    @BindView(R.id.update_btn)
    Button updateBtn;
    @BindView(R.id.update_app)
    Button updateApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_update);
        getImportPerssion();
        ButterKnife.bind(this);
        setClickEvent();
    }


    private void setClickEvent() {

        //在后台进行下载操作，通知栏显示下载进度
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (XXPermissions.isHasPermission(AppUpdateAct.this, Permission.WRITE_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE) == true) {

                    UpdateDialog.showUpDate(AppUpdateAct.this, new UpDateIne() {
                        @Override
                        public void BaseEvent(TextView version, TextView desc, TextView update) {
                            version.setText("1.0.1");//设置要更新的版本号
                            desc.setText("点点点\n啊啊啊\n哎哎哎\n嗷嗷嗷\n昂昂昂");//设此次更新内容

                            //进行更新安装
                            update.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (XXPermissions.isHasPermission(AppUpdateAct.this, Permission.WRITE_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE) == true) {

                                        DownLoadConfig bu = new DownLoadConfig.Builder(AppUpdateAct.this)
                                                .setFeilurl(PublicDateValue.downurl)
                                                .setDownpath("Download")//不添加下载路径的话，部分机型eg.华为测试手机无法进行下载
                                                .setInfoNmae(getResources().getString(R.string.app_name))
                                                .setInfoDesc("新版本下载中，请稍等...")
                                                .build();
                                        DownLoadManagerUtil.downFile(bu);

                                        UpdateDialog.closeDialog();
                                        Toast.makeText(AppUpdateAct.this, "新版本后台下载中，请稍等...", Toast.LENGTH_SHORT).show();

                                    } else {

                                        getImportPerssion();
                                    }
                                }
                            });
                        }
                    });


                } else {

                    getImportPerssion();

                }


            }
        });

        //在App界面/活动窗口中显示下载进度
        updateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView tt=null;
                tt.setText("696969");
                if (XXPermissions.isHasPermission(AppUpdateAct.this, Permission.WRITE_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE) == true) {


                } else {

                    getImportPerssion();

                }

            }
        });

    }


    //获取必要权限
    private void getImportPerssion() {


        XXPermissions.with(AppUpdateAct.this)
                .permission(Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        //权限申请成功后

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        //拒绝申请权限后
                        Toast.makeText(AppUpdateAct.this, "获取权限失败，部分功能无法正常使用", Toast.LENGTH_SHORT).show();

                    }
                });


    }


}
