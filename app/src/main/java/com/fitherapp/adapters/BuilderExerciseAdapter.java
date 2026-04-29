package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemBuilderExerciseBinding;
import com.fitherapp.models.Exercise;

public class BuilderExerciseAdapter extends ListAdapter<Exercise, BuilderExerciseAdapter.ViewHolder> {

    public interface OnRemoveListener { void onRemove(Exercise exercise); }
    private final OnRemoveListener listener;

    public BuilderExerciseAdapter(OnRemoveListener listener) {
        super(DIFF);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBuilderExerciseBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemBuilderExerciseBinding b;

        public ViewHolder(ItemBuilderExerciseBinding b) {
            super(b.getRoot()); this.b = b;
        }

        public void bind(Exercise ex, OnRemoveListener listener) {
            b.tvExerciseName.setText(ex.name);
            b.tvCategory.setText(ex.category);
            b.tvReps.setText(ex.defaultDurationSecs > 0 ? ex.defaultDurationSecs + "s" : "×" + ex.defaultReps);
            b.btnRemove.setOnClickListener(v -> listener.onRemove(ex));
        }
    }

    private static final DiffUtil.ItemCallback<Exercise> DIFF = new DiffUtil.ItemCallback<Exercise>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.id == b.id; }
        @Override
        public boolean areContentsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.name.equals(b.name); }
    };
}