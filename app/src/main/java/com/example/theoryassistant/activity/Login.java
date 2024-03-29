package com.example.theoryassistant.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theoryassistant.ForgetPassword;
import com.example.theoryassistant.R;
import com.example.theoryassistant.sqlite.SQLiteDataBase;

public class Login extends AppCompatActivity {

    Button login;
    EditText mobile, password;
    TextView forgetpassword, createid;
    String phone;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    String parameter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forgetpassword = findViewById(R.id.passwordforget);
        login = findViewById(R.id.login);
        createid = findViewById(R.id.createid);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        openHelper = new SQLiteDataBase(this);
        db = openHelper.getReadableDatabase();
        parameter2 = getIntent().getStringExtra("phone");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = mobile.getText().toString();
                String pass = password.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " + SQLiteDataBase.TABLE_NAME +
                        " WHERE " + SQLiteDataBase.COL_1 + " = ? " +
                        "AND " + SQLiteDataBase.COL_2 + " = ? ", new String[]{phone, pass});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, AdminSupport.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Mobile or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });

        createid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RegistrationAdmin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}