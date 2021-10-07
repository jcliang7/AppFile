package com.demo.ui;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SeekBarEx extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    ImageView ivTarget;
    SeekBar seekBar1, seekBar2, seekBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_seekbar);
        findViews();
        setListeners();
        setTitle();
    }

    private void setTitle(){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }

    void findViews() {
        ivTarget = (ImageView) findViewById(R.id.ivBgCircle);
        ivTarget.setColorFilter(Color.parseColor("#000000"));
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
    }

    void setListeners() {
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
    }

    int Alpha = 255, r, g, b;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()) {
            case R.id.seekBar1:
                r = progress;
                break;
            case R.id.seekBar2:
                g = progress;
                break;
            case R.id.seekBar3:
                b = progress;
                break;
        }

        int color = Alpha * (1 << 24) + r * (1<< 16) + g * (1<< 8) + b;
        ivTarget.setColorFilter(color);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
