package com.example.zerus.parkfinder;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText name, value, status;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        btn = (Button)findViewById(R.id.buttonBTS);
    }

    public void viewBTS(View view){
        Cursor result = db.getBTS();
        if (result.getCount()==0){
            showMsg("Error","The table is empty!");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (result.moveToNext()){
            buffer.append("ID: " + result.getString(0) + "\n");
            buffer.append("Tên: " + result.getString(1) + "\n");
            buffer.append("Giá trị: " + result.getString(2) + "\n");
            buffer.append("Tình trạng: " + result.getString(3) + "\n\n");
        }
        showMsg(DatabaseHelper.TABLE_NAME_BTS, buffer.toString());
    }

    public void viewBDS(View view){
        Cursor result = db.getBDS();
        if (result.getCount()==0){
            showMsg("Error","The table is empty!");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (result.moveToNext()){
            buffer.append("ID: " + result.getString(0) + "\n");
            buffer.append("Tên: " + result.getString(1) + "\n");
            buffer.append("Địa chỉ: " + result.getString(2) + "\n");
            buffer.append("Kinh độ: " + result.getString(3) + "\n");
            buffer.append("Vĩ độ: " + result.getString(4) + "\n");
            buffer.append("Điện thoại: " + result.getString(5) + "\n");
            buffer.append("Tình trạng: " + result.getString(6) + "\n\n");
        }
        showMsg(DatabaseHelper.TABLE_NAME_BDS, buffer.toString());
    }

    public void viewBG(View view){
        Cursor result = db.getBG();
        if (result.getCount()==0){
            showMsg("Error","The table is empty!");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (result.moveToNext()){
            buffer.append("ID: " + result.getString(0) + "\n");
            buffer.append("Bãi đỗ xe: " + result.getString(1) + "\n");
            buffer.append("Giờ bắt đầu: " + result.getString(2) + "\n");
            buffer.append("Giờ kết thúc: " + result.getString(3) + "\n");
            buffer.append("Tình trạng: " + result.getString(4) + "\n\n");
        }
        showMsg(DatabaseHelper.TABLE_NAME_BG, buffer.toString());
    }

    public void showMsg(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
