package gatech.a2340.donationtracker.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import gatech.a2340.donationtracker.R;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private static final String TAG = "RecyclerActivity";
    private static ArrayList<String> mNames = new ArrayList<>();
    private static ArrayList<String> mIds = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_locationlist);
        Log.d(TAG, "onCreate: after set content view");
        initLocationNames();

    }


    private void initLocationNames() {
        mDatabase.child("locations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: starting query");
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    String id = snapshot1.getKey();
                    for (DataSnapshot snapshot : snapshot1.getChildren()) {
                        String key = snapshot.getKey();
                        if (key.equals("Name")) {
                            mIds.add(id);
                            String value = (String) snapshot.getValue();
                            mNames.add(value);
                            Log.d(TAG, "onDataChange: add names");
                        }
                    }

                }
                Log.d(TAG, "onDataChange: start recycler view");
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerv_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mIds);
        mRecyclerView.setAdapter(adapter);




    }
}
