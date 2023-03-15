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

    TextView textViewAnswer;
    String ans;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle oldInstanceState) {
        super.onCreate(oldInstanceState);
        setContentView(R.layout.hintclicked_items);
        openHelper = new SQLiteDataBase(this);

        Bundle extras = getIntent().getExtras();
        ans = extras.getString("answer");

        textViewAnswer = findViewById(R.id.textView_answer);
        textViewAnswer.setMovementMethod(new ScrollingMovementMethod());
        textViewAnswer.setText(ans);
    }
}