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
import android.widget.TextView;


import java.util.ArrayList;

import gatech.a2340.donationtracker.R;

public class RecyclerViewDetailAdapter extends RecyclerView.Adapter<RecyclerViewDetailAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewDetailAdapter";

    private ArrayList<String> mDetails = new ArrayList<>();
    private Context mContext;

    public RecyclerViewDetailAdapter( Context mContext, ArrayList<String> details) {
        this.mDetails = details;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_location_detail_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");
        viewHolder.locationDetail.setText(mDetails.get(i));


    }

    @Override
    public int getItemCount() {
        return mDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationDetail;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationDetail = itemView.findViewById(R.id.location_detail);
            parentLayout = itemView.findViewById(R.id.detail_parent_layout);
        }
    }
}
