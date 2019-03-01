package com.eagledeveloper.newkpop.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.adapters.WallPaperAdapters;
import com.eagledeveloper.newkpop.models.WallPaperDetailModel;
import com.eagledeveloper.newkpop.models.WallPaperResponseModel;
import com.eagledeveloper.newkpop.networking.ApiClient;
import com.eagledeveloper.newkpop.networking.ApiInterface;
import com.eagledeveloper.newkpop.utils.AlertUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.gv_wallpaper)
    GridView gvWallpapers;
    ArrayList<WallPaperDetailModel> wallPaperDetailModelList;
    WallPaperAdapters wallPaperAdapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        wallPaperDetailModelList = new ArrayList<>();
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        apiCallShowWallPapers();
    }

    private void apiCallShowWallPapers() {
        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        Call<WallPaperResponseModel> allUsers = services.showWallPapers();
        allUsers.enqueue(new Callback<WallPaperResponseModel>() {
            @Override
            public void onResponse(Call<WallPaperResponseModel> call, Response<WallPaperResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {

                    wallPaperDetailModelList.addAll(response.body().getData());
                    wallPaperAdapters = new WallPaperAdapters(getActivity(), wallPaperDetailModelList);
                    gvWallpapers.setAdapter(wallPaperAdapters);
                    wallPaperAdapters.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<WallPaperResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
