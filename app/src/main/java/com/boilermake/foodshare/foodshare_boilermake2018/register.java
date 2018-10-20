package com.boilermake.foodshare.foodshare_boilermake2018;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        final TextView register = findViewById(R.id.Intro);
        final EditText email = findViewById(R.id.Email);
        final EditText password = findViewById(R.id.Password);
        final EditText name = findViewById(R.id.NameEditText);
        final EditText city = findViewById(R.id.City);

        Button signUp = (Button) findViewById(R.id.signUpBttn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue(register);
                myRef.setValue(email);
                myRef.setValue(password);
                myRef.setValue(name);
                myRef.setValue(city);
            }
        });

    }
}

