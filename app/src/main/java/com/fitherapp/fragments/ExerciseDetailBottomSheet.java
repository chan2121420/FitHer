package com.fitherapp.fragments;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.fitherapp.adapters.ExerciseListAdapter;
import com.fitherapp.databinding.FragmentExerciseDetailBottomSheetBinding;
import com.fitherapp.viewmodels.WorkoutViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ExerciseDetailBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_DAY_ID = "day_id";
    private static final String ARG_TITLE = "title";
    private FragmentExerciseDetailBottomSheetBinding binding;

    public static ExerciseDetailBottomSheet newInstance(int dayId, String title) {
        ExerciseDetailBottomSheet f = new ExerciseDetailBottomSheet();
        Bundle args = new Bundle();
        args.putInt(ARG_DAY_ID, dayId);
        args.putString(ARG_TITLE, title);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExerciseDetailBottomSheetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WorkoutViewModel viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        int dayId = getArguments().getInt(ARG_DAY_ID);
        String title = getArguments().getString(ARG_TITLE);

        binding.tvSheetTitle.setText(title);

        ExerciseListAdapter adapter = new ExerciseListAdapter();
        binding.rvExercises.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvExercises.setAdapter(adapter);

        viewModel.getExercisesForDay(dayId).observe(getViewLifecycleOwner(), list -> {
            if (list != null) adapter.submitList(list);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
