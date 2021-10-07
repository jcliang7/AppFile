package com.demo.bmi_basic;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.submit);
        button.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double BMI = calcBMI();
            showResult(BMI);
        }
    };

    private double calcBMI(){
        EditText fieldheight = findViewById(R.id.height);
        EditText fieldweight = findViewById(R.id.weight);
        double height = Double.parseDouble(fieldheight.getText()+"")/100;
        double weight = Double.parseDouble(fieldweight.getText()+"");
        double BMI = weight / (height*height);
        return BMI;
    }

    private void showResult(double BMI){
        DecimalFormat df = new DecimalFormat("0.00");
        TextView result = findViewById(R.id.result);
        result.setText(df.format(BMI));
        TextView suggest = findViewById(R.id.suggest);

        if(BMI>25)
            suggest.setText(R.string.advice_heavy);
        else if(BMI<20)
            suggest.setText(R.string.advice_light);
        else
            suggest.setText(R.string.advice_average);

    }




}