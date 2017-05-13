package com.phatware.android.recotest;

/**
 * Created by jmd19 on 5/10/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.phatware.android.model.Gesture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AirTouch.db";
    public static final String AIRTOUCH_TABLE_NAME = "action";
    public static final String AIRTOUCH_COLUMN_ID = "id";
    public static final String AIRTOUCH_COLUMN_SIGN = "sign_name";
    public static final String AIRTOUCH_COLUMN_ACTION = "action_name";
    public static final String TABLE = "contacts";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key AUTOINCREMENT, sign_name text,action_name text )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertAction (String sname, String aname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sign_name", sname);
        contentValues.put("action_name", aname);
        db.insert(TABLE, null, contentValues);
        return true;
    }

    public Gesture getAction(String sign) {
        Gesture gesture = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.query(TABLE, new String[]{"id", "sign_name", "action_name"}, "sign_name=?", new String[]{sign}, null, null, null, null);
        if (res.moveToNext()) {
            gesture = new Gesture(res.getString(res.getColumnIndex("id")), res.getString(res.getColumnIndex("sign_name")), res.getString(res.getColumnIndex("action_name")));
        }

        return gesture;
    }

    public Gesture getData(String id) {
        Gesture gesture = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.query(TABLE, new String[]{"id", "sign_name", "action_name"}, "id=?", new String[]{id}, null, null, null, null);
        if (res.moveToNext()) {
            gesture = new Gesture(res.getString(res.getColumnIndex("id")), res.getString(res.getColumnIndex("sign_name")), res.getString(res.getColumnIndex("action_name")));
        }

        return gesture;
    }

    public List<Gesture> getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Gesture> gestures = new ArrayList<Gesture>();

        Cursor res =  db.query(TABLE, new String[]{"id", "sign_name", "action_name"}, null, null, null, null, null, null);
        while (res.moveToNext()) {
            gestures.add(new Gesture(res.getString(res.getColumnIndex("id")), res.getString(res.getColumnIndex("sign_name")), res.getString(res.getColumnIndex("action_name"))));
        }

        return gestures;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, AIRTOUCH_TABLE_NAME);
        return numRows;
    }

    public boolean updateAcion (String sname, String aname) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("action_name", aname);
            db.update(TABLE, contentValues, "sign_name = ? ", new String[] { sname } );
        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }
        return true;
    }

    public Integer deleteAction (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("action",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

//    public ArrayList<String> getAllAction() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        try(Cursor res =  db.rawQuery( "select * from action", null )) {
//            res.moveToFirst();
//
//            while(res.isAfterLast() == false){
//                array_list.add(res.getString(res.getColumnIndex(AIRTOUCH_COLUMN_ACTION)));
//                res.moveToNext();
//            }
//        }
//        return array_list;
//    }

}