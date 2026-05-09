package com.fitherapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.databinding.FragmentProgressBinding;
import com.fitherapp.models.BodyMeasurement;
import com.fitherapp.viewmodels.MainViewModel;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.components.XAxis;
import java.util.ArrayList;
import java.util.List;

public class ProgressFragment extends Fragment {
    private FragmentProgressBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProgressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        setupCharts();
        observeData();

        binding.btnLogMeasurement.setOnClickListener(v ->
                new MeasurementInputFragment().show(getParentFragmentManager(), "measurement"));
    }

    private void setupCharts() {
        setupChart(binding.chartGlutes, 0xFF6A1B9A);
        setupChart(binding.chartHips, 0xFF1565C0);
        setupChart(binding.chartWaist, 0xFFB71C1C);
        setupChart(binding.chartWeight, 0xFF2E7D32);
    }

    private void setupChart(com.github.mikephil.charting.charts.LineChart chart, int color) {
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
        chart.setNoDataText("Log measurements to see progress");
    }

    private void observeData() {
        viewModel.getAllHistory().observe(getViewLifecycleOwner(), sessions ->
                binding.tvTotalWorkouts.setText(sessions != null ? String.valueOf(sessions.size()) : "0"));
        viewModel.getCurrentStreak().observe(getViewLifecycleOwner(), streak ->
                binding.tvCurrentStreak.setText(streak + " days"));
        viewModel.getTotalCaloriesWeek().observe(getViewLifecycleOwner(), cal ->
                binding.tvWeekCalories.setText(cal + " kcal"));
        viewModel.getAllMeasurements().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements == null || measurements.isEmpty()) return;
            updateMeasurementCharts(measurements);
            updateLatest(measurements.get(0));
        });
    }

    private void updateMeasurementCharts(List<BodyMeasurement> measurements) {
        List<Entry> g = new ArrayList<>(), h = new ArrayList<>(), w = new ArrayList<>(), wt = new ArrayList<>();
        for (int i = 0; i < measurements.size(); i++) {
            BodyMeasurement m = measurements.get(measurements.size() - 1 - i);
            g.add(new Entry(i, m.glutesCm));
            h.add(new Entry(i, m.hipsCm));
            w.add(new Entry(i, m.waistCm));
            wt.add(new Entry(i, m.weightKg));
        }
        setChartData(binding.chartGlutes, g, 0xFF6A1B9A);
        setChartData(binding.chartHips, h, 0xFF1565C0);
        setChartData(binding.chartWaist, w, 0xFFB71C1C);
        setChartData(binding.chartWeight, wt, 0xFF2E7D32);
    }

    private void setChartData(com.github.mikephil.charting.charts.LineChart chart, List<Entry> entries, int color) {
        if (entries.isEmpty()) return;
        LineDataSet ds = new LineDataSet(entries, "");
        ds.setColor(color); ds.setCircleColor(color);
        ds.setLineWidth(2f); ds.setCircleRadius(4f);
        ds.setDrawValues(false);
        ds.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        ds.setFillAlpha(40); ds.setFillColor(color); ds.setDrawFilled(true);
        chart.setData(new LineData(ds));
        chart.invalidate();
    }

    private void updateLatest(BodyMeasurement m) {
        binding.tvLatestGlutes.setText(m.glutesCm > 0 ? m.glutesCm + " cm" : "—");
        binding.tvLatestHips.setText(m.hipsCm > 0 ? m.hipsCm + " cm" : "—");
        binding.tvLatestWaist.setText(m.waistCm > 0 ? m.waistCm + " cm" : "—");
        binding.tvLatestWeight.setText(m.weightKg > 0 ? m.weightKg + " kg" : "—");
    }

    @Override public void onDestroyView() { super.onDestroyView(); binding = null; }
}