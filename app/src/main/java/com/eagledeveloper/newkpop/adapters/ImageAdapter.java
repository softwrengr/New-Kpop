package com.eagledeveloper.newkpop.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperDetailModel;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {
    Context context;
    List<WallPaperDetailModel> wallPaperDetailModelList;
    LayoutInflater mLayoutInflater;
    private ImageListener listener;

    public ImageAdapter(Context context, List<WallPaperDetailModel> wallPaperDetailModelList,ImageListener listener) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.wallPaperDetailModelList = wallPaperDetailModelList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return wallPaperDetailModelList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        final WallPaperDetailModel model = wallPaperDetailModelList.get(position);

        final ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        Glide.with(context).load(model.getImage()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageListener(String.valueOf(model.getId()),model.getImage(),imageView);
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    public interface ImageListener{
        void onImageListener(String imageID,String imageUrl,ImageView imageView);
    }

}
