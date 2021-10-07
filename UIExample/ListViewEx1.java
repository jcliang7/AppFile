package com.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewEx1 extends AppCompatActivity {

    String[] Data = {"網路概論", "電腦概論", "資料庫概論"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter vArrayData = new ArrayAdapter(
                this, android.R.layout.simple_list_item_multiple_choice, Data);

        listView.setAdapter(vArrayData);
        listView.setOnItemClickListener(listener);
        setContentView(listView);
        setTitle();
    }

    private void setTitle() {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }

    private OnItemClickListener listener = new OnItemClickListener() {
        boolean[] isChecked = new boolean[Data.length];

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            isChecked[position] = ((CheckedTextView) view).isChecked();
            String checkedItems = "";
            int checkedCounts = 0;
            for (int i = 0; i < Data.length; i++) {
                if (isChecked[i]) {
                    checkedCounts++;
                    if (checkedCounts <= 1)
                        checkedItems += Data[i];
                    else
                        checkedItems += "\n" + Data[i];
                }
            }
            if (checkedCounts != 0)
                Toast.makeText(ListViewEx1.this, checkedItems, Toast.LENGTH_SHORT).show();
        }
    };
}
