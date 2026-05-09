package com.fitherapp.viewmodels;

import android.app.Application;
import androidx.lifecycle.*;
import com.fitherapp.models.*;
import com.fitherapp.repository.WorkoutRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final WorkoutRepository repository;
    private final MutableLiveData<Integer> currentStreak = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> totalCaloriesWeek = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> totalWorkouts = new MutableLiveData<>(0);
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>("ALL");
    private final MutableLiveData<String> selectedMode = new MutableLiveData<>("BODYWEIGHT");

    public MainViewModel(Application application) {
        super(application);
        repository = new WorkoutRepository(application);
        refresh();
    }

    public void refresh() {
        long weekAgo = System.currentTimeMillis() - (7L * 24 * 60 * 60 * 1000);
        long thirtyDaysAgo = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000);
        repository.getStreakCount(thirtyDaysAgo, count -> currentStreak.postValue(count));
        repository.getTotalCalories(weekAgo, cal -> totalCaloriesWeek.postValue(cal));
        repository.getTotalWorkouts(count -> totalWorkouts.postValue(count));
    }

    public LiveData<User> getCurrentUser() { return repository.getCurrentUser(); }
    public void saveUser(User user) { repository.insertUser(user); }
    public void updateUser(User user) { repository.updateUser(user); }

    public LiveData<List<WorkoutPlan>> getAllWorkoutPlans() { return repository.getAllWorkoutPlans(); }
    public LiveData<List<WorkoutPlan>> getPlansByCategory(String category) { return repository.getWorkoutPlansByCategory(category); }
    public LiveData<List<WorkoutPlan>> getCustomPlans() { return repository.getCustomPlans(); }
    public void savePlan(WorkoutPlan plan, WorkoutRepository.InsertCallback callback) {
        repository.insertWorkoutPlan(plan, callback);
    }
    public void deletePlan(WorkoutPlan plan) { repository.deleteWorkoutPlan(plan); }

    public LiveData<List<Exercise>> getAllExercises() { return repository.getAllExercises(); }
    public LiveData<List<Exercise>> getExercisesByCategory(String category) { return repository.getExercisesByCategory(category); }
    public LiveData<List<Exercise>> getExercisesByCategoryAndType(String category, String type) {
        return repository.getExercisesByCategoryAndType(category, type);
    }
    public LiveData<List<Exercise>> searchExercises(String query) { return repository.searchExercises(query); }

    public LiveData<List<ExerciseWithDetails>> getExercisesForPlan(int planId) {
        return repository.getExercisesForPlan(planId);
    }
    public void saveWorkoutExercises(List<WorkoutExercise> exercises) {
        repository.insertWorkoutExercises(exercises);
    }
    public void clearExercisesForPlan(int planId) { repository.deleteExercisesForPlan(planId); }

    public LiveData<List<WorkoutHistory>> getAllHistory() { return repository.getAllHistory(); }
    public void saveHistory(WorkoutHistory history) {
        repository.insertHistory(history);
        refresh();
    }

    public LiveData<List<BodyMeasurement>> getAllMeasurements() { return repository.getAllMeasurements(); }
    public LiveData<BodyMeasurement> getLatestMeasurement() { return repository.getLatestMeasurement(); }
    public void saveMeasurement(BodyMeasurement m) { repository.insertMeasurement(m); }

    public LiveData<List<NutritionLog>> getAllNutritionLogs() { return repository.getAllNutritionLogs(); }
    public void saveNutritionLog(NutritionLog log) { repository.insertNutritionLog(log); }

    public LiveData<Integer> getCurrentStreak() { return currentStreak; }
    public LiveData<Integer> getTotalCaloriesWeek() { return totalCaloriesWeek; }
    public LiveData<Integer> getTotalWorkouts() { return totalWorkouts; }
    public LiveData<String> getSelectedCategory() { return selectedCategory; }
    public LiveData<String> getSelectedMode() { return selectedMode; }
    public void setSelectedCategory(String cat) { selectedCategory.setValue(cat); }
    public void setSelectedMode(String mode) { selectedMode.setValue(mode); }
}