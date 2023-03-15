package com.example.theoryassistant.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.theoryassistant.R;
import com.example.theoryassistant.sqlite.SQLitePhoneDetails;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddHint extends AppCompatActivity {
    EditText editTextQuestion, editTextAnswer;
    Button btncancel, btnadd;
    public static SQLitePhoneDetails sqLitePhoneDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hint);

        init();
        validation();
        sqLitePhoneDetails = new SQLitePhoneDetails(this, "PHONEDB.sqlite", null, 1);

        sqLitePhoneDetails.queryData("CREATE TABLE IF NOT EXISTS PHONE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question VARCHAR, " +
                "answer VARCHAR);");


        btnadd.setOnClickListener(view -> {
            sqLitePhoneDetails
                    .insertData(editTextQuestion.getText().toString().trim(),
                            editTextAnswer.getText().toString().trim());

            Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

            editTextQuestion.setText("");
            editTextAnswer.setText("");
        });

        btncancel.setOnClickListener(view -> {
            Intent intent = new Intent(AddHint.this, AdminSupport.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 999) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 999);
            } else {
                Toast.makeText(getApplicationContext(), "You Don't have Permission to Access file Location! ", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void init() {
        editTextQuestion = (EditText) findViewById(R.id.namedetails);
        editTextAnswer = (EditText) findViewById(R.id.product_details);
        btnadd = (Button) findViewById(R.id.btnadd);
        btncancel = (Button) findViewById(R.id.btnlist);
    }

    public boolean validation() {
        boolean add = true;
        String question = editTextQuestion.getText().toString();
        String answer = editTextAnswer.getText().toString();
        if (question.equals("")) {
            add = false;
            editTextQuestion.setError("Please Enter Question");
        }
        if (answer.equals("")) {
            add = false;
            editTextAnswer.setError("Please Enter Answer");
        }
        return add;
    }
}