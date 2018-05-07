package com.example.zerus.parkfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZeruS on 5/6/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Database.db";//Tên database
    public static final int DATABASE_VERSION = 6;

    public static final String TABLE_NAME_BTS = "BANG_THAM_SO";//Tên table bảng tham số
    public static final String BTS_COL_1 = "ID";
    public static final String BTS_COL_2 = "TEN";
    public static final String BTS_COL_3 = "GIA_TRI";
    public static final String BTS_COL_4 = "TINH_TRANG";

    public static final String TABLE_NAME_BDS = "BAI_DO_XE";//Tên table bãi đỗ xe
    public static final String BDS_COL_1 = "ID";
    public static final String BDS_COL_2 = "TEN";
    public static final String BDS_COL_3 = "DIA_CHI";
    public static final String BDS_COL_4 = "KINH_DO";
    public static final String BDS_COL_5 = "VI_DO";
    public static final String BDS_COL_6 = "DIEN_THOAI";
    public static final String BDS_COL_7 = "TINH_TRANG";

    public static final String TABLE_NAME_BG = "BANG_GIA";//Tên table bảng giá
    public static final String BG_COL_1 = "ID";
    public static final String BG_COL_2 = "BAI_DO_XE";
    public static final String BG_COL_3 = "GIO_BAT_DAU";
    public static final String BG_COL_4 = "GIO_KET_THUC";
    public static final String BG_COL_5 = "GIA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_BTS + " ("          //Tạo bảng tham số
                + BTS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BTS_COL_2 + " TEXT,"
                + BTS_COL_3 + " TEXT,"
                + BTS_COL_4 + " TEXT"
                +")");

        db.execSQL("CREATE TABLE " + TABLE_NAME_BDS + " ("          //Tạo bãi đỗ xe
                + BDS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BDS_COL_2 + " TEXT,"
                + BDS_COL_3 + " TEXT,"
                + BDS_COL_4 + " TEXT,"
                + BDS_COL_5 + " TEXT,"
                + BDS_COL_6 + " TEXT,"
                + BDS_COL_7 + " TEXT"
                +")");

        db.execSQL("CREATE TABLE " + TABLE_NAME_BG + " ("          //Tạo bảng giá
                + BG_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BG_COL_2 + " INTEGER,"
                + BG_COL_3 + " TEXT,"
                + BG_COL_4 + " TEXT,"
                + BG_COL_5 + " TEXT,"
                + "FOREIGN KEY (" + BG_COL_2 + ") REFERENCES "+ TABLE_NAME_BDS +" (" + BDS_COL_1 + ")"
                +")");


        inputDataBTS(db);
        inputDataBDS(db);
        inputDataBG(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BG);
        onCreate(db);
    }

    //Các method dành cho BTS
    public boolean setdataBTS(SQLiteDatabase db, String name, String value, String status){
        ContentValues valeus = new ContentValues();
        valeus.put(BTS_COL_2, name);
        valeus.put(BTS_COL_3, value);
        valeus.put(BTS_COL_4, status);
        long result = db.insert(TABLE_NAME_BTS, null, valeus);
        if (result == -1)
            return false;
        return true;
    }

    public boolean insertBTS(String name, String value, String status){//Chèn thêm data vào bảng tham số
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

    public Cursor getBTS(){//Lấy TOÀN BỘ dữ liệu từ bảng tham số
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_BTS, null);
        return result;
    }

    public boolean updateBTS(String id, String name, String value, String status){//Update data của BTS dựa trên ID
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BTS_COL_2, name);
        valeus.put(BTS_COL_3, value);
        valeus.put(BTS_COL_4, status);
        db.update(TABLE_NAME_BTS, valeus, "ID = ?", new String[]{ id });
        return true;
    }

    public Integer deleteBTS(String id){//Xóa data trong BTS dựa trên ID
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BTS,"ID = ?", new String[] {id});
    }


    //Các method dành cho BDS
    public boolean insertBDS(String name, String address, String longitude, String latitude, String phone, String status){//Chèn thêm data vào bãi đỗ xe
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BDS_COL_2, name);
        valeus.put(BDS_COL_3, address);
        valeus.put(BDS_COL_4, longitude);
        valeus.put(BDS_COL_5, latitude);
        valeus.put(BDS_COL_6, phone);
        valeus.put(BDS_COL_7, status);
        long result = db.insert(TABLE_NAME_BDS, null, valeus);
        if (result == -1)
            return false;
        return true;
    }

    public Cursor getBDS(){//Lấy TOÀN BỘ dữ liệu từ bãi đỗ xe
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_BDS, null);
        return result;
    }

    public boolean updateBDS(String id, String name, String address, String longitude, String latitude, String phone, String status){//Update data của BDS dựa trên ID
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BDS_COL_2, name);
        valeus.put(BDS_COL_3, address);
        valeus.put(BDS_COL_4, longitude);
        valeus.put(BDS_COL_5, latitude);
        valeus.put(BDS_COL_6, phone);
        valeus.put(BDS_COL_7, status);
        db.update(TABLE_NAME_BDS, valeus, "ID = ?", new String[]{ id });
        return true;
    }

    public Integer deleteBDS(String id){//Xóa data trong BDS dựa trên ID
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BDS,"ID = ?", new String[] {id});
    }


    //Các method dành cho BTS
    public boolean insertBG(String id, String start, String finish, String price){//Chèn thêm data vào bảng giá
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BG_COL_1, id);
        valeus.put(BG_COL_2, start);
        valeus.put(BG_COL_3, finish);
        valeus.put(BG_COL_4, price);
        long result = db.insert(TABLE_NAME_BG, null, valeus);
        if (result == -1)
            return false;
        return true;
    }

    public Cursor getBG(){//Lấy TOÀN BỘ dữ liệu từ bảng giá
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_BG, null);
        return result;
    }

    public boolean updateBG(String id, String name, String start, String finish, String price){//Update data của BG dựa trên id
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BG_COL_2, name);
        valeus.put(BG_COL_3, start);
        valeus.put(BG_COL_4, finish);
        valeus.put(BG_COL_5, price);
        db.update(TABLE_NAME_BG, valeus, "ID = ?", new String[]{ id });
        return true;
    }

    public Integer deleteBG(String id){//Xóa data trong BG dựa trên ID
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BG,"ID = ?", new String[] {id});
    }


    //Nhập dữ liệu
    private void inputDataBTS(SQLiteDatabase db) {
        setdataBTS(db,"APP_NAME", "Park Finder","1");
        setdataBTS(db,"APP_VERSION", "1.0.0","1");
    }

    private void inputDataBDS(SQLiteDatabase db) {
    }

    private void inputDataBG(SQLiteDatabase db) {
    }

}
