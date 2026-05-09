package com.fitherapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.fitherapp.activities.ActiveWorkoutActivity;
import com.fitherapp.adapters.WorkoutPlanAdapter;
import com.fitherapp.databinding.FragmentHomeBinding;
import com.fitherapp.models.WorkoutPlan;
import com.fitherapp.viewmodels.MainViewModel;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private MainViewModel viewModel;
    private WorkoutPlanAdapter planAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        SharedPreferences prefs = requireActivity().getSharedPreferences("fither_prefs", MODE_PRIVATE);
        String name = prefs.getString("user_name", "Warrior");
        String goal = prefs.getString("user_goal", "GLUTES");

        String greeting = getGreeting();
        binding.tvGreeting.setText(greeting + ", " + name + "!");
        binding.tvGoalTag.setText("Goal: " + formatGoal(goal));

        viewModel.getCurrentStreak().observe(getViewLifecycleOwner(), streak ->
                binding.tvStreak.setText(streak + "\nday streak"));
        viewModel.getTotalCaloriesWeek().observe(getViewLifecycleOwner(), cal ->
                binding.tvCalories.setText(cal + "\nkcal/week"));
        viewModel.getTotalWorkouts().observe(getViewLifecycleOwner(), total ->
                binding.tvTotalWorkouts.setText(total + "\ntotal"));

        planAdapter = new WorkoutPlanAdapter(new WorkoutPlanAdapter.OnPlanClickListener() {
            @Override public void onStart(WorkoutPlan plan) { showBandDialog(plan); }
            @Override public void onPreview(WorkoutPlan plan) {
                ExerciseDetailBottomSheet.newInstance(plan.id, plan.name)
                        .show(getParentFragmentManager(), "preview");
            }
        });

        binding.rvSuggestedPlans.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvSuggestedPlans.setAdapter(planAdapter);

        viewModel.getAllWorkoutPlans().observe(getViewLifecycleOwner(), plans -> {
            if (plans != null && !plans.isEmpty()) {
                planAdapter.submitList(plans.subList(0, Math.min(6, plans.size())));
            }
        });

        binding.btnSeeAll.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(com.fitherapp.R.id.nav_host_fragment, new WorkoutsFragment())
                        .addToBackStack(null).commit());
    }

    private String getGreeting() {
        int hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY);
        if (hour < 12) return "Good morning";
        if (hour < 17) return "Good afternoon";
        return "Good evening";
    }

    private String formatGoal(String goal) {
        if (goal == null) return "General fitness";
        switch (goal) {
            case "GLUTES": return "Grow glutes 🍑";
            case "LEGS": return "Tone legs 🦵";
            case "FULL_BODY": return "Full body sculpt 💪";
            case "WEIGHT_LOSS": return "Weight loss 🔥";
            default: return goal;
        }
    }

    private void showBandDialog(WorkoutPlan plan) {
        String[] options = {"🤸 No band (bodyweight)", "🔴 Light resistance band", "🟠 Heavy resistance band"};
        String[] levels = {"NONE", "LIGHT", "HEAVY"};
        new AlertDialog.Builder(requireContext())
                .setTitle("Equipment for today?")
                .setItems(options, (d, which) -> {
                    Intent intent = new Intent(requireActivity(), ActiveWorkoutActivity.class);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_PLAN_ID, plan.id);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_PLAN_NAME, plan.name);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_BAND_LEVEL, levels[which]);
                    startActivity(intent);
                }).show();
    }

    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}