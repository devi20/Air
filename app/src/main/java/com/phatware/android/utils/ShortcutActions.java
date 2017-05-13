package com.phatware.android.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.phatware.android.recotest.InternetActivity;
import com.phatware.android.recotest.ListActivity;

/**
 * Created by jmd19 on 5/12/2017.
 */

public class ShortcutActions {

    Context ctx;
    AudioManager myAudioManager;
    private boolean flashflag;
    private CameraManager mCameraManager;
    private String mCameraId;
SharedPreferences.Editor sfe;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShortcutActions(Context ctx){
        this.ctx = ctx;
        flashflag=false;
        sfe=ctx.getSharedPreferences("AirTouch",Context.MODE_PRIVATE).edit();
        myAudioManager = (AudioManager)ctx.getSystemService(Context.AUDIO_SERVICE);
        mCameraManager = (CameraManager) ctx.getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void setfeature(String type, int pos){
        sfe.putInt(type,pos-1);
        sfe.commit();
    }

    public void setflashfeature(String type, boolean pos){
        sfe.putBoolean(type,pos);
        sfe.commit();
    }


    public void select(String action){
            SharedPreferences sf = ctx.getSharedPreferences("AirTouch",Context.MODE_PRIVATE);


        if (action.equals("silent")) {
            silent();
        } else if (action.equals("airplane")) {
            airplane();

        } else if (action.equals("bluetooth")) {
            bluetooth();

        } else if (action.equals("data")) {
            data();

        } else if (action.equals("internet access")) {
            internet();
        } else if (action.equals("lock")) {
            lock();


        } else if (action.equals("flashlight")) {
            flashlight();

        } else if (action.equals("location")) {
            location();

        } else if (action.equals("call")) {
            call();

        } else if (action.equals("settings")) {
            settings();

        } else if (action.equals("vibration")) {
            vibration();

        } else if (action.equals("wifi")) {
            wifi();

        }
        else if (action.equals("installedapps")) {

            installedapps();
        }

    }

    private void internet(){
       /* ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);*/
        Intent intent6 = new Intent(ctx.getApplicationContext(),InternetActivity.class);
        intent6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.getApplicationContext().startActivity(intent6);
    }

    private void installedapps(){
        Intent intent7 = new Intent(ctx.getApplicationContext(),ListActivity.class);
        intent7.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.getApplicationContext().startActivity(intent7);
    }


    private void data(){
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
        ctx.startActivity(intent);
    }

    private void lock(){
        DevicePolicyManager mDPM;
        ComponentName mDeviceAdminSample;
        mDPM = (DevicePolicyManager)ctx.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDPM.lockNow();
    }

    private void flashlight(){
            if (flashflag == false) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mCameraManager.setTorchMode(mCameraId
                                , true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                flashflag = true;
            } else {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mCameraManager.setTorchMode(mCameraId, false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }            flashflag = false;
            }
        }


    private void wifi(){
        ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
    }

    private void vibration(){
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    private void call(){


        Intent intent8=new Intent(Intent.ACTION_DIAL);
        intent8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent8);
    }

    private void location(){
        Intent intent2 = new Intent();
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent2.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        ctx.startActivity(intent2);
    }
    private void bluetooth() {
        Intent intentOpenBluetoothSettings = new Intent();
        intentOpenBluetoothSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentOpenBluetoothSettings.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
        ctx.startActivity(intentOpenBluetoothSettings);
    }

    private void airplane() {
        Intent intent1 = new Intent();
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.setAction(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        ctx.startActivity(intent1);

    }

    private void silent(){
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Toastit("Silent Mode On!");
    }

    private void settings()
    {
        Intent intent3 = new Intent();
        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent3.setAction(Settings.ACTION_SETTINGS);
        ctx.startActivity(intent3);
    }

    private void Toastit(String message){
        Toast.makeText(ctx,message,Toast.LENGTH_SHORT).show();
    }

}
