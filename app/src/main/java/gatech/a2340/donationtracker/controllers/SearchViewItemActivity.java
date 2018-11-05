package gatech.a2340.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gatech.a2340.donationtracker.R;
import gatech.a2340.donationtracker.models.Item;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;


public class SearchViewItemActivity extends AppCompatActivity {
    private DatabaseReference itemDb;
    private String location;
    private String keyword;
    private String searchBy;
    private ArrayList<String> mIds = new ArrayList<>();
    private ArrayList<String> mItemNames = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDb = FirebaseDatabase.getInstance().getReference("items");
        setContentView(R.layout.activity_employee_item_list);
        getIncomingIntent();
        initItems();
    }

    private void initItems() {
        itemDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mIds.clear();
                mItemNames.clear();

                // Search by category
                if (searchBy.equals("Category")) {
                    for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                        Item currentItem = snapshot1.getValue(Item.class);
                        String id = (String) snapshot1.getKey();
                        String category = currentItem.getCategory().toString();


                        if (category.equals(keyword)
                                && (currentItem.getLocation().equals(location)
                                || location.equals("All locations"))) {
                            mIds.add(id);
                            mItemNames.add(currentItem.getDescription());
                        }
                    }

                } else {        // Searcb by name
                    for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                        Item currentItem = snapshot1.getValue(Item.class);
                        String id = (String) snapshot1.getKey();
                        String curLocation = currentItem.getLocation();
                        String itemName = currentItem.getDescription();
                        if ((curLocation.equals(location) || location.equals("All locations"))
                                && FuzzySearch.weightedRatio(keyword, itemName) > 60) {
                            mIds.add(id);
                            mItemNames.add(itemName);
                        }
                    }
                }
                if (mItemNames.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Item not found",Toast.LENGTH_LONG).show();

                } else {
                    initRecyclerView();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getIncomingIntent() {
        if (getIntent().hasExtra("location")) {
            location = getIntent().getStringExtra("location");
        }
        if (getIntent().hasExtra("keyword")) {
            keyword = getIntent().getStringExtra("keyword");
        }
        if (getIntent().hasExtra("searchby")) {
            searchBy = getIntent().getStringExtra("searchby");
        }
    }

    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.employee_items_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SearchViewItemActivity.RecyclerViewSearchItem adapter = new SearchViewItemActivity.RecyclerViewSearchItem(this, mItemNames, mIds);
        mRecyclerView.setAdapter(adapter);
    }


    public class RecyclerViewSearchItem extends RecyclerView.Adapter<SearchViewItemActivity.RecyclerViewSearchItem.ViewHolder> {


        private ArrayList<String> mIds = new ArrayList<>();
        private ArrayList<String> mItemNames = new ArrayList<>();
        private Context mContext;

        public RecyclerViewSearchItem( Context mContext, ArrayList<String> mItemNames, ArrayList<String> mIds) {
            this.mIds = mIds;
            this.mItemNames = mItemNames;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public SearchViewItemActivity.RecyclerViewSearchItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_employee_location_list_item, viewGroup, false);
            SearchViewItemActivity.RecyclerViewSearchItem.ViewHolder holder = new SearchViewItemActivity.RecyclerViewSearchItem.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull SearchViewItemActivity.RecyclerViewSearchItem.ViewHolder viewHolder, final int i) {


            viewHolder.itemName.setText(mItemNames.get(i));

            viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ItemDetailActivity.class);
                    intent.putExtra("item_id", mIds.get(i));
                    intent.putExtra("item_name", mItemNames.get(i));
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItemNames.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView itemName;
            RelativeLayout parentLayout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.employee_item_name);
                parentLayout = itemView.findViewById(R.id.employee_item_layout);
            }
        }
    }
}
