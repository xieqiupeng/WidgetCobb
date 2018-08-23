package com.twirling.lib_cobb.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Target: 为databinding补充绑定方法
 */
public class DataBindingAdapter {
    @BindingAdapter("stagePhoto")
    public static void setStagePhotoByPath(ImageView view,
                                           String videoPath) {
        RequestOptions options = new RequestOptions()
//				.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
//				.placeholder(drawable)
                ;
        //
        Glide.with(view.getContext())
                .asBitmap()
                .load(videoPath)
                .thumbnail(0.1f)
                .into(view)
        ;
    }
}
