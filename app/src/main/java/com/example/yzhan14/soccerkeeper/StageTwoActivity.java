package com.example.yzhan14.soccerkeeper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ImageView;
import android.widget.Switch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class StageTwoActivity extends AppCompatActivity
        implements  FieldFragment.OnFragmentInteractionListener,
        ButtonList1.OnFragmentInteractionListener,
        ButtonList2.OnFragmentInteractionListener{





    FieldFragment fieldFrag = null;

    GameDbHelper dbHelper = null;
    SQLiteDatabase db = null;

    private int event_count = 0;
    String[] myEventInfo; // to hold column values (time, position, team, player, action) for an entry

    private static String LOG_TAG = StageTwoActivity.class.getName();

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
    protected void onResume() {
        super.onResume();
        dbHelper = new GameDbHelper(this);
        myEventInfo = new String[5];
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
    public void onPositionTapped(String time, String position) {
        //update eventInfo entry (0-2)
        myEventInfo[0] = time;
        myEventInfo[1] = position;

        ButtonList1 currentButtons = (ButtonList1) getSupportFragmentManager().findFragmentById(R.id.fragment_buttons);
        String attackingTeam = currentButtons.getAttackingTeam();
        myEventInfo[2] = attackingTeam;

        //switch to buttonlist2 fragment
        ButtonList2 newFrag = new ButtonList2();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_buttons, newFrag);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void backPress() {
        onBackPressed();
    }

    @Override
    public void onSelected(String player, String action) {
        //update eventInfo (3-4)
        myEventInfo[3] = player;
        myEventInfo[4] = action;
        event_count++;

        //now eventInfo entry contains all values needed
        //insert into Database
        new insertDB().execute(myEventInfo);
    }

    private class insertDB extends AsyncTask<String[], Void, SQLiteDatabase>{

        @Override
        protected SQLiteDatabase doInBackground(String[]... eventInfos) {
            //TODO: note here even info is the first
            String[] eventInfo = eventInfos[0];
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(EventsContract.EventEntry._ID, event_count);
            values.put(EventsContract.EventEntry.COLUMN_NAME_TIME, eventInfo[0]);
            values.put(EventsContract.EventEntry.COLUMN_NAME_POSITION, eventInfo[1]);
            values.put(EventsContract.EventEntry.COLUMN_NAME_TEAM, eventInfo[2]);
            values.put(EventsContract.EventEntry.COLUMN_NAME_PLAYER, eventInfo[3]);
            values.put(EventsContract.EventEntry.COLUMN_NAME_ACTION, eventInfo[4]);

            //TODO: logcat here to debug
            long rowId = db.insert(EventsContract.EventEntry.TABLE_NAME, null, values);
            if (rowId != -1){
                Log.d(LOG_TAG,"New row added: " + rowId);
            }else{
                Log.d(LOG_TAG, "Something wrong when inserting");
            }
            return  db;
        }

        @Override
        protected void onPostExecute(SQLiteDatabase db) {
            super.onPostExecute(db);
            StageTwoActivity.this.db = db;
        }
    }

}
