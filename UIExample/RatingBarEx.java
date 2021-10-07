package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingBarEx extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ratingbar);
        final TextView textView01 = (TextView) findViewById(R.id.TextView01);
        final TextView textView02 = (TextView) findViewById(R.id.TextView02);
        final RatingBar ratingBar01 = (RatingBar) findViewById(R.id.RatingBar01);
        ratingBar01.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView01.setText(Float.toString(ratingBar01.getRating()));
            }
        });
        final RatingBar ratingBar02 = (RatingBar) findViewById(R.id.RatingBar02);
        ratingBar02.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView02.setText(Float.toString(ratingBar02.getRating()));
            }
        });
        setTitle();
    }

    private void setTitle(){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }
}
