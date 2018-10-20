package com.boilermake.foodshare.foodshare_boilermake2018;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    private TextView register;
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText city;
    private Button signUp;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        register = findViewById(R.id.Intro);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        name = findViewById(R.id.NameEditText);
        city = findViewById(R.id.City);
        signUp = findViewById(R.id.signUpBttn);

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

