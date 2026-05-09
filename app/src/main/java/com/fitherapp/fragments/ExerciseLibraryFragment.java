package com.fitherapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.fitherapp.activities.ExerciseTutorialActivity;
import com.fitherapp.adapters.ExerciseLibraryAdapter;
import com.fitherapp.databinding.FragmentExerciseLibraryBinding;
import com.fitherapp.models.Exercise;
import com.fitherapp.viewmodels.MainViewModel;
import java.util.List;

public class ExerciseLibraryFragment extends Fragment {
    private FragmentExerciseLibraryBinding binding;
    private MainViewModel viewModel;
    private ExerciseLibraryAdapter adapter;
    private LiveData<List<Exercise>> currentSource;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExerciseLibraryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        adapter = new ExerciseLibraryAdapter(ex -> ExerciseTutorialActivity.launch(requireActivity(), ex));

        binding.rvExercises.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvExercises.setAdapter(adapter);

        setupCategoryChips();
        observeCategory("ALL");

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String q = s.toString().trim();
                if (q.isEmpty()) {
                    observeCategory("ALL");
                } else {
                    if (currentSource != null) currentSource.removeObservers(getViewLifecycleOwner());
                    currentSource = viewModel.searchExercises(q);
                    currentSource.observe(getViewLifecycleOwner(), list -> {
                        if (list != null) adapter.submitList(list);
                    });
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void setupCategoryChips() {
        String[] cats = {"ALL","GLUTES","LEGS","ABS","ARMS","BACK","FULL_BODY","WARMUP","COOLDOWN"};
        String[] labels = {"All","Glutes","Legs","Abs","Arms","Back","Full Body","Warmup","Cooldown"};
        binding.chipGroupCategory.removeAllViews();
        for (int i = 0; i < cats.length; i++) {
            final String cat = cats[i];
            com.google.android.material.chip.Chip chip = new com.google.android.material.chip.Chip(requireContext());
            chip.setText(labels[i]);
            chip.setCheckable(true);
            chip.setChecked(cat.equals("ALL"));
            chip.setOnClickListener(v -> {
                binding.etSearch.setText("");
                observeCategory(cat);
            });
            binding.chipGroupCategory.addView(chip);
        }
    }

    private void observeCategory(String category) {
        if (currentSource != null) currentSource.removeObservers(getViewLifecycleOwner());
        currentSource = category.equals("ALL") ? viewModel.getAllExercises() : viewModel.getExercisesByCategory(category);
        currentSource.observe(getViewLifecycleOwner(), list -> { if (list != null) adapter.submitList(list); });
    }

    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}