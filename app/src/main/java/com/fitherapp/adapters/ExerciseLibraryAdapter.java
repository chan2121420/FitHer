package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemExerciseLibraryBinding;
import com.fitherapp.models.Exercise;

public class ExerciseLibraryAdapter extends ListAdapter<Exercise, ExerciseLibraryAdapter.ViewHolder> {

    public ExerciseLibraryAdapter() { super(DIFF); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemExerciseLibraryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemExerciseLibraryBinding b;
        private boolean expanded = false;

        public ViewHolder(ItemExerciseLibraryBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bind(Exercise ex) {
            b.tvName.setText(ex.name);
            b.tvCategory.setText(ex.category);
            b.tvType.setText(ex.type);
            b.tvMuscles.setText(ex.targetMuscles);
            b.tvDescription.setText(ex.description);
            b.tvInstructions.setText(ex.instructions);
            b.tvDifficulty.setText(ex.difficulty);

            if (ex.defaultDurationSecs > 0) {
                b.tvDefaultReps.setText(ex.defaultDurationSecs + "s");
            } else {
                b.tvDefaultReps.setText("×" + ex.defaultReps);
            }

            b.expandedLayout.setVisibility(expanded ? android.view.View.VISIBLE : android.view.View.GONE);
            b.ivExpandArrow.setRotation(expanded ? 180f : 0f);
            b.getRoot().setOnClickListener(v -> {
                expanded = !expanded;
                b.expandedLayout.setVisibility(expanded ? android.view.View.VISIBLE : android.view.View.GONE);
                b.ivExpandArrow.setRotation(expanded ? 180f : 0f);
            });
        }
    }

    private static final DiffUtil.ItemCallback<Exercise> DIFF = new DiffUtil.ItemCallback<Exercise>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.id == b.id; }
        @Override
        public boolean areContentsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.name.equals(b.name); }
    };
}