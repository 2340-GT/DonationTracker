package edu.gatech.cs2340.m4solution.controllers
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.location.LocationListener
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//I just import these classes
import gatech.a2340.donationtracker.models.Location
import gatech.a2340.donationtracker.R
import gatech.a2340.donationtracker.R.layout.activity_maps
import gatech.a2340.donationtracker.controllers.LocationDetailActivity

/**
 * Controller for the Map Display.   Mostly generated from the MapActivity wizard
 */
class MapsActivity : FragmentActivity(), OnMapReadyCallback, LocationListener, GoogleMap.onMarkerClickListener {

    /** holds the map object returned from Google  */
    private lateinit var mMap: GoogleMap
    private ChildEventListener mChildEventListener
    private DatabaseReference mUsers

    private var mAuth: FirebaseAuth? = null
    private var mDatabase: DatabaseReference? = null
    private var mLocations: DatabaseReference? = null
    Marker marker;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        ChildEventListener mChildEventListener;

        mUsers = FirebaseDatabase.getInstance().getReference(“Location”)
        mUsers.push().setValue(marker)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    override fun onMapReady(googleMap: GoogleMap) {
        //save the map instance returned from Google
        mMap = googleMap

        //reference to our GRASP Controller interface to the model
        val dataService = LocationDetailActivity.getInstance()

        // Setting a click event handler for the map
        mMap!!.setOnMapClickListener(object : GoogleMap.OnMapClickListener() {

            fun onMapClick(latLng: LatLng) {

                // Creating a marker
                val markerOptions = MarkerOptions()

                // Setting the position for the marker
                markerOptions.position(latLng)

                //add a new item where the touch happened, for non-hardcoded data, we would need
                //to launch an activity with a form to enter the data.
                dataService.addDataElement("Name",Location(latLng.latitude, latLng.longitude))

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(dataService.getLastElementAdded().getName())
                markerOptions.snippet(dataService.getLastElementAdded().getDescription())

                // Animating to the touched position
                mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))

                // Placing a marker on the touched position
                mMap!!.addMarker(markerOptions)
            }
        })

        //get the data to display
        val dataList = dataService.getData()

        //iterate through the list and add a pin for each element in the model
        for (de in dataList) {
            val loc = LatLng(de.getLatitude(), de.getLongitude())
            mMap!!.addMarker(MarkerOptions().position(loc).title(de.getName()).snippet(de.getDescription()))
            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(loc))
        }

        //Use a custom layout for the pin data
        mMap!!.setInfoWindowAdapter(CustomInfoWindowAdapter())
    }

    /**
     * This class implements a custom layout for the pin
     */
    internal inner class CustomInfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val myContentsView: View
        /**
         * Make the adapter
         */
        init {
            // hook up the custom layout view in res/custom_map_pin_layout.xml
            myContentsView = layoutInflater.inflate(R.layout.custom_map_pin_layout, null)
        }
        fun getInfoContents(marker: Marker): View {
            val tvTitle = myContentsView.findViewById<View>(R.id.title) as TextView
            tvTitle.setText(marker.getTitle())
            val tvSnippet = myContentsView.findViewById<View>(R.id.snippet) as TextView
            tvSnippet.setText(marker.getSnippet())
            return myContentsView
        }
        fun getInfoWindow(marker: Marker): View? {
            // TODO Auto-generated method stub
            return null
        }
    }
}