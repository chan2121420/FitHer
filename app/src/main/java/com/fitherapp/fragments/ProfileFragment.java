package com.fitherapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.fitherapp.databinding.FragmentProfileBinding;
import com.fitherapp.models.User;
import com.fitherapp.viewmodels.MainViewModel;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        SharedPreferences prefs = requireActivity().getSharedPreferences("fither_prefs", MODE_PRIVATE);
        binding.etProfileName.setText(prefs.getString("user_name", ""));
        binding.etProfileGoal.setText(prefs.getString("user_goal", "GLUTES"));
        binding.etProfileLevel.setText(prefs.getString("user_level", "BEGINNER"));

        boolean darkMode = prefs.getBoolean("dark_mode", false);
        binding.switchDarkMode.setChecked(darkMode);
        binding.switchDarkMode.setOnCheckedChangeListener((btn, checked) -> {
            prefs.edit().putBoolean("dark_mode", checked).apply();
            AppCompatDelegate.setDefaultNightMode(
                    checked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });

        boolean reminders = prefs.getBoolean("reminders_on", true);
        binding.switchReminders.setChecked(reminders);
        binding.switchReminders.setOnCheckedChangeListener((btn, checked) ->
                prefs.edit().putBoolean("reminders_on", checked).apply());

        binding.btnSaveProfile.setOnClickListener(v -> {
            String name = binding.etProfileName.getText().toString().trim();
            if (name.isEmpty()) { binding.etProfileName.setError("Required"); return; }

            prefs.edit()
                    .putString("user_name", name)
                    .putString("user_goal", binding.etProfileGoal.getText().toString())
                    .putString("user_level", binding.etProfileLevel.getText().toString())
                    .apply();

            User user = new User("", name,
                    binding.etProfileLevel.getText().toString(),
                    binding.etProfileGoal.getText().toString());
            viewModel.saveUser(user);
            Toast.makeText(requireContext(), "Profile saved!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}