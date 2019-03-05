package com.eagledeveloper.newkpop.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class FileUtils {
    public static boolean setWallPaper(Context context, ImageView string) {
        boolean setWallpaper = false;
        Bitmap bitmap;
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
        try {

            bitmap = ((BitmapDrawable) string.getDrawable()).getBitmap();
            myWallpaperManager.setBitmap(bitmap);
            Toast.makeText(context, "WallPaper set Successfully", Toast.LENGTH_SHORT).show();
            setWallpaper = true;


        } catch (IOException e) {
            Log.d("zma", e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return setWallpaper;
    }

    public static boolean saveWallPaper(Context context, Bitmap bm) throws IOException {
        Toast.makeText(context, "WallPaper saved in folder New Kpop", Toast.LENGTH_SHORT).show();

        boolean imageSave = false;

        Date currentTime = Calendar.getInstance().getTime();
        String dataTime = String.valueOf(currentTime);
        // Environment.DIRECTORY_PICTURES
        File path = Environment.getExternalStoragePublicDirectory("New Kpop");

        if (!path.exists()) {
            path.mkdirs();
        }
        File imageFile = new File(path, dataTime + ".PNG");
        try {
            FileOutputStream out = new FileOutputStream(imageFile);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out); // Compress Image
            out.flush();
            out.close();

            imageSave = true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return imageSave;
    }
}
