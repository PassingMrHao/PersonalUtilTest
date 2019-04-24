package com.mrhao.personalutiltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.mrhao.personalutiltest.myactivity.AppUpdateAct;
import com.mrhao.personalutiltest.myactivity.FunctionDescAct;
import com.mrhao.personalutiltest.myactivity.TencentX5WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_functionDesc)
    Button btnFunctionDesc;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.Tencent_X5Webview)
    Button TencentX5Webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setBaseEvent();

        getBuglyPermission();
    }

    //获取腾讯bugly应用更新必要permission
    private void getBuglyPermission() {

        if (XXPermissions.isHasPermission(MainActivity.this, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE, Permission.READ_EXTERNAL_STORAGE) == false) {

            XXPermissions.with(this).permission(Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE, Permission.READ_EXTERNAL_STORAGE)
                    .request(new OnPermission() {
                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {

                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            //拒绝申请权限后
                            Toast.makeText(MainActivity.this, "获取权限失败，部分功能无法正常使用", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {

        }

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

        //腾讯X5WebView
        TencentX5Webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TencentX5WebActivity.class));
            }
        });

    }

}
