package com.fitherapp.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.databinding.FragmentNutritionBinding;
import com.fitherapp.models.NutritionLog;
import com.fitherapp.viewmodels.MainViewModel;

public class NutritionFragment extends Fragment {
    private FragmentNutritionBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNutritionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        observeLogs();

        binding.btnLogNutrition.setOnClickListener(v -> saveLog());
        binding.switchMassGainer.setOnCheckedChangeListener((btn, checked) -> {
            binding.layoutMassGainerAmount.setVisibility(checked ? View.VISIBLE : View.GONE);
            binding.tvMassGainerWarning.setVisibility(checked ? View.VISIBLE : View.GONE);
        });
    }

    private void saveLog() {
        try {
            NutritionLog log = new NutritionLog();
            log.dateRecorded = System.currentTimeMillis();
            log.proteinG = parseFloat(binding.etProtein.getText().toString());
            log.carbsG = parseFloat(binding.etCarbs.getText().toString());
            log.fatG = parseFloat(binding.etFat.getText().toString());
            log.caloriesTotal = parseFloat(binding.etCalories.getText().toString());
            log.waterMl = parseFloat(binding.etWater.getText().toString());
            log.massGainerTaken = binding.switchMassGainer.isChecked();
            log.massGainerServings = log.massGainerTaken
                    ? parseFloat(binding.etMassGainerServings.getText().toString()) : 0f;
            log.mealNotes = binding.etMealNotes.getText().toString();

            if (log.massGainerServings > 1.5f) {
                Toast.makeText(requireContext(),
                        "⚠ More than 1 serving may cause fat gain. Keep it to 1 post-workout.",
                        Toast.LENGTH_LONG).show();
            }

            viewModel.saveNutritionLog(log);
            Toast.makeText(requireContext(), "Nutrition logged! ✓", Toast.LENGTH_SHORT).show();
            clearForm();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Check your entries", Toast.LENGTH_SHORT).show();
        }
    }

    private void observeLogs() {
        viewModel.getAllNutritionLogs().observe(getViewLifecycleOwner(), logs -> {
            if (logs != null && !logs.isEmpty()) {
                NutritionLog latest = logs.get(0);
                binding.tvLatestProtein.setText(latest.proteinG + "g protein");
                binding.tvLatestCalories.setText((int)latest.caloriesTotal + " kcal");
                binding.tvLatestWater.setText((int)latest.waterMl + " ml");
                binding.tvMassGainerStatus.setText(latest.massGainerTaken
                        ? "Mass gainer: " + latest.massGainerServings + " serving(s) ✓"
                        : "Mass gainer: not taken");
            }
        });
    }

    private void clearForm() {
        binding.etProtein.setText("");
        binding.etCarbs.setText("");
        binding.etFat.setText("");
        binding.etCalories.setText("");
        binding.etWater.setText("");
        binding.etMealNotes.setText("");
        binding.switchMassGainer.setChecked(false);
    }

    private float parseFloat(String s) {
        if (s == null || s.isEmpty()) return 0f;
        return Float.parseFloat(s);
    }

    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}