package com.fitherapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fitherapp.databinding.ItemCategoryBinding;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public interface OnCategoryClickListener { void onClick(String category); }

    private final List<String> categories;
    private final List<Integer> colors;
    private final OnCategoryClickListener listener;

    public CategoryAdapter(List<String> categories, List<Integer> colors, OnCategoryClickListener listener) {
        this.categories = categories;
        this.colors = colors;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(categories.get(position), colors.get(position), listener);
    }

    @Override
    public int getItemCount() { return categories.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryBinding b;

        public ViewHolder(ItemCategoryBinding b) {
            super(b.getRoot()); this.b = b;
        }

        public void bind(String category, int color, OnCategoryClickListener listener) {
            b.tvCategory.setText(category);
            b.cardCategory.setCardBackgroundColor(color);
            b.getRoot().setOnClickListener(v -> listener.onClick(category));
        }
    }
}