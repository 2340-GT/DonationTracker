package gatech.a2340.donationtracker.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import gatech.a2340.donationtracker.models.user;
import gatech.a2340.donationtracker.models.UserType;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gatech.a2340.donationtracker.R;


public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Cancel;
    private int counter = 5;
    private SharedPreferences prefs;
    private FirebaseAuth mAuth;


    //SET TIME TO ENABLE THE LOGIN BUTTON AGAIN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = getSharedPreferences("gatech.a2340.donationtracker.prefs", 0);
        Name = findViewById(R.id.emailTextField);
        Password = findViewById(R.id.passwordTextField);
        Info = findViewById(R.id.infoTextView);
        Login = findViewById(R.id.btnLogin);
        Cancel = findViewById(R.id.btnCancel);
        mAuth = FirebaseAuth.getInstance();
        Info.setText("Number of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Name.getText().toString();
                String password = Password.getText().toString();
                if (password.length() == 0 || userName.length() == 0) {
                    Toast.makeText(getApplicationContext(),"Password/Username cannot be empty",Toast.LENGTH_LONG).show();
                    return;
                }
                validate (Name.getText().toString(), Password.getText().toString());
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validate(String userName, String userPassword) {
        String password = prefs.getString(userName, null);

        mAuth.signInWithEmailAndPassword(userName, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            final String id = mAuth.getCurrentUser().getUid();
                            DatabaseReference mdb = FirebaseDatabase.getInstance().getReference();
                            mdb.child("users").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot userSnapShop : dataSnapshot.getChildren()) {
                                        if(userSnapShop.getKey().equals((id))) {
                                            user retrievedUser = userSnapShop.getValue(user.class);
                                            Intent intent = null;
                                            if (retrievedUser.getUserType().getUserTypeId() == 1) {
                                                intent = new Intent(MainActivity.this, UserDashboardActivity.class);
                                            } else if (retrievedUser.getUserType().getUserTypeId() == 2) {
                                                // location employee
                                                intent = new Intent(MainActivity.this, EmployeeDashboardActivity.class);
                                                intent.putExtra("location", retrievedUser.getLocation());
                                            } else {
                                                // admin
                                                intent = new Intent(MainActivity.this, RecyclerActivity.class);
                                            }
                                            startActivity(intent);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        } else {
                            counter--;
                            Info.setText("Number of attempts " + String.valueOf(counter));
                            Toast.makeText(getApplicationContext(),"Bad Login Attempt",Toast.LENGTH_LONG).show();
                            if(counter == 0) {
                                Login.setEnabled(false);
                            }
                        }
                    }
                });
    }

}

