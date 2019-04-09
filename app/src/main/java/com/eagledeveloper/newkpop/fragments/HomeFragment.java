package com.eagledeveloper.newkpop.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.adapters.WallPaperAdapters;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperDetailModel;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperResponseModel;
import com.eagledeveloper.newkpop.networking.ApiClient;
import com.eagledeveloper.newkpop.networking.ApiInterface;
import com.eagledeveloper.newkpop.utils.AlertUtils;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    AlertDialog alertDialog;
    View view;
    @BindView(R.id.rv_wallpaper)
    RecyclerView gvWallpapers;
    WallPaperAdapters wallPaperAdapters;
    public  List<WallPaperDetailModel> wallPaperDetailModelList;
    List<WallPaperDetailModel> loadMoreList;
    public static List<WallPaperDetailModel> list;


    int pageNo = 1;
    int totalItem;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previousTotal = 0;
    private int view_threshold = 15;
    private boolean isLoading = true;
    GridLayoutManager layoutManager;

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    boolean checkAd = false;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        customActionBar();
        initUI();

        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        MobileAds.initialize(getActivity(),
                getActivity().getResources().getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getActivity().getResources().getString(R.string.interstitial_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        showAds();
        context = getActivity();


        layoutManager = new GridLayoutManager(getActivity(), 3);
        gvWallpapers.setLayoutManager(layoutManager);
        wallPaperDetailModelList = new ArrayList<>();
        loadMoreList = new ArrayList<>();
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        wallPaperAdapters = new WallPaperAdapters(getActivity(), wallPaperDetailModelList);
        gvWallpapers.setAdapter(wallPaperAdapters);
        apiCallShowWallPapers();


        gvWallpapers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();  //10
                totalItemCount = layoutManager.getItemCount();     //10
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();   //0

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;

                        }
                    }
                }

                if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + view_threshold)) {
                    if (totalItemCount < totalItem) {
                        pageNo++;
                        loadMoreItems(pageNo);
                        isLoading = true;
                    } else {
                        Log.d("no", "no more items");
                    }

                }
            }
        });


    }

    private void apiCallShowWallPapers() {
        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        Call<WallPaperResponseModel> allUsers = services.showWallPapers(pageNo);
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
                } else if (response.body().getSuccess()) {
                    totalItem = response.body().getCount();
                    wallPaperDetailModelList.addAll(response.body().getData().getData());
                    wallPaperAdapters.notifyDataSetChanged();

                    WallPaperFragment.wallPaperDetailModelList = wallPaperDetailModelList;

                }

            }

            @Override
            public void onFailure(Call<WallPaperResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1f2e36")));
        mActionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#16222C")));
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView tvTitle = mCustomView.findViewById(R.id.title);
        tvTitle.setText("New Kpop Wallpapers");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.show();

    }

    private void loadMoreItems(int pageNo) {
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        ApiInterface services = ApiClient.getApiClient().create(ApiInterface.class);
        Call<WallPaperResponseModel> allUsers = services.showWallPapers(pageNo);
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
                } else if (response.body().getSuccess()) {

                    loadMoreList.addAll(response.body().getData().getData());
                    wallPaperDetailModelList.addAll(loadMoreList);
                    WallPaperFragment.wallPaperDetailModelList = wallPaperDetailModelList;
                    list = wallPaperDetailModelList;
                    wallPaperAdapters.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<WallPaperResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAds() {


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                //Toast.makeText(getActivity(), "Ad failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Toast.makeText(getActivity(), "Ad open", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                Log.d("tag", "closed");
            }
        });
    }

}
