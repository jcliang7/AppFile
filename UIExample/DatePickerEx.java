package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerEx extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_datepicker);

        final DatePicker datePicker = (DatePicker) findViewById(R.id.DatePicker01);
        final Button myButton = (Button) findViewById(R.id.Button01);
        final TextView textView = (TextView) findViewById(R.id.TextView01);

        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                textView.setText(year + "-" + month + "-" + day);
            }
        });

        setTitle();
    }

    private void setTitle() {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }
}
