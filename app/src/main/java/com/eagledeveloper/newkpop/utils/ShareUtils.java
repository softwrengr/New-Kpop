package com.eagledeveloper.newkpop.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ShareUtils {

    public static Intent shareApp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at:\n https://play.google.com/store/apps/details?id=com.squaresdevelopers.lateststockwallpapers&hl=en");
        sendIntent.setType("text/plain");
        return sendIntent;
    }

    public static Intent loadApp(Activity activity){
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

        return  goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
    }
}
