package com.example.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationJobService extends JobService {

    NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onStartJob(JobParameters params) {

        createNotificationChannel();

        PendingIntent contentPendingIntent = PendingIntent.getActivity(
                this,0,new Intent(this,MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,PRIMARY_CHANNEL_ID).
                setContentTitle("Job Service").setContentText("Job Complete!")
                .setSmallIcon(R.drawable.ic_job_running).setPriority(NotificationCompat.PRIORITY_DEFAULT).
                setDefaults(NotificationCompat.DEFAULT_ALL).setAutoCancel(true);

        notificationManager.notify(0,builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                "Job Service Notification", NotificationManager.IMPORTANCE_DEFAULT);

        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("Notification from Job Service");
        notificationManager.createNotificationChannel(notificationChannel);
    }
}
