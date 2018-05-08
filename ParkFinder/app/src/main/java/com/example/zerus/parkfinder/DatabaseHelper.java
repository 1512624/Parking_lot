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
    public static final int DATABASE_VERSION = 9;

    public static final String TABLE_NAME_BTS = "BANG_THAM_SO";//Tên table bảng tham số
    public static final String BTS_COL_1 = "ID";
    public static final String BTS_COL_2 = "TEN";
    public static final String BTS_COL_3 = "GIA_TRI";
    public static final String BTS_COL_4 = "TINH_TRANG";

    public static final String TABLE_NAME_BDX = "BAI_DO_XE";//Tên table bãi đỗ xe
    public static final String BDX_COL_1 = "ID";
    public static final String BDX_COL_2 = "TEN";
    public static final String BDX_COL_3 = "DIA_CHI";
    public static final String BDX_COL_4 = "KINH_DO";
    public static final String BDX_COL_5 = "VI_DO";
    public static final String BDX_COL_6 = "DIEN_THOAI";
    public static final String BDX_COL_7 = "TINH_TRANG";

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

        db.execSQL("CREATE TABLE " + TABLE_NAME_BDX + " ("          //Tạo bãi đỗ xe
                + BDX_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BDX_COL_2 + " TEXT,"
                + BDX_COL_3 + " TEXT,"
                + BDX_COL_4 + " TEXT,"
                + BDX_COL_5 + " TEXT,"
                + BDX_COL_6 + " TEXT,"
                + BDX_COL_7 + " TEXT"
                +")");

        db.execSQL("CREATE TABLE " + TABLE_NAME_BG + " ("          //Tạo bảng giá
                + BG_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BG_COL_2 + " INTEGER,"
                + BG_COL_3 + " TEXT,"
                + BG_COL_4 + " TEXT,"
                + BG_COL_5 + " TEXT,"
                + "FOREIGN KEY (" + BG_COL_2 + ") REFERENCES "+ TABLE_NAME_BDX +" (" + BDX_COL_1 + ")"
                +")");


        inputDataBTS(db);
        inputDataBDX(db);
        inputDataBG(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BDX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BG);
        onCreate(db);
    }

    //Các method dành cho BTS
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


    //Các method dành cho BDX
    public boolean insertBDX(String name, String address, String longitude, String latitude, String phone, String status){//Chèn thêm data vào bãi đỗ xe
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BDX_COL_2, name);
        valeus.put(BDX_COL_3, address);
        valeus.put(BDX_COL_4, longitude);
        valeus.put(BDX_COL_5, latitude);
        valeus.put(BDX_COL_6, phone);
        valeus.put(BDX_COL_7, status);
        long result = db.insert(TABLE_NAME_BDX, null, valeus);
        if (result == -1)
            return false;
        return true;
    }

    public Cursor getBDX(){//Lấy TOÀN BỘ dữ liệu từ bãi đỗ xe
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME_BDX, null);
        return result;
    }

    public boolean updateBDX(String id, String name, String address, String longitude, String latitude, String phone, String status){//Update data của BDX dựa trên ID
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BDX_COL_2, name);
        valeus.put(BDX_COL_3, address);
        valeus.put(BDX_COL_4, longitude);
        valeus.put(BDX_COL_5, latitude);
        valeus.put(BDX_COL_6, phone);
        valeus.put(BDX_COL_7, status);
        db.update(TABLE_NAME_BDX, valeus, "ID = ?", new String[]{ id });
        return true;
    }

    public Integer deleteBDX(String id){//Xóa data trong BDX dựa trên ID
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BDX,"ID = ?", new String[] {id});
    }


    //Các method dành cho BTS
    public boolean insertBG(Integer id, String start, String finish, String price){//Chèn thêm data vào bảng giá
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valeus = new ContentValues();
        valeus.put(BG_COL_2, id);
        valeus.put(BG_COL_3, start);
        valeus.put(BG_COL_4, finish);
        valeus.put(BG_COL_5, price);
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

    public boolean setdataBDX(SQLiteDatabase db, String name, String address, String longitude, String latitude, String phone, String status){
        ContentValues valeus = new ContentValues();
        valeus.put(BDX_COL_2, name);
        valeus.put(BDX_COL_3, address);
        valeus.put(BDX_COL_4, longitude);
        valeus.put(BDX_COL_5, latitude);
        valeus.put(BDX_COL_6, phone);
        valeus.put(BDX_COL_7, status);
        long result = db.insert(TABLE_NAME_BDX, null, valeus);
        if (result == -1)
            return false;
        return true;
    }

    public boolean setdataBG(SQLiteDatabase db, Integer id, String start, String finish, String price){
        ContentValues valeus = new ContentValues();
        valeus.put(BG_COL_2, id);
        valeus.put(BG_COL_3, start);
        valeus.put(BG_COL_4, finish);
        valeus.put(BG_COL_5, price);
        long result = db.insert(TABLE_NAME_BG, null, valeus);
        if (result == -1)
            return false;
        return true;
    }

    private void inputDataBTS(SQLiteDatabase db) {
        setdataBTS(db,"APP_NAME", "Park Finder","1");
        setdataBTS(db,"APP_VERSION", "1.0.0","1");
    }

    private void inputDataBDX(SQLiteDatabase db) {
        setdataBDX(db,"Vincom Center","72 Lê Thánh Tôn, Bến Nghé, Quận 1, Hồ Chí Minh, Việt Nam","106.701824","10.7781984","+84 97 503 32 88","1");
        setdataBDX(db,"Nhà Văn hóa Thanh niên","04 Phạm Ngọc Thạch, Bến Nghé, Quận 1, Hồ Chí Minh, Việt Nam","106.697566","10.781979","+84 28 3829 4345","1");
        setdataBDX(db,"Sân vận động Hoa Lư","2 Đinh Tiên Hoàng, Đa Kao, Quận 1, Hồ Chí Minh, Việt Nam","106.701558","10.78811","","1");
        setdataBDX(db,"Thảo Cầm Viên","2 Nguyễn Bỉnh Khiêm, Bến Nghé, Quận 1, Hồ Chí Minh, Việt Nam","106.705028","10.787646","+84 28 3829 1425","1");
        setdataBDX(db,"Nhà khách Phương Nam","252 Nguyễn Trãi, Phường Nguyễn Cư Trinh, Quận 1, Hồ Chí Minh, Việt Nam","106.686096","10.763037","+84 28 3838 6411","1");
        setdataBDX(db,"Hotel Nikko Saigon","235 Đường Nguyễn Văn Cừ, Phường Nguyễn Cư Trinh, Quận 1, Hồ Chí Minh, Việt Nam","106.682895","10.764678","+84 28 3925 7777","1");
        setdataBDX(db,"Chung cư Thạnh Mỹ Lợi","Cao Ốc Thạnh Mỹ Lợi, 2,, Đồng Văn Cống, Phường Thạnh Mỹ Lợi, Quận 2, Hồ Chí Minh, Việt Nam","106.773012","10.770885","","1");
        setdataBDX(db,"Bảo tàng Phụ nữ Nam bộ","202 Võ Thị Sáu, Phường 7, Quận 3, Hồ Chí Minh, Việt Nam","106.687894","10.78426","+84 28 3932 5519","1");
        setdataBDX(db,"Bệnh Viện Phạm Ngọc Thạch - Cổng 2","Ngô Quyền, Phường 12, 5, Hồ Chí Minh, Việt Nam","106.665981","10.757721","+84 28 3855 1746","1");
        setdataBDX(db,"Giữ xe Đăng Khoa","10 Đường 44, Phường 10, Quận 6, Hồ Chí Mình, Việt Nam","106.62614","10.736216","","1");
        setdataBDX(db,"Bãi xe Huỳnh Tấn Phát","Hẻm 860 Huỳnh Tấn Phát, Quận 7, Hồ Chí Minh, Việt Nam","106.731831","10.728798","","1");
        setdataBDX(db,"Bãi xe Thành Thái","C30 Thành Thái, Phường 14, Quận 10, Hồ Chí Minh, Việt Nam","106.666809","10.768804","","1");
        setdataBDX(db,"Tòa nhà Everrich","The EverRich 1, 968 Ba Tháng Hai, Phường 15, Quận 11, Hồ Chí Minh, Việt Nam","106.657155","10.763238","+84 91 888 00 21","1");
    }

    private void inputDataBG(SQLiteDatabase db) {
        setdataBG(db,1,"9:30","22:00","40000");
        setdataBG(db,2,"7:00","18:00","20000");
        setdataBG(db,3,"7:00","17:00","20000");
        setdataBG(db,4,"7:00","17:00","30000");
        setdataBG(db,5,"7:00","18:00","20000");
        setdataBG(db,6,"7:00","21:00","20000");
        setdataBG(db,7,"0:00","0:00","20000");
        setdataBG(db,8,"7:30","17:00","20000");
        setdataBG(db,9,"7:00","20:00","30000");
        setdataBG(db,10,"6:30","22:00","30000");
        setdataBG(db,11,"6:00","22:00","20000");
        setdataBG(db,11,"0:00","0:00","50000");
        setdataBG(db,12,"0:00","0:00","50000");
        setdataBG(db,13,"9:00","17:00","30000");
    }

}
