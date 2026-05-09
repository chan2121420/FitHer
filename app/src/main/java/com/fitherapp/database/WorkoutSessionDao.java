package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.*;
import java.util.List;

// ─── WorkoutDay DAO ──────────────────────────────────────────────────────────

@Dao
public interface WorkoutSessionDao {
    @Insert
    long insert(WorkoutSession session);
    @Query("SELECT * FROM workout_sessions ORDER BY dateCompleted DESC")
    LiveData<List<WorkoutSession>> getAll();
    @Query("SELECT COUNT(*) FROM workout_sessions WHERE dateCompleted >= :sinceMillis AND completed = 1")
    int countCompletedSince(long sinceMillis);
    @Query("SELECT SUM(caloriesBurned) FROM workout_sessions WHERE dateCompleted >= :sinceMillis")
    int totalCaloriesSince(long sinceMillis);
}