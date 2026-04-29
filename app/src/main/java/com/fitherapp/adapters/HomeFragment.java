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
import java.util.Arrays;
import java.util.List;

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
        binding.tvGreeting.setText("Hey, " + name + "! 💪");

        viewModel.getCurrentStreak().observe(getViewLifecycleOwner(), streak ->
                binding.tvStreak.setText(streak + " day streak 🔥"));
        viewModel.getTotalCaloriesWeek().observe(getViewLifecycleOwner(), cal ->
                binding.tvCalories.setText(cal + " kcal this week"));
        viewModel.getTotalWorkouts().observe(getViewLifecycleOwner(), total ->
                binding.tvTotalWorkouts.setText(total + " workouts total"));

        planAdapter = new WorkoutPlanAdapter(new WorkoutPlanAdapter.OnPlanClickListener() {
            @Override
            public void onStart(WorkoutPlan plan) { showBandDialog(plan); }
            @Override
            public void onPreview(WorkoutPlan plan) { showPlanPreview(plan); }
        });
        binding.rvSuggestedPlans.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvSuggestedPlans.setAdapter(planAdapter);

        viewModel.getAllWorkoutPlans().observe(getViewLifecycleOwner(), plans -> {
            if (plans != null && !plans.isEmpty()) {
                List<WorkoutPlan> first = plans.subList(0, Math.min(4, plans.size()));
                planAdapter.submitList(first);
            }
        });

        binding.btnSeeAll.setOnClickListener(v -> {
            if (getActivity() != null) {
                ((androidx.appcompat.app.AppCompatActivity) getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(com.fitherapp.R.id.nav_host_fragment,
                                new WorkoutsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void showBandDialog(WorkoutPlan plan) {
        String[] options = {"No band (bodyweight)", "Light resistance band", "Heavy resistance band"};
        String[] levels = {"NONE", "LIGHT", "HEAVY"};
        new AlertDialog.Builder(requireContext())
                .setTitle("Equipment for today?")
                .setItems(options, (d, which) -> {
                    Intent intent = new Intent(requireActivity(), ActiveWorkoutActivity.class);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_PLAN_ID, plan.id);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_PLAN_NAME, plan.name);
                    intent.putExtra(ActiveWorkoutActivity.EXTRA_BAND_LEVEL, levels[which]);
                    startActivity(intent);
                })
                .show();
    }

    private void showPlanPreview(WorkoutPlan plan) {
        ExerciseDetailBottomSheet sheet = ExerciseDetailBottomSheet.newInstance(plan.id, plan.name);
        sheet.show(getParentFragmentManager(), "preview");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}