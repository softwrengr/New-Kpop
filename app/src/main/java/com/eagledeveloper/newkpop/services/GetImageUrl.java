package com.eagledeveloper.newkpop.services;

import android.content.Context;
import android.media.audiofx.DynamicsProcessing;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eagledeveloper.newkpop.fragments.HomeFragment;
import com.eagledeveloper.newkpop.fragments.WallPaperFragment;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperResponseModel;
import com.eagledeveloper.newkpop.networking.ApiClient;
import com.eagledeveloper.newkpop.networking.ApiInterface;
import com.eagledeveloper.newkpop.utils.Configuration;
import com.eagledeveloper.newkpop.utils.FileUtils;
import com.eagledeveloper.newkpop.utils.GeneralUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetImageUrl {
    public ArrayList<String> urlArrayList;
    Context context;
    public static boolean checkWall = false;
    private Random randomGenerator;


    public GetImageUrl(Context context) {
        this.context = context;
        apiCallShowWallPapers();
    }

    private void apiCallShowWallPapers() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Configuration.url
                , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject object = jsonObject.getJSONObject("data");
                    JSONArray jsonArray = object.getJSONArray("data");

                    urlArrayList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject imageObject = jsonArray.getJSONObject(i);
                        String url = imageObject.getString("image");
                        urlArrayList.add(url);
                    }


                    if (!checkWall) {

                        ArrayList<String> list = urlArrayList;
                        randomGenerator = new Random();
                        int index = randomGenerator.nextInt(list.size());

                        String string = urlArrayList.get(index);
                        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                        setWall(string);
                        Log.d("url",string);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }

        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);

    }

    private void setWall(final String image) {
        checkWall = true;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean setWallpaper = FileUtils.setWallPaper(context, image);
                if (setWallpaper) {

                } else {
                }
            }
        }, 100);
    }
}
