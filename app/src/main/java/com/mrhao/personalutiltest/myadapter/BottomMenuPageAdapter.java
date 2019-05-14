package com.mrhao.personalutiltest.myadapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


/**
 * @Title:
 * @Description: BottomNavigationView+viewpage 底部menu适配器
 * @author: MrHao
 * @data: 2019\5\13   17:46
 */

public class BottomMenuPageAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    List<Fragment> list;

    public BottomMenuPageAdapter(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.mContext = context;
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
