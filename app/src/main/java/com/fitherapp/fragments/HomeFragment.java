package com.fitherapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.activities.ActiveWorkoutActivity;
import com.fitherapp.databinding.FragmentHomeBinding;
import com.fitherapp.models.WorkoutDay;
import com.fitherapp.viewmodels.WorkoutViewModel;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WorkoutViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        SharedPreferences prefs = requireActivity().getSharedPreferences("fither_prefs", MODE_PRIVATE);
        String name = prefs.getString("user_name", "Warrior");
        binding.tvGreeting.setText("Hey " + name + "!");

        viewModel.getCurrentStreak().observe(getViewLifecycleOwner(), streak ->
                binding.tvStreak.setText(streak + " day streak"));

        viewModel.getTotalCaloriesThisWeek().observe(getViewLifecycleOwner(), cal ->
                binding.tvCaloriesWeek.setText(cal + " kcal this week"));

        viewModel.getAllWorkoutDays().observe(getViewLifecycleOwner(), this::setupTodayCard);

        binding.btnStartWorkout.setOnClickListener(v -> launchTodayWorkout());
    }

    private void setupTodayCard(List<WorkoutDay> days) {
        if (days == null || days.isEmpty()) return;
        int dow = getTodayDow();
        WorkoutDay today = null;
        for (WorkoutDay d : days) {
            if (d.dayOfWeek == dow) { today = d; break; }
        }
        if (today == null) return;

        binding.tvTodayLabel.setText(today.dayName);
        binding.tvTodayFocus.setText(today.focusTitle);

        if (today.isRestDay) {
            binding.tvTodayMeta.setText("Rest day — take a walk");
            binding.btnStartWorkout.setEnabled(false);
            binding.btnStartWorkout.setText("Rest Day");
        } else {
            binding.tvTodayMeta.setText(today.estimatedMins + " min  ·  ~" + today.estimatedCalories + " kcal");
            binding.btnStartWorkout.setEnabled(true);
            binding.btnStartWorkout.setText("Start Today's Workout");

            final WorkoutDay finalToday = today;
            binding.btnStartWorkout.setOnClickListener(v -> showBandDialog(finalToday));
        }

        // Set focus colour chip
        int chipColor;
        switch (today.focusType) {
            case "GLUTE": chipColor = 0xFF1D9E75; break;
            case "HIP":   chipColor = 0xFF534AB7; break;
            default:      chipColor = 0xFF888780; break;
        }
        binding.chipFocusType.setBackgroundColor(chipColor);
        binding.chipFocusType.setText(today.isRestDay ? "REST" : today.focusType);
    }

    private void showBandDialog(WorkoutDay day) {
        String[] options = {"No band (bodyweight)", "Light resistance band", "Heavy resistance band"};
        String[] levels = {"NONE", "LIGHT", "HEAVY"};

        new AlertDialog.Builder(requireContext())
                .setTitle("Band level for today?")
                .setItems(options, (dialog, which) -> {
                    Intent intent = new Intent(requireActivity(), ActiveWorkoutActivity.class);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_WORKOUT_DAY_ID, day.id);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_WORKOUT_DAY_NAME, day.dayName + " — " + day.focusTitle);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_BAND_LEVEL, levels[which]);
                    startActivity(intent);
                })
                .show();
    }

    private void launchTodayWorkout() {}

    private int getTodayDow() {
        int cal = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        // Calendar: SUN=1,MON=2... → our model: MON=1,TUE=2...SUN=7
        return cal == Calendar.SUNDAY ? 7 : cal - 1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
