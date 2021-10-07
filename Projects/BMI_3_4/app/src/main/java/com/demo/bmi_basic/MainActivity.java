package com.demo.bmi_basic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText fieldheight;
    EditText fieldweight;
    TextView result;
    TextView suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    private void setListeners(){
        button.setOnClickListener(listener);
    }

    private void findViews(){
        button = findViewById(R.id.submit);
        fieldheight = findViewById(R.id.height);
        fieldweight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
        suggest = findViewById(R.id.suggest);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                    double BMI = calcBMI();
                    showResult(BMI);
                    openOptionsDialog();
            }catch(Exception e){

            }
        }
    };

    private double calcBMI(){
            double height = Double.parseDouble(fieldheight.getText() + "") / 100;
            double weight = Double.parseDouble(fieldweight.getText() + "");
            double BMI = weight / (height * height);
            return BMI;
    }

    private void showResult(double BMI){
        DecimalFormat df = new DecimalFormat("0.00");
        result.setText(df.format(BMI));

        if(BMI>25)
            suggest.setText(R.string.advice_heavy);
        else if(BMI<20)
            suggest.setText(R.string.advice_light);
        else
            suggest.setText(R.string.advice_average);
    }

    private void openOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_button_confirm, null)
                .setNeutralButton(R.string.homepage_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("geo:24.957405,121.240760");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.dialog_button_cancel, null)
                .show();
    }
}