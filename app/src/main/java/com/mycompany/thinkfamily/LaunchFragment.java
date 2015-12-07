package com.mycompany.thinkfamily;

import android.content.Context;
import android.content.Intent;
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


public class LaunchFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "drive-quickstart";

    public LaunchFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "Analytics fragment created");
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Think Family");
        View view = inflater.inflate(R.layout.fragment_launch, container, false);
        ImageButton capture = (ImageButton) view.findViewById(R.id.imageButton);
        capture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
        return view;
    }

//    private void configureImageButton(){
//
//    }

}
