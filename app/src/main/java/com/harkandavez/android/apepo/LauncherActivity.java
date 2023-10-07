package com.harkandavez.android.apepo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(LauncherActivity.this,MainActivity.class);
                LauncherActivity.this.startActivity(mainIntent);
                LauncherActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
