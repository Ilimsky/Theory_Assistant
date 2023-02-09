package com.example.theoryassistant.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "theory";
    public static final String TABLE_NAME = "admin";
    public static final String COL_1 = "name";
    public static final String COL_2 = "email";
    public static final String COL_3 = "mobile";
    public static final String COL_4 = "address";
    public static final String COL_5 = "password";

    public static final String TABLE_NAME2 = "note";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String DETAILS = "details";


    String CREATE_PROJECT = "CREATE TABLE " + TABLE_NAME +
            " (" + COL_1 + " TEXT," +
            COL_2 + " TEXT," +
            COL_3 + " TEXT," +
            COL_4 + " TEXT," +
            COL_5 + " TEXT" + ")";

    String CREATE_CART = "CREATE TABLE " + TABLE_NAME2 +
            " (" + NAME + " TEXT," +
            PRICE + " TEXT," +
            DETAILS + " TEXT)";

    public SQLiteDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROJECT);
        sqLiteDatabase.execSQL(CREATE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }
}
