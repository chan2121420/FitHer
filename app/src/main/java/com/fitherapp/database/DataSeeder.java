package com.fitherapp.database;

import android.content.Context;
import com.fitherapp.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Seeds the complete Glute & Hip workout plan into Room on first launch.
 * Mon = Glutes, Tue = Rest, Wed = Hips, Thu = Rest, Fri = Glutes+Core, Sat = Hips, Sun = Rest
 */
public class DataSeeder {

    public static void seedIfEmpty(Context context) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            if (db.workoutDayDao().count() > 0) return; // Already seeded

            // ── EXERCISES ─────────────────────────────────────────────────────
            List<Exercise> exercises = new ArrayList<>();

            // Warmup
            exercises.add(new Exercise("Butt Kicks", "WARMUP",
                    "High-knee running in place kicking heels to glutes",
                    "Stand tall. Jog in place driving heels up toward your glutes. Keep core tight.",
                    "Quads, Hip Flexors, Cardiovascular", false, 0, 30, "BEGINNER"));

            // Glute exercises
            exercises.add(new Exercise("Sumo Squat", "GLUTE",
                    "Wide-stance squat targeting inner thighs and glutes",
                    "Stand with feet wider than shoulder-width, toes pointed out 45°. Lower until thighs are parallel. Squeeze glutes hard at the top.",
                    "Glutes, Inner Thighs, Quads", false, 15, 0, "BEGINNER"));

            exercises.add(new Exercise("Bulgarian Split Squat", "GLUTE",
                    "Single-leg squat with rear foot elevated - best glute builder",
                    "Place rear foot on a chair or elevated surface. Lower your front knee to 90°. Drive through front heel to stand. Keep torso upright.",
                    "Glutes, Quads, Hip Flexors", false, 12, 0, "INTERMEDIATE"));

            exercises.add(new Exercise("Donkey Kicks", "GLUTE",
                    "On all-fours kick extending leg behind for glute isolation",
                    "Start on hands and knees. Keep core tight and back flat. Kick one leg back and up, squeezing glute at top. Lower slowly.",
                    "Glutes, Lower Back", true, 15, 0, "BEGINNER"));

            exercises.add(new Exercise("Glute Bridge", "GLUTE",
                    "Lying hip thrust targeting glutes and hamstrings",
                    "Lie on back, knees bent, feet flat. Drive hips up squeezing glutes hard. Hold 2 seconds at top. Lower slowly.",
                    "Glutes, Hamstrings, Core", true, 20, 0, "BEGINNER"));

            exercises.add(new Exercise("Glute Kickback (Standing)", "GLUTE",
                    "Standing version of glute kickback for max range of motion",
                    "Stand holding a wall for balance. Kick one leg back and up with a straight or bent knee. Squeeze glute at top.",
                    "Glutes, Hip Extensors", true, 20, 0, "BEGINNER"));

            exercises.add(new Exercise("Squat Pulses", "GLUTE",
                    "Isometric hold at squat bottom with small pulses",
                    "Get into a squat position. Stay low. Do small up-down pulses. Keep tension throughout.",
                    "Quads, Glutes", false, 0, 30, "BEGINNER"));

            exercises.add(new Exercise("Butt Bridge (High Rep)", "GLUTE",
                    "High-rep glute bridge for endurance and pump",
                    "Same as glute bridge — focus on squeezing at the top of every rep.",
                    "Glutes, Hamstrings", true, 25, 0, "BEGINNER"));

            // Hip exercises
            exercises.add(new Exercise("Side Leg Raise", "HIP",
                    "Lying side leg raise for hip abductors",
                    "Lie on your side, body in a straight line. Raise top leg to 45°, hold 1 second. Lower slowly. Do not swing.",
                    "Hip Abductors, Glute Med", true, 20, 0, "BEGINNER"));

            exercises.add(new Exercise("Fire Hydrant", "HIP",
                    "On-all-fours hip abduction for glute med and outer hip",
                    "Start on hands and knees. Keeping knee bent, lift one leg out to the side like a dog at a fire hydrant. Squeeze at top.",
                    "Glute Med, Hip Abductors", true, 15, 0, "BEGINNER"));

            exercises.add(new Exercise("Curtsy Lunge", "HIP",
                    "Cross-behind lunge targeting outer glute and hips",
                    "From standing, step one foot behind and across the other into a curtsy. Lower back knee toward floor. Return to stand.",
                    "Glutes, Hip Abductors, Quads", false, 15, 0, "INTERMEDIATE"));

            exercises.add(new Exercise("Side Lunge", "HIP",
                    "Lateral lunge for inner thighs and hip width",
                    "Step wide to one side, bending that knee and sitting your weight into it. Keep other leg straight. Push back to center.",
                    "Inner Thighs, Glutes, Quads", false, 15, 0, "BEGINNER"));

