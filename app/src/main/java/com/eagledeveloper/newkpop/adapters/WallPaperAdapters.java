package com.eagledeveloper.newkpop.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.fragments.HomeFragment;
import com.eagledeveloper.newkpop.fragments.WallPaperFragment;
import com.eagledeveloper.newkpop.models.WallPaperDetailModel;
import com.eagledeveloper.newkpop.utils.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public class WallPaperAdapters extends RecyclerView.Adapter<WallPaperAdapters.MyViewHolder> {
    List<WallPaperDetailModel> itemResturantDetailModelList;
    Context context;

    public WallPaperAdapters(Context context, List<WallPaperDetailModel> itemResturantDetailModelList) {
        this.context = context;
        this.itemResturantDetailModelList = itemResturantDetailModelList;

    }


    @NonNull
    @Override
    public WallPaperAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_wallpaper_layout, parent, false);

        return new WallPaperAdapters.MyViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        final WallPaperDetailModel model = itemResturantDetailModelList.get(position);
        if (itemResturantDetailModelList != null && itemResturantDetailModelList.size() > position)
            return itemResturantDetailModelList.size();
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final WallPaperAdapters.MyViewHolder viewHolder, final int position) {
        final WallPaperDetailModel model = itemResturantDetailModelList.get(position);

        Glide.with(context).load(model.getImage()).into(viewHolder.ivWallpaper);


        viewHolder.layout_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragementWithDrawer(context, new WallPaperFragment());
                GeneralUtils.putStringValueInEditor(context, "image", model.getImage());
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemResturantDetailModelList.size();
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
