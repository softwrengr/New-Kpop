package com.eagledeveloper.newkpop.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.services.NotificationServices;
import com.eagledeveloper.newkpop.shake.ShakeService;
import com.eagledeveloper.newkpop.utils.GeneralUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        GeneralUtils.putBooleanValueInEditor(this,"check_ad",true);
      //  startService(new Intent(MainActivity.this, NotificationServices.class));



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(MainActivity.this, DrawerActivity.class));
            }
        }, 2000);
    }
}
