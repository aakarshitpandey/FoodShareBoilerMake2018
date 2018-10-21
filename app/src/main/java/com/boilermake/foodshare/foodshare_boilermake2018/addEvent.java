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

import java.util.*;


public class addEvent extends AppCompatActivity {

    private String restName;
    private String location;
    private EditText seatCapacityText;
    private EditText menuC;
    private EditText price;
    private FirebaseAuth auth;
    private String userID;
    private Button add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent1);

        final int seatCapacity;
        String name;

        auth = FirebaseAuth.getInstance();
        price = (EditText) findViewById(R.id.dishPrice);
        add = findViewById(R.id.addToMenu);
        seatCapacityText = findViewById(R.id.setCapacity);
        menuC = findViewById(R.id.menuCard_dynamic);
        seatCapacity = Integer.parseInt(seatCapacityText.getText().toString());
        int count = 0;


        if(auth.getCurrentUser() != null) {
            userID = auth.getCurrentUser().getUid();
        }

        final DatabaseReference currentUserDB = FirebaseDatabase.getInstance().getReference().child("Event").child(userID);

        final DatabaseReference restaurantAdd = FirebaseDatabase.getInstance().getReference().child("Restaurant").child(userID);

        restaurantAdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String address = dataSnapshot.child("address").getValue().toString() +
                          dataSnapshot.child("city").getValue().toString();
               String name = dataSnapshot.child("name").getValue().toString();
                final Map newData = new HashMap();
                newData.put("num", seatCapacity);
                newData.put("location",address);
                newData.put("name", name);
                currentUserDB.setValue(newData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() { //Method on click


            @Override
            public void onClick(View v) {
                final Map newMenu = new HashMap();
                newMenu.put("price","$" + price.getText().toString());
                newMenu.put("dish",menuC.getText().toString());
                currentUserDB.child("menu").setValue(newMenu);
                startActivity(new Intent(addEvent.this, addMenu.class));
            }
        });

}}
