package com.example.theoryassistant.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.theoryassistant.R;
import com.example.theoryassistant.activity.RegistrationAdmin;
import com.example.theoryassistant.sqlite.SQLiteDataBase;

public class MyAccount extends RegistrationAdmin {

    Button back;
    TextView acmobile, acpassword;
    String parameter2;
    private Cursor cursor;
    String mobiles, passwords;
    public SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        acmobile = findViewById(R.id.acmobile);
        acpassword = findViewById(R.id.acpassword);
        back = findViewById(R.id.back);
        openHelper = new SQLiteDataBase(this);
        db = openHelper.getReadableDatabase();

        parameter2 = getIntent().getStringExtra("phone");

        cursor = db.rawQuery("SELECT * FROM " + SQLiteDataBase.TABLE_NAME + " WHERE "
                + SQLiteDataBase.COL_1 + " = ? ", new String[]{parameter2});

        cursor.moveToLast();
        mobiles = cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_1));
        acmobile.setText(mobiles);
        passwords = cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_2));
        acpassword.setText(passwords);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}