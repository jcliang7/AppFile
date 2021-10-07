package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerEx extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spinner);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.cards, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(listener);
        spinner.setAdapter(adapter);
        setTitle();
    }

    private void setTitle(){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }

    private OnItemSelectedListener listener = new OnItemSelectedListener() {
        boolean isFirstTime = true;
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            if (!isFirstTime)
                Toast.makeText(SpinnerEx.this, ((TextView) v).getText(),
                    Toast.LENGTH_SHORT).show();
            else
                isFirstTime = false;
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {     }
    };
}
