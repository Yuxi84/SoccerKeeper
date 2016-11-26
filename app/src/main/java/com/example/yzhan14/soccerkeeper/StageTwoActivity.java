package com.example.yzhan14.soccerkeeper;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StageTwoActivity extends AppCompatActivity implements  FieldFragment.OnFragmentInteractionListener, ButtonList1.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_two);
    }
    @Override
    public void onStartGame(boolean toStart) {
        //to start the chronometer
        FieldFragment fieldFrag = (FieldFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_field);
        //since in landscape, so fieldFrag should not be null
        if (toStart) {
            fieldFrag.startChronometer();
        }else{
            fieldFrag.stopChronometer();
        }

    }

    @Override
    public void onPositionTapped() {

    }
}
