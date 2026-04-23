package com.fitherapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.fitherapp.activities.ActiveWorkoutActivity;
import com.fitherapp.adapters.WorkoutDayAdapter;
import com.fitherapp.adapters.ExerciseListAdapter;
import com.fitherapp.databinding.FragmentWorkoutPlanBinding;
import com.fitherapp.models.WorkoutDay;
import com.fitherapp.viewmodels.WorkoutViewModel;

public class WorkoutPlanFragment extends Fragment {

    private FragmentWorkoutPlanBinding binding;
    private WorkoutViewModel viewModel;
    private WorkoutDayAdapter dayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWorkoutPlanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        dayAdapter = new WorkoutDayAdapter(day -> showDayDetail(day));
        binding.rvWorkoutDays.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvWorkoutDays.setAdapter(dayAdapter);

        viewModel.getAllWorkoutDays().observe(getViewLifecycleOwner(), days -> {
            if (days != null) dayAdapter.submitList(days);
        });
    }

    private void showDayDetail(WorkoutDay day) {
        if (day.isRestDay) {
            new AlertDialog.Builder(requireContext())
                    .setTitle(day.dayName + " — Rest Day")
                    .setMessage("Take a 20-minute walk or rest completely.\n\nYour muscles grow during recovery. Don't skip this.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        // Show exercise list bottom sheet dialog
        ExerciseDetailBottomSheet sheet = ExerciseDetailBottomSheet.newInstance(day.id, day.dayName + " — " + day.focusTitle);
        sheet.show(getParentFragmentManager(), "exercise_detail");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