            exercises.add(new Exercise("Side-Lying Leg Lift", "HIP",
                    "Lying elevated leg lift with extended range",
                    "Lie on your side with hips stacked. Lift top leg as high as comfortable. Pause at top. Lower with control.",
                    "Hip Abductors, Glute Med", true, 20, 0, "BEGINNER"));

            exercises.add(new Exercise("Side Leg Circles", "HIP",
                    "Circular hip motion for full hip abductor activation",
                    "Lie on side. Lift top leg slightly. Draw 15 large circles forward, then 15 backward.",
                    "Hip Abductors, Hip Flexors", true, 15, 0, "BEGINNER"));

            // Core
            exercises.add(new Exercise("Bird Dog", "CORE",
                    "Core stability exercise on all fours",
                    "On hands and knees. Extend opposite arm and leg simultaneously. Hold 2 seconds. Return and switch.",
                    "Core, Lower Back, Glutes", false, 10, 0, "BEGINNER"));

            exercises.add(new Exercise("Bicycle Crunches", "CORE",
                    "Rotational crunch for obliques and full core",
                    "Lie on back, hands behind head. Bring opposite elbow to opposite knee while extending other leg. Alternate in cycling motion.",
                    "Obliques, Rectus Abdominis, Hip Flexors", false, 16, 0, "BEGINNER"));

            exercises.add(new Exercise("V-Hold", "CORE",
                    "Isometric core hold in V-sit position",
                    "Sit on floor. Lean back slightly, lift feet off floor into V-shape. Hold tight. Keep breathing.",
                    "Core, Hip Flexors", false, 0, 20, "INTERMEDIATE"));

            // Cooldown / Stretches
            exercises.add(new Exercise("Glute Stretch", "COOLDOWN",
                    "Lying figure-4 glute stretch",
                    "Lie on back. Cross one ankle over opposite knee. Pull that knee toward your chest until you feel stretch in glute.",
                    "Glutes, Piriformis", false, 0, 30, "BEGINNER"));

            exercises.add(new Exercise("Kneeling Lunge Stretch", "COOLDOWN",
                    "Hip flexor and glute cooldown stretch",
                    "Kneel on one knee. Lean forward into a lunge position. Feel the stretch in the rear hip. Keep torso tall.",
                    "Hip Flexors, Glutes", false, 0, 30, "BEGINNER"));

            exercises.add(new Exercise("Child's Pose", "COOLDOWN",
                    "Full body cooldown and spine decompression",
                    "Kneel and sit back on heels. Extend arms forward on floor. Rest forehead down. Breathe deeply.",
                    "Lower Back, Hips, Shoulders", false, 0, 20, "BEGINNER"));

            db.exerciseDao().insertAll(exercises);

            // ── WORKOUT DAYS ────────────────────────────────────────────────
            List<WorkoutDay> days = new ArrayList<>();

            WorkoutDay monday = new WorkoutDay(1, "Monday", "Glute Activation & Strength",
                    "GLUTE", 22, 132, false);
            WorkoutDay tuesday = new WorkoutDay(2, "Tuesday", "Active Recovery",
                    "REST", 0, 0, true);
            WorkoutDay wednesday = new WorkoutDay(3, "Wednesday", "Hip Width & Outer Glute",
                    "HIP", 22, 153, false);
            WorkoutDay thursday = new WorkoutDay(4, "Thursday", "Active Recovery",
                    "REST", 0, 0, true);
            WorkoutDay friday = new WorkoutDay(5, "Friday", "Glutes + Core Burn",
                    "GLUTE", 25, 156, false);
            WorkoutDay saturday = new WorkoutDay(6, "Saturday", "Hip Circles & Depth",
                    "HIP", 20, 120, false);
            WorkoutDay sunday = new WorkoutDay(7, "Sunday", "Full Rest",
                    "REST", 0, 0, true);

            days.add(monday); days.add(tuesday); days.add(wednesday);
            days.add(thursday); days.add(friday); days.add(saturday); days.add(sunday);
            db.workoutDayDao().insertAll(days);

            // Fetch inserted days to get their IDs
            WorkoutDay mon = db.workoutDayDao().getByDayOfWeek(1);
            WorkoutDay wed = db.workoutDayDao().getByDayOfWeek(3);
            WorkoutDay fri = db.workoutDayDao().getByDayOfWeek(5);
            WorkoutDay sat = db.workoutDayDao().getByDayOfWeek(6);

            // Fetch exercise IDs by name helper
            Exercise[] allEx = db.exerciseDao().getByCategory("GLUTE").getValue() == null
                    ? new Exercise[0] : new Exercise[0]; // Use direct query instead

