package com.eagledeveloper.newkpop.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.adapters.LikeAdapter;
import com.eagledeveloper.newkpop.helpers.KpopCrud;
import com.eagledeveloper.newkpop.models.LikeWallPaperModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LikedWallPaperFragment extends Fragment {
    View view;
    @BindView(R.id.rv_like_wallpaper)
    RecyclerView rvLikeWallpaper;
    KpopCrud kpopCrud;
    ArrayList<LikeWallPaperModel> likeWallPaperModelArrayList;
    LikeAdapter likeAdapter;
    GridLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_liked_wall_paper, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        kpopCrud = new KpopCrud(getActivity());
        layoutManager = new GridLayoutManager(getActivity(), 3);
        rvLikeWallpaper.setLayoutManager(layoutManager);
        likeWallPaperModelArrayList = new ArrayList<>();
        showLikeImages();
    }

    public void showLikeImages() {
        Cursor cursor = kpopCrud.getProducts();
        while (cursor.moveToNext()) {
            String imageID = cursor.getString(1).trim();
            String imagetUrl = cursor.getString(2).trim();

            LikeWallPaperModel model = new LikeWallPaperModel();
            model.setImageID(imageID);
            model.setImageUrl(imagetUrl);

            likeWallPaperModelArrayList.add(model);

            likeAdapter = new LikeAdapter(getActivity(), likeWallPaperModelArrayList);
            rvLikeWallpaper.setAdapter(likeAdapter);
            likeAdapter.notifyDataSetChanged();
        }
    }

}
