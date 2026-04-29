package com.fitherapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.fitherapp.R;
import com.fitherapp.activities.ActiveWorkoutActivity;
import com.fitherapp.activities.WorkoutBuilderActivity;
import com.fitherapp.adapters.WorkoutPlanAdapter;
import com.fitherapp.databinding.FragmentWorkoutsBinding;
import com.fitherapp.models.WorkoutPlan;
import com.fitherapp.viewmodels.MainViewModel;

public class WorkoutsFragment extends Fragment {

    private FragmentWorkoutsBinding binding;
    private MainViewModel viewModel;
    private WorkoutPlanAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWorkoutsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        adapter = new WorkoutPlanAdapter(new WorkoutPlanAdapter.OnPlanClickListener() {
            @Override
            public void onStart(WorkoutPlan plan) { showBandDialog(plan); }
            @Override
            public void onPreview(WorkoutPlan plan) {
                ExerciseDetailBottomSheet.newInstance(plan.id, plan.name)
                        .show(getParentFragmentManager(), "detail");
            }
        });

        binding.rvPlans.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvPlans.setAdapter(adapter);

        viewModel.getAllWorkoutPlans().observe(getViewLifecycleOwner(), plans -> {
            if (plans != null) adapter.submitList(plans);
        });

        binding.fabBuild.setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), WorkoutBuilderActivity.class)));

        setupCategoryFilter();
    }

    private void setupCategoryFilter() {
        String[] cats = {"ALL", "GLUTES", "LEGS", "ABS", "ARMS", "BACK", "FULL_BODY"};
        for (String cat : cats) {
            com.google.android.material.chip.Chip chip = new com.google.android.material.chip.Chip(requireContext());
            chip.setText(cat.equals("ALL") ? "All" : cap(cat));
            chip.setCheckable(true);
            chip.setChecked(cat.equals("ALL"));
            chip.setOnClickListener(v -> {
                if (cat.equals("ALL")) {
                    viewModel.getAllWorkoutPlans().observe(getViewLifecycleOwner(), list -> {
                        if (list != null) adapter.submitList(list);
                    });
                } else {
                    viewModel.getPlansByCategory(cat).observe(getViewLifecycleOwner(), list -> {
                        if (list != null) adapter.submitList(list);
                    });
                }
            });
            binding.chipGroupFilter.addView(chip);
        }
    }

    private String cap(String s) {
        if (s == null || s.isEmpty()) return s;
        String lower = s.toLowerCase().replace("_", " ");
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}