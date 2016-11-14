package com.example.yzhan14.soccerkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StageOneActivity extends AppCompatActivity {
    Button startbutton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_one);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startbutton = (Button)findViewById(R.id.start_game_button);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this,StageTwoActivity.class);
                startActivity(intent);
            }
        });
    }
}
