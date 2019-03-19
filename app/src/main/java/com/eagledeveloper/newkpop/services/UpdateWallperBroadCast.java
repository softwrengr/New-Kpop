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
        context.startService(new Intent(context, NotificationServices.class));




    }


}
