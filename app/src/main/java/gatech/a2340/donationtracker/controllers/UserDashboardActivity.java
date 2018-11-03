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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_dashboard);

        SearchItem = (Button) findViewById(R.id.btnSearchItem);
        ViewStores = (Button) findViewById(R.id.btnViewStores);

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
    }
}
