package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemExercisePickerBinding;
import com.fitherapp.models.Exercise;

public class ExercisePickerAdapter extends ListAdapter<Exercise, ExercisePickerAdapter.ViewHolder> {

    public interface OnPickListener { void onPick(Exercise exercise); }
    private final OnPickListener listener;

    public ExercisePickerAdapter(OnPickListener listener) {
        super(DIFF);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemExercisePickerBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemExercisePickerBinding b;

        public ViewHolder(ItemExercisePickerBinding b) {
            super(b.getRoot()); this.b = b;
        }

        public void bind(Exercise ex, OnPickListener listener) {
            b.tvExerciseName.setText(ex.name);
            b.tvMuscles.setText(ex.targetMuscles);
            b.tvType.setText(ex.type);
            b.btnAdd.setOnClickListener(v -> listener.onPick(ex));
        }
    }

    private static final DiffUtil.ItemCallback<Exercise> DIFF = new DiffUtil.ItemCallback<Exercise>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.id == b.id; }
        @Override
        public boolean areContentsTheSame(@NonNull Exercise a, @NonNull Exercise b) { return a.name.equals(b.name); }
    };
}