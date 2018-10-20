package com.boilermake.foodshare.foodshare_boilermake2018;

import android.support.v7.app.AppCompatActivity;
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

public class clientType extends AppCompatActivity {

    private Button restaurant;
    private Button customer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clienttype);

        restaurant = findViewById(R.id.restaurantButton);
        customer = findViewById(R.id.customerButton);

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterRestaurantActivity();
            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccountActivity();
            }
        });
    }

    public void openCreateAccountActivity() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
        finish();
    }

    public void openRegisterRestaurantActivity() {
        Intent intent = new Intent(this, registerRestaurant.class);
        startActivity(intent);
        finish();
    }
}
