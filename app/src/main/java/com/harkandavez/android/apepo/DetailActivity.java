package com.harkandavez.android.apepo;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.TreeMap;


public class DetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    TextView nameTxt, propTxt, descTxt;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView img;
    DownloadManager downloadManager;
    SliderLayout mDemoSlider;
    String location = Server.URL+"Admin/images/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //propTxt= (TextView) findViewById(R.id.hasil);
        descTxt = (TextView) findViewById(R.id.detail_tree_desc);
        //img = (ImageView) findViewById(R.id.detail_tree_image);

        //RECEIVE
        Intent i = this.getIntent();
        String name = i.getExtras().getString("NAME_KEY");
        final String qrUri = i.getExtras().getString("PROPELLANT_KEY");
        String desc = i.getExtras().getString("DESCRIPTION_KEY");
        String imageurl = i.getExtras().getString("IMAGEURL_KEY");
        String img1 = i.getExtras().getString("IMAGEURL_1");
        String img2 = i.getExtras().getString("IMAGEURL_2");
        String img3 = i.getExtras().getString("IMAGEURL_3");

        final String qrUrl = Server.URL+"Admin/qr_codes/"+qrUri;


        if (img1.equals("") && img2.equals("") && img3.equals("")){
            sliderImage(imageurl);
        } else if (img2.equals("") && img3.equals("")){
            sliderImage(imageurl,img1);
        } else if (img3.equals("")){
            sliderImage(imageurl, img1, img2);
        }else {
            sliderImage(imageurl, img1, img2, img3);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.stopAutoCycle();
        mDemoSlider.addOnPageChangeListener(this);


        //BIND
        collapsingToolbarLayout.setTitle(name);
        descTxt.setText(desc);
        //GlideClient.downloadImage(this, imageurl, img);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mendonload QR Code", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(qrUrl);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
            }
        });
    }

    public void sliderImage (String img_thumb, String img1,String img2, String img3){

        //slider
        TreeMap<Integer,String> url_maps = new TreeMap<Integer, String>();
        url_maps.put(1, location+img_thumb);
        url_maps.put(2, location+img1);
        url_maps.put(3, location+img2);
        url_maps.put(4, location+img3);

        for(Integer no : url_maps.keySet()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    .image(url_maps.get(no))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            mDemoSlider.addSlider(defaultSliderView);
        }
    }
    public void sliderImage (String img_thumb, String img1,String img2){

        //slider
        TreeMap<Integer,String> url_maps = new TreeMap<Integer, String>();
        url_maps.put(1, location+img_thumb);
        url_maps.put(2, location+img1);
        url_maps.put(3, location+img2);

        for(Integer no : url_maps.keySet()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    .image(url_maps.get(no))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            mDemoSlider.addSlider(defaultSliderView);
        }
    }
    public void sliderImage (String img_thumb, String img1){

        //slider
        TreeMap<Integer,String> url_maps = new TreeMap<Integer, String>();
        url_maps.put(1, location+img_thumb);
        url_maps.put(2, location+img1);

        for(Integer no : url_maps.keySet()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    .image(url_maps.get(no))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            mDemoSlider.addSlider(defaultSliderView);
        }
    }
    public void sliderImage (String img_thumb){

        //slider
        TreeMap<Integer,String> url_maps = new TreeMap<Integer, String>();
        url_maps.put(1, location+img_thumb);

        for(Integer no : url_maps.keySet()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            defaultSliderView
                    .image(url_maps.get(no))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            mDemoSlider.addSlider(defaultSliderView);
        }
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


}