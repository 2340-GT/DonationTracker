package gatech.a2340.donationtracker.controllers

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import gatech.a2340.donationtracker.R
import gatech.a2340.donationtracker.models.user
import gatech.a2340.donationtracker.models.UserType
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity(){

    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var userType: Spinner
    private lateinit var register: Button
    private lateinit var cancel: Button
    private lateinit var user: user
    private lateinit var goToLogIn: TextView

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth =  FirebaseAuth.getInstance()
        userName = findViewById<View>(R.id.userEditText) as EditText
        password = findViewById<View>(R.id.passwordEditText) as EditText
        userType = findViewById<View>(R.id.UserTypeSpinner) as Spinner
        register = findViewById<View>(R.id.registerButton) as Button
        cancel = findViewById<View>(R.id.cancelButton) as Button
        goToLogIn = findViewById<View>(R.id.goToLogIn) as TextView

        val adapterStanding = ArrayAdapter(this, android.R.layout.simple_spinner_item, UserType.values())
        adapterStanding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userType.setAdapter(adapterStanding)


        register.setOnClickListener{
            user = user(userName.getText().toString(), password.getText().toString(), userType.getSelectedItem() as UserType)
            if (userName.length() == 0 || password.length() == 0) {
                Toast.makeText(this, "Please Enter text in email/password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth!!.createUserWithEmailAndPassword(user.username, user.password).addOnCompleteListener() {
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                }
                // else if succesfull
                Log.d("main", "Successfully created user with uid: $(it.user.id)");
                val userId = mAuth!!.currentUser!!.uid
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
}