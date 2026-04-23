package com.fitherapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.fitherapp.R;
import com.fitherapp.database.DataSeeder;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Seed database on background thread
        DataSeeder.seedIfEmpty(this);

        SharedPreferences prefs = getSharedPreferences("fither_prefs", MODE_PRIVATE);
        boolean onboarded = prefs.getBoolean("onboarded", false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent;
            if (!onboarded) {
                intent = new Intent(this, OnboardingActivity.class);
            } else {
                intent = new Intent(this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }, 1800);
    }
}
