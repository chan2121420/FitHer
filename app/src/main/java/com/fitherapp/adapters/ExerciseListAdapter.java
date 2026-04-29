package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemExerciseListBinding;
import com.fitherapp.models.ExerciseWithDetails;

public class ExerciseListAdapter extends ListAdapter<ExerciseWithDetails, ExerciseListAdapter.ViewHolder> {

    public ExerciseListAdapter() { super(DIFF); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemExerciseListBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemExerciseListBinding b;

        public ViewHolder(ItemExerciseListBinding b) {
            super(b.getRoot()); this.b = b;
        }

        public void bind(ExerciseWithDetails ewd) {
            b.tvExerciseName.setText(ewd.exercise.name);
            b.tvSets.setText(ewd.workoutExercise.sets + " sets");
            b.tvReps.setText(ewd.getDisplayReps());
            b.tvMuscles.setText(ewd.exercise.targetMuscles);
            if (ewd.workoutExercise.notes != null && !ewd.workoutExercise.notes.isEmpty()) {
                b.tvNotes.setText(ewd.workoutExercise.notes);
                b.tvNotes.setVisibility(android.view.View.VISIBLE);
            } else {
                b.tvNotes.setVisibility(android.view.View.GONE);
            }
        }
    }

    private static final DiffUtil.ItemCallback<ExerciseWithDetails> DIFF = new DiffUtil.ItemCallback<ExerciseWithDetails>() {
        @Override
        public boolean areItemsTheSame(@NonNull ExerciseWithDetails a, @NonNull ExerciseWithDetails b) {
            return a.workoutExercise.id == b.workoutExercise.id;
        }
        @Override
        public boolean areContentsTheSame(@NonNull ExerciseWithDetails a, @NonNull ExerciseWithDetails b) {
            return a.workoutExercise.reps == b.workoutExercise.reps;
        }
    };
}