package com.boilermake.foodshare.foodshare_boilermake2018;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button createAccount;
    private Button login;
    private final String invalidUserInfo = "Invalid username or password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) { //the user is already logged in
                    openLoginSuccessful();
                } //start from the main page
            }
        };

        mUsername = findViewById(R.id.userName); //linking the input username
        mPassword = findViewById(R.id.userPassword); //linking the input password
        createAccount = findViewById(R.id.createAccount); //linking the create account textView
        login = findViewById(R.id.confirmButton); //linking the login button

        createAccount.setOnClickListener(new View.OnClickListener() { //Method on click
            @Override
            public void onClick(View v) {
                openCreateAccountActivity();
            }
        });

        login.setOnClickListener(new View.OnClickListener() { //Method on click
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }

    public void openCreateAccountActivity() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    public void openLoginSuccessful() {
        Intent intent = new Intent(this, mainPage.class);
        startActivity(intent);
    }

    private void startSignIn() {

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (username.length() == 0 || password.length() == 0) {
            Toast.makeText(LoginActivity.this, invalidUserInfo, Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) { //not successfully logged in
                        Toast.makeText(LoginActivity.this, invalidUserInfo, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    } //startSignIn()

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }




}
