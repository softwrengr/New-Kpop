package com.eagledeveloper.newkpop.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RestartPhoneStartServices extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        context.startService(new Intent(context,AlarmStartService.class));
    }
}
