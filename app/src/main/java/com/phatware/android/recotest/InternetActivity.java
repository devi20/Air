package com.phatware.android.recotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Devi on 5/12/2017.
 */



public class InternetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

    }

    // Launch Google Chrome after clicking the button
    public void launchGoogleChrome(View view) {
        Intent launchGoogleChrome = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        startActivity(launchGoogleChrome);
    }

    // Launch Facebook Application after clicking the button
    public void launchFacebookApplication(View view) {
        Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
        startActivity(launchFacebookApplication);
    }

    // Launch Twitter APplication after clicking the button
    public void launchTwitterApp(View view) {
        Intent launchTwitterApp = getPackageManager().getLaunchIntentForPackage("com.twitter.android");
        startActivity(launchTwitterApp);
    }

    // Launch YouTube App after clicking the button
    public void launchYouTube(View view) {
        Intent launchYouTube = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
        startActivity(launchYouTube);
    }
}