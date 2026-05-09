package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.ExerciseWithDetails;
import com.fitherapp.models.WorkoutExercise;
import java.util.List;

@Dao
interface WorkoutExerciseDao {
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
    @Transaction
    @Query("SELECT * FROM workout_exercises WHERE workoutPlanId = :planId ORDER BY orderIndex ASC")
    LiveData<List<ExerciseWithDetails>> getExercisesWithDetailsForPlan(int planId);
    @Query("DELETE FROM workout_exercises WHERE workoutPlanId = :planId")
    void deleteAllForPlan(int planId);
}