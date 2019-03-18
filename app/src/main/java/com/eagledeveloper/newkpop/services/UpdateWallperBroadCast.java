package com.eagledeveloper.newkpop.services;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import com.eagledeveloper.newkpop.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UpdateWallperBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       IntentFilter mTime = new IntentFilter(Intent.ACTION_TIME_TICK);
        Toast.makeText(context, String.valueOf(mTime), Toast.LENGTH_SHORT).show();

        Random randomGenerator;

        List list = HomeFragment.list;
        randomGenerator = new Random();

//        int index = randomGenerator.nextInt(list.size());
//        Toast.makeText(context, String.valueOf(index), Toast.LENGTH_SHORT).show();


    }


}
