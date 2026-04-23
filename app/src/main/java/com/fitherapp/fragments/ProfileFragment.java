package com.fitherapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import com.fitherapp.databinding.FragmentProfileBinding;
import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences prefs = requireActivity().getSharedPreferences("fither_prefs", MODE_PRIVATE);

        binding.etProfileName.setText(prefs.getString("user_name", ""));
        binding.etProfileWeight.setText(prefs.getString("user_weight", ""));
        binding.etProfileGoal.setText(prefs.getString("user_goal", "Grow my glutes and hips"));
        binding.etMassGainerBrand.setText(prefs.getString("mass_gainer_brand", "Freak Mass"));
        binding.etMassGainerCalories.setText(prefs.getString("mass_gainer_cal_per_serving", "350"));

        binding.switchDarkMode.setChecked(prefs.getBoolean("dark_mode", false));
        binding.switchDarkMode.setOnCheckedChangeListener((btn, checked) -> {
            prefs.edit().putBoolean("dark_mode", checked).apply();
            if (checked) {
                androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(
                        androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(
                        androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        binding.btnSaveProfile.setOnClickListener(v -> {
            prefs.edit()
                    .putString("user_name", binding.etProfileName.getText().toString())
                    .putString("user_weight", binding.etProfileWeight.getText().toString())
                    .putString("user_goal", binding.etProfileGoal.getText().toString())
                    .putString("mass_gainer_brand", binding.etMassGainerBrand.getText().toString())
                    .putString("mass_gainer_cal_per_serving", binding.etMassGainerCalories.getText().toString())
                    .apply();
            Toast.makeText(requireContext(), "Profile saved!", Toast.LENGTH_SHORT).show();
        });

        // Weekly progress notification toggle
        binding.switchReminders.setChecked(prefs.getBoolean("reminders_on", true));
        binding.switchReminders.setOnCheckedChangeListener((btn, checked) ->
                prefs.edit().putBoolean("reminders_on", checked).apply());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
