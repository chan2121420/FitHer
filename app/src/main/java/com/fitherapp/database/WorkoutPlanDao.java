package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.WorkoutPlan;
import java.util.List;

@Dao
public interface WorkoutPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(WorkoutPlan plan);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WorkoutPlan> plans);

    @Update
    void update(WorkoutPlan plan);

    @Delete
    void delete(WorkoutPlan plan);

    @Query("SELECT * FROM workout_plans ORDER BY createdAt DESC")
    LiveData<List<WorkoutPlan>> getAll();

    @Query("SELECT * FROM workout_plans WHERE category = :category ORDER BY createdAt DESC")
    LiveData<List<WorkoutPlan>> getByCategory(String category);

    @Query("SELECT * FROM workout_plans WHERE isCustom = 1 ORDER BY createdAt DESC")
    LiveData<List<WorkoutPlan>> getCustomPlans();

    @Query("SELECT * FROM workout_plans WHERE id = :id")
    WorkoutPlan getById(int id);

    @Query("SELECT COUNT(*) FROM workout_plans")
    int count();
}