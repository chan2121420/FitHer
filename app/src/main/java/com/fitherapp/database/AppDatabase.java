package com.fitherapp.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import com.fitherapp.models.*;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(
    entities = {
        Exercise.class,
        WorkoutDay.class,
        WorkoutExercise.class,
        WorkoutSession.class,
        BodyMeasurement.class,
        NutritionLog.class
    },
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutDayDao workoutDayDao();
    public abstract WorkoutExerciseDao workoutExerciseDao();
    public abstract WorkoutSessionDao workoutSessionDao();
    public abstract BodyMeasurementDao bodyMeasurementDao();
    public abstract NutritionLogDao nutritionLogDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "fither_database"
                    )
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // Seeding happens via DataSeeder after DB creation
                        }
                    })
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
