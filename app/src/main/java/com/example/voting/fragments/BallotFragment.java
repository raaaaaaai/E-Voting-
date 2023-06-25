package com.example.voting.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.voting.R;
import com.example.voting.activities.DatabaseConnection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class BallotFragment extends Fragment {
    RadioGroup radioGroup;
    RadioButton radioButton0,radioButton1,radioButton2,radioButton3;
    LinearLayout candidate0,candidate1,candidate2,candidate3;
    Button generate_button,submit_button;
    String string;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseConnection databaseConnection;

    String[] url = {"https://cdn.myanimelist.net/s/common/uploaded_files/1437747974-f9e1c043e716c48a369aff764f013735.jpeg",
            "https://qph.cf2.quoracdn.net/main-qimg-60d822f5da4aa9b82c490d68f0fcf135-lq",
            "https://www.postavy.cz/foto/osamu-dazai-foto.jpg",
            "https://cdna.artstation.com/p/media_assets/images/images/001/016/630/medium/Prozess_08.jpg?1667756396"};
    ImageView imageView0,imageView1,imageView2,imageView3;





    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_ballot, container, false);
        imageView0=view.findViewById(R.id.imageView0);
        imageView1=view.findViewById(R.id.imageView1);
        imageView2=view.findViewById(R.id.imageView2);
        imageView3=view.findViewById(R.id.imageView3);
        generate_button=view.findViewById(R.id.generate_button);
        candidate0=view.findViewById(R.id.candidate0);
        candidate1=view.findViewById(R.id.candidate1);
        candidate2=view.findViewById(R.id.candidate2);
        candidate3=view.findViewById(R.id.candidate3);
        radioGroup=view.findViewById(R.id.radioGroup);
        submit_button=view.findViewById(R.id.submit_button);
        radioButton0=view.findViewById(R.id.radiobutton0);
        radioButton1=view.findViewById(R.id.radiobutton1);
        radioButton2=view.findViewById(R.id.radiobutton2);
        radioButton3=view.findViewById(R.id.radiobutton3);
        database=FirebaseDatabase.getInstance("https://e-voting-4544c-default-rtdb.firebaseio.com/");
        reference=database.getReference("Results");


        candidate0.setVisibility(View.INVISIBLE);
        candidate1.setVisibility(View.INVISIBLE);
        candidate2.setVisibility(View.INVISIBLE);
        candidate3.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        submit_button.setVisibility(View.INVISIBLE);

        generate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candidate0.setVisibility(View.VISIBLE);
                candidate1.setVisibility(View.VISIBLE);
                candidate2.setVisibility(View.VISIBLE);
                candidate3.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                generate_button.setVisibility(View.INVISIBLE);
                submit_button.setVisibility(View.VISIBLE);
                Download_Image(view);
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!radioButton0.isChecked() && !radioButton1.isChecked() && !radioButton2.isChecked() && !radioButton3.isChecked()){
                    Toast.makeText(getActivity(), "Choose Something", Toast.LENGTH_SHORT).show();
                }else {
                    Votes();
                    Toast.makeText(getActivity(), "Vote Successfully Casted", Toast.LENGTH_SHORT).show();
                }

            }
        });






        return view;
    }
    int votes0;
    int votes1;
    int votes2;
    int votes3;




    private void Votes() {

        reference.child("Result").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {

                    votes0=(int) snapshot.child("Candidate_1").getValue();;

                    votes1= (int) snapshot.child("Candidate_2").getValue();

                    votes2= (int) snapshot.child("Candidate_3").getValue();

                    votes3= (int) snapshot.child("Candidate_4").getValue();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(radioButton0.isChecked()){
            votes0++;
            reference.child("Candidate_1").setValue(votes0);
        } else if (radioButton1.isChecked()) {
            reference.child("Candidate_2").setValue(votes1+1);
        } else if (radioButton2.isChecked()) {
            reference.child("Candidate_3").setValue(votes2+1);
        } else{
            reference.child("Candidate_4").setValue(votes3+1);
        }

    }

    public class DownloadImage extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap=null;
            URL url;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;

            try {
                url=new URL(strings[0]);
                httpURLConnection= (HttpURLConnection) url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                bitmap= BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            return bitmap;
        }
    }

    public void Download_Image(View view) {
        DownloadImage obj = new DownloadImage();
        DownloadImage obj1 = new DownloadImage();
        DownloadImage obj2= new DownloadImage();
        DownloadImage obj3 = new DownloadImage();
        try {
            string = url[0];
            Bitmap bitmap = obj.execute(string).get();
            imageView0.setImageBitmap(bitmap);


            string = url[1];
            Bitmap bitmap1 = obj1.execute(string).get();
            imageView1.setImageBitmap(bitmap1);


            string = url[2];
            Bitmap bitmap2 = obj2.execute(string).get();
            imageView2.setImageBitmap(bitmap2);


            string = url[3];
            Bitmap bitmap3 = obj3.execute(string).get();
            imageView3.setImageBitmap(bitmap3);


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}