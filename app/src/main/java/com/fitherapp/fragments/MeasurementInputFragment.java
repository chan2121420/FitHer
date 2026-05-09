package com.fitherapp.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import androidx.annotation.*;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.databinding.FragmentMeasurementInputBinding;
import com.fitherapp.models.BodyMeasurement;
import com.fitherapp.viewmodels.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MeasurementInputFragment extends BottomSheetDialogFragment {
    private FragmentMeasurementInputBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMeasurementInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.btnSaveMeasurement.setOnClickListener(v -> {
            try {
                BodyMeasurement m = new BodyMeasurement();
                m.dateRecorded = System.currentTimeMillis();
                m.weightKg = parseFloat(binding.etWeight.getText().toString());
                m.hipsCm = parseFloat(binding.etHips.getText().toString());
                m.waistCm = parseFloat(binding.etWaist.getText().toString());
                m.glutesCm = parseFloat(binding.etGlutes.getText().toString());
                m.thighLeftCm = parseFloat(binding.etThighLeft.getText().toString());
                m.thighRightCm = parseFloat(binding.etThighRight.getText().toString());
                m.bustCm = parseFloat(binding.etBust.getText().toString());
                m.notes = binding.etNotes.getText().toString();
                viewModel.saveMeasurement(m);
                Toast.makeText(requireContext(), "Measurements saved! ✓", Toast.LENGTH_SHORT).show();
                dismiss();
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnCancel.setOnClickListener(v -> dismiss());
    }

    private float parseFloat(String s) {
        if (s == null || s.isEmpty()) return 0f;
        return Float.parseFloat(s);
    }

    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}