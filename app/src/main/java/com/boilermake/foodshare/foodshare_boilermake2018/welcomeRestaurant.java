package com.boilermake.foodshare.foodshare_boilermake2018;

import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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


public class welcomeRestaurant extends AppCompatActivity{

    private Button addEvent;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addEvent = findViewById(R.id.addEvent);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (welcomeRestaurant.this, addEvent.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
