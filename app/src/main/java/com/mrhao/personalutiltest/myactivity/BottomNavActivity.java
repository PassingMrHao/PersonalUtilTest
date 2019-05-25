package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myadapter.BottomMenuPageAdapter;
import com.mrhao.personalutiltest.myfragment.BottomMenuFragment1;
import com.mrhao.personalutiltest.myfragment.BottomMenuFragment2;
import com.mrhao.personalutiltest.myfragment.BottomMenuFragment3;
import com.mrhao.personalutiltest.myfragment.BottomMenuFragment4;
import com.mrhao.personalutiltest.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavActivity extends AppCompatActivity {

    @BindView(R.id.md_bottom)
    BottomNavigationView mdBottom;
    @BindView(R.id.vp_home_pager)
    NoScrollViewPager vpHomePager;

    List<Fragment> fragmentList = new ArrayList<>();
    BottomMenuPageAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        ButterKnife.bind(this);
        //调用此方法不使用图标默认变色
        mdBottom.setItemIconTintList(null);
        setBottomMenuClickEvent();
    }


    private void setBottomMenuClickEvent() {

        //往listfragment列表中添加fragment页面
        fragmentList.add(new BottomMenuFragment1()); //首页
        fragmentList.add(new BottomMenuFragment2()); //发现
        fragmentList.add(new BottomMenuFragment2()); //发现
        fragmentList.add(new BottomMenuFragment3()); //探索
        fragmentList.add(new BottomMenuFragment4()); //我的

        ad = new BottomMenuPageAdapter(getSupportFragmentManager(), this, fragmentList);
        vpHomePager.setAdapter(ad);


        //BottomMenu子选项点击事件
        mdBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()) {

                    case R.id.bottommenu1:
                        vpHomePager.setCurrentItem(0);
                        return true;
                    case R.id.bottommenu2:
                        vpHomePager.setCurrentItem(1);
                        return true;
                    case R.id.bottommenu3:
                        vpHomePager.setCurrentItem(3);
                        return true;
                    case R.id.bottommenu4:
                        vpHomePager.setCurrentItem(4);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });


        //viewpage左右滑动监听
        vpHomePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //该方法多次调用，参数解释如下：
                //position当前所处页面索引,滑动调用的最后一次绝对是滑动停止所在页面
                //positionOffset:表示从位置的页面偏移的[0,1]的值。
                //positionOffsetPixels:以像素为单位的值，表示与位置的偏移

            }


            @Override
            public void onPageSelected(int position) {
                //滑动停止时调用
                if(position!=2){
                    mdBottom.getMenu().getItem(position).setChecked(true);// 当滑动到某一位置，导航栏对应位置被按下
                }else {

                }

                Log.e("dddd6669996969", "onPageSelected: " + position);

                //这里使用navigation.setSelectedItemId(position);无效，
                //setSelectedItemId(position)的官网原句：Set the selected
                // menu item ID. This behaves the same as tapping on an item
                //未找到原因


            }


            @Override
            public void onPageScrollStateChanged(int state) {
                // 这个方法在滑动是调用三次，分别对应下面三种状态
                // 这个方法对于发现用户何时开始拖动，
                // 何时寻呼机自动调整到当前页面，或何时完全停止/空闲非常有用。
                // state表示新的滑动状态，有三个值：
                // SCROLL_STATE_IDLE：开始滑动（空闲状态->滑动），实际值为0
                // SCROLL_STATE_DRAGGING：正在被拖动，实际值为1
                // SCROLL_STATE_SETTLING：拖动结束,实际值为2
            }
        });


    }


}
