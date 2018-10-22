package gatech.a2340.donationtracker.controllers

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import gatech.a2340.donationtracker.R
import gatech.a2340.donationtracker.models.user
import gatech.a2340.donationtracker.models.UserType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.widget.ArrayAdapter




class RegisterActivity : AppCompatActivity(){

    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var userType: Spinner
    private lateinit var employeeLocation: Spinner
    private lateinit var register: Button
    private lateinit var cancel: Button
    private lateinit var user: user
    private lateinit var goToLogIn: TextView
    private lateinit var locationList: ArrayList<String>
    private var mAuth: FirebaseAuth? = null
    private var mDatabase: DatabaseReference? = null
    private var mLocations: DatabaseReference? = null
    private var mContext: Context? = null
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mContext = this
        mDatabase = FirebaseDatabase.getInstance().reference
        mAuth =  FirebaseAuth.getInstance()
        mLocations = FirebaseDatabase.getInstance().getReference("locations")
        userName = findViewById<View>(R.id.userEditText) as EditText
        password = findViewById<View>(R.id.passwordEditText) as EditText
        userType = findViewById<View>(R.id.UserTypeSpinner) as Spinner
        register = findViewById<View>(R.id.registerButton) as Button
        cancel = findViewById<View>(R.id.cancelButton) as Button
        goToLogIn = findViewById<View>(R.id.goToLogIn) as TextView

        employeeLocation = findViewById<View>(R.id.employee_location_spinner) as Spinner
        locationList = ArrayList<String>()
        employeeLocation.isEnabled = false

        readLocations( object : FireBaseCallback {
            override fun onCallBack(list: ArrayList<String>) {
                Log.d("in call back", locationList.toString())
            }
        })
        Log.d("in call back", locationList.toArray().toString())


        val adapterStanding = ArrayAdapter(this, android.R.layout.simple_spinner_item, UserType.values())
        adapterStanding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userType.adapter = adapterStanding

//        employeeLocation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        }


        userType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0.getItemAtPosition(p2)
                if (selectedItem.equals(UserType.LOCATION_EMPLOYEE)) {
                    employeeLocation.isEnabled = true;
                    val adapterEmployee = ArrayAdapter(mContext!!, android.R.layout.simple_spinner_item, locationList.toArray())
                    adapterEmployee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    employeeLocation.adapter = adapterEmployee
                }
            }
        }
            
        

        register.setOnClickListener{
            user = user(userName.getText().toString(), password.getText().toString(), userType.selectedItem as UserType)
            if (userName.length() == 0 || password.length() == 0) {
                Toast.makeText(this, "Please Enter text in email/password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth!!.createUserWithEmailAndPassword(user.username, user.password).addOnCompleteListener() {
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                }
                // else if successful
                Log.d("main", "Successfully created user with uid: $(it.user.id)");
                val userId = mAuth!!.currentUser!!.uid
                if(user.userType.equals(UserType.LOCATION_EMPLOYEE)) {
                    user = user(userName.getText().toString(), password.getText().toString(), userType.selectedItem as UserType, employeeLocation.selectedItem as String);
                }
                FirebaseDatabase.getInstance().reference.child("users").child(userId).setValue(user)
            }.addOnFailureListener {

            }
            startActivity(Intent(this, WelcomeActivity:: class.java))
        }

        cancel.setOnClickListener{
            startActivity(Intent(this, WelcomeActivity:: class.java))
        }

        goToLogIn.setOnClickListener{
            startActivity(Intent(this, MainActivity:: class.java))
        }
    }
    private fun readLocations(firebaseCallback : FireBaseCallback) {
        Log.d("readLocations" , "enter read Locations")
        val locationListener = object  : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val locations = p0.children
                locations.forEach {
                    val locationName = it.child("Name").value.toString()
                    locationList.add(locationName)
                }

                firebaseCallback.onCallBack(locationList)
            }

        }
        mLocations!!.addValueEventListener(locationListener)
    }
    private interface FireBaseCallback {
        fun onCallBack(list:ArrayList<String>);
    }
}