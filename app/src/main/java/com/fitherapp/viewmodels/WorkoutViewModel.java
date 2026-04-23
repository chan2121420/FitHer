package com.fitherapp.viewmodels;

import android.app.Application;
import androidx.lifecycle.*;
import com.fitherapp.database.WorkoutRepository;
import com.fitherapp.models.*;
import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {

    private final WorkoutRepository repository;
    private final MutableLiveData<Integer> currentStreak = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> totalCaloriesThisWeek = new MutableLiveData<>(0);
    private final MutableLiveData<String> selectedBandLevel = new MutableLiveData<>("NONE");

    public WorkoutViewModel(Application application) {
        super(application);
        repository = new WorkoutRepository(application);
        loadStreak();
        loadWeeklyCalories();
    }

    public LiveData<List<WorkoutDay>> getAllWorkoutDays() {
        return repository.getAllWorkoutDays();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return repository.getAllExercises();
    }

    public LiveData<List<Exercise>> getExercisesByCategory(String category) {
        return repository.getExercisesByCategory(category);
    }

    public LiveData<List<ExerciseWithDetails>> getExercisesForDay(int workoutDayId) {
        return repository.getExercisesForDay(workoutDayId);
    }

    public LiveData<List<WorkoutSession>> getAllSessions() {
        return repository.getAllSessions();
    }

    public LiveData<List<BodyMeasurement>> getAllMeasurements() {
        return repository.getAllMeasurements();
    }

    public LiveData<BodyMeasurement> getLatestMeasurement() {
        return repository.getLatestMeasurement();
    }

    public LiveData<List<NutritionLog>> getAllNutritionLogs() {
        return repository.getAllNutritionLogs();
    }

    public LiveData<Integer> getCurrentStreak() { return currentStreak; }
    public LiveData<Integer> getTotalCaloriesThisWeek() { return totalCaloriesThisWeek; }
    public LiveData<String> getSelectedBandLevel() { return selectedBandLevel; }

    public void setBandLevel(String level) { selectedBandLevel.setValue(level); }

    public void saveSession(WorkoutSession session) {
        repository.insertSession(session);
        loadStreak();
        loadWeeklyCalories();
    }

    public void saveMeasurement(BodyMeasurement measurement) {
        repository.insertMeasurement(measurement);
    }

    public void saveNutritionLog(NutritionLog log) {
        repository.insertNutritionLog(log);
    }

    private void loadStreak() {
        long thirtyDaysAgo = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000);
        repository.getStreakCount(thirtyDaysAgo, count -> currentStreak.postValue(count));
    }

    private void loadWeeklyCalories() {
        long weekAgo = System.currentTimeMillis() - (7L * 24 * 60 * 60 * 1000);
        repository.getTotalCalories(weekAgo, cal -> totalCaloriesThisWeek.postValue(cal));
    }
}
