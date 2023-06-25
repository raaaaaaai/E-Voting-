package com.example.voting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voting.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private Button login_activity,register;
    private EditText name,email,password,confirm_password,cnc;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseConnection databaseConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login_activity=findViewById(R.id.login_activity);
        name=findViewById(R.id.name);
        cnc=findViewById(R.id.cnic);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirm_password);
        register=findViewById(R.id.register_Button);

        firebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance("https://e-voting-4544c-default-rtdb.firebaseio.com/");
        reference=database.getReference("E-Voting");
        databaseConnection= new DatabaseConnection();

        login_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=name.getText().toString().trim();
                String cn=cnc.getText().toString().trim();
                String em=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String c_pass=confirm_password.getText().toString().trim();

                if(!TextUtils.isEmpty(nm) && !TextUtils.isEmpty( cn) && !TextUtils.isEmpty(em) && Patterns.EMAIL_ADDRESS.matcher(em).matches() && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(c_pass) && pass.equals(c_pass)){
                    Users obj = new Users(nm,cn,em,pass);
                    reference.child(cn).setValue(obj);

                    createUser(cn,pass);
                }else{
                    Toast.makeText(SignUp.this, "Fill All The Credentials and your confirm password should be equal to password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void createUser(String cn, String pass) {
        // Validate email and password
        if (TextUtils.isEmpty(cn)) {
            Toast.makeText(SignUp.this, "Invalid CNIC", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(SignUp.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(cn, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                    Toast.makeText(SignUp.this, "Failed. Error: " + errorCode, Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this, "Failed. Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void openLoginActivity(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }


}