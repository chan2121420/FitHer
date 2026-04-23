package com.fitherapp.activities;

import android.content.Intent;
import android.media.ToneGenerator;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.R;
import com.fitherapp.databinding.ActivityActiveWorkoutBinding;
import com.fitherapp.models.*;
import com.fitherapp.viewmodels.WorkoutViewModel;
import java.util.List;
import java.util.Locale;

public class ActiveWorkoutActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_DAY_ID = "workout_day_id";
    public static final String EXTRA_WORKOUT_DAY_NAME = "workout_day_name";
    public static final String EXTRA_BAND_LEVEL = "band_level";

    private ActivityActiveWorkoutBinding binding;
    private WorkoutViewModel viewModel;

    private List<ExerciseWithDetails> exercises;
    private int currentExerciseIndex = 0;
    private int currentSet = 1;
    private int totalRepsCompleted = 0;
    private int workoutDayId;
    private String bandLevel = "NONE";
    private String workoutDayName = "";

    private long workoutStartTime;
    private CountDownTimer exerciseTimer;
    private CountDownTimer restTimer;
    private boolean isResting = false;
    private static final int REST_DURATION_SECS = 30;

    private ToneGenerator toneGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActiveWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        workoutDayId = getIntent().getIntExtra(EXTRA_WORKOUT_DAY_ID, -1);
        workoutDayName = getIntent().getStringExtra(EXTRA_WORKOUT_DAY_NAME);
        bandLevel = getIntent().getStringExtra(EXTRA_BAND_LEVEL);
        if (bandLevel == null) bandLevel = "NONE";

        toneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, 80);
        workoutStartTime = SystemClock.elapsedRealtime();

        viewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        binding.tvWorkoutTitle.setText(workoutDayName);
        binding.chronometer.setBase(SystemClock.elapsedRealtime());
        binding.chronometer.start();

        viewModel.getExercisesForDay(workoutDayId).observe(this, list -> {
            if (list != null && !list.isEmpty()) {
                exercises = list;
                updateTotalProgress();
                showCurrentExercise();
            }
        });

        binding.btnComplete.setOnClickListener(v -> onExerciseCompleted());
        binding.btnSkip.setOnClickListener(v -> skipExercise());
        binding.btnQuit.setOnClickListener(v -> confirmQuit());
        binding.btnRestSkip.setOnClickListener(v -> skipRest());
    }

    private void showCurrentExercise() {
        if (exercises == null || currentExerciseIndex >= exercises.size()) {
            finishWorkout();
            return;
        }

        isResting = false;
        binding.restLayout.setVisibility(View.GONE);
        binding.exerciseLayout.setVisibility(View.VISIBLE);

        ExerciseWithDetails ewd = exercises.get(currentExerciseIndex);
        Exercise ex = ewd.exercise;
        WorkoutExercise we = ewd.workoutExercise;

        binding.tvExerciseName.setText(ex.name);
        binding.tvMuscleTarget.setText(ex.targetMuscles);
        binding.tvInstructions.setText(ex.instructions);
        binding.tvSetCounter.setText(String.format(Locale.getDefault(),
                "Set %d of %d", currentSet, we.sets));

        if (we.durationSecs > 0) {
            binding.tvRepsOrTime.setText(we.durationSecs + " seconds");
            binding.progressCircular.setVisibility(View.VISIBLE);
            startExerciseTimer(we.durationSecs);
            binding.btnComplete.setText("Done");
        } else {
            binding.tvRepsOrTime.setText(ewd.getDisplayReps());
            binding.progressCircular.setVisibility(View.GONE);
            cancelTimer();
            binding.btnComplete.setText("Completed");
        }

        if (we.isBilateral) {
            binding.tvBilateralNote.setVisibility(View.VISIBLE);
            binding.tvBilateralNote.setText("Do both left and right sides");
        } else {
            binding.tvBilateralNote.setVisibility(View.GONE);
        }

        if (ex.requiresBand && !bandLevel.equals("NONE")) {
            binding.tvBandNote.setVisibility(View.VISIBLE);
            binding.tvBandNote.setText("Use " + bandLevel.toLowerCase() + " resistance band");
        } else {
            binding.tvBandNote.setVisibility(View.GONE);
        }

        updateTotalProgress();
    }

    private void startExerciseTimer(int seconds) {
        cancelTimer();
        binding.progressCircular.setMax(seconds);
        exerciseTimer = new CountDownTimer(seconds * 1000L, 100) {
            @Override
            public void onTick(long ms) {
                int secsLeft = (int)(ms / 1000) + 1;
                binding.tvRepsOrTime.setText(secsLeft + "s");
                binding.progressCircular.setProgress(seconds - secsLeft + 1);
            }
            @Override
            public void onFinish() {
                toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 400);
                binding.tvRepsOrTime.setText("Done!");
                onExerciseCompleted();
            }
        }.start();
    }

    private void onExerciseCompleted() {
        cancelTimer();
        ExerciseWithDetails ewd = exercises.get(currentExerciseIndex);
        totalRepsCompleted += ewd.workoutExercise.reps > 0
                ? ewd.workoutExercise.reps : ewd.workoutExercise.durationSecs;

        if (currentSet < ewd.workoutExercise.sets) {
            currentSet++;
            startRest();
        } else {
            currentSet = 1;
            currentExerciseIndex++;
            if (currentExerciseIndex < exercises.size()) {
                startRest();
            } else {
                finishWorkout();
            }
        }
    }

    private void startRest() {
        isResting = true;
        binding.exerciseLayout.setVisibility(View.GONE);
        binding.restLayout.setVisibility(View.VISIBLE);
        binding.tvRestCountdown.setText(REST_DURATION_SECS + "s");
        binding.restProgressBar.setMax(REST_DURATION_SECS);
        binding.restProgressBar.setProgress(REST_DURATION_SECS);

        if (restTimer != null) restTimer.cancel();
        restTimer = new CountDownTimer(REST_DURATION_SECS * 1000L, 1000) {
            @Override
            public void onTick(long ms) {
                int secsLeft = (int)(ms / 1000) + 1;
                binding.tvRestCountdown.setText(secsLeft + "s");
                binding.restProgressBar.setProgress(secsLeft);
            }
            @Override
            public void onFinish() {
                toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP2, 300);
                showCurrentExercise();
            }
        }.start();
    }

    private void skipRest() {
        if (restTimer != null) restTimer.cancel();
        showCurrentExercise();
    }

    private void skipExercise() {
        cancelTimer();
        currentSet = 1;
        currentExerciseIndex++;
        showCurrentExercise();
    }

    private void cancelTimer() {
        if (exerciseTimer != null) { exerciseTimer.cancel(); exerciseTimer = null; }
    }

    private void updateTotalProgress() {
        if (exercises == null) return;
        int total = exercises.size();
        int done = currentExerciseIndex;
        binding.tvProgressLabel.setText(done + " / " + total + " exercises");
        binding.linearProgress.setMax(total);
        binding.linearProgress.setProgress(done);
    }

    private void finishWorkout() {
        binding.chronometer.stop();
        long elapsed = SystemClock.elapsedRealtime() - workoutStartTime;
        int durationSecs = (int)(elapsed / 1000);

        int estimatedCalories = 0;
        if (exercises != null) {
            estimatedCalories = exercises.size() * 8;
        }

        WorkoutSession session = new WorkoutSession(
                workoutDayId,
                System.currentTimeMillis(),
                durationSecs,
                estimatedCalories,
                bandLevel
        );
        session.totalRepsCompleted = totalRepsCompleted;
        viewModel.saveSession(session);

        Intent intent = new Intent(this, WorkoutCompleteActivity.class);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_DURATION_SECS, durationSecs);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_CALORIES, estimatedCalories);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_EXERCISES_DONE, exercises != null ? exercises.size() : 0);
        intent.putExtra(WorkoutCompleteActivity.EXTRA_WORKOUT_NAME, workoutDayName);
        startActivity(intent);
        finish();
    }

    private void confirmQuit() {
        new AlertDialog.Builder(this)
                .setTitle("Quit Workout?")
                .setMessage("Progress for this session will not be saved.")
                .setPositiveButton("Quit", (d, w) -> finish())
                .setNegativeButton("Keep Going", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
        if (restTimer != null) restTimer.cancel();
        if (toneGenerator != null) toneGenerator.release();
    }
}
