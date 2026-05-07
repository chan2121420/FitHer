package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.ExerciseWithDetails;
import com.fitherapp.models.WorkoutExercise;
import java.util.List;

@Dao
public interface WorkoutExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorkoutExercise we);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WorkoutExercise> list);

    @Update
    void update(WorkoutExercise we);

    @Delete
    void delete(WorkoutExercise we);

    @Query("SELECT * FROM workout_exercises WHERE workoutPlanId = :planId ORDER BY orderIndex ASC")
    List<WorkoutExercise> getForPlan(int planId);

    // 1. The cleaned up "Day" query returning ExerciseWithDetails
    @Transaction
    @Query("SELECT * FROM workout_exercises WHERE workoutPlanId = :workoutDayId")
    LiveData<List<ExerciseWithDetails>> getExercisesWithDetailsForDay(int workoutDayId);

    // 2. The missing "Plan" query your repository was asking for!
    @Transaction
    @Query("SELECT * FROM workout_exercises WHERE workoutPlanId = :planId")
    LiveData<List<ExerciseWithDetails>> getExercisesWithDetailsForPlan(int planId);

    @Query("DELETE FROM workout_exercises WHERE workoutPlanId = :planId")
    void deleteAllForPlan(int planId);
}