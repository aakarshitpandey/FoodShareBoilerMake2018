package com.boilermake.foodshare.foodshare_boilermake2018;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class mainPage extends AppCompatActivity {
    Toolbar toolbar;
    private FirebaseAuth auth;

    //TODO: Retrieve user data (User name, City)
    public String profileName;
    public String profileCity;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        toolbar = findViewById(R.id.toolbar); //log out tool bar sync
        setSupportActionBar(toolbar);
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
            case R.id.item:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.item2:
                openDialog();
        }

        return true;
    }
    //Option to log out >>>>>>

    public void openDialog() {
        profileDialogBox profileDialogBox = new profileDialogBox();
        profileDialogBox.show(getSupportFragmentManager(), "profile information dialogue");
    }


}
