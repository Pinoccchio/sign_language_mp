package com.google.mediapipe.examples.gesturerecognizer;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainMenuActivity extends AppCompatActivity {

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;
    private AlertDialog.Builder d_about;

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(getResources().getColor(R.color.blue));

        cardView1 = findViewById(R.id.cardview1);
        cardView2 = findViewById(R.id.cardview2);
        cardView3 = findViewById(R.id.cardview3);
        cardView4 = findViewById(R.id.cardview4);

        d_about = new AlertDialog.Builder(this);

        // Request camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("camera_permission_requested", false)) {
                if (hasCameraPermission()) {

                } else {

                }
            }
        }

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraPermission()) {
                    startActivity(new Intent(MainMenuActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (hasCameraPermission()) {
                    startActivity(new Intent(MainMenuActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }

                 */
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraPermission()) {
                    startActivity(new Intent(MainMenuActivity.this, FSLGallery.class));
                }
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                final View v2 = (View) getLayoutInflater().inflate(R.layout.about, null);

                LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                d_about.setView(v2);
                d_about.create().show();
            }
        });
    }

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }
}
