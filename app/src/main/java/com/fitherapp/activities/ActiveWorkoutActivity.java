package com.fitherapp.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.databinding.ActivityActiveWorkoutBinding;
import com.fitherapp.models.*;
import com.fitherapp.viewmodels.MainViewModel;
import java.util.List;
import java.util.Locale;

public class ActiveWorkoutActivity extends AppCompatActivity {

    public static final String EXTRA_PLAN_ID = "plan_id";
    public static final String EXTRA_PLAN_NAME = "plan_name";
    public static final String EXTRA_BAND_LEVEL = "band_level";

    private ActivityActiveWorkoutBinding binding;
    private MainViewModel viewModel;
    private List<ExerciseWithDetails> exercises;
    private int currentIndex = 0;
    private int currentSet = 1;
    private int totalReps = 0;
    private int planId;
    private String planName = "";
    private String bandLevel = "NONE";
    private long workoutStart;
    private CountDownTimer exerciseTimer;
    private CountDownTimer restTimer;
    private static final int REST_SECS = 30;
    private ToneGenerator toneGen;
    private Exercise currentExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActiveWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        planId = getIntent().getIntExtra(EXTRA_PLAN_ID, -1);
        planName = getIntent().getStringExtra(EXTRA_PLAN_NAME);
        bandLevel = getIntent().getStringExtra(EXTRA_BAND_LEVEL);
        if (bandLevel == null) bandLevel = "NONE";
        if (planName == null) planName = "Workout";

        toneGen = new ToneGenerator(AudioManager.STREAM_MUSIC, 80);
        workoutStart = SystemClock.elapsedRealtime();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.tvWorkoutTitle.setText(planName);
        binding.chronometer.setBase(SystemClock.elapsedRealtime());
        binding.chronometer.start();

        viewModel.getExercisesForPlan(planId).observe(this, list -> {
            if (list != null && !list.isEmpty()) {
                exercises = list;
                updateProgress();
                showCurrentExercise();
            }
        });

