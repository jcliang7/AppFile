package com.example.uiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextEx extends AppCompatActivity {

    EditText etEmail, etPassword;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_ex);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
    }

    public void onSingIn(View v) {
        if ("abc@gmail.com".equalsIgnoreCase(etEmail.getText().toString())
                && "password".equalsIgnoreCase(etPassword.getText().toString()))
            Toast.makeText(this, "驗證成功!!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "驗證失敗!!", Toast.LENGTH_SHORT).show();
    }
}