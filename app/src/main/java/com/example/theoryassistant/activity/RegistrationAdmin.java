package com.example.theoryassistant.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theoryassistant.R;
import com.example.theoryassistant.sqlite.SQLiteDataBase;

public class RegistrationAdmin extends AppCompatActivity {

    EditText mobile, password;
    TextView register;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String mobiles, passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_admin);

        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        openHelper = new SQLiteDataBase(this);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid();
                db = openHelper.getReadableDatabase();
                mobiles = mobile.getText().toString();
                passwords = password.getText().toString();

                insertData(mobiles, passwords);

                Toast.makeText(RegistrationAdmin.this, "Your Registration Is Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationAdmin.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean valid() {
        boolean prog = true;
        String mobile1 = mobile.getText().toString();
        String password1 = password.getText().toString();

        if (mobile1.equals("")) {
            prog = false;
            mobile.setError("Enter Contact No");
        }
        if (password1.equals("")) {
            prog = false;
            password.setError("Enter Password");
        }
        return prog;
    }

    public void insertData(String mobiles, String passwords) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBase.COL_1, mobiles);
        contentValues.put(SQLiteDataBase.COL_2, passwords);
        long id = db.insert(SQLiteDataBase.TABLE_NAME, null, contentValues);
        Toast.makeText(this, " " + id, Toast.LENGTH_SHORT).show();
    }
}