package com.fitherapp.database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.fitherapp.models.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutRepository {

    private final ExerciseDao exerciseDao;
    private final WorkoutDayDao workoutDayDao;
    private final WorkoutExerciseDao workoutExerciseDao;
    private final WorkoutSessionDao workoutSessionDao;
    private final BodyMeasurementDao bodyMeasurementDao;
    private final NutritionLogDao nutritionLogDao;
    private final ExecutorService executor;

    public WorkoutRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        exerciseDao = db.exerciseDao();
        workoutDayDao = db.workoutDayDao();
        workoutExerciseDao = db.workoutExerciseDao();
        workoutSessionDao = db.workoutSessionDao();
        bodyMeasurementDao = db.bodyMeasurementDao();
        nutritionLogDao = db.nutritionLogDao();
        executor = Executors.newFixedThreadPool(4);
    }

    // ── Exercises ─────────────────────────────────────────────────────────
    public LiveData<List<Exercise>> getAllExercises() {
        return exerciseDao.getAllExercises();
    }

    public LiveData<List<Exercise>> getExercisesByCategory(String category) {
        return exerciseDao.getByCategory(category);
    }

    // ── Workout Days ──────────────────────────────────────────────────────
    public LiveData<List<WorkoutDay>> getAllWorkoutDays() {
        return workoutDayDao.getAllDays();
    }

    // ── Workout Exercises for a day ────────────────────────────────────────
    public LiveData<List<ExerciseWithDetails>> getExercisesForDay(int workoutDayId) {
        return workoutExerciseDao.getExercisesWithDetailsForDay(workoutDayId);
    }

    // ── Sessions ──────────────────────────────────────────────────────────
    public LiveData<List<WorkoutSession>> getAllSessions() {
        return workoutSessionDao.getAll();
    }

    public void insertSession(WorkoutSession session) {
        executor.execute(() -> workoutSessionDao.insert(session));
    }

    public void getStreakCount(long sinceMillis, StreakCallback callback) {
        executor.execute(() -> {
            int count = workoutSessionDao.countCompletedSince(sinceMillis);
            callback.onResult(count);
        });
    }

    public void getTotalCalories(long sinceMillis, CaloriesCallback callback) {
        executor.execute(() -> {
            int cal = workoutSessionDao.totalCaloriesSince(sinceMillis);
            callback.onResult(cal);
        });
    }

    // ── Body Measurements ─────────────────────────────────────────────────
    public LiveData<List<BodyMeasurement>> getAllMeasurements() {
        return bodyMeasurementDao.getAll();
    }

    public LiveData<BodyMeasurement> getLatestMeasurement() {
        return bodyMeasurementDao.getLatest();
    }

    public void insertMeasurement(BodyMeasurement measurement) {
        executor.execute(() -> bodyMeasurementDao.insert(measurement));
    }

    // ── Nutrition ─────────────────────────────────────────────────────────
    public LiveData<List<NutritionLog>> getAllNutritionLogs() {
        return nutritionLogDao.getAll();
    }

    public void insertNutritionLog(NutritionLog log) {
        executor.execute(() -> nutritionLogDao.insert(log));
    }

    public void updateNutritionLog(NutritionLog log) {
        executor.execute(() -> nutritionLogDao.update(log));
    }

    // ── Callbacks ─────────────────────────────────────────────────────────
    public interface StreakCallback { void onResult(int streak); }
    public interface CaloriesCallback { void onResult(int calories); }
}
