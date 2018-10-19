package gatech.a2340.donationtracker.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import gatech.a2340.donationtracker.R;


public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Cancel;
    private int counter = 5;
    private SharedPreferences prefs;


    //SET TIME TO ENABLE THE LOGIN BUTTON AGAIN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = getSharedPreferences("gatech.a2340.donationtracker.prefs", 0);
        Name = (EditText)findViewById(R.id.emailTextField);
        Password = (EditText)findViewById(R.id.passwordTextField);
        Info = (TextView)findViewById(R.id.infoTextView);
        Login = (Button)findViewById(R.id.btnLogin);
        Cancel = (Button)findViewById(R.id.btnCancel);

        Info.setText("Number of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        System.out.println("------------");
        System.out.println(password);
        if((userName.equals(userName)) && (userPassword.equals("null"))) {
            Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
            startActivity(intent);
        } else if (password == null){
            Toast.makeText(getApplicationContext(),"Wrong user name",Toast.LENGTH_LONG).show();
        } else {
            counter--;
            Info.setText("Number of attemps " + String.valueOf(counter));
            Toast.makeText(getApplicationContext(),"Bad Login Attempt",Toast.LENGTH_LONG).show();
            if(counter == 0) {
                Login.setEnabled(false);
            }
        }
    }
}
