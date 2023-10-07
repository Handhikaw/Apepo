package com.harkandavez.android.apepo;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Handhika on 7/9/2017.
 */
public class Menu2 extends Fragment  {

    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;


    public  Menu2(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Scan QR Code");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_2, container, false);

        Button button = (Button)view.findViewById(R.id.scan);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                int id = v.getId();
                //switch(v.getId()){
                if (id == R.id.scan){
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity)getContext(),
                                new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
                    } else {
                        Intent intent = new Intent(getContext(), SimpleScannerActivity.class);
                        startActivity(intent);
                    }

                    // case R.id.scan:
                    //Intent intent1 = new Intent(getContext(), SimpleScannerActivity.class);
                    //startActivity(intent1);//Edited here
                    // break;


                }
            }
        });


        return view;
    }
    /*public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(getContext(), clss);
            startActivity(intent);
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(getContext(), mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getContext(), "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

}