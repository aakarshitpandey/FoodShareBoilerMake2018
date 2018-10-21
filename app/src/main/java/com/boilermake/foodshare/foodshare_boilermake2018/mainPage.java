package com.boilermake.foodshare.foodshare_boilermake2018;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mainPage extends AppCompatActivity {

    Toolbar toolbar;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser currentUser;
    private String userId;
    private String profileName;
    private String profileCity;
    private DataSnapshot dataSnapshot;
    private DatabaseReference mDatabaseRestaurant;

    /**
     * event list
     */
    private TextView event1;
    private TextView event2;
    private TextView event3;
    private TextView event4;
    private TextView event5;
    private TextView event6;
    private TextView event7;
    private TextView event8;
    private TextView event9;
    private TextView event10;

    private String eventStr;
    private String eventStrLoc;
    private String eventStrMenu;
    private String eventStrName;
    private String eventStrNum;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        toolbar = findViewById(R.id.toolbar); //log out tool bar sync
        setSupportActionBar(toolbar);

        event1 = findViewById(R.id.event1);
        event2 = findViewById(R.id.event2);
        event3 = findViewById(R.id.event3);
        event4 = findViewById(R.id.event4);
        event5 = findViewById(R.id.event5);
        event6 = findViewById(R.id.event6);
        event7 = findViewById(R.id.event7);
        event8 = findViewById(R.id.event8);
        event9 = findViewById(R.id.event9);
        event10 = findViewById(R.id.event10);



        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(userId); //access current user's database

        mDatabaseRestaurant = FirebaseDatabase.getInstance().getReference().child("Event");

        mDatabaseRestaurant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventStrLoc = dataSnapshot.child("Event1").child("loc").getValue().toString();
                eventStrMenu = dataSnapshot.child("Event1").child("menu").getValue().toString();
                eventStrName = dataSnapshot.child("Event1").child("name").getValue().toString();
                eventStrNum = dataSnapshot.child("Event1").child("num").getValue().toString();

                eventStr = eventStrLoc + " " + eventStrMenu + " " + eventStrName + " " + eventStrNum;
                event1.setText(eventStr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mainPage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mDatabaseRestaurant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventStrLoc = dataSnapshot.child("Event2").child("loc").getValue().toString();
                eventStrMenu = dataSnapshot.child("Event2").child("menu").getValue().toString();
                eventStrName = dataSnapshot.child("Event2").child("name").getValue().toString();
                eventStrNum = dataSnapshot.child("Event2").child("num").getValue().toString();

                eventStr = eventStrLoc + " " + eventStrMenu + " " + eventStrName + " " + eventStrNum;
                event2.setText(eventStr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mainPage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    profileName = dataSnapshot.child("name").getValue().toString();
                } catch (NullPointerException n) {
                    profileName = "Error: n";
                }

                try {
                    profileCity = dataSnapshot.child("city").getValue().toString();
                } catch (NullPointerException n) {
                    profileCity = "Error: n";
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mainPage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    //Option to log out <<<<<<
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.item2:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Profile Information");
                builder.setMessage("Name: " + profileName + "\nCity: " + profileCity);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                break;
        }

        return true;
    }
    //Option to log out >>>>>>


}
