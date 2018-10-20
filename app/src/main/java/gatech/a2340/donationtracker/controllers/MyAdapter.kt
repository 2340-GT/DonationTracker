package gatech.a2340.donationtracker.controllers

import gatech.a2340.donationtracker.R
import android.support.v7.widget.RecyclerView
import android.content.Intent
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.location_list_item.view.*




class MyAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.location_list_item, parent, false))
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.location?.text = items.get(position)
        holder.location.setOnClickListener {
//            val intent = Intent(this, LocationDetailActivity:: class.java)

        }
    }
}



class ViewHolder(view :View) : RecyclerView.ViewHolder(view) {

    val location = view.location_item
//    context = view.context
}
