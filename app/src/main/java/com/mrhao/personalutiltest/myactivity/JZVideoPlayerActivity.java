package com.mrhao.personalutiltest.myactivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.widget.MyJZVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class JZVideoPlayerActivity extends BaseActivity {

    @BindView(R.id.videoplayer)
    JzvdStd videoplayer;
    String videourl = "http://47.52.169.82/public/uploads/20190628/b696ffbacd34b7d649d0e87d7ebbf15b.mp4";
    String thumbImg = "http://47.52.169.82/public/uploads/20190628/1833bdcbd3cf24c1591dc7eae8592086.jpg";
    @BindView(R.id.my_Jzvideo)
    MyJZVideoPlayer myJzvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzvideo_player);
        ButterKnife.bind(this);
        setJZVideoPlayerBaseMsg();
    }

    private void setJZVideoPlayerBaseMsg() {
        //（视频地址，title，播放模式）
//        videoplayer.setUp(videourl, "视频Title", JzvdStd.SCREEN_STATE_ON);
        videoplayer.setUp(videourl, "视频Title", JzvdStd.SCREEN_NORMAL);
        Glide.with(this).load(thumbImg).into(videoplayer.thumbImageView);

        myJzvideo.setUp(videourl, "666669999", JzvdStd.SCREEN_NORMAL);
        Glide.with(this).load(thumbImg).into(myJzvideo.thumbImageView);


    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }


}
