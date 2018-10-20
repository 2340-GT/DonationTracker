package gatech.a2340.donationtracker.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gatech.a2340.donationtracker.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mLocationNames = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter( Context mContext, ArrayList<String> location_name) {
        this.mLocationNames = location_name;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_list_item, viewGroup, false);
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
                Log.d(TAG, "onClick: clicked on: " + mLocationNames.get(i));

                Toast.makeText(mContext, mLocationNames.get(i), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mLocationNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.location_item);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
