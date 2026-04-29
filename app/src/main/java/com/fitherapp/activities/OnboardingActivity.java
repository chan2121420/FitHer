package com.fitherapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.databinding.ActivityOnboardingBinding;
import com.fitherapp.models.User;
import com.fitherapp.viewmodels.MainViewModel;

public class OnboardingActivity extends AppCompatActivity {

    private ActivityOnboardingBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.btnGetStarted.setOnClickListener(v -> {
            String name = binding.etName.getText().toString().trim();
            if (name.isEmpty()) {
                binding.etName.setError("Please enter your name");
                return;
            }

            String goal = "GLUTES";
            int checkedId = binding.rgGoal.getCheckedRadioButtonId();
            if (checkedId == com.fitherapp.R.id.rbGoalGlutes) goal = "GLUTES";
            else if (checkedId == com.fitherapp.R.id.rbGoalLegs) goal = "LEGS";
            else if (checkedId == com.fitherapp.R.id.rbGoalFullBody) goal = "FULL_BODY";
            else if (checkedId == com.fitherapp.R.id.rbGoalWeightLoss) goal = "WEIGHT_LOSS";

            String level = "BEGINNER";
            int levelId = binding.rgLevel.getCheckedRadioButtonId();
            if (levelId == com.fitherapp.R.id.rbLevelIntermediate) level = "INTERMEDIATE";
            else if (levelId == com.fitherapp.R.id.rbLevelAdvanced) level = "ADVANCED";

            User user = new User("", name, level, goal);
            viewModel.saveUser(user);

            SharedPreferences prefs = getSharedPreferences("fither_prefs", MODE_PRIVATE);
            prefs.edit()
                    .putBoolean("onboarded", true)
                    .putString("user_name", name)
                    .putString("user_goal", goal)
                    .putString("user_level", level)
                    .apply();

            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });
    }
}