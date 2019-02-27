package com.eagledeveloper.newkpop.utils;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.eagledeveloper.newkpop.R;


/**
 * Created by eapple on 30/10/2018.
 */

public class GeneralUtils {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;


    public static Fragment connectFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        return fragment;
    }

    public static Fragment connectFragmentWithBackStack(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("true").commit();
        return fragment;
    }


    public static SharedPreferences.Editor putStringValueInEditor(Context context, String key, String value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putIntegerValueInEditor(Context context, String key, int value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putInt(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putBooleanValueInEditor(Context context, String key, boolean value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).commit();
        return editor;
    }


    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Configuration.My_PREF, 0);
    }

    public static int getUserID(Context context){
        return getSharedPreferences(context).getInt("id",0);
    }

    public static int getUserScore(Context context){
        return getSharedPreferences(context).getInt("score",0);
    }

    public static String getContestID(Context context){
        return getSharedPreferences(context).getString("contestID","");
    }

    public static String getEmail(Context context){
        return getSharedPreferences(context).getString("email","");
    }
    public static String getToken(Context context){
        return getSharedPreferences(context).getString("token","");
    }
    public static String getResultDate(Context context){
        return getSharedPreferences(context).getString("date","");
    }

    public static Boolean getOTP(Context context){
        return getSharedPreferences(context).getBoolean("check_otp",false);
    }

}
