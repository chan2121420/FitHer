package com.fitherapp.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.fitherapp.adapters.ExerciseLibraryAdapter;
import com.fitherapp.databinding.FragmentExerciseLibraryBinding;
import com.fitherapp.models.Exercise;
import com.fitherapp.viewmodels.WorkoutViewModel;
import java.util.List;

public class ExerciseLibraryFragment extends Fragment {

    private FragmentExerciseLibraryBinding binding;
    private WorkoutViewModel viewModel;
    private ExerciseLibraryAdapter adapter;
    private LiveData<List<Exercise>> currentSource;
    private final String[] categories = {"ALL","GLUTE","HIP","CORE","WARMUP","COOLDOWN"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExerciseLibraryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
        adapter = new ExerciseLibraryAdapter();
        binding.rvExercises.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvExercises.setAdapter(adapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCategory.setAdapter(spinnerAdapter);

        binding.spinnerCategory.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View v, int pos, long id) {
                observeCategory(categories[pos]);
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
        observeCategory("ALL");
    }

    private void observeCategory(String category) {
        if (currentSource != null) currentSource.removeObservers(getViewLifecycleOwner());
        currentSource = category.equals("ALL") ? viewModel.getAllExercises()
                : viewModel.getExercisesByCategory(category);
        currentSource.observe(getViewLifecycleOwner(), list -> { if (list != null) adapter.submitList(list); });
    }

    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}
