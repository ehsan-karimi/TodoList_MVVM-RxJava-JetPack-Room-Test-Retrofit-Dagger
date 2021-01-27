package com.example.todolist.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;



import com.example.todolist.Main.MainActivity;
import com.example.todolist.R;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DaggerBroadcastReceiver;


public class ScheduleAfterReboot extends DaggerBroadcastReceiver {
    @Inject
    public ScheduleNotification scheduleNotification;

//    @Override
//    public void onReceive(Context context, Intent intent) {
//
//
//        scheduleNotification.createNotificationChannel();
//        scheduleNotification.scheduleNotification(scheduleNotification.getNotification(R.drawable.ic_calendar, "test title", "test content"), 5000);
//
//      //  startActivityNotification(context, 11, "dyfu", "fttdr");
//    }

    public static void startActivityNotification(Context context, int notificationID,
                                                 String title, String message) {

        NotificationManager mNotificationManager =
                (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Create GPSNotification builder
        NotificationCompat.Builder mBuilder;

        //Initialise ContentIntent
        Intent ContentIntent = new Intent(context, MainActivity.class);
        ContentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent ContentPendingIntent = PendingIntent.getActivity(context,
                0,
                ContentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_github)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setAutoCancel(true)
                .setContentIntent(ContentPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("11",
                    "Activity Opening Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setDescription("Activity opening notification");

            mBuilder.setChannelId("11");

            Objects.requireNonNull(mNotificationManager).createNotificationChannel(mChannel);
        }

        Objects.requireNonNull(mNotificationManager).notify("11", notificationID,
                mBuilder.build());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AndroidInjection.inject(this, context);
        super.onReceive(context, intent);
        scheduleNotification.createNotificationChannel();
        scheduleNotification.scheduleNotification(scheduleNotification.getNotification(R.drawable.ic_calendar, "test title", "test content"), 5000);

    }
}
