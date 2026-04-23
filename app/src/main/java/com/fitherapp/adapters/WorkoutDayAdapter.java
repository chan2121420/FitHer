package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemWorkoutDayBinding;
import com.fitherapp.models.WorkoutDay;

public class WorkoutDayAdapter extends ListAdapter<WorkoutDay, WorkoutDayAdapter.ViewHolder> {

    public interface OnDayClickListener { void onDayClick(WorkoutDay day); }
    private final OnDayClickListener listener;

    public WorkoutDayAdapter(OnDayClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkoutDayBinding binding = ItemWorkoutDayBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemWorkoutDayBinding binding;

        public ViewHolder(ItemWorkoutDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(WorkoutDay day, OnDayClickListener listener) {
            binding.tvDayName.setText(day.dayName);
            binding.tvFocusTitle.setText(day.focusTitle);

            if (day.isRestDay) {
                binding.tvMeta.setText("Rest / Active Recovery");
                binding.viewFocusStripe.setBackgroundColor(0xFF888780);
                binding.tvDuration.setText("—");
            } else {
                binding.tvMeta.setText(day.estimatedMins + " min · ~" + day.estimatedCalories + " kcal");
                binding.tvDuration.setText(day.estimatedMins + "m");
                int color;
                switch (day.focusType) {
                    case "GLUTE": color = 0xFF1D9E75; break;
                    case "HIP":   color = 0xFF534AB7; break;
                    default:      color = 0xFF888780; break;
                }
                binding.viewFocusStripe.setBackgroundColor(color);
            }

            binding.getRoot().setOnClickListener(v -> listener.onDayClick(day));
        }
    }

    private static final DiffUtil.ItemCallback<WorkoutDay> DIFF_CALLBACK = new DiffUtil.ItemCallback<WorkoutDay>() {
        @Override
        public boolean areItemsTheSame(@NonNull WorkoutDay a, @NonNull WorkoutDay b) { return a.id == b.id; }
        @Override
        public boolean areContentsTheSame(@NonNull WorkoutDay a, @NonNull WorkoutDay b) {
            return a.focusTitle.equals(b.focusTitle) && a.dayOfWeek == b.dayOfWeek;
        }
    };
}
