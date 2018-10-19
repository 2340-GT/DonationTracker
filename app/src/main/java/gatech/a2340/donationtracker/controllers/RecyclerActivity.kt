package gatech.a2340.donationtracker.controllers

import android.support.v7.app.AppCompatActivity
import gatech.a2340.donationtracker.R
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_locationlist.*



class RecyclerActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    val locations: ArrayList<String> = ArrayList()
    val locationDetails: HashMap<Int, HashMap<String, String>> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locationlist)

        addLocations()

        location_list.layoutManager = LinearLayoutManager(this)

        location_list.adapter = MyAdapter(locations, this)

    }
    fun addLocations() {
        locations.add("1. AFD Station 4")
        locations.add("2. BOYS & GILRS CLUB W.W. WOOLFOLK")
        locations.add("3. PATHWAY UPPER ROOM CHRISTIAN MINISTRIES")
        locations.add("4. PAVILION OF HOPE INC")
        locations.add("5. D&D CONVENIENCE STORE")
        locations.add("6. KEEP NORTH FULTON BEAUTIFUL")
    }
    fun addLocationsDetail() {
        for (i in 1..6) {
            locationDetails[i] =  HashMap<String, String>()
        }
        locationDetails[1]!!["name"] = "AFD Station 4"
        locationDetails[1]!!["latitude"] = "33.75416"
        locationDetails[1]!!["longtitude"] = "-84.37742"
        locationDetails[1]!!["address"] = "309 EDGEWOOD AVE SE"
        locationDetails[1]!!["city"] = "Atlanta"
        locationDetails[1]!!["state"] = "GA"
        locationDetails[1]!!["zip"] = "30332"
        locationDetails[1]!!["type"] = "Drop Off"
        locationDetails[1]!!["phone"] = "(404) 555 - 3456"
        locationDetails[1]!!["website"] = "www.afd04.atl.ga"


        locationDetails[2]!!["name"] = "BOYS & GILRS CLUB W.W. WOOLFOLK"
        locationDetails[2]!!["latitude"] = "33.73182"
        locationDetails[2]!!["longtitude"] = "-84.43971"
        locationDetails[2]!!["address"] = "1642 RICHLAND RD"
        locationDetails[2]!!["city"] = "Atlanta"
        locationDetails[2]!!["state"] = "GA"
        locationDetails[2]!!["zip"] = "30332"
        locationDetails[2]!!["type"] = "Store"
        locationDetails[2]!!["phone"] = "(404) 555 - 1234"
        locationDetails[2]!!["website"] = "www.bgc.wool.ga"

        locationDetails[3]!!["name"] = "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES"
        locationDetails[3]!!["latitude"] = "33.70866"
        locationDetails[3]!!["longtitude"] = "-84.41853"
        locationDetails[3]!!["address"] = "1683 SYLVAN RD"
        locationDetails[3]!!["city"] = "Atlanta"
        locationDetails[3]!!["state"] = "GA"
        locationDetails[3]!!["zip"] = "30332"
        locationDetails[3]!!["type"] = "Warehouse"
        locationDetails[3]!!["phone"] = "(404) 555 - 5432"
        locationDetails[3]!!["website"] = "www.pathways.org"

        locationDetails[4]!!["name"] = "PAVILION OF HOPE INC"
        locationDetails[4]!!["latitude"] = "33.80129"
        locationDetails[4]!!["longtitude"] = "-84.25537"
        locationDetails[4]!!["address"] = "3558 EAST PONCE DE LEON AVE"
        locationDetails[4]!!["city"] = "SCOTTDALE"
        locationDetails[4]!!["state"] = "GA"
        locationDetails[4]!!["zip"] = "30079"
        locationDetails[4]!!["type"] = "Warehouse"
        locationDetails[4]!!["phone"] = "(404) 555 - 8765"
        locationDetails[4]!!["website"] = "www.pavhope.org"

        locationDetails[5]!!["name"] = "D&D CONVENIENCE STORE"
        locationDetails[5]!!["latitude"] = "33.71747"
        locationDetails[5]!!["longtitude"] = "-84.2521"
        locationDetails[5]!!["address"] = "2426 COLUMBIA DRIVE"
        locationDetails[5]!!["city"] = "DECATUR"
        locationDetails[5]!!["state"] = "GA"
        locationDetails[5]!!["zip"] = "30034"
        locationDetails[5]!!["type"] = "Drop Off"
        locationDetails[5]!!["phone"] = "(404) 555 - 9876"
        locationDetails[5]!!["website"] = "www.ddconv.com"

        locationDetails[6]!!["name"] = "KEEP NORTH FULTON BEAUTIFUL"
        locationDetails[6]!!["latitude"] = "33.96921"
        locationDetails[6]!!["longtitude"] = "-84.2521"
        locationDetails[6]!!["address"] = "2426 COLUMBIA DRIVE"
        locationDetails[6]!!["city"] = "DECATUR"
        locationDetails[6]!!["state"] = "GA"
        locationDetails[6]!!["zip"] = "30034"
        locationDetails[6]!!["type"] = "Drop Off"
        locationDetails[6]!!["phone"] = "(404) 555 - 9876"
        locationDetails[6]!!["website"] = "www.ddconv.com"
    }
}