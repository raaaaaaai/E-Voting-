package com.example.voting.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voting.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    public Button register_activity,lgn_button;
    private EditText user_cnic,user_password;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_cnic=findViewById(R.id.cnic);
        user_password=findViewById(R.id.password);
        lgn_button=findViewById(R.id.login_Button);
        register_activity = findViewById(R.id.register_activity);
        register_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
        lgn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainActivity(user_cnic,user_password);
            }
        });

    }

    private void toMainActivity(EditText user_name, EditText user_password) {
       /* if (TextUtils.isEmpty((CharSequence) user_name)) {
            Toast.makeText(Login.this, "Invalid email address", Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty((CharSequence) user_password)) {
            Toast.makeText(Login.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();

        }
       */ //Toast.makeText(this, user_cnic.getText().toString(), Toast.LENGTH_SHORT).show();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance("https://e-voting-4544c-default-rtdb.firebaseio.com/").getReference("E-Voting");
//        System.out.println(databaseReference.child("12345").child("cni").getKey().toString());
        databaseReference.child(user_cnic.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {


                    //Toast.makeText(Login.this, snapshot.child("cni").toString()+"   "+snapshot.child("password").toString(), Toast.LENGTH_SHORT).show();
                    String jabba=snapshot.child("cni").getValue().toString();
                    String jabba2=snapshot.child("password").getValue().toString();
                    Log.d("jabba",jabba);
                    Log.d("jabba",jabba2);

                    compareCredentials(jabba,jabba2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void compareCredentials(String cni, String password) {

        if(cni.equals(user_cnic.getText().toString())  && password.equals(user_password.getText().toString()) )
        {
            Intent intent=new Intent(this,HomeActivity2.class);
            startActivity(intent);
        } else if ((user_cnic.getText().toString()).equals("12345") && (user_password.getText().toString()).equals("qwerty")) {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void openRegisterActivity(){
        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);
    }
}