package com.fitherapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.R;
import com.fitherapp.databinding.FragmentProgressBinding;
import com.fitherapp.models.*;
import com.fitherapp.viewmodels.WorkoutViewModel;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import java.util.ArrayList;
import java.util.List;

public class ProgressFragment extends Fragment {

    private FragmentProgressBinding binding;
    private WorkoutViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProgressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);

        setupCharts();
        observeData();

        binding.btnLogMeasurement.setOnClickListener(v ->
                new MeasurementInputFragment().show(getParentFragmentManager(), "measurement"));
    }

    private void setupCharts() {
        setupChart(binding.chartGlutes, "Glutes (cm)", 0xFF1D9E75);
        setupChart(binding.chartHips, "Hips (cm)", 0xFF534AB7);
        setupChart(binding.chartWaist, "Waist (cm)", 0xFFD85A30);
        setupChart(binding.chartWeight, "Weight (kg)", 0xFF378ADD);
    }

    private void setupChart(LineChart chart, String label, int color) {
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setTextColor(Color.GRAY);
        chart.getXAxis().setTextColor(Color.GRAY);
    }

    private void observeData() {
        viewModel.getAllSessions().observe(getViewLifecycleOwner(), sessions -> {
            binding.tvTotalWorkouts.setText(String.valueOf(sessions != null ? sessions.size() : 0));
        });

        viewModel.getCurrentStreak().observe(getViewLifecycleOwner(), streak ->
                binding.tvCurrentStreak.setText(streak + " days"));

        viewModel.getTotalCaloriesThisWeek().observe(getViewLifecycleOwner(), cal ->
                binding.tvWeekCalories.setText(cal + " kcal"));

        viewModel.getAllMeasurements().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements == null || measurements.isEmpty()) return;
            updateMeasurementCharts(measurements);
            updateLatestMeasurements(measurements.get(0));
        });
    }

    private void updateMeasurementCharts(List<BodyMeasurement> measurements) {
        List<Entry> glutesEntries = new ArrayList<>();
        List<Entry> hipsEntries = new ArrayList<>();
        List<Entry> waistEntries = new ArrayList<>();
        List<Entry> weightEntries = new ArrayList<>();

        for (int i = 0; i < measurements.size(); i++) {
            BodyMeasurement m = measurements.get(measurements.size() - 1 - i); // oldest first
            glutesEntries.add(new Entry(i, m.glutesCm));
            hipsEntries.add(new Entry(i, m.hipsCm));
            waistEntries.add(new Entry(i, m.waistCm));
            weightEntries.add(new Entry(i, m.weightKg));
        }

        setChartData(binding.chartGlutes, glutesEntries, 0xFF1D9E75);
        setChartData(binding.chartHips, hipsEntries, 0xFF534AB7);
        setChartData(binding.chartWaist, waistEntries, 0xFFD85A30);
        setChartData(binding.chartWeight, weightEntries, 0xFF378ADD);
    }

    private void setChartData(LineChart chart, List<Entry> entries, int color) {
        if (entries.isEmpty()) return;
        LineDataSet ds = new LineDataSet(entries, "");
        ds.setColor(color);
        ds.setCircleColor(color);
        ds.setLineWidth(2f);
        ds.setCircleRadius(4f);
        ds.setDrawValues(false);
        ds.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ds.setFillAlpha(30);
        ds.setFillColor(color);
        ds.setDrawFilled(true);
        chart.setData(new LineData(ds));
        chart.invalidate();
    }

    private void updateLatestMeasurements(BodyMeasurement m) {
        binding.tvLatestGlutes.setText(m.glutesCm > 0 ? m.glutesCm + " cm" : "—");
        binding.tvLatestHips.setText(m.hipsCm > 0 ? m.hipsCm + " cm" : "—");
        binding.tvLatestWaist.setText(m.waistCm > 0 ? m.waistCm + " cm" : "—");
        binding.tvLatestWeight.setText(m.weightKg > 0 ? m.weightKg + " kg" : "—");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
