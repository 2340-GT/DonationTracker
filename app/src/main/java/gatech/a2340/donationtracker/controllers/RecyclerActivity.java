package gatech.a2340.donationtracker.controllers;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import gatech.a2340.donationtracker.R;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class RecyclerActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private static final String TAG = "RecyclerActivity";
    private ArrayList<String> mNames = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_locationlist);
        initLocationNames();
    }


    private void initLocationNames() {
        mNames.add("An Tran");
        mNames.add("An Nguyen");
        mNames.add("Kiet Tran");
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerview = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }
}
