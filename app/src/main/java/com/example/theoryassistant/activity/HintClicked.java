package com.example.theoryassistant.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theoryassistant.R;
import com.example.theoryassistant.sqlite.SQLiteDataBase;

public class HintClicked extends AppCompatActivity {

    TextView  buy_details;
    String  det;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle oldInstanceState) {
        super.onCreate(oldInstanceState);
        setContentView(R.layout.hintclicked_items);
        openHelper = new SQLiteDataBase(this);

        Bundle extras = getIntent().getExtras();
        det = extras.getString("details");

        buy_details = findViewById(R.id.buy_details);
        buy_details.setMovementMethod(new ScrollingMovementMethod());

        //Setting Data to its Ids
        buy_details.setText(det);
    }

    public void insertData(String cart_name, String cart_price, String cart_details) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBase.NAME, cart_name);
        contentValues.put(SQLiteDataBase.PRICE, cart_price);
        contentValues.put(SQLiteDataBase.DETAILS, cart_details);
        long id = db.insert(SQLiteDataBase.TABLE_NAME2, null, contentValues);
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
    }
}