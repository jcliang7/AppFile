package com.demo.bmi_basic;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText feet, inch, height;
    EditText weight;
    TextView result;
    TextView suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        findViews();
        setListeners();
    }

    void findViews() {
        button = findViewById(R.id.submit);
        height = findViewById(R.id.height);
        feet = findViewById(R.id.feet);
        inch = findViewById(R.id.inch);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
        suggest = findViewById(R.id.suggest);
    }

    void setListeners() {
        button.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Locale lang = getResources().getConfiguration().locale;
                if (lang.getLanguage().equals("zh"))
                    showResult(calcBMI());
                else
                    showResult(calcBMI_en());

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "是否忘記輸入?", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    };

    double calcBMI() {
        double h = Double.parseDouble(height.getText().toString()) / 100;
        double w = Double.parseDouble(weight.getText().toString());
        double BMI = w / (h * h);
        return BMI;
    }

    double calcBMI_en() {
        double h1 = Double.parseDouble(feet.getText().toString());
        double h2 = Double.parseDouble(inch.getText().toString());
        double h = (h1 * 12 + h2) * 2.54 / 100;
        double w = Double.parseDouble(weight.getText().toString()) * 0.45359;
        double BMI = w / (h * h);
        return BMI;
    }

    void showResult(double BMI) {
        DecimalFormat df = new DecimalFormat("0.00");
        result.setText(getString(R.string.bmi_result) + df.format(BMI));

        if (BMI > 25)
            suggest.setText(getString(R.string.advice_heavy));
        else if (BMI < 20)
            suggest.setText(getString(R.string.advice_light));
        else
            suggest.setText(getString(R.string.advice_average));
    }
}