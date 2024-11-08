package com.pam.minds.crashplanes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Intent inten = getIntent();
        int final_score=inten.getIntExtra("key",GameView.score);
        TextView scoredd = findViewById(R.id.textView4);
        String scored = "You Scored ! "+final_score;
        scoredd.setText(scored);
    }
    public void OverHome(View v)
    {
        Intent Home=new Intent(GameOver.this,home.class);
        startActivity(Home);
        finish();
    }
}
