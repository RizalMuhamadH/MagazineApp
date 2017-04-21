package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.lumiere.user.magazineapp.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static int splashInterval = 2000;
    private Thread splashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        startSplashScreen();
    }
    private void startSplashScreen(){
        splashThread = new Thread(){
            @Override
            public void run() {
                try {
                    int waited = 0;

                    while (waited < splashInterval){
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                }catch (InterruptedException e){
                    Log.e("run: ", e.getMessage());
                }finally {
                    SplashScreenActivity.this.finish();
                }
            }
        };
        splashThread.start();
    }
}
