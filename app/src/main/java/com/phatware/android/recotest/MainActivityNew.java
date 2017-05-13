package com.phatware.android.recotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


public class MainActivityNew extends Activity
{
    private ImageView iv;
@Override
    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_splash);

        new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                    iv=(ImageView)findViewById(R.id.imageView);



                    return;
                }
                catch (InterruptedException localInterruptedException)
                {
                    localInterruptedException.printStackTrace();
                    return;
                }
                finally
                {
                    Intent i = new Intent(MainActivityNew.this, HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    MainActivityNew.this.startActivity(i);
                    MainActivityNew.this.finish();
                }
            }
        }.start();
    }
}
