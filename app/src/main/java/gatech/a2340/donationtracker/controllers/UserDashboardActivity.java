package gatech.a2340.donationtracker.controllers;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import gatech.a2340.donationtracker.R;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class UserDashboardActivity extends AppCompatActivity {

    private Button SearchItem;
    private Button ViewStores;
    private Button ViewMap;
    private Button LogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_dashboard);

        SearchItem = findViewById(R.id.btnSearchItem);
        ViewStores = findViewById(R.id.btnViewStores);
        ViewMap = findViewById(R.id.btnViewMap);
        LogOut = findViewById(R.id.btnLogOut);

        SearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboardActivity.this, SearchItemActivity.class);
                startActivity(intent);
            }
        });

        ViewStores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboardActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

        ViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboardActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboardActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
