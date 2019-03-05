package com.eagledeveloper.newkpop.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.fragments.WallPaperFragment;
import com.eagledeveloper.newkpop.models.LikeWallPaperModel;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperDetailModel;
import com.eagledeveloper.newkpop.utils.GeneralUtils;

import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.MyViewHolder> {
    List<LikeWallPaperModel> likeWallPaperModelList;
    Context context;

    public LikeAdapter(Context context, List<LikeWallPaperModel> likeWallPaperModelList) {
        this.context = context;
        this.likeWallPaperModelList = likeWallPaperModelList;

    }


    @NonNull
    @Override
    public LikeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_wallpaper_layout, parent, false);

        return new LikeAdapter.MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        final LikeWallPaperModel model = likeWallPaperModelList.get(position);
        if (likeWallPaperModelList != null && likeWallPaperModelList.size() > position)
            return likeWallPaperModelList.size();
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final LikeAdapter.MyViewHolder viewHolder, final int position) {
        final LikeWallPaperModel model = likeWallPaperModelList.get(position);

        Toast.makeText(context, model.getImageUrl(), Toast.LENGTH_SHORT).show();
        Glide.with(context).load(model.getImageUrl()).into(viewHolder.ivWallpaper);


        viewHolder.layout_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragementWithDrawer(context, new WallPaperFragment());
                GeneralUtils.putStringValueInEditor(context,"image_id",String.valueOf(model.getImageID()));
                GeneralUtils.putStringValueInEditor(context, "image", model.getImageUrl());
            }
        });


    }

    @Override
    public int getItemCount() {
        return likeWallPaperModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivWallpaper;
        RelativeLayout layout_category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivWallpaper = itemView.findViewById(R.id.iv_wallpaper);
            layout_category = itemView.findViewById(R.id.layout);


        }
    }
}

