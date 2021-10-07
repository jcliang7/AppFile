package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class EditTextEx extends AppCompatActivity {

    EditText etEmail, etPassword;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edittext);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        setTitle();
    }

    public void onSingIn(View v) {
        if ("abc@gmail.com".equalsIgnoreCase(etEmail.getText().toString())
                && "password".equalsIgnoreCase(etPassword.getText().toString()))
            Toast.makeText(this, "驗證成功!!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "驗證失敗!!", Toast.LENGTH_SHORT).show();
    }

    private void setTitle() {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }
}
