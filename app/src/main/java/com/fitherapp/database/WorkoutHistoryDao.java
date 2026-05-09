package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.WorkoutHistory;
import java.util.List;

@Dao
public interface WorkoutHistoryDao {
    @Insert
    long insert(WorkoutHistory history);
    @Query("SELECT * FROM workout_history ORDER BY dateCompleted DESC")
    LiveData<List<WorkoutHistory>> getAll();
    @Query("SELECT COUNT(*) FROM workout_history WHERE dateCompleted >= :sinceMillis AND completed = 1")
    int countCompletedSince(long sinceMillis);
    @Query("SELECT SUM(caloriesBurned) FROM workout_history WHERE dateCompleted >= :sinceMillis")
    int totalCaloriesSince(long sinceMillis);
    @Query("SELECT COUNT(*) FROM workout_history WHERE completed = 1")
    int totalCompleted();
    @Query("SELECT * FROM workout_history WHERE dateCompleted >= :from AND dateCompleted <= :to ORDER BY dateCompleted ASC")
    List<WorkoutHistory> getInRange(long from, long to);
}