package com.example.yzhan14.soccerkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StageOneActivity extends AppCompatActivity {
    Button start = null;
    EditText hometeam = null;
    EditText awayteam = null;
    EditText weather = null;
    EditText halflength = null;
    EditText time = null;
    EditText location = null;
    EditText referee = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_one);
    }

    @Override
    protected void onStart() {
        super.onStart();
        start = (Button)findViewById(R.id.start_game_button);
        hometeam = (EditText) findViewById(R.id.hometeam_input);
        awayteam = (EditText) findViewById(R.id.awayteam_input);
        weather = (EditText) findViewById(R.id.weather_input);
        halflength = (EditText) findViewById(R.id.half_input);
        time = (EditText) findViewById(R.id.time_input);
        location = (EditText) findViewById(R.id.location_input);
        referee = (EditText) findViewById(R.id.referee_input);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
