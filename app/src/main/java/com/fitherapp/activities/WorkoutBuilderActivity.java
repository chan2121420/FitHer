package com.fitherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.adapters.BuilderExerciseAdapter;
import com.fitherapp.adapters.ExercisePickerAdapter;
import com.fitherapp.databinding.ActivityWorkoutBuilderBinding;
import com.fitherapp.models.*;
import com.fitherapp.viewmodels.MainViewModel;
import java.util.ArrayList;
import java.util.List;

public class WorkoutBuilderActivity extends AppCompatActivity {

    public static final String EXTRA_PLAN_ID = "edit_plan_id";
    private ActivityWorkoutBuilderBinding binding;
    private MainViewModel viewModel;
    private BuilderExerciseAdapter builderAdapter;
    private ExercisePickerAdapter pickerAdapter;
    private final List<Exercise> selectedExercises = new ArrayList<>();
    private int editPlanId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutBuilderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        editPlanId = getIntent().getIntExtra(EXTRA_PLAN_ID, -1);

        binding.toolbar.setNavigationOnClickListener(v -> finish());
        binding.toolbar.setTitle(editPlanId == -1 ? "Build Workout" : "Edit Workout");

        setupBuilderList();
        setupPickerList();
        setupCategoryChips();

        binding.btnSavePlan.setOnClickListener(v -> savePlan());

        viewModel.getAllExercises().observe(this, exercises -> {
            if (exercises != null) pickerAdapter.submitList(exercises);
        });
    }

    private void setupBuilderList() {
        builderAdapter = new BuilderExerciseAdapter(exercise -> {
            selectedExercises.remove(exercise);
            builderAdapter.submitList(new ArrayList<>(selectedExercises));
        });
        binding.rvSelectedExercises.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSelectedExercises.setAdapter(builderAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(RecyclerView rv, RecyclerView.ViewHolder vh, RecyclerView.ViewHolder target) {
                int from = vh.getAdapterPosition();
                int to = target.getAdapterPosition();
                Exercise ex = selectedExercises.remove(from);
                selectedExercises.add(to, ex);
                builderAdapter.submitList(new ArrayList<>(selectedExercises));
                return true;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder vh, int dir) {}
        });
        touchHelper.attachToRecyclerView(binding.rvSelectedExercises);
    }

    private void setupPickerList() {
        pickerAdapter = new ExercisePickerAdapter(exercise -> {
            if (!selectedExercises.contains(exercise)) {
                selectedExercises.add(exercise);
                builderAdapter.submitList(new ArrayList<>(selectedExercises));
                Toast.makeText(this, exercise.name + " added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Already added", Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvExercisePicker.setLayoutManager(new LinearLayoutManager(this));
        binding.rvExercisePicker.setAdapter(pickerAdapter);
    }

    private void setupCategoryChips() {
        String[] cats = {"ALL", "GLUTES", "LEGS", "ABS", "ARMS", "BACK", "FULL_BODY"};
        binding.chipGroupCategory.removeAllViews();
        for (String cat : cats) {
            com.google.android.material.chip.Chip chip = new com.google.android.material.chip.Chip(this);
            chip.setText(cat.equals("ALL") ? "All" : cap(cat));
            chip.setCheckable(true);
            chip.setChecked(cat.equals("ALL"));
            chip.setOnClickListener(v -> {
                if (cat.equals("ALL")) {
                    viewModel.getAllExercises().observe(this, list -> { if (list != null) pickerAdapter.submitList(list); });
                } else {
                    viewModel.getExercisesByCategory(cat).observe(this, list -> { if (list != null) pickerAdapter.submitList(list); });
                }
            });
            binding.chipGroupCategory.addView(chip);
        }
    }

    private String cap(String s) {
        if (s == null || s.isEmpty()) return s;
        String lower = s.toLowerCase().replace("_", " ");
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    private void savePlan() {
        String name = binding.etPlanName.getText().toString().trim();
        if (name.isEmpty()) {
            binding.etPlanName.setError("Enter a plan name");
            return;
        }
        if (selectedExercises.isEmpty()) {
            Toast.makeText(this, "Add at least one exercise", Toast.LENGTH_SHORT).show();
            return;
        }

        WorkoutPlan plan = new WorkoutPlan(name, "", "INTERMEDIATE", "FULL_BODY",
                selectedExercises.size() * 4, selectedExercises.size() * 8);
        plan.isCustom = true;

        viewModel.savePlan(plan, id -> {
            List<WorkoutExercise> wes = new ArrayList<>();
            for (int i = 0; i < selectedExercises.size(); i++) {
                Exercise ex = selectedExercises.get(i);
                WorkoutExercise we = new WorkoutExercise(id, ex.id, i, ex.defaultSets,
                        ex.defaultReps, ex.defaultDurationSecs, ex.restTimeSecs, false, null);
                wes.add(we);
            }
            viewModel.saveWorkoutExercises(wes);
            runOnUiThread(() -> {
                Toast.makeText(this, "Plan saved!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}