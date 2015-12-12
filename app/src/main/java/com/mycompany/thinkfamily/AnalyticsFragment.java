package com.mycompany.thinkfamily;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class AnalyticsFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "drive-quickstart";

    public AnalyticsFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "Analytics fragment created");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("How's Mom Doing?");
        View view = inflater.inflate(R.layout.fragment_analytics, container, false);
        return view;
    }

}