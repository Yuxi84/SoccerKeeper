package com.example.yzhan14.soccerkeeper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView homeshotstat = null;
    TextView awayshotstat = null;
    TextView homesotstat = null;
    TextView awaysotstat = null;
    TextView homecstat = null;
    TextView awaycstat = null;
    TextView homefstat = null;
    TextView awayfstat = null;
    TextView homepstat = null;
    TextView awaypstat = null;
    TextView htitle = null;
    TextView awtitle = null;
    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
        View rootview = inflater.inflate(R.layout.fragment_stats, container, false);

        homeshotstat = (TextView)rootview.findViewById(R.id.hteamshot);
        awayshotstat = (TextView)rootview.findViewById(R.id.awteamshot);
        homesotstat = (TextView)rootview.findViewById(R.id.hteamshotot);
        awaysotstat = (TextView)rootview.findViewById(R.id.awteamshotot);
        homecstat = (TextView)rootview.findViewById(R.id.hteamcorn);
        awaycstat = (TextView)rootview.findViewById(R.id.awteamcorn);
        homefstat = (TextView)rootview.findViewById(R.id.hteamfree);
        awayfstat = (TextView)rootview.findViewById(R.id.awteamfree);
        homepstat = (TextView)rootview.findViewById(R.id.hteampos);
        awaypstat = (TextView)rootview.findViewById(R.id.awteampos);
        htitle = (TextView)rootview.findViewById(R.id.hometeamtitle);
        awtitle = (TextView)rootview.findViewById(R.id.awteamtitle);

        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
 /*   public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/
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

    public void setSomething(String value, int code){
        if(code == 0){
            homeshotstat.setText(value);
        }
        if(code == 1){
            awayshotstat.setText(value);
        }
        if(code == 2){
            homesotstat.setText(value);
        }
        if(code == 3){
            awaysotstat.setText(value);
        }
        if(code == 4){
            homecstat.setText(value);
        }
        if(code == 5){
            awaycstat.setText(value);
        }
        if(code == 6){
            homefstat.setText(value);
        }
        if(code == 7){
            awayfstat.setText(value);
        }
        if(code == 8){
            homepstat.setText(value);
        }
        if(code == 9){
            awaypstat.setText(value);
        }
        if(code == 10){
            htitle.setText(value);
        }
        if(code==11){
            awtitle.setText(value);
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

    }
}
