package com.tausif2606.asynctaskexample;

import androidx.appcompat.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mText;
    private ProgressBar mPBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText=findViewById(R.id.textView);
        mPBar=findViewById(R.id.progressBar);
    }
    private static class  SimpleAsyncTask extends AsyncTask<Void, Integer, String >{
        WeakReference<MainActivity> mainActivityWeakReference;
        public SimpleAsyncTask(MainActivity activity)
        {
            mainActivityWeakReference= new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity activity = mainActivityWeakReference.get();
            if(activity==null || activity.isFinishing())
                return;


            activity.mPBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {

            Random r = new Random();


            int n = r.nextInt(1001);

            for(int i=0;i<10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress((i+1) *10);
            }
                return "Awake at last after sleeping for " +  n *10+ "milliseconds!" ;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            MainActivity activity = mainActivityWeakReference.get();
            if(activity==null || activity.isFinishing())
                return;

            activity.mPBar.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity activity = mainActivityWeakReference.get();
            if(activity==null || activity.isFinishing())
                return;
            activity.mText.setText(s);
            activity.mPBar.setProgress(0);
            activity.mPBar.setVisibility(View.INVISIBLE);

        }



    }


    public void startAsyncTask(View view) {

        SimpleAsyncTask task = new SimpleAsyncTask(this);
        task.execute();
    }
}