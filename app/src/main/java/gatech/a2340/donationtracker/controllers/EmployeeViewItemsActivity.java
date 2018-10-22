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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import gatech.a2340.donationtracker.R;
import gatech.a2340.donationtracker.models.Item;

public class EmployeeViewItemsActivity extends AppCompatActivity {
    private DatabaseReference itemDb;
    private String location;
    private static ArrayList<String> mIds = new ArrayList<>();
    private static ArrayList<String> mItemNames = new ArrayList<>();
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
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                    Item currentItem = snapshot1.getValue(Item.class);
                    String id = (String) snapshot1.getKey();
                    if (currentItem.getLocation().equals(location)); {
                        mIds.add(id);
                        mItemNames.add(currentItem.getDescription());

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
        if (getIntent().hasExtra("location")) {
            location = getIntent().getStringExtra("location");
        }
    }

    private void initRecyclerView(){
        mRecyclerView = findViewById(R.id.employee_items_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewEmployeeItem adapter = new RecyclerViewEmployeeItem(this, mItemNames, mIds);
        mRecyclerView.setAdapter(adapter);
    }


    public class RecyclerViewEmployeeItem extends RecyclerView.Adapter<RecyclerViewEmployeeItem.ViewHolder> {


        private ArrayList<String> mIds = new ArrayList<>();
        private ArrayList<String> mItemNames = new ArrayList<>();
        private Context mContext;

        public RecyclerViewEmployeeItem( Context mContext, ArrayList<String> mItemNames, ArrayList<String> mIds) {
            this.mIds = mIds;
            this.mItemNames = mItemNames;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_employee_location_list_item, viewGroup, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
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
