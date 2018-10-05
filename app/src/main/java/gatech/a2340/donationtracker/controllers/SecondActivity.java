package gatech.a2340.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import gatech.a2340.donationtracker.R;

//import com.google.firebase.auth.FirebaseAuth;
//import Firebase.auth;

public class SecondActivity extends AppCompatActivity {

//    private FirebaseAuth firebaseAuth;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
    }

    public void Logout(){
//        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, WelcomeActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu: {
                Logout();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
