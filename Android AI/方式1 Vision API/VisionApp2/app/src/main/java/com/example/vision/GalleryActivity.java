package com.example.vision;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = "VisionApp";
    private File[] mImageFiles;
    private VisionHandler mVisionHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mVisionHandler = new VisionHandler(this);
        Bundle bundle = getIntent().getExtras();
        String path = bundle.getString("PICTURES_FOLDER", "");
        final File folder = new File(path);
        mImageFiles = folder.listFiles();

        GridView gridview = findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(listener);
    }

    OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Toast.makeText(GalleryActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            mVisionHandler.setFilePath(mImageFiles[position]);
            mVisionHandler.parse();
        }
    };

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mImageFiles.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Bitmap bmp = BitmapFactory.decodeFile(mImageFiles[position].toString());
            imageView.setImageBitmap(bmp);
            return imageView;
        }
    }
}