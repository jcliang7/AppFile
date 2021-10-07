package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerEx extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timepicker);

        final TimePicker timePicker = (TimePicker) findViewById(R.id.TimePicker01);
        final Button myButton = (Button) findViewById(R.id.Button01);
        final TextView textView = (TextView) findViewById(R.id.TextView01);

        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String formatedMinute = String.format("%02d",minute);
                textView.setText(hour + " : " + formatedMinute);
            }
        });
        setTitle();
    }

    private void setTitle(){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }
}
