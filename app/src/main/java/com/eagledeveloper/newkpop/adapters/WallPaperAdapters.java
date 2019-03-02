package com.eagledeveloper.newkpop.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
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

public class WallPaperAdapters extends BaseAdapter {
    ArrayList<WallPaperDetailModel> wallPaperDetailModelArrayList;
    Context context;
    private LayoutInflater layoutInflater;
    WallPaperAdapters.MyViewHolder viewHolder = null;


    public WallPaperAdapters(Context context, ArrayList<WallPaperDetailModel> wallPaperDetailModelArrayList) {
        this.wallPaperDetailModelArrayList = wallPaperDetailModelArrayList;
        this.context = context;
        if (context != null) {
            this.layoutInflater = LayoutInflater.from(context);

        }
    }

    @Override
    public int getCount() {
        if (wallPaperDetailModelArrayList != null) return wallPaperDetailModelArrayList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (wallPaperDetailModelArrayList != null && wallPaperDetailModelArrayList.size() > position)
            return wallPaperDetailModelArrayList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        final WallPaperDetailModel model = wallPaperDetailModelArrayList.get(position);
        if (wallPaperDetailModelArrayList != null && wallPaperDetailModelArrayList.size() > position)
            return wallPaperDetailModelArrayList.size();
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final WallPaperDetailModel model = wallPaperDetailModelArrayList.get(position);

        viewHolder = new WallPaperAdapters.MyViewHolder();
        convertView = layoutInflater.inflate(R.layout.custome_wallpaper_layout, parent, false);
        viewHolder.ivWallpaper = convertView.findViewById(R.id.iv_wallpaper);
        viewHolder.layout_category = convertView.findViewById(R.id.layout);


        Glide.with(context).load(model.getImage()).into(viewHolder.ivWallpaper);


        viewHolder.layout_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragementWithDrawer(context, new WallPaperFragment());
                GeneralUtils.putStringValueInEditor(context,"image",model.getImage());
            }
        });


        convertView.setTag(viewHolder);
        return convertView;
    }


    private class MyViewHolder {
        ImageView ivWallpaper;
        RelativeLayout layout_category;
    }
}


