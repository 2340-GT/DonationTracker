package gatech.a2340.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;

import gatech.a2340.donationtracker.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mLocationNames = new ArrayList<>();
    private ArrayList<String> mIds = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter( Context mContext, ArrayList<String> location_name, ArrayList<String> ids) {
        this.mLocationNames = location_name;
        this.mContext = mContext;
        this.mIds = ids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_location_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.locationName.setText(mLocationNames.get(i));


        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mIds.get(i));

                Toast.makeText(mContext, mIds.get(i), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, LocationDetailActivity.class);
                intent.putExtra("location_id", mIds.get(i));
                intent.putExtra("location_name", mLocationNames.get(i));
                mContext.startActivity(intent);
            }

//        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on: " + mLocationNames.get(i));
//                Toast.makeText(mContext, mIds.get(i), Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(mContext, LocationDetailActivity.class);
////
////                intent.putExtra("location_id", mIds.get(i));
////
////                mContext.startActivity(intent);
//
//            }
        });

    }

    @Override
    public int getItemCount() {
        return mLocationNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView locationName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.location_item);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
