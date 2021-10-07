package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxEx extends AppCompatActivity {

    TextView result;
    int[] checkBoxResIds = {
            R.id.CheckBox01,
            R.id.CheckBox02,
            R.id.CheckBox03,
            R.id.CheckBox04,
            R.id.CheckBox05,
            R.id.CheckBox06,
            };

    CheckBox[] checkBoxs = new CheckBox[checkBoxResIds.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_checkbox);
        findViews();
        setTitle();
    }

    private void setTitle(){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }

    private void findViews() {
        result = (TextView) findViewById(R.id.result);
        for (int i = 0; i < checkBoxResIds.length; i++)
            checkBoxs[i] = (CheckBox) findViewById(checkBoxResIds[i]);
    }

    public void onSubmit(View v) {
        String text = "";
        for (int i = 0; i < checkBoxResIds.length; i++) {
            if (i == 0)
                text += "你喜歡的顏色是：\n";

            if (i < 3 && checkBoxs[i].isChecked())
                text += checkBoxs[i].getText() + "\n";

            if (i == 3)
                text += "平常的休閒活動是：\n";

            if (i >= 3 && checkBoxs[i].isChecked())
                text += checkBoxs[i].getText() + "\n";

        }
        result.setText(text);
    }
}
