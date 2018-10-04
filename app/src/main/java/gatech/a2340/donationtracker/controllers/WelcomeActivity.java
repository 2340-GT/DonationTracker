package gatech.a2340.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import gatech.a2340.donationtracker.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button Signin;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Signin = (Button)findViewById(R.id.btnSignin);
        Register = (Button)findViewById(R.id.btnCancel);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
//        Register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(WelcomeActivity.this, SecondActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}