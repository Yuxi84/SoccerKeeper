package com.example.yzhan14.soccerkeeper;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private AlertDialog aboutDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        //handles two buttons "start", "about" here
        Button startButton = (Button) rootView.findViewById(R.id.start_button);
        final Button aboutButton = (Button) rootView.findViewById(R.id.about_button);

        //click handler for start_button, which leads to STAGE I activity
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StageOneActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //aboutButton brings up info dialog
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.aboutMsg);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.about_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //nothing
                    }
                });
                aboutDialog = builder.show();
            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        //get rid of the about dialog id it is still up
        if (aboutDialog != null){
            aboutDialog.dismiss();
        }
    }
}
