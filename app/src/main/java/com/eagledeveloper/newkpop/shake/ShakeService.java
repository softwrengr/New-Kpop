package com.eagledeveloper.newkpop.shake;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.eagledeveloper.newkpop.services.GetImageUrl;

public class ShakeService extends Service {
    public ShakeService() {

    }

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    boolean shake = true;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {


                if (shake) {

                    shake = false;
                    Toast.makeText(ShakeService.this, "please wait a few second", Toast.LENGTH_SHORT).show();


                    GetImageUrl getImageUrl = new GetImageUrl(getApplicationContext());
                    new CountDownTimer(5000, 1000) {

                        public void onTick(long millisUntilFinished) {


                        }

                        public void onFinish() {
                            Log.d("shake","shake finished");
                            shake = true;
                        }

                    }.start();
                }


            }
        });


        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
