package com.google.mediapipe.examples.gesturerecognizer;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class FullScreenImageActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_RES_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(getResources().getColor(R.color.blue));


        int imageResId = getIntent().getIntExtra(EXTRA_IMAGE_RES_ID, 0);


        ImageView imageView = findViewById(R.id.full_screen_image_view);
        imageView.setImageResource(imageResId);
    }
}
