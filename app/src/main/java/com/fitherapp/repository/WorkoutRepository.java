package com.fitherapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.fitherapp.database.*;
import com.fitherapp.models.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutRepository {

    private final ExerciseDao exerciseDao;
    private final WorkoutPlanDao workoutPlanDao;
    private final WorkoutExerciseDao workoutExerciseDao;
    private final WorkoutHistoryDao workoutHistoryDao;
    private final BodyMeasurementDao bodyMeasurementDao;
    private final NutritionLogDao nutritionLogDao;
    private final UserDao userDao;
    private final ExecutorService executor;

    public WorkoutRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        exerciseDao = db.exerciseDao();
        workoutPlanDao = db.workoutPlanDao();
        workoutExerciseDao = db.workoutExerciseDao();
        workoutHistoryDao = db.workoutHistoryDao();
        bodyMeasurementDao = db.bodyMeasurementDao();
        nutritionLogDao = db.nutritionLogDao();
        userDao = db.userDao();
        executor = Executors.newFixedThreadPool(4);
    }

    public LiveData<User> getCurrentUser() { return userDao.getCurrentUser(); }
    public void insertUser(User user) { executor.execute(() -> userDao.insert(user)); }
    public void updateUser(User user) { executor.execute(() -> userDao.update(user)); }

    public LiveData<List<Exercise>> getAllExercises() { return exerciseDao.getAllExercises(); }
    public LiveData<List<Exercise>> getExercisesByCategory(String category) { return exerciseDao.getByCategory(category); }
    public LiveData<List<Exercise>> getExercisesByCategoryAndType(String category, String type) {
        return exerciseDao.getByCategoryAndType(category, type);
    }

    public LiveData<List<WorkoutPlan>> getAllWorkoutPlans() { return workoutPlanDao.getAll(); }
    public LiveData<List<WorkoutPlan>> getWorkoutPlansByCategory(String category) { return workoutPlanDao.getByCategory(category); }
    public LiveData<List<WorkoutPlan>> getCustomPlans() { return workoutPlanDao.getCustomPlans(); }
    public void insertWorkoutPlan(WorkoutPlan plan, InsertCallback callback) {
        executor.execute(() -> {
            long id = workoutPlanDao.insert(plan);
            if (callback != null) callback.onInserted((int) id);
        });
    }
    public void updateWorkoutPlan(WorkoutPlan plan) { executor.execute(() -> workoutPlanDao.update(plan)); }
    public void deleteWorkoutPlan(WorkoutPlan plan) { executor.execute(() -> workoutPlanDao.delete(plan)); }

    public LiveData<List<ExerciseWithDetails>> getExercisesForPlan(int planId) {
        return workoutExerciseDao.getExercisesWithDetailsForPlan(planId);
    }
    public void insertWorkoutExercises(List<WorkoutExercise> list) {
        executor.execute(() -> workoutExerciseDao.insertAll(list));
    }
    public void deleteExercisesForPlan(int planId) {
        executor.execute(() -> workoutExerciseDao.deleteAllForPlan(planId));
    }

    public LiveData<List<WorkoutHistory>> getAllHistory() { return workoutHistoryDao.getAll(); }
    public void insertHistory(WorkoutHistory history) { executor.execute(() -> workoutHistoryDao.insert(history)); }
    public void getStreakCount(long sinceMillis, CountCallback callback) {
        executor.execute(() -> callback.onResult(workoutHistoryDao.countCompletedSince(sinceMillis)));
    }
    public void getTotalCalories(long sinceMillis, CountCallback callback) {
        executor.execute(() -> callback.onResult(workoutHistoryDao.totalCaloriesSince(sinceMillis)));
    }
    public void getTotalWorkouts(CountCallback callback) {
        executor.execute(() -> callback.onResult(workoutHistoryDao.totalCompleted()));
    }

    public LiveData<List<BodyMeasurement>> getAllMeasurements() { return bodyMeasurementDao.getAll(); }
    public LiveData<BodyMeasurement> getLatestMeasurement() { return bodyMeasurementDao.getLatest(); }
    public void insertMeasurement(BodyMeasurement m) { executor.execute(() -> bodyMeasurementDao.insert(m)); }

    public LiveData<List<NutritionLog>> getAllNutritionLogs() { return nutritionLogDao.getAll(); }
    public void insertNutritionLog(NutritionLog log) { executor.execute(() -> nutritionLogDao.insert(log)); }
    public void updateNutritionLog(NutritionLog log) { executor.execute(() -> nutritionLogDao.update(log)); }

    public interface CountCallback { void onResult(int count); }
    public interface InsertCallback { void onInserted(int id); }
}