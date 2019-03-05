package com.eagledeveloper.newkpop.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KpopDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "KPOP_DB";
    public static int DB_VERSION = 1;

    public KpopDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE LIKE_IMAGES (ID INTEGER PRIMARY KEY AUTOINCREMENT,IMAGE_ID,IMAGE_URL)";
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS LIKE_IMAGES");
        onCreate(db);
    }
}
