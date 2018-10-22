package gatech.a2340.donationtracker.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gatech.a2340.donationtracker.R;
import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private static ArrayList<String> mDetails = new ArrayList<>();
    private static String itemId;
    private static String itemName;
    private Button title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference("items");
        setContentView(R.layout.activity_item_detail);
        title = (Button) findViewById(R.id.item_title);
        getIncomingIntent();
        title.setText(itemName);
        Log.d("why", "onCreate: " + itemId);
        Log.d("why", "onCreate: " + itemName);
        initItemDetails();
    }

    private void initItemDetails() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    if (snapshot1.getKey().equals(itemId)) {
                        for (DataSnapshot snapshot : snapshot1.getChildren()) {
                            String key = snapshot.getKey();
                            String value = "";
                            if (key.equals("value")) {
                                value = snapshot.getValue() + "";
                            } else {
                                value = (String) snapshot.getValue();
                            }
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




    private void getIncomingIntent() {
        if (getIntent().hasExtra("item_id") && getIntent().hasExtra("item_name")) {
            itemId = getIntent().getStringExtra("item_id");
            itemName = getIntent().getStringExtra("item_name");
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerview = findViewById(R.id.item_detail_recycler_view);
        Log.d("layout", mDetails.toString());
        RecyclerViewDetailAdapter adapter = new RecyclerViewDetailAdapter(this, mDetails);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}