        binding.btnComplete.setOnClickListener(v -> onExerciseCompleted());
        binding.btnSkip.setOnClickListener(v -> skipExercise());
        binding.btnQuit.setOnClickListener(v -> confirmQuit());
        binding.btnRestSkip.setOnClickListener(v -> skipRest());
        binding.btnTutorial.setOnClickListener(v -> {
            if (currentExercise != null) {
                ExerciseTutorialActivity.launch(this, currentExercise);
            }
        });
    }

    private void showCurrentExercise() {
        if (exercises == null || currentIndex >= exercises.size()) {
            finishWorkout();
            return;
        }

        binding.restLayout.setVisibility(View.GONE);
        binding.exerciseLayout.setVisibility(View.VISIBLE);

        ExerciseWithDetails ewd = exercises.get(currentIndex);
        currentExercise = ewd.exercise;
        Exercise ex = ewd.exercise;
        WorkoutExercise we = ewd.workoutExercise;

        binding.tvExerciseName.setText(ex.name);
        binding.tvMuscleTarget.setText(ex.targetMuscles);
        binding.tvSetCounter.setText(String.format(Locale.getDefault(), "Set %d of %d", currentSet, we.sets));

        // Notes/coaching
        if (we.notes != null && !we.notes.isEmpty()) {
            binding.tvCoachNote.setText("💡 " + we.notes);
            binding.tvCoachNote.setVisibility(View.VISIBLE);
        } else if (ex.tips != null && !ex.tips.isEmpty()) {
            // Show first sentence of tips
            String tip = ex.tips.split("\\.")[0];
            binding.tvCoachNote.setText("💡 " + tip);
            binding.tvCoachNote.setVisibility(View.VISIBLE);
        } else {
            binding.tvCoachNote.setVisibility(View.GONE);
        }

        // Breathing cue
        if (ex.breathingCue != null && !ex.breathingCue.isEmpty()) {
            binding.tvBreathingCue.setText("🫁 " + ex.breathingCue);
            binding.tvBreathingCue.setVisibility(View.VISIBLE);
        } else {
            binding.tvBreathingCue.setVisibility(View.GONE);
        }

        if (we.durationSecs > 0) {
            binding.tvRepsOrTime.setText(we.durationSecs + "s");
            binding.progressCircular.setVisibility(View.VISIBLE);
            startExerciseTimer(we.durationSecs);
            binding.btnComplete.setText("Done");
        } else {
            binding.tvRepsOrTime.setText(ewd.getDisplayReps());
            binding.progressCircular.setVisibility(View.GONE);
            cancelExerciseTimer();
            binding.btnComplete.setText("Completed ✓");
        }

        if (we.isBilateral) {
            binding.tvBilateralNote.setVisibility(View.VISIBLE);
        } else {
            binding.tvBilateralNote.setVisibility(View.GONE);
        }

        if (ex.requiresBand && !bandLevel.equals("NONE")) {
            binding.tvBandNote.setVisibility(View.VISIBLE);
            binding.tvBandNote.setText("🔴 Using: " + bandLevel.toLowerCase() + " band");
        } else {
            binding.tvBandNote.setVisibility(View.GONE);
        }

        // Instructions preview
        if (ex.instructions != null && !ex.instructions.isEmpty()) {
            // Show step 1 only in active workout
            String[] steps = ex.instructions.split("\n");
            if (steps.length > 0) {
                binding.tvInstructions.setText(steps[0]);
            }
        }

        updateProgress();
    }

    private void startExerciseTimer(int seconds) {
        cancelExerciseTimer();
        binding.progressCircular.setMax(seconds);
        exerciseTimer = new CountDownTimer(seconds * 1000L, 100) {
            @Override
            public void onTick(long ms) {
                int left = (int)(ms / 1000) + 1;
                binding.tvRepsOrTime.setText(left + "s");
                binding.progressCircular.setProgress(seconds - left + 1);
            }
            @Override
            public void onFinish() {
                toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 400);
                binding.tvRepsOrTime.setText("Done!");
                onExerciseCompleted();
            }
        }.start();
    }

    private void onExerciseCompleted() {
        cancelExerciseTimer();
        ExerciseWithDetails ewd = exercises.get(currentIndex);
        totalReps += ewd.workoutExercise.reps > 0 ? ewd.workoutExercise.reps : ewd.workoutExercise.durationSecs;

        if (currentSet < ewd.workoutExercise.sets) {
            currentSet++;
            int restSecs = ewd.workoutExercise.restTimeSecs > 0 ? ewd.workoutExercise.restTimeSecs : REST_SECS;
            startRest(restSecs, false);
        } else {
            currentSet = 1;
            currentIndex++;
            if (currentIndex < exercises.size()) {
                startRest(REST_SECS, true);
            } else {
                finishWorkout();
            }
        }
    }

    private void startRest(int secs, boolean isExerciseTransition) {
        binding.exerciseLayout.setVisibility(View.GONE);
        binding.restLayout.setVisibility(View.VISIBLE);

        String nextLabel = "";
        if (isExerciseTransition && currentIndex < exercises.size()) {
            nextLabel = "Next: " + exercises.get(currentIndex).exercise.name;
        } else if (!isExerciseTransition && currentIndex < exercises.size()) {
            ExerciseWithDetails ewd = exercises.get(currentIndex);
            nextLabel = "Set " + currentSet + " of " + ewd.workoutExercise.sets + " — " + ewd.exercise.name;
        }
        binding.tvNextUp.setText(nextLabel);
        binding.tvRestCountdown.setText(secs + "s");
        binding.restProgressBar.setMax(secs);
        binding.restProgressBar.setProgress(secs);

        if (restTimer != null) restTimer.cancel();
        restTimer = new CountDownTimer(secs * 1000L, 1000) {
            @Override
            public void onTick(long ms) {
                int left = (int)(ms / 1000) + 1;
                binding.tvRestCountdown.setText(left + "s");
                binding.restProgressBar.setProgress(left);
            }
            @Override
            public void onFinish() {
                toneGen.startTone(ToneGenerator.TONE_PROP_BEEP2, 300);
                showCurrentExercise();
            }
        }.start();
    }

    private void skipRest() { if (restTimer != null) restTimer.cancel(); showCurrentExercise(); }
    private void skipExercise() { cancelExerciseTimer(); currentSet = 1; currentIndex++; showCurrentExercise(); }
    private void cancelExerciseTimer() { if (exerciseTimer != null) { exerciseTimer.cancel(); exerciseTimer = null; } }

    private void updateProgress() {
        if (exercises == null) return;
        int total = exercises.size();
        binding.tvProgressLabel.setText(currentIndex + " / " + total);
        binding.linearProgress.setMax(total);
        binding.linearProgress.setProgress(currentIndex);
    }

    private void finishWorkout() {
        binding.chronometer.stop();
        int durationSecs = (int)((SystemClock.elapsedRealtime() - workoutStart) / 1000);
        int calories = exercises != null ? exercises.size() * 10 : 0;

        WorkoutHistory history = new WorkoutHistory(0, planId, planName,
                System.currentTimeMillis(), durationSecs, calories, bandLevel);
        history.totalReps = totalReps;
        viewModel.saveHistory(history);

        Intent intent = new Intent(this, WorkoutCompleteActivity.class);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_DURATION_SECS, durationSecs);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_CALORIES, calories);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_EXERCISES_DONE, exercises != null ? exercises.size() : 0);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_WORKOUT_NAME, planName);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_TOTAL_REPS, totalReps);
        startActivity(intent);
        finish();
    }

    private void confirmQuit() {
        new AlertDialog.Builder(this)
                .setTitle("Quit Workout?")
                .setMessage("Progress will not be saved.")
                .setPositiveButton("Quit", (d, w) -> finish())
                .setNegativeButton("Keep Going", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelExerciseTimer();
        if (restTimer != null) restTimer.cancel();
        if (toneGen != null) toneGen.release();
    }
}