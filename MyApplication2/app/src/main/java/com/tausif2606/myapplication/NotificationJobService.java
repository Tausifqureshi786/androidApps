package com.tausif2606.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class NotificationJobService extends JobService {

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    NotificationManager mNotifyManager;

    protected class MyBackgroundWork extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            Log.i("Background Work", "Started............");

            for(int i=0;i<10;i++)
            {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("Background Work", "Started............" + i);
            }

            return "Background Work Completed!!";
        }
    }

    public void createNotificationChannel() {

        // Define notification manager object.
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Job Service notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifications from Job Service");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
    @Override
    public boolean onStartJob(JobParameters params) {

        //Create the notification channel
        createNotificationChannel();

        //Set up the notification content intent to launch the app when clicked
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, 0, new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Your Job ran to completion!")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_job_running)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotifyManager.notify(0, builder.build());

        MyBackgroundWork backgroundWork=new MyBackgroundWork(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.i("Background Work", "Before starting background Work............");

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                jobFinished(params,true);
                Log.i("Background Work", "");

                Toast.makeText(NotificationJobService.this, s, Toast.LENGTH_SHORT).show();

            }
        };

        backgroundWork.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
