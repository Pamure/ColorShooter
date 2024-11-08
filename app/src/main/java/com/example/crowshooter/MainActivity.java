 package com.example.crowshooter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        int time = 400;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent plane = new Intent(MainActivity.this,home.class);
                startActivity(plane);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, time);
    }
}
