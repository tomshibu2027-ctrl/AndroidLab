package com.example.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "UserDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE Users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "uname TEXT)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
    }
    public boolean addName(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uname", name);
        long result = db.insert("Users", null, values);
        return result != -1;
    }
    public String getAllNames() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT uname FROM Users", null);
        StringBuilder names = new StringBuilder();
                try {
            while (cursor.moveToNext()) {
                names.append(cursor.getString(0)).append("\n");
            }
        } finally {
            cursor.close();
        }
        if (names.length() == 0) {
            return "No names found";
        }
        return names.toString();
    }
}