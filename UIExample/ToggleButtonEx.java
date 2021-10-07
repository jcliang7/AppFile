package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ToggleButtonEx extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_togglebutton);

        final TextView textView01 = (TextView) findViewById(R.id.TextView01);
        final ToggleButton toggleButton01 = (ToggleButton)
                findViewById(R.id.ToggleButton01);
        toggleButton01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (toggleButton01.isChecked()) {
                    textView01.setText("Now is ON");
                } else {
                    textView01.setText("Now is OFF");
                }
            }
        });
        final TextView textView02 = (TextView) findViewById(R.id.TextView02);
        final ToggleButton toggleButton02 = (ToggleButton)
                findViewById(R.id.ToggleButton02);
        toggleButton02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (toggleButton02.isChecked()) {
                    textView02.setText("Now is ON");
                } else {
                    textView02.setText("Now is OFF");
                }
            }
        });
        setTitle();
    }

    private void setTitle(){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }
}
