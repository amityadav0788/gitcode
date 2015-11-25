package com.ngo.binappsync;

/**
 * Created by A_K_Yadav on 9/30/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.prgguru.example.R;
import android.util.Log;

public class DBController  extends SQLiteOpenHelper {

    private static final String TAG = "DBController";
    public DBController(Context applicationcontext) {
        super(applicationcontext, "bin.db", null, 1);
    }
    //Creates Table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE bins ( binId INTEGER, Fill INTEGER)";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS bins";
        database.execSQL(query);
        onCreate(database);
    }

    /**
     * Inserts User into SQLite DB
     * @param queryValues
     */
    public void insertUser(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("binId", queryValues.get("Id"));
        values.put("Fill", queryValues.get("Fill"));
        database.insert("bins", null, values);
        database.close();
    }

    /**
     * Get list of Users from SQLite DB as Array List
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllUsers() {
        ArrayList<HashMap<String, String>> usersList;
        usersList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM bins";
        Log.d(TAG,"hehehe in DBController get all users query "+ selectQuery);
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("binId", cursor.getString(0));
                map.put("Fill", cursor.getString(1));
                usersList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Log.d(TAG, "hehehe in DBController get all users list " + usersList.toString());
        return usersList;
    }

}