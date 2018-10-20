package com.boilermake.foodshare.foodshare_boilermake2018;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registerRestaurant extends AppCompatActivity {
    private EditText address;
    private TextView register;
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText city;
    private Button signUp;
    private FirebaseAuth auth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) { //if the user is already logged in, go to main activity
            startActivity(new Intent(registerRestaurant.this, mainPage.class));
            finish();
        }

        register = (TextView) findViewById(R.id.Intro);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
        name = (EditText) findViewById(R.id.NameEditText);
        city = (EditText) findViewById(R.id.City);
        signUp = (Button) findViewById(R.id.signUpBttn);
        address = (EditText) findViewById(R.id.address);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String inputEmail = email.getText().toString();
                final String inputPassword = password.getText().toString();
                final String inputName = name.getText().toString();
                final String inputCity = city.getText().toString();
                final String inputAddress = address.getText().toString();

                try {
                    if (inputEmail.length() > 0 && inputPassword.length() > 0 && inputName.length() > 0 && inputCity.length() > 0) {
                        auth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(registerRestaurant.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(registerRestaurant.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                                } else {
                                    String userID = auth.getCurrentUser().getUid();
                                    DatabaseReference currentUserDB = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(userID);

                                    Map newPost = new HashMap();
                                    newPost.put("name", inputName);
                                    newPost.put("city", inputCity);
                                    newPost.put("address", inputAddress);

                                    currentUserDB.setValue(newPost);

                                    Intent intent = new Intent(registerRestaurant.this, mainPage.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(registerRestaurant.this, "Fill All Fields", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

