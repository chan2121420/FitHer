package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.Exercise;
import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Exercise> exercises);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Exercise exercise);
    @Query("SELECT * FROM exercises ORDER BY name ASC")
    LiveData<List<Exercise>> getAllExercises();
    @Query("SELECT * FROM exercises WHERE category = :category ORDER BY name ASC")
    LiveData<List<Exercise>> getByCategory(String category);
    @Query("SELECT * FROM exercises WHERE category = :cat AND type = :type ORDER BY name ASC")
    LiveData<List<Exercise>> getByCategoryAndType(String cat, String type);
    @Query("SELECT * FROM exercises WHERE id = :id")
    Exercise getById(int id);
    @Query("SELECT COUNT(*) FROM exercises")
    int count();
    @Query("SELECT * FROM exercises WHERE difficulty = :difficulty ORDER BY name ASC")
    LiveData<List<Exercise>> getByDifficulty(String difficulty);
    @Query("SELECT * FROM exercises WHERE name LIKE '%' || :q || '%' OR targetMuscles LIKE '%' || :q || '%'")
    LiveData<List<Exercise>> search(String q);
}
