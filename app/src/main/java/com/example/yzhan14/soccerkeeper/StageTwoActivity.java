package com.example.yzhan14.soccerkeeper;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class StageTwoActivity extends AppCompatActivity
        implements  FieldFragment.OnFragmentInteractionListener,
        ButtonList1.OnFragmentInteractionListener,
        ButtonList2.OnFragmentInteractionListener, StatsFragment.OnFragmentInteractionListener{

    private final static int REQUEST_CODE = 999;

    ArrayList<Button> buttonslist = new ArrayList<Button>();

    String homename = null;
    String awayname = null;
    Boolean teamver = false;
    String title = null;
    Integer shotshome = null;
    Integer shotsaway = null;
    Integer shotsontargethome = null;
    Integer shotsontargetaway = null;
    Integer cornershome = null;
    Integer cornersaway = null;
    Integer foulshome = null;
    Integer foulsaway = null;
    Integer fhshotholder = null;
    Integer fashotholder = null;
    Integer fhsotholder = null;
    Integer fasotholder = null;
    Integer fhcholder = null;
    Integer facholder = null;
    Integer fhfholder = null;
    Integer fafholder = null;
    Integer phhome = null;
    Integer phaway = null;
    Boolean isHalf = false;

    View StageThree = null;
    View StageFour = null;
    TextView gtitle3 = null;
    TextView gtitle4= null;

    //keeps track of possession for both teams in mins
    int possessionHome = 0;
    int possessionAway = 0;
    int updateMins = 0;//total elapsed time

    StatsFragment firsthalfstat = null;
    StatsFragment secondhalfstat = null;
    StatsFragment sumstat = null;

    HashMap<String,String> namemap = new HashMap<String, String>();

    FieldFragment fieldFrag = null;

    GameDbHelper dbHelper = null;
    SQLiteDatabase db = null;

    private int event_count = 0;
    String[] myEventInfo; // to hold column values (time, position, team, player, action) for an entry

    private static String LOG_TAG = StageTwoActivity.class.getName();

    //TODO
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_two);

        //check and request for permissions to access external storage
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
        homename = getIntent().getStringExtra("HOME");
        awayname = getIntent().getStringExtra("AWAY");
        title = getIntent().getStringExtra("TITLE");
        shotshome = 0;
        shotsaway = 0;
        shotsontargethome = 0;
        shotsontargetaway = 0;
        cornershome = 0;
        cornersaway = 0;
        foulshome = 0;
        foulsaway = 0;
        fhshotholder = 0;
        fashotholder = 0;
        fhsotholder = 0;
        fasotholder = 0;
        fhcholder = 0;
        facholder = 0;
        fhfholder = 0;
        fafholder = 0;
        phhome = 0;
        phaway = 0;
        namemap.put("Team 1", homename);
        namemap.put("Team 2", awayname);
        StageThree = findViewById(R.id.activity_stage_three);
        StageFour = findViewById(R.id.activity_stage_four);

        gtitle3 = (TextView)StageThree.findViewById(R.id.game_title1);
        gtitle4 = (TextView)StageFour.findViewById(R.id.game_title2);
        gtitle3.setText(title);
        gtitle4.setText(title);

        firsthalfstat = (StatsFragment) getSupportFragmentManager().findFragmentById(R.id.table_fragment);
        secondhalfstat = (StatsFragment) getSupportFragmentManager().findFragmentById(R.id.table_fragment1);
        sumstat = (StatsFragment) getSupportFragmentManager().findFragmentById(R.id.table_fragment2);
        firsthalfstat.setSomething(homename,10);
        secondhalfstat.setSomething(homename,10);
        firsthalfstat.setSomething(awayname,11);
        secondhalfstat.setSomething(awayname,11);
        sumstat.setSomething(homename,10);
        sumstat.setSomething(awayname,11);
        for(int i =0; i<7;i++){
            firsthalfstat.setSomething("0",i);
            secondhalfstat.setSomething("0",i);
            sumstat.setSomething("0",i);
        }





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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE)
            if (!(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                Log.e(LOG_TAG, "permission not granted to read data base");
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
    public void onExportData() {
        new ExportDataTask(this, dbHelper.DATABASE_NAME).execute();
    }

    @Override
    public void onActionButtonsTapped(String team, String action) {
        //handling click events for four buttons on top of buttonlist1

        //get time from fieldFragment
        FieldFragment fieldFrag = (FieldFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_field);
        String time = fieldFrag.getTime();

        //update eventInfo entry
        myEventInfo[0] = time;
        myEventInfo[1] = " ";
        myEventInfo[2] = team;
        myEventInfo[3] = " ";
        myEventInfo[4] = action;
        event_count ++;
        new insertDB().execute(myEventInfo);

    }

    @Override
    public void onAddPossession(int team) {
        //read elapsed time in minutes from chronometer to get elapsed time
        FieldFragment fieldFrag = (FieldFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_field);
        int elapsedTotal = fieldFrag.getElapsedMin();
        int minsToAdd = elapsedTotal-updateMins;
        updateMins=elapsedTotal;

        //decide add mins to which team
        if (team == 1){
            //to home
            possessionHome +=minsToAdd;
        }else{
            if (team == 2){
                possessionAway +=minsToAdd;
            }
        }

        updateShots("a",5);
    }

    @Override
    public void onPositionTapped(String time, String position) {
        //update eventInfo entry (0-2)
        myEventInfo[0] = time;
        myEventInfo[1] = position;
        ButtonList1 currentButtons = (ButtonList1) getSupportFragmentManager().findFragmentById(R.id.fragment_buttons);
        //It looks like the middle of the field is anywhere from x = 709 to x = 714,
        // I think we can use this to determine which side has been clicked
        // Or your way is cool, too, but we need to flip the sides and make sure below doesnt bug
        String attackingTeam = currentButtons.getAttackingTeam();
        myEventInfo[2] = attackingTeam;

        //switch to buttonlist2 fragment
        ButtonList2 newFrag = new ButtonList2();
        Bundle bundle = new Bundle();
        if(namemap.get(attackingTeam) != null && namemap.get(attackingTeam).equals("Saints men")){
            teamver = true;
        }
        bundle.putBoolean("SETNAMES",teamver);
        bundle.putString("ATEAM",attackingTeam);
        newFrag.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_buttons, newFrag);
        transaction.addToBackStack(null);
        transaction.commit();
        teamver = false;

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

    public void startThree() {
        View thinkView = findViewById(R.id.activity_stage_three);
        thinkView.setVisibility(View.VISIBLE);
    }

    public void stopThree() {
        View thinkView = findViewById(R.id.activity_stage_three);
        thinkView.setVisibility(View.GONE);
    }

    public void startFour() {
        View thinkView = findViewById(R.id.activity_stage_four);
        thinkView.setVisibility(View.VISIBLE);
    }

    public void stopFour() {
        View thinkView = findViewById(R.id.activity_stage_four);
        thinkView.setVisibility(View.GONE);
    }

    public void updateShots(String teamname, int actionid) {
        StatsFragment aFragment = !isHalf ? firsthalfstat : secondhalfstat;

        if(teamname.equals("Team 1") && actionid == 1){
            shotshome += 1;
            aFragment.setSomething(Integer.toString(shotshome),0);
            sumstat.setSomething(Integer.toString(shotshome+fhshotholder),0);

        }
        if(teamname.equals("Team 1") && actionid == 2){
            shotsontargethome += 1;
            aFragment.setSomething(Integer.toString(shotsontargethome),2);
            sumstat.setSomething(Integer.toString(shotsontargethome+ fhsotholder),2);
        }
        if(teamname.equals("Team 2") && actionid == 1){
            shotsaway += 1;
            aFragment.setSomething(Integer.toString(shotsaway),1);
            sumstat.setSomething(Integer.toString(shotsaway + fashotholder),1);
        }
        if(teamname.equals("Team 1") && actionid == 3){
            cornershome += 1;
            aFragment.setSomething(Integer.toString(cornershome),4);
            sumstat.setSomething(Integer.toString(cornershome + fhcholder),4);
        }
        if(teamname.equals("Team 2") && actionid == 3){
            cornersaway += 1;
            aFragment.setSomething(Integer.toString(cornersaway),5);
            sumstat.setSomething(Integer.toString(cornersaway +facholder),5);
        }
        if(teamname.equals("Team 1") && actionid == 4){
            foulshome += 1;
            aFragment.setSomething(Integer.toString(foulshome),6);
            sumstat.setSomething(Integer.toString(foulshome+fhfholder),6);
        }
        if(teamname.equals("Team 2") && actionid == 4){
            foulsaway += 1;
            aFragment.setSomething(Integer.toString(foulsaway),7);
            sumstat.setSomething(Integer.toString(foulsaway + fafholder),7);
        }
        if(teamname.equals("Team 2") && actionid ==2){
            shotsontargetaway += 1;
            aFragment.setSomething(Integer.toString(shotsontargetaway),3);
            sumstat.setSomething(Integer.toString(shotsontargetaway + fasotholder),3);
        }
        if(actionid == 5){
            int posperc1 = (int)((double)possessionHome  / ((possessionHome + possessionAway)) * 100);
            int posperc2 = (int)((double) possessionAway  / (possessionHome + possessionAway) * 100);
            int posperc3 = (int)((double)(possessionHome + phhome) / (possessionHome + phhome + phaway + possessionAway) * 100);
            int posperc4 = (int)((double)(possessionAway  + phaway) / (possessionHome + phhome + phaway + possessionAway) *100);
            aFragment.setSomething(Integer.toString(posperc1)+"%", 8);
            aFragment.setSomething(Integer.toString(posperc2)+"%", 9);
            sumstat.setSomething(Integer.toString(posperc3)+"%",8);
            sumstat.setSomething(Integer.toString(posperc4)+"%",9);
        }


    }

    public void clearNums(){
        fhshotholder = shotshome;
        fashotholder = shotsaway;
        fhsotholder = shotsontargethome;
        fasotholder = shotsontargetaway;
        fhcholder = cornershome;
        facholder = cornersaway;
        fhfholder = foulshome;
        fafholder =foulsaway;
        phhome = possessionHome;
        phaway = possessionAway;
        possessionHome = 0;
        possessionAway = 0;
        updateMins = 0;
        shotshome = 0;
        shotsaway = 0;
        shotsontargethome = 0;
        shotsontargetaway = 0;
        cornershome = 0;
        cornersaway = 0;
        foulshome = 0;
        foulsaway = 0;
    }

    public void updateHalf(){
        isHalf = !isHalf;
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
            Toast.makeText(StageTwoActivity.this, "data added", Toast.LENGTH_SHORT).show();
        }
    }

}
