package com.example.voting.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.voting.R;
import com.example.voting.RecyclerAdapter;
import com.example.voting.Vote;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<Vote> votes;
    private Button fetchButton;

    FirebaseDatabase database;

    DatabaseReference reference;
    DatabaseConnection databaseConnection;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        votes = new ArrayList<>();
        adapter = new RecyclerAdapter(votes);
        recyclerView.setAdapter(adapter);
        databaseConnection=DatabaseConnection.createConnection();

        fetchButton = findViewById(R.id.result_button);



        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchVotesFromFirebase();
            }
        });
    }

    private void fetchVotesFromFirebase() {
        databaseConnection.getFirebaseDatabase().getReference("Results")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        votes.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            int voteCount = snapshot.getValue(Integer.class);
                          //  @SuppressLint("RestrictedApi") String path = snapshot.getChildren().toString();
                            Vote vote = new Vote(voteCount);
                            votes.add(vote);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(HomeActivity.this, "Failed to fetch votes: " + databaseError.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


}