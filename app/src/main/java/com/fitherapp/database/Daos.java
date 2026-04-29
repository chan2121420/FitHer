package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.*;
import java.util.List;

// ─── WorkoutDay DAO ──────────────────────────────────────────────────────────
@Dao
interface WorkoutDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(WorkoutDay day);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WorkoutDay> days);

    @Query("SELECT * FROM workout_days ORDER BY dayOfWeek ASC")
    LiveData<List<WorkoutDay>> getAllDays();

    @Query("SELECT * FROM workout_days WHERE dayOfWeek = :dow LIMIT 1")
    WorkoutDay getByDayOfWeek(int dow);

    @Query("SELECT COUNT(*) FROM workout_days")
    int count();
}

// ─── WorkoutSession DAO ──────────────────────────────────────────────────────
@Dao
interface WorkoutSessionDao {

    @Insert
    long insert(WorkoutSession session);

    @Query("SELECT * FROM workout_sessions ORDER BY dateCompleted DESC")
    LiveData<List<WorkoutSession>> getAll();

    @Query("SELECT * FROM workout_sessions ORDER BY dateCompleted DESC LIMIT 1")
    WorkoutSession getLastSession();

    @Query("SELECT COUNT(*) FROM workout_sessions WHERE dateCompleted >= :sinceMillis AND completed = 1")
    int countCompletedSince(long sinceMillis);

    @Query("SELECT * FROM workout_sessions WHERE dateCompleted >= :from AND dateCompleted <= :to ORDER BY dateCompleted ASC")
    List<WorkoutSession> getInRange(long from, long to);

    @Query("SELECT SUM(caloriesBurned) FROM workout_sessions WHERE dateCompleted >= :sinceMillis")
    int totalCaloriesSince(long sinceMillis);
}
