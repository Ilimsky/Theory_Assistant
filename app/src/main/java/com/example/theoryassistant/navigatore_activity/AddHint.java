package com.example.theoryassistant.navigatore_activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
    EditText namedetails, pricedetails, product_details;
    Button btnchoose, btncancel, btnadd;
    final int REQUEST_CODE_GALLERY = 999;
    public static SQLitePhoneDetails sqLitePhoneDetails;
//    public static final int PICK_IMAGE_Gallery = 900;
//    public static final int PICK_IMAGE_CAMERA = 901;
//    private String mCurrentPhotoPath;
//    private Uri selectedImage;
//    int pixels;
//    public Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hint);

        inti();
        validation();
        sqLitePhoneDetails = new SQLitePhoneDetails(this, "PHONEDB.sqlite", null, 1);

        sqLitePhoneDetails.queryData("CREATE TABLE IF NOT EXISTS PHONE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, " +
                "price VARCHAR, " +
                "details VARCHAR);");


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLitePhoneDetails
                        .insertData(namedetails.getText().toString().trim(),
                                pricedetails.getText().toString().trim(),
                                product_details.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                namedetails.setText("");
                pricedetails.setText("");
                product_details.setText("");

            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddHint.this, AdminSupport.class);
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You Don't have Permission to Access file Location! ", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void inti() {
        namedetails = (EditText) findViewById(R.id.namedetails);
        pricedetails = (EditText) findViewById(R.id.pricedetails);
        product_details = (EditText) findViewById(R.id.product_details);
        btnadd = (Button) findViewById(R.id.btnadd);
        btncancel = (Button) findViewById(R.id.btnlist);
    }

    public boolean validation() {
        boolean add = true;
        String name = namedetails.getText().toString();
        String price = pricedetails.getText().toString();
        String details = product_details.getText().toString();
        if (name.equals("")) {
            add = false;
            namedetails.setError("Please Enter Name");
        }
        if (price.equals("")) {
            add = false;
            pricedetails.setError("Please Enter Price of Product");
        }
        if (details.equals("")) {
            add = false;
            product_details.setError("Please Enter Details");
        }
        return add;
    }
}