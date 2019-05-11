package com.mrhao.personalutiltest.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title:
 * @Description: 注意，设置侧滑菜单后最好重写onBackPressed()，当侧滑菜单打开时先退出侧滑菜单，不要直接退出界面
 * @author: MrHao
 * @data: 2019\5\10  9:51
 */

public class DrawerLayoutActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.drawer_btn)
    Button drawerBtn;
    @BindView(R.id.other_btn)
    Button otherBtn;
    @BindView(R.id.lay_drawer)
    DrawerLayout layDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.bind(this);
        setBaseEvent();
    }


    private void setBaseEvent() {

        titleBack.setImageResource(R.mipmap.cehua_menu);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layDrawer.openDrawer(Gravity.LEFT);//调用此方法，显示侧滑菜单

            }
        });

        titleName.setText("侧滑菜单");


        //google 原生DrawerLayout布局实现侧滑菜单效果
        drawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layDrawer.openDrawer(Gravity.LEFT);

            }
        });

        //其他第三方框架实现侧滑菜单效果，仿QQ侧滑菜单界面样式
        //开源参考：https://github.com/qiantao94/CoordinatorMenu

        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DrawerLayoutActivity.this, CoordinatorMenuAct.class));
            }
        });
    }


    /**
     * 系统右键返回
     */
    @Override
    public void onBackPressed() {

        if (layDrawer.isDrawerOpen(Gravity.LEFT)==true) {
            //侧滑菜单打开状态
            layDrawer.closeDrawers(); //关闭

        } else {
            super.onBackPressed();//父类方法：直接返回
        }

    }


}
