package com.example.voting.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.voting.R;

public class FeedbackActivity extends AppCompatActivity {
    Button submit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FeedbackActivity.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FeedbackActivity.this,HomeActivity2.class);
                startActivity(intent);
            }
        });


    }
}