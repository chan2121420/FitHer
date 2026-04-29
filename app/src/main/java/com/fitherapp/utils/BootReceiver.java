package com.fitherapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            SharedPreferences prefs = context.getSharedPreferences("fither_prefs", Context.MODE_PRIVATE);
            if (prefs.getBoolean("reminders_on", true)) {
                ReminderScheduler.scheduleDaily(context);
            }
        }
    }
}