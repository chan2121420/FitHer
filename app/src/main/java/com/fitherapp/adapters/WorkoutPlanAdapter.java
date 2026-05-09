package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemWorkoutPlanBinding;
import com.fitherapp.models.WorkoutPlan;

public class WorkoutPlanAdapter extends ListAdapter<WorkoutPlan, WorkoutPlanAdapter.ViewHolder> {

    public interface OnPlanClickListener {
        void onStart(WorkoutPlan plan);
        void onPreview(WorkoutPlan plan);
    }

    private final OnPlanClickListener listener;

    public WorkoutPlanAdapter(OnPlanClickListener listener) {
        super(DIFF);
        this.listener = listener;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemWorkoutPlanBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemWorkoutPlanBinding b;

        public ViewHolder(ItemWorkoutPlanBinding b) { super(b.getRoot()); this.b = b; }

        public void bind(WorkoutPlan plan, OnPlanClickListener listener) {
            b.tvPlanName.setText(plan.name);
            b.tvPlanDescription.setText(plan.description);
            b.tvDifficulty.setText(plan.difficulty);
            b.tvMeta.setText(plan.estimatedMins + " min  ·  ~" + plan.estimatedCalories + " kcal");
            b.tvCategory.setText(plan.category);

            String equipment = plan.equipment != null ? plan.equipment : "NONE";
            String equipIcon = equipment.equals("BANDS") ? "🔴 Bands" : equipment.equals("DUMBBELLS") ? "🏋️ Weights" : "🤸 Bodyweight";
            b.tvEquipment.setText(equipIcon);

            String days = plan.daysPerWeek > 0 ? plan.daysPerWeek + "x/week" : "";
            b.tvDaysPerWeek.setText(days);

            int exCount = plan.exerciseCount;
            b.tvExerciseCount.setText(exCount > 0 ? exCount + " exercises" : "");

            int color = getCategoryColor(plan.category);
            b.viewStripe.setBackgroundColor(color);
            b.tvCategory.setBackgroundColor(color);

            // Difficulty badge color
            int diffColor;
            switch (plan.difficulty) {
                case "BEGINNER": diffColor = 0xFF2E7D32; break;
                case "ADVANCED": diffColor = 0xFFB71C1C; break;
                default: diffColor = 0xFFE65100; break;
            }
            b.tvDifficulty.setTextColor(diffColor);

            b.btnStart.setOnClickListener(v -> listener.onStart(plan));
            b.getRoot().setOnClickListener(v -> listener.onPreview(plan));
        }

        private int getCategoryColor(String cat) {
            if (cat == null) return 0xFF37474F;
            switch (cat) {
                case "GLUTES": return 0xFF6A1B9A;
                case "LEGS":   return 0xFF1565C0;
                case "ABS":    return 0xFFB71C1C;
                case "ARMS":   return 0xFF2E7D32;
                case "BACK":   return 0xFF4E342E;
                default:       return 0xFF37474F;
            }
        }
    }

    private static final DiffUtil.ItemCallback<WorkoutPlan> DIFF = new DiffUtil.ItemCallback<WorkoutPlan>() {
        @Override public boolean areItemsTheSame(@NonNull WorkoutPlan a, @NonNull WorkoutPlan b) { return a.id == b.id; }
        @Override public boolean areContentsTheSame(@NonNull WorkoutPlan a, @NonNull WorkoutPlan b) { return a.name.equals(b.name); }
    };
}