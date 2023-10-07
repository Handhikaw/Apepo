package com.harkandavez.android.apepo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;


public class GlideClient {

    public static  void downloadImage(Context c,String imageUrl,ImageView img)
    {
        String location = Server.URL+"Admin/images/";
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder);
        if(imageUrl != null && imageUrl.length()>0)
        {
            Glide.with(c).load(location + imageUrl).apply(myOptions).into(img);
        }else {
            Glide.with(c).load(R.drawable.placeholder).apply(myOptions).transition(DrawableTransitionOptions.withCrossFade()).into(img);
        }
    }
    public static  void thumbnail(Context c,String imageUrl,ImageView img)
    {
        String location = Server.URL+"Admin/images/";
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .override(300, 300);
        if(imageUrl != null && imageUrl.length()>0)
        {
            Glide.with(c).load(location + imageUrl).thumbnail(0.5f).apply(myOptions).transition(DrawableTransitionOptions.withCrossFade()).into(img);
        }else {
            Glide.with(c).load(R.drawable.placeholder).apply(myOptions).into(img);
        }
    }
}
