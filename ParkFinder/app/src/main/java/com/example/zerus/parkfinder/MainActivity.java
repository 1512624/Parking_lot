package com.example.zerus.parkfinder;

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

        name = (EditText)findViewById(R.id.editText1);
        value = (EditText)findViewById(R.id.editText2);
        status = (EditText)findViewById(R.id.editText3);
        btn = (Button)findViewById(R.id.button);
    }

    public void insertData(View view){
        boolean check = db.insertBTS(name.getText().toString(), value.getText().toString(), status.getText().toString());
        if (check)
            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Not yet", Toast.LENGTH_LONG).show();
    }
}
