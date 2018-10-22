package gatech.a2340.donationtracker.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gatech.a2340.donationtracker.models.Item;
import gatech.a2340.donationtracker.models.ItemType;

import java.util.Date;



import gatech.a2340.donationtracker.R;

public class AddItemActivity extends AppCompatActivity {

    private String itemLocation;

    private Button addButton;
    private Button cancelButton;
    private EditText shortDescritionText;
    private EditText valueText;
    private Spinner categorySpinner;
    private EditText longDescriptionText;
    private Context mContext;

    private FirebaseDatabase mDatabase;

    private DatabaseReference itemDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getIncomingIntent();

        mDatabase = FirebaseDatabase.getInstance();
        itemDb = FirebaseDatabase.getInstance().getReference("items");
        mContext = this;

        addButton = (Button) findViewById(R.id.button_add_item);
        cancelButton = (Button) findViewById(R.id.button_cancel);
        shortDescritionText = (EditText) findViewById(R.id.text_short_description);
        valueText = (EditText) findViewById(R.id.text_value);
        categorySpinner = (Spinner) findViewById(R.id.spinner_category);
        longDescriptionText = ( EditText) findViewById(R.id.text_long_description);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, ItemType.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                String shortDescription = shortDescritionText.getText().toString();
                String longDescription = longDescriptionText.getText().toString();
                Double value = Double.parseDouble(valueText.getText().toString());
                ItemType type = (ItemType) categorySpinner.getSelectedItem();

                Item newItem = new Item(date.toString(), itemLocation, shortDescription, longDescription, value, type);
                String newKey = mDatabase.getReference().child("items").push().getKey();

                itemDb.child(newKey).setValue(newItem).addOnCompleteListener((Activity) mContext, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(mContext, EmployeeDashboardActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EmployeeDashboardActivity.class);
                startActivity(intent);
            }
        });
    }



    private void getIncomingIntent() {
        if (getIntent().hasExtra("location")) {
            Log.d("getIncoming Intent", "getIncomingIntent: " + "get in here");
            itemLocation = getIntent().getStringExtra("location");
        }
    }
}
