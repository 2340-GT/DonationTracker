package gatech.a2340.donationtracker.controllers
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import gatech.a2340.donationtracker.R

/**
 * Controller for the Map Display.
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    /** holds the map object returned from Google  */
    private lateinit var mMap: GoogleMap
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mDatabase = FirebaseDatabase.getInstance().reference
    }
    

     override fun onMapReady(googleMap: GoogleMap) {
         mMap = googleMap
         mDatabase.child("locations").addValueEventListener(object : ValueEventListener {
             override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                 for (snapshot1 in dataSnapshot.children) {
                     mMap.addMarker(MarkerOptions().position(LatLng((snapshot1.child("Latitude").value as String).toDouble(), (snapshot1.child("Longitude").value as String).toDouble()))
                                                   .title(snapshot1.child("Name").value as String)
                                                   .snippet("Phone number: " + snapshot1.child("Phone").value as String))
                 }
             }
             override fun onCancelled(databaseError: DatabaseError) {

             }
         })
         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(33.753746, -84.386330), 11.0f)) //Atlanta coordinates and start zoom
    }
}