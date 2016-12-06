package com.example.yzhan14.soccerkeeper;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonList2 extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    Button player1 = null;
    Button player2 = null;
    Button player3 = null;
    Button player4 = null;
    Button player5 = null;
    Button player6 = null;
    Button player7 = null;
    Button player8 = null;
    Button player9 = null;
    Button player10 = null;
    Button player11 = null;
    ArrayList<Button> buttonslist = null;
    int textid = 0;

    public ButtonList2() {
        // Required empty public constructor
    }
    public static ButtonList2 newInstance(String param1, String param2) {
        ButtonList2 fragment = new ButtonList2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*        if(conditions are met to line up team){
            ArrayList<String> players = readRawTextFile(getContext(),textid);
            for(Button abutton: buttonslist ){
                abutton.setText(players.remove(players.size()-1));
            }

        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_button_list2, container, false);

        final RadioGroup players = (RadioGroup) rootview.findViewById(R.id.playerradiogroup);
        final RadioGroup actions = (RadioGroup) rootview.findViewById(R.id.actionradiogroup);

        //when user clicked the "next" button, selections have been made, back to button list 1
        //TODO: error checking to make sure selected player and action
        Button nextButton = (Button) rootview.findViewById(R.id.nextbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check whether completed the selection and deliver to activity through interface
                int selectedPlayerId = players.getCheckedRadioButtonId();
                int selectedActionId = actions.getCheckedRadioButtonId();
                if ((selectedPlayerId != -1) && (selectedActionId != -1)) {
                    String selectedPlayer = ((RadioButton) rootview.findViewById(selectedPlayerId)).getText().toString();
                    String selectedAction = ((RadioButton) rootview.findViewById(selectedActionId)).getText().toString();
                    mListener.onSelected(selectedPlayer, selectedAction);
                    mListener.backPress();
                }else{
                    //pop error msg as Toast
                    Toast.makeText(getContext(),R.string.list2_error_checking, Toast.LENGTH_SHORT).show();
                }
            }
        });
        textid = R.raw.saints_men;
        buttonslist = new ArrayList<>();
        player1 = (Button)rootview.findViewById(R.id.player1);
        player2 = (Button)rootview.findViewById(R.id.player2);
        player3 = (Button)rootview.findViewById(R.id.player3);
        player4 = (Button)rootview.findViewById(R.id.player4);
        player5 = (Button)rootview.findViewById(R.id.player5);
        player6 = (Button)rootview.findViewById(R.id.player6);
        player7 = (Button)rootview.findViewById(R.id.player7);
        player8 = (Button)rootview.findViewById(R.id.player8);
        player9 = (Button)rootview.findViewById(R.id.player9);
        player10 = (Button)rootview.findViewById(R.id.player10);
        player11 = (Button)rootview.findViewById(R.id.player11);
        buttonslist.add(player11);
        buttonslist.add(player10);
        buttonslist.add(player9);
        buttonslist.add(player8);
        buttonslist.add(player7);
        buttonslist.add(player6);
        buttonslist.add(player5);
        buttonslist.add(player4);
        buttonslist.add(player3);
        buttonslist.add(player2);
        buttonslist.add(player1);

        return rootview;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override

    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void backPress();

        void onSelected(String player, String action);
    }

    //copied from http://stackoverflow.com/questions/4087674/android-read-text-raw-resource-file
    public static ArrayList<String> readRawTextFile(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        ArrayList<String> playerslist = null;

        try {
            while (( line = buffreader.readLine()) != null) {
                playerslist.add(line);

            }
        } catch (IOException e) {
            return null;
        }
        return playerslist;

    }
}
