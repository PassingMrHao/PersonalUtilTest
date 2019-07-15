package com.mrhao.personalutiltest.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import km.lmy.searchview.SearchView;

public class CopyBiliSearchBarAct extends BaseActivity {

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_showresult)
    TextView tvShowresult;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.my_searchView)
    SearchView myS;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.my_searchBar)
    TextView mySearchBar;
    @BindView(R.id.load_svgr)
    TextView loadSvgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_bili_search_bar);
        ButterKnife.bind(this);
        setBilibiliSeacherBar();
        setBilibiliSeacherBar2();
    }


    private void setBilibiliSeacherBar2() {
        //设置软键盘搜索按钮点击事件
        myS.setOnSearchActionListener(new SearchView.OnSearchActionListener() {
            @Override
            public void onSearchAction(String searchText) {

                myS.addOneHistory(searchText);
                Toast.makeText(CopyBiliSearchBarAct.this, myS.getEditTextView().getText(), Toast.LENGTH_SHORT).show();
            }
        });


        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myS.defaultState(SearchView.OPEN);
            }
        });


    }


    private void setBilibiliSeacherBar() {

        mySearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CopyBiliSearchBarAct.this, MySearchBarActivity.class));
            }
        });

        titleName.setText("仿哔哩哔哩搜索框");
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final SearchFragment ss = new SearchFragment(); //搜索框实例化

        //设置回调
        ss.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                tvShowresult.setText(keyword);
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示搜索框
                ss.showFragment(getSupportFragmentManager(), SearchFragment.TAG);
            }
        });

        //加载svg可缩放矢量图
        loadSvgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CopyBiliSearchBarAct.this, PickSeatActivity.class));
            }
        });


    }

}
