package com.google.mediapipe.examples.gesturerecognizer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_DELAY = 3000;
    private Handler mHandler;
    private TextView textViewProgress;
    private String appTitle = "Filipino Sign Language App";
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(getResources().getColor(R.color.blue));

        mHandler = new Handler();
        textViewProgress = findViewById(R.id.textViewProgress);

        animateText();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainMenuActivity.class));
                finish();
            }
        }, SPLASH_DELAY);
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mHandler = new Handler();
        textViewProgress = findViewById(R.id.textViewProgress);

        animateText();

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        boolean isNetworkEnabled = networkInfo != null && networkInfo.isConnected();
        if (!isNetworkEnabled) {
            Toast.makeText(this, "Check data connection, try again", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, SPLASH_DELAY);
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }, SPLASH_DELAY);
        }
    }
     */

    private void animateText() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentIndex <= appTitle.length()) {
                    textViewProgress.setText(appTitle.substring(0, currentIndex));
                    currentIndex++;
                    animateText();
                }
            }
        }, 50); // Adjust delay time as needed for desired animation speed
    }
}
