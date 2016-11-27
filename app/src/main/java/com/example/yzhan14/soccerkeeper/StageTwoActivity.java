package com.example.yzhan14.soccerkeeper;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StageTwoActivity extends AppCompatActivity implements  FieldFragment.OnFragmentInteractionListener, ButtonList1.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_two);

        //check that the activity is using the fragment_buttons Framelayout
        if (findViewById(R.id.fragment_buttons) != null){

            //but if we are being restored from a previous state,
            //we don'r need to do anything otherwise we could end up overlapping fragments
            if (savedInstanceState != null){
                return;
            }

            //create buttonList1 fragment to be placed in the activity layout
            ButtonList1 firstFrag = new ButtonList1();
            //TODO: may need to pass Intent's extras
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_buttons, firstFrag).commit();
        }
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
        //switch to buttonlist2 fragment
        ButtonList2 newFrag = new ButtonList2();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_buttons, newFrag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
