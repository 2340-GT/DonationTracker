package gatech.a2340.donationtracker.controllers;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import gatech.a2340.donationtracker.R;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class SearchItemActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private EditText searchTextField;

    private Spinner spinner;

    private Button btnSubmit;

    private RadioGroup radioGroup;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        spinner = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        addItemsOnSpinner();
        addListenerOnButton();

    }

    // add items into spinner dynamically
    public void addItemsOnSpinner() {


        List<String> list = new ArrayList<>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    // get the selected dropdown list value and search text
    public void addListenerOnButton() {

//        btnSubmit.setOnClickListener(() -> {
//            String searchText = searchTextField.getText().toString();
//            String location = String.valueOf(spinner.getSelectedItem());
//            int selectedRadioId = radioGroup.getCheckedRadioButtonId();
//
//        });
    }


}