            // ── MONDAY exercises ─────────────────────────────────────────────
            // Get exercise IDs (they're inserted in order so IDs are 1-based predictably)
            List<WorkoutExercise> monExercises = new ArrayList<>();
            // Butt Kicks (id=1), warmup
            monExercises.add(new WorkoutExercise(mon.id, 1, 0, 1, 0, 30, false, "Warm-up"));
            // Sumo Squat (id=2)
            monExercises.add(new WorkoutExercise(mon.id, 2, 1, 3, 15, 0, false, "Wide stance, squeeze at top"));
            // Bulgarian Split Squat (id=3)
            monExercises.add(new WorkoutExercise(mon.id, 3, 2, 3, 12, 0, true, "Left leg then right leg"));
            // Donkey Kicks (id=4)
            monExercises.add(new WorkoutExercise(mon.id, 4, 3, 3, 15, 0, true, "Control the motion"));
            // Glute Bridge (id=5)
            monExercises.add(new WorkoutExercise(mon.id, 5, 4, 3, 20, 0, false, "Hold 2 seconds at top"));
            // Glute Stretch (id=18) cooldown
            monExercises.add(new WorkoutExercise(mon.id, 18, 5, 1, 0, 30, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(monExercises);

            // ── WEDNESDAY exercises ──────────────────────────────────────────
            List<WorkoutExercise> wedExercises = new ArrayList<>();
            // Side Leg Raise (id=9)
            wedExercises.add(new WorkoutExercise(wed.id, 9, 0, 3, 20, 0, true, "Slow and controlled"));
            // Fire Hydrant (id=10)
            wedExercises.add(new WorkoutExercise(wed.id, 10, 1, 3, 15, 0, true, "Feel hip abductors"));
            // Curtsy Lunge (id=11)
            wedExercises.add(new WorkoutExercise(wed.id, 11, 2, 3, 15, 0, true, "Targets hip and outer glute"));
            // Side Lunge (id=12)
            wedExercises.add(new WorkoutExercise(wed.id, 12, 3, 3, 15, 0, true, "Sit deep into the lunge"));
            // Side-Lying Leg Lift (id=13)
            wedExercises.add(new WorkoutExercise(wed.id, 13, 4, 3, 20, 0, true, "Slow up, slow down"));
            // Kneeling Lunge Stretch (id=19) cooldown
            wedExercises.add(new WorkoutExercise(wed.id, 19, 5, 1, 0, 30, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(wedExercises);

            // ── FRIDAY exercises ─────────────────────────────────────────────
            List<WorkoutExercise> friExercises = new ArrayList<>();
            // Sumo Squat warmup
            friExercises.add(new WorkoutExercise(fri.id, 2, 0, 1, 15, 0, false, "Warm-up"));
            // Glute Kickback Standing (id=6)
            friExercises.add(new WorkoutExercise(fri.id, 6, 1, 3, 20, 0, true, "Squeeze at top"));
            // Squat Pulses (id=7)
            friExercises.add(new WorkoutExercise(fri.id, 7, 2, 3, 0, 30, false, "Stay low"));
            // Butt Bridge high rep (id=8)
            friExercises.add(new WorkoutExercise(fri.id, 8, 3, 3, 25, 0, false, "Squeeze very hard at top"));
            // Bird Dog (id=15)
            friExercises.add(new WorkoutExercise(fri.id, 15, 4, 3, 10, 0, true, "Slow and controlled"));
            // Bicycle Crunches (id=16)
            friExercises.add(new WorkoutExercise(fri.id, 16, 5, 3, 16, 0, false, "Core support for glute growth"));
            // Child's Pose (id=20)
            friExercises.add(new WorkoutExercise(fri.id, 20, 6, 1, 0, 20, false, "Cooldown"));
            db.workoutExerciseDao().insertAll(friExercises);

            // ── SATURDAY exercises ───────────────────────────────────────────
            List<WorkoutExercise> satExercises = new ArrayList<>();
            // Side Leg Circles (id=14)
            satExercises.add(new WorkoutExercise(sat.id, 14, 0, 3, 15, 0, true, "Full range circles"));
            // Side Lunge (id=12)
            satExercises.add(new WorkoutExercise(sat.id, 12, 1, 3, 15, 0, true, "Deep and wide"));
            // Fire Hydrant (id=10)
            satExercises.add(new WorkoutExercise(sat.id, 10, 2, 3, 15, 0, true, null));
            // Glute Bridge (id=5)
            satExercises.add(new WorkoutExercise(sat.id, 5, 3, 3, 20, 0, false, "Hold 2 seconds at top"));
            // Glute Stretch (id=18)
            satExercises.add(new WorkoutExercise(sat.id, 18, 4, 1, 0, 30, true, "Cooldown"));
            db.workoutExerciseDao().insertAll(satExercises);
        });
    }
}
