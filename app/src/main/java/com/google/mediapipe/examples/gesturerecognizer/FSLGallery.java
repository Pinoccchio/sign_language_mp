package com.google.mediapipe.examples.gesturerecognizer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FSLGallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsl_gallery);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(getResources().getColor(R.color.blue));


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setTitle("Learn FSL");

        String[] signLanguageName = {
                // Alphabet
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y","Z",

                // Words
                "Wala", "At", "Mama", "Nauuhaw ako", "Saan", "Sana", "Tumigil", "Okay", "Okay lang", "Di kita gusto",
                "Long live", "Mabuti", "Mahal kita", "Kamao", "Kanan", "Kapangyarihan", "Hindi mabuti", "Kaliwa", "Bakit", "Eroplano",
                "Hello", "Ako", "Astig", "Taas", "Pababa", "Maganda", "Pagmamahal", "Papa", "Tumahan ka", "Oo", "Tumawag ka", "Pasensya na",

        };
        int[] signLanguageImages = {
                // Alphabet
                R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
                R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
                R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u,
                R.drawable.v, R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z,

                // Words
                R.drawable.wala, R.drawable.at, R.drawable.mama, R.drawable.tubig, R.drawable.saan, R.drawable.sana, R.drawable.tumigil,
                R.drawable.okay, R.drawable.okay_lang, R.drawable.ihateu, R.drawable.longlive, R.drawable.mabuti, R.drawable.mahalkita, R.drawable.kamao,
                R.drawable.kanan, R.drawable.kapangyarihan, R.drawable.hindi_mabuti, R.drawable.kaliwa, R.drawable.bakit, R.drawable.eroplano, R.drawable.hello,
                R.drawable.ako, R.drawable.astig, R.drawable.taas, R.drawable.pababa, R.drawable.maganda, R.drawable.pagmamahal,
                R.drawable.papa, R.drawable.tumahan_ka, R.drawable.oo, R.drawable.tumawag_ka, R.drawable.pasensya_na,
        };

        GridAdapter gridAdapter = new GridAdapter(this, signLanguageName, signLanguageImages);
        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(FSLGallery.this, FullScreenImageActivity.class);
                intent.putExtra(FullScreenImageActivity.EXTRA_IMAGE_RES_ID, signLanguageImages[position]);
                startActivity(intent);
            }
        });


    }
}