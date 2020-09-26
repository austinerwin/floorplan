package com.example.floorplan.ui.table;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.floorplan.R;

public class ViewTable extends AppCompatActivity {

    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_table);
        setTitle("Edit Table");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        deleteButton = (Button) findViewById(R.id.delete_table_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("shouldDelete", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
