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


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonList2 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;
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


    public interface OnFragmentInteractionListener{
        void backPress();
        void onSelected(String player, String action);
    }
}
