package gatech.a2340.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import gatech.a2340.donationtracker.R;

public class EmployeeDashboardActivity extends AppCompatActivity {


    private Button addItem;
    private Button viewItems;
    private String location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);
        addItem = findViewById(R.id.add_item_button);
        viewItems = findViewById(R.id.view_items_button);
        getIncomingIntent();
        Log.d("Employee dashboard", "onCreate: " + location);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, AddItemActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);

            }
        });


        viewItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeViewItemsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("location")) {
            location = getIntent().getStringExtra("location");
        }
    }
}
