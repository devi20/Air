package com.phatware.android.recotest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DBHelper dbHelper=new DBHelper(this);
//        dbHelper.insertAction("a",null);
//        dbHelper.insertAction("b",null);
//        dbHelper.insertAction("c",null);

//        ArrayList arrayList=dbHelper.getAllAction();



        System.out.println("byyyy");
    }
}
