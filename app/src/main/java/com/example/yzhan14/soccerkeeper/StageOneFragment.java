package com.example.yzhan14.soccerkeeper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StageOneFragment extends Fragment {


    public StageOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_stage_one, container, false);

        //specify the adapter (Home Team/Away Team)
        AutoCompleteTextView homeTeamView = (AutoCompleteTextView) rootView.findViewById(R.id.hometeam_input);
        AutoCompleteTextView awayTeamView = (AutoCompleteTextView) rootView.findViewById(R.id.awayteam_input);

        String[] teams = getResources().getStringArray(R.array.teams_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, teams);
        homeTeamView.setAdapter(adapter);
        awayTeamView.setAdapter(adapter);

        return rootView;
    }

}