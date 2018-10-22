package gatech.a2340.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gatech.a2340.donationtracker.R;

public class EmployeeDashboardActivity extends AppCompatActivity {


    private Button addItem;
    private Button viewItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);
        addItem = (Button)findViewById(R.id.add_item_button);
        viewItems = (Button) findViewById(R.id.view_items_button);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
    }
}
