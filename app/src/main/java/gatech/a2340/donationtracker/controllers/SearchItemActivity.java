package gatech.a2340.donationtracker.controllers;

import android.app.Dialog;
import android.graphics.PostProcessor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import gatech.a2340.donationtracker.R;
import gatech.a2340.donationtracker.models.Item;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SearchItemActivity extends AppCompatActivity {

    private static final String TAG = "SearchItemActivity";


    private DatabaseReference mDatabase;

    private EditText searchTextField;

    private Spinner spinner;

    private Button btnSubmit;

    private RadioGroup radioGroup;

    private Map<String, Set<Item>> mapCategory;

    private Map<String, Set<Item>> mapLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("items");

        spinner = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        System.out.println("start here--------------------------------------------------------------------------------------------------");

        onChangeDatabase();


    }

    // add listener to database
    public void onChangeDatabase() {
        mapCategory = new ConcurrentHashMap<>();
        mapLocation = new ConcurrentHashMap<>();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
//                Post post = dataSnapshot.getValue(Post.class);
//                // [START_EXCLUDE]
//                mAuthorView.setText(post.author);
//                mTitleView.setText(post.title);
//                mBodyView.setText(post.body);
                Map<String, Object> rawData = (Map<String, Object>) dataSnapshot.getValue();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    System.out.println("------------------------------" + dsp.getValue(Item.class));
//                    for (DataSnapshot d : dsp.getChildren()) {
//                        System.out.println("->>>>>>>>>>>>>>>>>>>" + d);
//                    }
                    Item item = dsp.getValue(Item.class);

                    String category = String.valueOf(item.getCategory());
                    mapCategory.putIfAbsent(category, new LinkedHashSet<Item>());
                    mapCategory.get(category).add(item);

                    String location = String.valueOf(item.getLocation());
                    mapLocation.putIfAbsent(location, new LinkedHashSet<Item>());
                    mapLocation.get(location).add(item);
                }
                addItemsOnSpinner();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };


        mDatabase.addValueEventListener(postListener);


        addListenerOnButton();
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner() {
        System.out.println("-@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + "add Item neeeeeeeeeeeeeeeee");

        List<String> categories = mapCategory.keySet().stream().collect(Collectors.toList());
        System.out.println("->>>>>>>>>>>>>>>>>>" + categories);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    // get the selected dropdown list value and search text
    public void addListenerOnButton() {

//        btnSubmit.setOnClickListener((View v) -> {
//            String searchText = searchTextField.getText().toString();
//            String location = String.valueOf(spinner.getSelectedItem());
//            int selectedRadioId = radioGroup.getCheckedRadioButtonId();
//            if (selectedRadioId == 0) {
//                searchCategory(location);
//            } else {
//                searchName();
//            }
//        });
    }

    // search by category
    public void searchCategory(String location) {
        if (location == "All locations") {
            System.out.println("hi");
        }
    }

    // search by name
    public void searchName() {

    }

}
