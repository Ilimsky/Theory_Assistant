package com.example.theoryassistant.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLitePhoneDetails extends SQLiteOpenHelper {

    public SQLitePhoneDetails(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String question, String answer) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = " INSERT INTO PHONE VALUES (NULL, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, question);
        statement.bindString(2, answer);
        statement.executeInsert();
    }

    public int CountData() {
        int dataCount = 0;
        String sql = "SELECT COUNT(*) FROM PHONE";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            dataCount = cursor.getInt(0);
        }
        return dataCount;
    }

    public void updateData(String question, String answer, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE PHONE SET question = ?, answer = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, question);
        statement.bindString(2, answer);
        statement.bindDouble(3, (double) id);

        statement.execute();
        database.close();
    }

    public void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM PHONE WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);
        statement.execute();
        database.close();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}