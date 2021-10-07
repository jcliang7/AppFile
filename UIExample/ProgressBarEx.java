package com.demo.ui;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressBarEx extends AppCompatActivity {

    ProgressBar progressBar01, progressBar02, progressBar03, progressBar04;
    TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_progressbar);
        setTitle();
        percent = (TextView) findViewById(R.id.percent);
        progressBar01 = (ProgressBar) findViewById(R.id.ProgressBar01);
        progressBar02 = (ProgressBar) findViewById(R.id.ProgressBar02);
        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            int total = 1000;
            int receive = 0;
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(receive += 20, total);
                if (receive == total)
                    break;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = (int) (((double) values[0]) / values[1] * progressBar01.getMax());
            progressBar01.setProgress(progress);
            percent.setText(progress + " %");
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ProgressBarEx.this, "任務完成", Toast.LENGTH_LONG).show();
        }
    }

    private void setTitle() {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getClass().getSimpleName());
    }
}
