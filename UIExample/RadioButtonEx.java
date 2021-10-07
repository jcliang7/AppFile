package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioButtonEx extends AppCompatActivity {

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_radiobutton);
        rg = (RadioGroup) findViewById(R.id.RadioGroup01);
        setTitle();
    }

    private void setTitle() {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }

    public void onResult(View v) {
        int rbtnId = rg.getCheckedRadioButtonId();
        RadioButton rbtn = (RadioButton) findViewById(rbtnId);
        String item = rbtn.getText().toString();
        Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
    }
}
