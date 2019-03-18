package com.eagledeveloper.newkpop.services;

import android.content.Context;
import android.media.audiofx.DynamicsProcessing;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eagledeveloper.newkpop.fragments.WallPaperFragment;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperResponseModel;
import com.eagledeveloper.newkpop.networking.ApiClient;
import com.eagledeveloper.newkpop.networking.ApiInterface;
import com.eagledeveloper.newkpop.utils.Configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetImageUrl {
    ArrayList<String> urlArrayList;
    Context context;


    public GetImageUrl( Context context) {
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
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject imageObject = jsonArray.getJSONObject(i);
                        String url = imageObject.getString("image");
                        urlArrayList.add(url);
                        Log.d("url",urlArrayList.get(i));
                        Toast.makeText(context, urlArrayList.get(i), Toast.LENGTH_SHORT).show();

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
}
