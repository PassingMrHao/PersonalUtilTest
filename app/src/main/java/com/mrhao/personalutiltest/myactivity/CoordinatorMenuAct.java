package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.qiantao.coordinatormenu.CoordinatorMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Title:
 * @Description: 注意，设置侧滑菜单后最好重写onBackPressed()，当侧滑菜单打开时先退出侧滑菜单，不要直接退出界面
 * @author: MrHao
 * @data: 2019\5\10  9:56
 */


public class CoordinatorMenuAct extends BaseActivity {

    @BindView(R.id.mainview_menu)
    ImageView mainviewMenu;
    @BindView(R.id.coord_drawer)
    CoordinatorMenu coordDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_menu);
        ButterKnife.bind(this);
        setMenuClickEvent();
    }


    private void setMenuClickEvent() {

        //点击菜单图标调出侧滑菜单
        mainviewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用coordDrawer.isOpened(),返回boolean类型值，判断侧滑菜单是否打开
                if (coordDrawer.isOpened()) {
                    coordDrawer.closeMenu();//调用此方法关闭侧滑菜单
                } else {
                    coordDrawer.openMenu();//打开侧滑菜单
                }
            }
        });

    }



    /**
     * 系统右键返回
     */
    @Override
    public void onBackPressed() {

        if (coordDrawer.isOpened()) {
            //侧滑菜单打开状态
            coordDrawer.closeMenu();//调用此方法关闭侧滑菜单

        } else {
            super.onBackPressed();//父类方法：直接返回
        }

    }



}
