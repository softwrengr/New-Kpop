package com.eagledeveloper.newkpop.services;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.eagledeveloper.newkpop.adapters.LikeAdapter;
import com.eagledeveloper.newkpop.fragments.HomeFragment;
import com.eagledeveloper.newkpop.fragments.WallPaperFragment;
import com.eagledeveloper.newkpop.helpers.KpopCrud;
import com.eagledeveloper.newkpop.models.LikeWallPaperModel;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperDetailModel;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperResponseModel;
import com.eagledeveloper.newkpop.networking.ApiClient;
import com.eagledeveloper.newkpop.networking.ApiInterface;
import com.eagledeveloper.newkpop.utils.Configuration;
import com.eagledeveloper.newkpop.utils.FileUtils;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationServices extends Service {
    public static List<WallPaperDetailModel> wallPaperDetailModelList;
    public static boolean checkNofication = true;
    KpopCrud kpopCrud;
    ArrayList<LikeWallPaperModel> likeWallPaperModelArrayList;

    public NotificationServices() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("HH-mm");
                String currentTime = df.format(new Date());

                if (currentTime.equals("16-10")) {
                    Log.d("hello", "called");
                    //Toast.makeText(NotificationServices.this, "called", Toast.LENGTH_SHORT).show();
                    automaticSet();
                } else {
                    Log.d("zma", "this is not the time");
                }

                handler.postDelayed(this, 500);
            }

        };
        handler.postDelayed(runnable, 1000);


        return super.onStartCommand(intent, flags, startId);
    }

    private void automaticSet() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

             //   setAutomaticWallpaper(Configuration.image);

            }
        }, 200);
    }


    private void setAutomaticWallpaper(final String image) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean setWallpaper = FileUtils.setWallPaper(NotificationServices.this, image);
                if (setWallpaper) {
                } else {
                    Toast.makeText(NotificationServices.this, "try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }, 200);
    }

}
