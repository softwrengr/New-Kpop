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
import com.eagledeveloper.newkpop.models.LikeWallPaperModel;
import com.eagledeveloper.newkpop.models.wallpaperDataModels.WallPaperDetailModel;
import com.eagledeveloper.newkpop.utils.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {
    Context context;
    List<WallPaperDetailModel> wallPaperDetailModelList;
    List<LikeWallPaperModel> likeWallPaperModelList;
    LayoutInflater mLayoutInflater;
    private ImageListener listener;
    String check;

    public ImageAdapter(Context context, List<WallPaperDetailModel> wallPaperDetailModelList, List<LikeWallPaperModel> likeWallPaperModelList,String check, ImageListener listener) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.wallPaperDetailModelList = wallPaperDetailModelList;
        this.likeWallPaperModelList = likeWallPaperModelList;
        this.check = check;
        this.listener = listener;
    }

    @Override
    public int getCount() {
         int count = 0;
        if(check.equals("like_screen")){
            count = likeWallPaperModelList.size();
        }
        else if(check.equals("normal_screen")){
            count = wallPaperDetailModelList.size();
        }
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        final ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        if (check.equals("like_screen")) {
             LikeWallPaperModel likeWallPaperModel = likeWallPaperModelList.get(position);
             Glide.with(context).load(likeWallPaperModel.getImageUrl()).into(imageView);
        }
        else if(check.equals("normal_screen")) {
             WallPaperDetailModel model = wallPaperDetailModelList.get(position);
             Glide.with(context).load(model.getImage()).into(imageView);
        }
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public interface ImageListener {
        void onImageListener(String imageID, String imageUrl, ImageView imageView);
    }

}
