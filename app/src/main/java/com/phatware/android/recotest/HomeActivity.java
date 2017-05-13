package com.phatware.android.recotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.WaveDetector;
import com.phatware.android.model.Gesture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devi on 5/11/2017.
 */

public class HomeActivity extends Activity {
    GridView gridView1;
    List<Gesture> gestures = new ArrayList<Gesture>();
    CustomGridViewAdapter1 customGridAdapter1;
    Button button;
    DBHelper dbHelper;

    @Override
    protected void onResume() {
        super.onResume();
        gestures = dbHelper.getAllData();
        customGridAdapter1 = new CustomGridViewAdapter1(this, com.phatware.android.recotest.R.layout.content_homescreen, gestures);
        gridView1.setAdapter(customGridAdapter1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.phatware.android.recotest.R.layout.activity_homescreen);
        dbHelper = new DBHelper(this);
        gestures = dbHelper.getAllData();
        if(gestures.size()==0)
        {
            String[] s = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4",
                    "5","6","7","8","9","!","@","#","$"};

            for(int i=0; i<s.length; i++){
                dbHelper.insertAction(s[i],"");
            }

        }

        gridView1 = (GridView) findViewById(com.phatware.android.recotest.R.id.gridView2);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, ActionGridActivity.class);
                intent.putExtra("sign",gestures.get(i).getSign());
                startActivity(intent);
            }
        });

        //Service Start
//        startService(new Intent(this, ProximityService.class));

        Sensey.getInstance().init(getApplicationContext());
        WaveDetector.WaveListener waveListener=new WaveDetector.WaveListener() {
            @Override public void onWave() {
                // Wave of hand gesture detected
                Intent panel = new Intent(HomeActivity.this, RecoTest.class);
                panel.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(panel);
            }
        };
        Sensey.getInstance().startWaveDetection(waveListener);



    }


}