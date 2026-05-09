package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemExerciseLibraryBinding;
import com.fitherapp.models.Exercise;

public class ExerciseLibraryAdapter extends ListAdapter<Exercise, ExerciseLibraryAdapter.ViewHolder> {

    public interface OnTutorialClickListener { void onClick(Exercise exercise); }
    private final OnTutorialClickListener tutorialListener;

    public ExerciseLibraryAdapter(OnTutorialClickListener tutorialListener) {
        super(DIFF);
        this.tutorialListener = tutorialListener;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemExerciseLibraryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), tutorialListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemExerciseLibraryBinding b;
        private boolean expanded = false;

        public ViewHolder(ItemExerciseLibraryBinding b) { super(b.getRoot()); this.b = b; }

        public void bind(Exercise ex, OnTutorialClickListener listener) {
            b.tvName.setText(ex.name);
            b.tvCategory.setText(ex.category);
            b.tvType.setText(ex.type);
            b.tvMuscles.setText(ex.targetMuscles);
            b.tvDifficulty.setText(ex.difficulty);

            if (ex.defaultDurationSecs > 0) {
                b.tvDefaultReps.setText(ex.defaultDurationSecs + "s");
            } else {
                b.tvDefaultReps.setText("×" + ex.defaultReps);
            }

            // Calories badge
            if (ex.caloriesPerMin > 0) {
                b.tvCalories.setText(ex.caloriesPerMin + " kcal/min");
                b.tvCalories.setVisibility(View.VISIBLE);
            } else {
                b.tvCalories.setVisibility(View.GONE);
            }

            // Equipment
            if (ex.equipmentNeeded != null && !ex.equipmentNeeded.isEmpty() && !ex.equipmentNeeded.equalsIgnoreCase("None")) {
                b.tvEquipment.setText("🎽 " + ex.equipmentNeeded);
                b.tvEquipment.setVisibility(View.VISIBLE);
            } else {
                b.tvEquipment.setVisibility(View.GONE);
            }

            b.expandedLayout.setVisibility(expanded ? View.VISIBLE : View.GONE);
            b.ivExpandArrow.setRotation(expanded ? 180f : 0f);

            b.getRoot().setOnClickListener(v -> {
                expanded = !expanded;
                b.expandedLayout.setVisibility(expanded ? View.VISIBLE : View.GONE);
                b.ivExpandArrow.setRotation(expanded ? 180f : 0f);
            });

            // Expanded content
            b.tvDescription.setText(ex.description);
            b.tvInstructions.setText(ex.instructions);

            if (ex.tips != null && !ex.tips.isEmpty()) {
                b.tvTips.setText("💡 " + ex.tips);
                b.tvTips.setVisibility(View.VISIBLE);
            } else {
                b.tvTips.setVisibility(View.GONE);
            }

            if (ex.commonMistakes != null && !ex.commonMistakes.isEmpty()) {
                b.tvMistakes.setText("⚠ " + ex.commonMistakes);
                b.tvMistakes.setVisibility(View.VISIBLE);
            } else {
                b.tvMistakes.setVisibility(View.GONE);
            }

            if (ex.breathingCue != null && !ex.breathingCue.isEmpty()) {
                b.tvBreathing.setText("🫁 " + ex.breathingCue);
                b.tvBreathing.setVisibility(View.VISIBLE);
            } else {
                b.tvBreathing.setVisibility(View.GONE);
            }

            if (ex.progressionTip != null && !ex.progressionTip.isEmpty()) {
                b.tvProgression.setText("📈 " + ex.progressionTip);
                b.tvProgression.setVisibility(View.VISIBLE);
            } else {
                b.tvProgression.setVisibility(View.GONE);
            }

            if (ex.secondaryMuscles != null && !ex.secondaryMuscles.isEmpty()) {
                b.tvSecondaryMuscles.setText("Also: " + ex.secondaryMuscles);
                b.tvSecondaryMuscles.setVisibility(View.VISIBLE);
            } else {
                b.tvSecondaryMuscles.setVisibility(View.GONE);
            }

            b.btnFullTutorial.setOnClickListener(v -> listener.onClick(ex));
        }
    }

    private static final DiffUtil.ItemCallback<Exercise> DIFF = new DiffUtil.ItemCallback<Exercise>() {
        @Override public boolean areItemsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.id == b.id; }
        @Override public boolean areContentsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.name.equals(b.name); }
    };
}