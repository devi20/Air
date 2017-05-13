package com.phatware.android.recotest;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jmd19 on 5/12/2017.
 */

public class ProximityService extends Service implements SensorEventListener {

    Sensor proxSensor;
    SensorManager sm;
    private static final int SENSOR_SENSITIVITY = 4;

    int flag = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        proxSensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                flag=0;
            }
        }, 0, 3000);

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                //near
                flag++;
            } else {
                //far
//                flag++;
            }
            if(flag==2)
            {
                Intent panel = new Intent(this, RecoTest.class);
                panel.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(panel);
            }
        }

    }
    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
    }
}
