package gatech.a2340.donationtracker.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import gatech.a2340.donationtracker.R;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LocationDetailActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private static final String TAG = "LocationDetailActivity";
    private static ArrayList<String> mDetails = new ArrayList<>();
    private String locationId = null;
    private String locationName = null;
    private Button title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_location_detail);
        title = (Button)findViewById(R.id.location_title);
        getIncomingIntent();
        title.setText(locationName);
        initLocationDetails();

    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("location_id") && getIntent().hasExtra("location_name")) {
            locationId = getIntent().getStringExtra("location_id");
            locationName = getIntent().getStringExtra("location_name");
        }
    }

    private void initLocationDetails() {
        mDatabase.child("locations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    if (snapshot1.getKey().equals(locationId)) {
                        for (DataSnapshot snapshot : snapshot1.getChildren()) {
                            String key = snapshot.getKey();
                            String value = (String) snapshot.getValue();
                            mDetails.add(key + ": " + value);
                        }
                    }

                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initRecyclerView() {
        RecyclerView recyclerview = findViewById(R.id.detail_recyclerv_view);
        RecyclerViewDetailAdapter adapter = new RecyclerViewDetailAdapter(this, mDetails);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }
}
