package com.example.f4cmpro.evdictionaryapp.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.f4cmpro.evdictionaryapp.Activity.View.MainActivity;
import com.example.f4cmpro.evdictionaryapp.Activity.View.QuizActivity;
import com.example.f4cmpro.evdictionaryapp.R;

public class AlarmReceiver extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 1;
    private NotificationCompat.Builder mBuilder;
    private Notification mNotifi;
    private NotificationManager mNotifiManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("AR", "OnReceive() in AlarmReceiver!");
        //Create Builder
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_notifications);
        mBuilder.setContentTitle("Quiz");
        mBuilder.setContentText("It's for practice!");
        mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        // Creates an explicit intent for an Activity in your app
        Intent myIntent = new Intent(context, QuizActivity.class);
        //call activity outside activity
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your app to the Home screen
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(myIntent);
        PendingIntent myPI = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(myPI);
        mNotifi = mBuilder.build();
        mNotifi.fullScreenIntent = myPI;
        mNotifiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifiManager.notify(NOTIFICATION_ID, mNotifi);
    }
}
