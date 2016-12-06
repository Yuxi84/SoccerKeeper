package com.example.yzhan14.soccerkeeper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StageOneActivity extends AppCompatActivity {
    Button start = null;
    EditText title = null;
    EditText hometeam = null;
    EditText awayteam = null;
    EditText weather = null;
    EditText halflength = null;
    EditText time = null;
    EditText location = null;
    Spinner side = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_one);
    }

    @Override
    protected void onStart() {
        super.onStart();
        start = (Button)findViewById(R.id.start_game_button);
        title = (EditText)findViewById(R.id.game_title_input);
        hometeam = (EditText) findViewById(R.id.hometeam_input);
        awayteam = (EditText) findViewById(R.id.awayteam_input);
        weather = (EditText) findViewById(R.id.weather_input);
        halflength = (EditText) findViewById(R.id.half_input);
        time = (EditText) findViewById(R.id.time_input);
        location = (EditText) findViewById(R.id.location_input);
        side = (Spinner) findViewById(R.id.side_input);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sides_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        side.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = null;
                if (title.getText().toString().isEmpty()) {
                    text = "Please enter a Game Title";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    title.requestFocus();

                }
                if (hometeam.getText().toString().isEmpty()) {
                    text = "Please enter a Home Team Name";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    hometeam.requestFocus();
                }
                if (awayteam.getText().toString().isEmpty()) {
                    text = "Please enter a Away Team Name";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    awayteam.requestFocus();
                }
                if (weather.getText().toString().isEmpty()) {
                    text = "Please enter the weather conditions";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    weather.requestFocus();
                }
                if (halflength.getText().toString().isEmpty()) {
                    text = "Please enter a length for halves";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    halflength.requestFocus();
                }
                if (time.getText().toString().isEmpty()) {
                    text = "Please enter the start time of the game";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    time.requestFocus();
                }
                if (location.getText().toString().isEmpty()) {
                    text = "Please enter the field location";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    location.requestFocus();
                }
                if(side.getSelectedItem() == null){
                    text = "Please choose a starting side for the home team";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    side.requestFocus();
                }
                else{

                    Intent intent = new Intent(getBaseContext(),StageTwoActivity.class);
                    intent.putExtra("HOME_TEAM", hometeam.getText().toString());
                    intent.putExtra("AWAY_TEAM", awayteam.getText().toString());
                    intent.putExtra("SIDE",(String)side.getSelectedItem());
                    intent.putExtra("HALF_LENGTH",halflength.getText().toString());
                    intent.putExtra("TIME",time.getText().toString());
                    intent.putExtra("WEATHER", weather.getText().toString());
                    intent.putExtra("LOCATION",location.getText().toString());
                    startActivity(intent);
                }
            }

        });

    }


}
