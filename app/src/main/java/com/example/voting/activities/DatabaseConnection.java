package com.example.voting.activities;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConnection {//its a singleton class for database connection
    static FirebaseDatabase firebaseDatabase;
    static DatabaseReference databaseReference;
    public static DatabaseConnection databaseConnection;
    DatabaseConnection()
    {}
    public static DatabaseConnection createConnection()
    {
        if(databaseConnection==null) {
            firebaseDatabase = FirebaseDatabase.getInstance("https://e-voting-4544c-default-rtdb.firebaseio.com/");
            databaseReference = firebaseDatabase.getReference("E-Voting");
            databaseConnection=new DatabaseConnection();
        }
        return databaseConnection;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public FirebaseDatabase getFirebaseDatabase()
    {
        return firebaseDatabase;
    }
}
