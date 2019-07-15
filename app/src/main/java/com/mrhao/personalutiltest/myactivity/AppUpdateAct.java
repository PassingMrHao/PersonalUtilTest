package com.mrhao.personalutiltest.myactivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.myinterface.UpDateIne;
import com.mrhao.personalutiltest.utils.DownLoadConfig;
import com.mrhao.personalutiltest.utils.DownLoadManagerUtil;
import com.mrhao.personalutiltest.utils.PublicDateValue;
import com.mrhao.personalutiltest.utils.UpdateDialog;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppUpdateAct extends BaseActivity {

    @BindView(R.id.update_btn)
    Button updateBtn;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;

    @BindView(R.id.update_point)
    ImageView updatePoint;
    @BindView(R.id.bugly_up_point)
    TextView buglyUpPoint;
    UpgradeInfo upgradeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_update);
        getImportPerssion();
        ButterKnife.bind(this);
        setClickEvent();
        CheckAPPUpdate();//腾讯bugly检查应用升级信息
    }

    //检查应用更新信息
    private void CheckAPPUpdate() {


        if (upgradeInfo == null) {
            //无升级信息
            updatePoint.setImageResource(R.mipmap.up_graypoint);
        } else {
            updatePoint.setImageResource(R.mipmap.up_redpoint);
        }

        //腾讯bugly应用全量更新手动检测
        buglyUpPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (XXPermissions.isHasPermission(AppUpdateAct.this, Permission.WRITE_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE) == true) {

                    Beta.checkUpgrade();//调用此方法手动检查应用

                } else {

                    getImportPerssion();
                }
            }
        });


    }


    private void setClickEvent() {
        titleName.setText("应用版本更新");
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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


    }


    //获取必要权限
    private void getImportPerssion() {

        upgradeInfo= Beta.getUpgradeInfo();//获取本地已有升级策略（非实时，可用于界面红点展示）


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
