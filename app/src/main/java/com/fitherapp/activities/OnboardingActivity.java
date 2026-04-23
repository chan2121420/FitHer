package com.fitherapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.fitherapp.R;
import com.fitherapp.databinding.ActivityOnboardingBinding;

public class OnboardingActivity extends AppCompatActivity {

    private ActivityOnboardingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetStarted.setOnClickListener(v -> {
            String name = binding.etName.getText().toString().trim();
            String weightStr = binding.etWeight.getText().toString().trim();
            String goal = binding.etGoal.getText().toString().trim();

            if (name.isEmpty()) {
                binding.etName.setError("Please enter your name");
                return;
            }

            SharedPreferences prefs = getSharedPreferences("fither_prefs", MODE_PRIVATE);
            prefs.edit()
                    .putBoolean("onboarded", true)
                    .putString("user_name", name)
                    .putString("user_weight", weightStr)
                    .putString("user_goal", goal.isEmpty() ? "Grow my glutes and hips" : goal)
                    .apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
