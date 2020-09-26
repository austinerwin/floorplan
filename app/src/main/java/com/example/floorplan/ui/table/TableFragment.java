package com.example.floorplan.ui.table;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.floorplan.R;
import com.google.android.flexbox.FlexboxLayout;

import static android.app.Activity.RESULT_OK;

public class TableFragment extends Fragment {

    private TableViewModel tableViewModel;
    Button button;
    FlexboxLayout layout;
    int tag = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        tableViewModel =
                ViewModelProviders.of(this).get(TableViewModel.class);
        View root = inflater.inflate(R.layout.fragment_table, container, false);
        layout = (FlexboxLayout) root.findViewById(R.id.flexbox);
        button = (Button) root.findViewById(R.id.add_table);
        button.setOnClickListener(v -> openNewActivity());

        /*final TextView textView = root.findViewById(R.id.text_table);
        tableViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    public void openNewActivity(){
        Intent intent = new Intent(getView().getContext(), ActivityNewTable.class);
        startActivityForResult(intent, 1);
    }

    public void openViewTableActivity(){
        Intent intent = new Intent(getView().getContext(), ViewTable.class);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {

            if (data.hasExtra("tableNum")) {
                String tableNum = data.getExtras().getString("tableNum", " ");
                int seatCap = data.getExtras().getInt("seatCap", 1);
                int length = data.getExtras().getInt("length", 1);
                int width = data.getExtras().getInt("width", 1);
                Button button = new Button(getContext());
                FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(length*150, width*150);
                params.setMargins(30,30,30,30);
                button.setLayoutParams(params);
                if (seatCap == 1) button.setText(tableNum + " (" + seatCap + " " + "seat)");
                else button.setText(tableNum + " (" + seatCap + " " + "seats)");
                button.setTag(Integer.toString(tag++));
                button.setBackgroundResource(R.color.colorTable);
                layout.addView(button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getContext(), data.getExtras().getString("tableNum"), Toast.LENGTH_SHORT).show();
                        openViewTableActivity();
                    }
                });
            }
        }
        if (resultCode == RESULT_OK && requestCode == 2) {
            if (data.hasExtra("shouldDelete")) {
                boolean shouldDelete = data.getExtras().getBoolean("shouldDelete", false);
                if (shouldDelete) {
                    // TODO: delete button
                    //Toast.makeText(getContext(), "Table Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}