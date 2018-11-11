package gatech.a2340.donationtracker.controllers;

import android.app.Dialog;
import android.content.Intent;
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
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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

        spinner = findViewById(R.id.spinner);
        btnSubmit = findViewById(R.id.btnSubmit);
        radioGroup = findViewById(R.id.radiogroup);
        searchTextField = findViewById(R.id.searchTextField);
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

        List<String> locations = mapLocation.keySet().stream().collect(Collectors.toList());
        locations.add("All locations");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, locations);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    // get the selected dropdown list value and search text
    public void addListenerOnButton() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchTextField.getText().toString();
                String location = String.valueOf(spinner.getSelectedItem());


                if (searchText.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Empty Search Keyword",Toast.LENGTH_LONG).show();

                } else {
                    String searchBy = (String) ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText();

                    Intent intent = new Intent(SearchItemActivity.this, SearchViewItemActivity.class);
                    intent.putExtra("location", location);
                    intent.putExtra("keyword", searchText);
                    intent.putExtra("searchby", searchBy);
                    startActivity(intent);
                }
            }
        });
    }
//
//    // search by category
//    public List<Item> searchCategory(String keyword, String location) {
//        List<Item> itemList = new ArrayList<>();
////        List<String> itemListName = new ArrayList<>();
//
//        if (location == "All locations") {
//            mapLocation.forEach((k,v) -> {
//                v.forEach(item -> {
////                    itemListName.add(item.getDescription());
//                    itemList.add(item);
//                });
//            });
//        } else {
//            mapLocation.get(location).forEach(item -> {
////                itemListName.add(item.getDescription());
//                itemList.add(item);
//            });
//        }
////        List<ExtractedResult> fuzzySearchResult = FuzzySearch.extractSorted(keyword, itemListName);
//        List<Item> results = itemList.stream()
//                .filter(item -> item.getCategory().equals(keyword))
//                .collect(Collectors.toList());
////        fuzzySearchResult.stream()
////                .filter(item -> item.getScore() > 50)
////                .forEach(item -> {
////                    Item i = itemList.get(item.getIndex());
////                    results.add(i);
////                });
//
//        return results;
//    }

//    // search by name
//    public List<Item> searchName(String keyword, String location) {
//        List<Item> itemList = new ArrayList<>();
//        List<String> itemListName = new ArrayList<>();
//
//        if (location == "All locations") {
//            mapLocation.forEach((k,v) -> {
//                v.forEach(item -> {
//                    itemListName.add(item.getDescription());
//                    itemList.add(item);
//                });
//            });
//        } else {
//            mapLocation.get(location).forEach(item -> {
//                itemListName.add(item.getDescription());
//                itemList.add(item);
//            });
//        }
//        List<ExtractedResult> fuzzySearchResult = FuzzySearch.extractSorted(keyword, itemListName);
//        List<Item> results = new ArrayList<>();
//        fuzzySearchResult.stream()
//                .filter(item -> item.getScore() > 50)
//                .forEach(item -> {
//                    Item i = itemList.get(item.getIndex());
//                    results.add(i);
//                });
//        return results;
//    }

}
