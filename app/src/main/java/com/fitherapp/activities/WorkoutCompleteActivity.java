package com.fitherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.fitherapp.databinding.ActivityWorkoutCompleteBinding;
import java.util.Locale;

public class WorkoutCompleteActivity extends AppCompatActivity {

    public static final String EXTRA_DURATION_SECS = "duration_secs";
    public static final String EXTRA_CALORIES = "calories";
    public static final String EXTRA_EXERCISES_DONE = "exercises_done";
    public static final String EXTRA_WORKOUT_NAME = "workout_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWorkoutCompleteBinding binding = ActivityWorkoutCompleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int durationSecs = getIntent().getIntExtra(EXTRA_DURATION_SECS, 0);
        int calories = getIntent().getIntExtra(EXTRA_CALORIES, 0);
        int exercises = getIntent().getIntExtra(EXTRA_EXERCISES_DONE, 0);
        String workoutName = getIntent().getStringExtra(EXTRA_WORKOUT_NAME);

        int mins = durationSecs / 60;
        int secs = durationSecs % 60;

        binding.tvWorkoutName.setText(workoutName != null ? workoutName : "Workout");
        binding.tvDuration.setText(String.format(Locale.getDefault(), "%d:%02d", mins, secs));
        binding.tvCalories.setText(String.valueOf(calories));
        binding.tvExercisesDone.setText(String.valueOf(exercises));
        binding.tvMotivation.setText(getMotivationalMessage());

        binding.btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        });

        binding.btnShare.setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT,
                    "Just completed " + workoutName + " — " + mins + " mins, "
                            + calories + " calories burned! #FitHer #GluteGains");
            startActivity(Intent.createChooser(share, "Share your workout"));
        });
    }

    private String getMotivationalMessage() {
        String[] messages = {
            "Every rep brings you closer to your goal!",
            "Your glutes are growing stronger every session!",
            "Consistency is your superpower. Keep going!",
            "Results don't lie — you're building something amazing!",
            "Another session done. The body you want is being built right now."
        };
        return messages[(int)(Math.random() * messages.length)];
    }
}
