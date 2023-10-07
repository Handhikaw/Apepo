package com.harkandavez.android.apepo;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.harkandavez.android.apepo.API.ApiService;
import com.harkandavez.android.apepo.Model.ModelData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.TreeMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataScan extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    String ID_TREE;
    CollapsingToolbarLayout nama;
    TextView id_tree, deskripsi;
    ImageView image;
    DownloadManager downloadManager;
    String qrUri;
    SliderLayout mDemoSlider;
    final String qrUrl = Server.URL+"Admin/qr_codes/"+qrUri;
    String location = Server.URL+"Admin/images/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scan);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        ID_TREE = getIntent().getStringExtra(ModelData.id_tree);
        //ID_TREE = "1";

        //id_tree = (EditText) findViewById(R.id.edit_id);
        nama = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        deskripsi = (TextView) findViewById(R.id.isi);
        //image = (ImageView) findViewById(R.id.scan_tree_image);

        bindData();


        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.stopAutoCycle();
        mDemoSlider.addOnPageChangeListener(this);

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

    public void bindData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService service = retrofit.create(ApiService.class);

        Call<List<ModelData>> call = service.getSingleData(ID_TREE);
        call.enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {

                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().size(); i++) {

                        //et_id.setText(response.body().get(i).getidTree());
                        nama.setTitle(response.body().get(i).getNama());
                        deskripsi.setText(response.body().get(i).getDesc_tree());
                        //GlideClient.downloadImage(getBaseContext(), response.body().get(i).getImage(), image);
                        qrUri=response.body().get(i).getQr_url();


                        String imgThumb = response.body().get(i).getImage();
                        String img1 = response.body().get(i).getImage1();
                        String img2 = response.body().get(i).getImage2();
                        String img3 = response.body().get(i).getImage3();

                        if (img1.equals("") && img2.equals("") && img3.equals("")){
                            sliderImage(imgThumb);
                        } else if (img2.equals("") && img3.equals("")){
                            sliderImage(imgThumb,img1);
                        } else if (img3.equals("")){
                            sliderImage(imgThumb, img1, img2);
                        }else {
                            sliderImage(imgThumb, img1, img2, img3);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {

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
