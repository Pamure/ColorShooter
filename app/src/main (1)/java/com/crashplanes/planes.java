package com.pam.minds.crashplanes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;

public class planes extends AppCompatActivity implements View.OnClickListener {
    private GameView cView,dView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planes);

        cView =  findViewById(R.id.left_view);
        dView =   findViewById(R.id.right_view);
        ImageButton shootButton = (ImageButton) findViewById(R.id.shootButton);
        shootButton.setOnClickListener(this);
        ImageButton Button = (ImageButton) findViewById(R.id.Button);
        Button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.shootButton) {
            cView.shootCannon();
        }
        else if (v.getId() == R.id.Button){dView.shootCannon();}
    }
}
