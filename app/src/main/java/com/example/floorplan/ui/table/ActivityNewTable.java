package com.example.floorplan.ui.table;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.floorplan.MainActivity;
import com.example.floorplan.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ActivityNewTable extends AppCompatActivity {

    TextInputEditText tableNumInput;
    Button submitButton;

    String tableNum;
    Integer seatCapacity;
    Integer length;
    Integer width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tableNumInput = (TextInputEditText) findViewById(R.id.table_num_input);


        Spinner seatCapSpinner = (Spinner) findViewById(R.id.seat_cap_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        List<Integer> seats = new ArrayList<Integer>();
        seats.add(1);
        seats.add(2);
        seats.add(3);
        seats.add(4);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, seats);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        seatCapSpinner.setAdapter(adapter);

        Spinner lengthSpinner = (Spinner) findViewById(R.id.length_spinner);
        List<Integer> length_choices = new ArrayList<Integer>();
        length_choices.add(1);
        length_choices.add(2);
        length_choices.add(3);
        length_choices.add(4);
        ArrayAdapter<Integer> lengthAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, length_choices);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lengthSpinner.setAdapter(lengthAdapter);

        Spinner widthSpinner = (Spinner) findViewById(R.id.width_spinner);
        List<String> width_choices = new ArrayList<String>();
        width_choices.add("1");
        width_choices.add("2");
        width_choices.add("3");
        width_choices.add("4");
        ArrayAdapter<String> widthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, width_choices);
        widthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        widthSpinner.setAdapter(widthAdapter);

        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNum = tableNumInput.getText().toString();
                seatCapacity = Integer.valueOf(seatCapSpinner.getSelectedItem().toString());
                width = Integer.valueOf(widthSpinner.getSelectedItem().toString());
                length = Integer.valueOf(lengthSpinner.getSelectedItem().toString());

                Intent intent = new Intent();
                intent.putExtra("tableNum", tableNum);
                intent.putExtra("seatCap", seatCapacity);
                intent.putExtra("length", length);
                intent.putExtra("width", width);
                setResult(RESULT_OK, intent);
                finish();

                //Toast.makeText(getApplicationContext(), String.valueOf(seatCapacity), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
