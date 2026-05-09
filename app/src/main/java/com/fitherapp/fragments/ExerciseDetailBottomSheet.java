package com.fitherapp.fragments;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.fitherapp.activities.ExerciseTutorialActivity;
import com.fitherapp.adapters.ExerciseListAdapter;
import com.fitherapp.databinding.FragmentExerciseDetailBottomSheetBinding;
import com.fitherapp.viewmodels.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ExerciseDetailBottomSheet extends BottomSheetDialogFragment {
    private static final String ARG_PLAN_ID = "plan_id";
    private static final String ARG_TITLE = "title";
    private FragmentExerciseDetailBottomSheetBinding binding;

    public static ExerciseDetailBottomSheet newInstance(int planId, String title) {
        ExerciseDetailBottomSheet f = new ExerciseDetailBottomSheet();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAN_ID, planId);
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
        MainViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        int planId = getArguments().getInt(ARG_PLAN_ID);
        String title = getArguments().getString(ARG_TITLE);
        binding.tvSheetTitle.setText(title);

        ExerciseListAdapter adapter = new ExerciseListAdapter(ewd ->
                ExerciseTutorialActivity.launch(requireActivity(), ewd.exercise));
        binding.rvExercises.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvExercises.setAdapter(adapter);
        viewModel.getExercisesForPlan(planId).observe(getViewLifecycleOwner(), list -> {
            if (list != null) adapter.submitList(list);
        });
    }

    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}