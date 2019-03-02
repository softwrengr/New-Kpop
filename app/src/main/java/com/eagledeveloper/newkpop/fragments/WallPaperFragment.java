package com.eagledeveloper.newkpop.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.utils.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WallPaperFragment extends Fragment {
    View view;
    @BindView(R.id.wallpaper)
    ImageView ivWallPaper;

    String strImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wall_paper, container, false);
        customActionBar();
        initUI();
        return view;
    }
    private void initUI() {
        ButterKnife.bind(this, view);


        strImage = GeneralUtils.getImage(getActivity());

        if (strImage.equals("") || strImage == null) {
            ivWallPaper.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.splash));
        } else {
            Glide.with(getActivity()).load(strImage).into(ivWallPaper);
        }

    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#330000ff")));
        mActionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_wallp_layout, null);
        LinearLayout layoutLike,layoutShare,layoutSave,layoutSet;
        layoutLike = mCustomView.findViewById(R.id.layout_like);
        layoutSet = mCustomView.findViewById(R.id.layout_set);
        layoutSave = mCustomView.findViewById(R.id.layout_save);
        layoutShare = mCustomView.findViewById(R.id.layout_share);

        layoutLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //saving wallpaper
        layoutSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //sharing wallpaper
        layoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        layoutSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.show();
    }
}
