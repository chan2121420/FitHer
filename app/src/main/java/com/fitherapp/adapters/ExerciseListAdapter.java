package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemExerciseListBinding;
import com.fitherapp.models.ExerciseWithDetails;

public class ExerciseListAdapter extends ListAdapter<ExerciseWithDetails, ExerciseListAdapter.ViewHolder> {

    public interface OnTutorialClick { void onClick(ExerciseWithDetails ewd); }
    private final OnTutorialClick tutorialClick;

    public ExerciseListAdapter(OnTutorialClick tutorialClick) { super(DIFF); this.tutorialClick = tutorialClick; }
    public ExerciseListAdapter() { this(null); }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemExerciseListBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), tutorialClick);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemExerciseListBinding b;

        public ViewHolder(ItemExerciseListBinding b) { super(b.getRoot()); this.b = b; }

        public void bind(ExerciseWithDetails ewd, OnTutorialClick tutorialClick) {
            b.tvExerciseName.setText(ewd.exercise.name);
            b.tvSets.setText(ewd.workoutExercise.sets + " sets");
            b.tvReps.setText(ewd.getDisplayReps());
            b.tvMuscles.setText(ewd.exercise.targetMuscles);
            b.tvType.setText(ewd.exercise.type);
            b.tvDifficulty.setText(ewd.exercise.difficulty);

            if (ewd.workoutExercise.notes != null && !ewd.workoutExercise.notes.isEmpty()) {
                b.tvNotes.setText("💡 " + ewd.workoutExercise.notes);
                b.tvNotes.setVisibility(View.VISIBLE);
            } else if (ewd.exercise.tips != null && !ewd.exercise.tips.isEmpty()) {
                String tip = ewd.exercise.tips.split("\\.")[0];
                b.tvNotes.setText("💡 " + tip);
                b.tvNotes.setVisibility(View.VISIBLE);
            } else {
                b.tvNotes.setVisibility(View.GONE);
            }

            if (ewd.workoutExercise.restTimeSecs > 0) {
                b.tvRest.setText("Rest: " + ewd.workoutExercise.restTimeSecs + "s");
                b.tvRest.setVisibility(View.VISIBLE);
            } else {
                b.tvRest.setVisibility(View.GONE);
            }

            if (tutorialClick != null) {
                b.btnTutorial.setVisibility(View.VISIBLE);
                b.btnTutorial.setOnClickListener(v -> tutorialClick.onClick(ewd));
            } else {
                b.btnTutorial.setVisibility(View.GONE);
            }
        }
    }

    private static final DiffUtil.ItemCallback<ExerciseWithDetails> DIFF = new DiffUtil.ItemCallback<ExerciseWithDetails>() {
        @Override public boolean areItemsTheSame(@NonNull ExerciseWithDetails a, @NonNull ExerciseWithDetails b) {
            return a.workoutExercise.id == b.workoutExercise.id;
        }
        @Override public boolean areContentsTheSame(@NonNull ExerciseWithDetails a, @NonNull ExerciseWithDetails b) {
            return a.workoutExercise.reps == b.workoutExercise.reps;
        }
    };
}