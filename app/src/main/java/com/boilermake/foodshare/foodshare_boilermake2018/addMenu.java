package com.boilermake.foodshare.foodshare_boilermake2018;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class addMenu extends AppCompatActivity {

    private Button addMore;
    private Button done;
    private EditText dishName;
    private EditText dishPrice;
    private FirebaseAuth auth;
    private String userID;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu);

        auth = FirebaseAuth.getInstance();
        addMore = findViewById(R.id.addMore);
        done = findViewById(R.id.Done);
        dishName = findViewById(R.id.dishName);
        dishPrice = findViewById(R.id.dishPrice);

        if (auth.getCurrentUser() != null) {
            userID = auth.getCurrentUser().getUid();
        }

        final DatabaseReference currentUserDB = FirebaseDatabase.getInstance().getReference().child("Event").child(userID);

        final String name = dishName.getText().toString();
        final String price = dishPrice.getText().toString();
        Map newDish = new HashMap();
        newDish.put("name",name);
        newDish.put("price","$" + price);
        currentUserDB.child("menu").setValue(newDish);

        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(addMenu.this, addMenu.class));
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(addMenu.this, welcomeRestaurant.class));
            }
        });

    }
}
