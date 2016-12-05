package com.example.yzhan14.soccerkeeper;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link FieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FieldFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Chronometer myChronometer = null;

    private static final String DEBUG_TAG = "Gestures";


    public FieldFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FieldFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FieldFragment newInstance(String param1, String param2) {
        FieldFragment fragment = new FieldFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.field_fragment, container, false);
        //find chronometer
        myChronometer = (Chronometer) rootView.findViewById(R.id.my_chronometer);
        //create gesture detector
        ImageView fieldView = (ImageView) rootView.findViewById(R.id.image_field);
        fieldView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                int action = MotionEventCompat.getActionMasked(e);
                if (action == e.ACTION_DOWN) {
                    //get the absolute coordinates of tapped postion
                    int x = (int) e.getRawX();
                    int y = (int) e.getRawY();
                    Point coord = new Point (x,y);
                    String position = coord.toString();

                    //get the time
                    String time = myChronometer.getText().toString();

                    mListener.onPositionTapped(time, position);//ask stageII acitivity to switch fragment
                    //TODO: which is the better effect
                    view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.image_tap));
                    view.setEnabled(false);
                    Log.d(DEBUG_TAG, "OnTouch" + e.toString());
                }
                return true;
            }
        });
        return rootView;
    }




    // TODO: Rename method, update argument and hook method into UI event
/*    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPositionTapped(String time, String position);
    }

    public void startChronometer(){
        myChronometer.setBase(SystemClock.elapsedRealtime());
        myChronometer.start();
    }
    public void stopChronometer(){
        myChronometer.stop();
    }
}
