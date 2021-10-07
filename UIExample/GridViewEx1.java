package com.demo.ui;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GridViewEx1 extends AppCompatActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gridview);
        GridView gridview = (GridView) findViewById(R.id.GridView01);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(listener);
        setTitle();
    }

    private void setTitle(){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName()+"：圖檔來自專案資源");
    }

    OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Toast.makeText(GridViewEx1.this, "" + position, Toast.LENGTH_SHORT).show();
        }
    };

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Integer[] mThumbIds = {
            R.drawable.sakura01, R.drawable.sakura02, R.drawable.sakura03,
            R.drawable.sakura04, R.drawable.sakura05, R.drawable.sakura06,
            R.drawable.sakura07, R.drawable.sakura08, R.drawable.sakura09,
            R.drawable.sakura10, R.drawable.sakura11, R.drawable.sakura12,
        };

        public ImageAdapter(Context context) {
            mContext = context;
        }

        @Override public int getCount() { return mThumbIds.length; }
        @Override public Object getItem(int position) { return null; }
        @Override public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }
    }
}
