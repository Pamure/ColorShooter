package com.pam.minds.crashplanes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class home extends AppCompatActivity {
    private String fab="Settings ";
    private String fab2 ="Sound On Off";
    FloatingActionButton play;
    ImageView homies;
    Button img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView banner_ad = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner_ad.loadAd(adRequest);
        img= findViewById(R.id.img_mode);
        homies=findViewById(R.id.imageView);
        homies.setAdjustViewBounds(true);
        homies.setBaselineAlignBottom(true);homies.setImageResource(R.drawable.home);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,"Work in Progress",Toast.LENGTH_LONG).show();
            }
        });
        play =  findViewById(R.id.play_button);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setEnabled(false);
                Intent play = new Intent(home.this , planes.class);
                startActivity(play);
            }
        });
        FloatingActionButton opt1 =  findViewById(R.id.fab);
        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,fab, Toast.LENGTH_LONG).show();
                //TODO
            }
        });
        FloatingActionButton opt2 =  findViewById(R.id.fab2);
        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this,fab2, Toast.LENGTH_LONG).show();
                //TODO
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        play.setEnabled(true);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        play.setEnabled(true);
    }
}