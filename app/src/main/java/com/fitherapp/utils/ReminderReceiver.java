package com.fitherapp.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.fitherapp.R;
import com.fitherapp.activities.MainActivity;

public class ReminderReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "fither_reminders";
    public static final int NOTIF_ID = 1001;

    @Override
    public void onReceive(Context context, Intent intent) {
        createChannel(context);
        Intent openApp = new Intent(context, MainActivity.class);
        openApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 0, openApp,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        String[] messages = {
                "Time to train! Your body won't change itself. 💪",
                "Today's session is waiting for you. Let's go!",
                "Consistency builds the body you want. Start now.",
                "Don't break your streak! Open FitHer.",
                "One session closer to your goal. You've got this!"
        };
        String msg = messages[(int)(Math.random() * messages.length)];

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("FitHer — Time to Train")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)
                .setAutoCancel(true);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NOTIF_ID, builder.build());
    }

    public static void createChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "Workout Reminders", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Daily workout reminder notifications");
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }
}