package com.eagledeveloper.newkpop.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.eagledeveloper.newkpop.utils.GeneralUtils;

public class AlarmStartService extends Service {
    public AlarmStartService() {
    }

    PendingIntent pendingIntent;
    AlarmManager manager;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Intent alarmIntent = new Intent(this, UpdateWallperBroadCast.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        startAlarm();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelAlarm();

    }

    public void startAlarm() {
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000;

//        interval = GeneralUtils.getSharedPreferences(getApplicationContext()).getInt("wallpaperChangeTime",10000);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm() {
        if (manager != null) {
            manager.cancel(pendingIntent);
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        }
    }

}
