package com.example.voting.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.voting.R;
import com.example.voting.activities.FeedbackActivity;
import com.example.voting.activities.Login;


public class HomeFragment extends Fragment {
    Button logout_button,feedback_button;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        logout_button=view.findViewById(R.id.logout_button);
        feedback_button=view.findViewById(R.id.Feedback_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });

        feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });


        return  view;
    }
}