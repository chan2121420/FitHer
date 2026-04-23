package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.*;
import java.util.List;

// ─── Exercise DAO ───────────────────────────────────────────────────────────
@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Exercise> exercises);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Exercise exercise);

    @Query("SELECT * FROM exercises ORDER BY name ASC")
    LiveData<List<Exercise>> getAllExercises();

    @Query("SELECT * FROM exercises WHERE category = :category ORDER BY name ASC")
    LiveData<List<Exercise>> getByCategory(String category);

    @Query("SELECT * FROM exercises WHERE id = :id")
    Exercise getById(int id);

    @Query("SELECT COUNT(*) FROM exercises")
    int count();
}

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

// ─── WorkoutExercise DAO ─────────────────────────────────────────────────────
@Dao
interface WorkoutExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorkoutExercise we);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WorkoutExercise> list);

    @Query("SELECT * FROM workout_exercises WHERE workoutDayId = :dayId ORDER BY orderIndex ASC")
    List<WorkoutExercise> getForDay(int dayId);

    @Transaction
    @Query("SELECT * FROM workout_exercises WHERE workoutDayId = :dayId ORDER BY orderIndex ASC")
    LiveData<List<ExerciseWithDetails>> getExercisesWithDetailsForDay(int dayId);

    @Update
    void update(WorkoutExercise we);
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

// ─── BodyMeasurement DAO ─────────────────────────────────────────────────────
@Dao
interface BodyMeasurementDao {

    @Insert
    long insert(BodyMeasurement m);

    @Query("SELECT * FROM body_measurements ORDER BY dateRecorded DESC")
    LiveData<List<BodyMeasurement>> getAll();

    @Query("SELECT * FROM body_measurements ORDER BY dateRecorded DESC LIMIT 1")
    LiveData<BodyMeasurement> getLatest();

    @Query("SELECT * FROM body_measurements WHERE dateRecorded >= :from ORDER BY dateRecorded ASC")
    List<BodyMeasurement> getFromDate(long from);
}

// ─── NutritionLog DAO ────────────────────────────────────────────────────────
@Dao
interface NutritionLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(NutritionLog log);

    @Update
    void update(NutritionLog log);

    @Query("SELECT * FROM nutrition_logs ORDER BY dateRecorded DESC")
    LiveData<List<NutritionLog>> getAll();

    @Query("SELECT * FROM nutrition_logs WHERE dateRecorded >= :from AND dateRecorded <= :to ORDER BY dateRecorded DESC")
    LiveData<List<NutritionLog>> getInRange(long from, long to);

    @Query("SELECT * FROM nutrition_logs ORDER BY dateRecorded DESC LIMIT 1")
    NutritionLog getLatest();
}
