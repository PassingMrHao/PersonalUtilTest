package com.mrhao.personalutiltest.widget;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mrhao.personalutiltest.R;

import mrhao.com.imagepicker.loader.ImageLoader;

public class GlideImageLoader implements ImageLoader {
    public void clearMemoryCache() {
    }

    public void displayImage(Activity activity, String str, ImageView imageView, int i, int i2) {
        RequestOptions ro = new RequestOptions().error(R.mipmap.default_image).placeholder(R.mipmap.default_image);
        Glide.with(activity).load(str).apply(ro).into(imageView);

    }

    public void displayImagePreview(Activity activity, String str, ImageView imageView, int i, int i2) {
        RequestOptions ro = new RequestOptions().error(R.mipmap.default_image).placeholder(R.mipmap.default_image);
        Glide.with(activity).load(str).apply(ro).into(imageView);
    }
}
