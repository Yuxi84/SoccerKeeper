package com.example.yzhan14.soccerkeeper;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class StageTwoActivity extends AppCompatActivity implements  FieldFragment.OnFragmentInteractionListener, ButtonList1.OnFragmentInteractionListener{

    FieldFragment fieldFrag = null;

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
    //TODO: stack over flow code
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            //TODO: why super.onBackPressed() is called but not this one
            getFragmentManager().popBackStack();

        } else {
            super.onBackPressed();
            findViewById(R.id.image_field).setEnabled(true);
        }
    }
    @Override
    public void onStartGame(boolean toStart) {
        //to start the chronometer
        fieldFrag = (FieldFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_field);
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
