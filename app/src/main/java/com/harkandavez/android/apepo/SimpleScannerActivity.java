package com.harkandavez.android.apepo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends BaseScannerActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<Tree> trees;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple_scanner);
        setupToolbar();

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result rawResult) {

        String result =  rawResult.getText();
        String uri = result.substring(0,13);
        result = result.substring(13);
        //int id =trees.indexOf(result);
        if (!"apepo://scan/".equals(uri)) {
            Toast.makeText(this, "QR Code Tidak Sesuai", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(SimpleScannerActivity.this, DataScan.class);
            intent.putExtra("ID_TREE", result);
            startActivity(intent);
        }
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
            }
        }, 2000);
    }


}
