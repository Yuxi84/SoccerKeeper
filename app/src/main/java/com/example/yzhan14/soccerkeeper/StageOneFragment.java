package com.example.yzhan14.soccerkeeper;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class StageOneFragment extends Fragment {

    GameDbHelper dbHelper = null;
    Button start = null;
    EditText title = null;
    EditText hometeam = null;
    EditText awayteam = null;
    EditText weather = null;
    EditText halflength = null;
    EditText time = null;
    EditText location = null;
    Spinner sideselect = null;

    public StageOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_stage_one, container, false);
        hometeam = (EditText) rootView.findViewById(R.id.hometeam_input);

        awayteam = (EditText) rootView.findViewById(R.id.awayteam_input);
        title = (EditText)rootView.findViewById(R.id.game_title_input);
        weather = (EditText) rootView.findViewById(R.id.weather_input);
        halflength = (EditText) rootView.findViewById(R.id.half_input);
        time = (EditText) rootView.findViewById(R.id.time_input);
        location = (EditText) rootView.findViewById(R.id.location_input);
        sideselect = (Spinner) rootView.findViewById(R.id.side_input);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getContext(),
                R.array.sides_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sideselect.setAdapter(adapter1);
        //specify the adapter (Home Team/Away Team)
        AutoCompleteTextView homeTeamView = (AutoCompleteTextView) rootView.findViewById(R.id.hometeam_input);
        AutoCompleteTextView awayTeamView = (AutoCompleteTextView) rootView.findViewById(R.id.awayteam_input);

        Button startGameButton = (Button) rootView.findViewById(R.id.start_game_button);

        String[] teams = getResources().getStringArray(R.array.teams_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, teams);
        homeTeamView.setAdapter(adapter);
        awayTeamView.setAdapter(adapter);

        //click start_game_button to go to stage II
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";
                Context context = getContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = null;
                if (title.getText().toString().isEmpty()) {
                    text = "Please enter a Game Title";
                    toast = Toast.makeText(context,text,duration);
                    toast.show();
                    title.requestFocus();

                }
                else{
                    if (hometeam.getText().toString().isEmpty()) {
                        text = "Please enter a Home Team Name";
                        toast = Toast.makeText(context,text,duration);
                        toast.show();
                        hometeam.requestFocus();
                    }

                    else{
                        if (awayteam.getText().toString().isEmpty()) {
                            text = "Please enter a Away Team Name";
                            toast = Toast.makeText(context,text,duration);
                            toast.show();
                            awayteam.requestFocus();
                        }
                        else{
                            if (weather.getText().toString().isEmpty()) {
                                text = "Please enter the weather conditions";
                                toast = Toast.makeText(context,text,duration);
                                toast.show();
                                weather.requestFocus();
                            }
                            else{
                                if (halflength.getText().toString().isEmpty()) {
                                    text = "Please enter a length for halves";
                                    toast = Toast.makeText(context,text,duration);
                                    toast.show();
                                    halflength.requestFocus();
                                }
                                else{
                                    if (time.getText().toString().isEmpty()) {
                                        text = "Please enter the start time of the game";
                                        toast = Toast.makeText(context,text,duration);
                                        toast.show();
                                        time.requestFocus();
                                    }
                                    else{
                                        if (location.getText().toString().isEmpty()) {
                                            text = "Please enter the field location";
                                            toast = Toast.makeText(context,text,duration);
                                            toast.show();
                                            location.requestFocus();
                                        }
                                        else{
                                            if (sideselect.getSelectedItem()==null) {
                                                text = "Please select a start side for the home team";
                                                toast = Toast.makeText(context,text,duration);
                                                toast.show();
                                                sideselect.requestFocus();
                                            }
                                            else{
                                                Intent intent = new Intent(getActivity(), StageTwoActivity.class);
                                                intent.putExtra("HOME",hometeam.getText().toString());
                                                intent.putExtra("AWAY",awayteam.getText().toString());
                                                intent.putExtra("TITLE",title.getText().toString());
                                                //intent.putExtra("SIDE",sideselect.getSelectedItem().toString());

                                                getActivity().startActivity(intent);
                                                new deleteDB().execute();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }









            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        dbHelper = new GameDbHelper(getContext());

    }

    private class deleteDB extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete(EventsContract.EventEntry.TABLE_NAME, null,null);
            return null;
        }
    }
}
