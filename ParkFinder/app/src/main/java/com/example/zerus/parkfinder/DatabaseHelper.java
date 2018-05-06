package com.example.zerus.parkfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZeruS on 5/6/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Database.db";//Tên database
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME_BTS = "BANG_THAM_SO";//Tên table bảng tham số
    public static final String BTS_COL_1 = "ID";
    public static final String BTS_COL_2 = "TEN";
    public static final String BTS_COL_3 = "GIA_TRI";
    public static final String BTS_COL_4 = "TINH_TRANG";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_BTS + " ("
                + BTS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BTS_COL_2 + " TEXT,"
                + BTS_COL_3 + " TEXT,"
                + BTS_COL_4 + " TEXT"
                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BTS);
        onCreate(db);
    }

    public boolean insertBTS(String name, String value, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BTS_COL_2, name);
        valeus.put(BTS_COL_3, value);
        valeus.put(BTS_COL_4, status);
        long result = db.insert(TABLE_NAME_BTS, null, valeus);
        if (result == -1)
            return false;
        return true;
    }
}
