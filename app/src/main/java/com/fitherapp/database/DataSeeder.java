package com.fitherapp.database;

import android.content.Context;
import com.fitherapp.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataSeeder {

    public static void seedIfEmpty(Context context) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            if (db.exerciseDao().count() > 0) return;

            List<Exercise> exercises = new ArrayList<>();

            // GLUTES - BODYWEIGHT
            exercises.add(new Exercise("Glute Bridge", "GLUTES", "BODYWEIGHT",
                    "Lying hip thrust targeting glutes and hamstrings",
                    "Lie on back, knees bent, feet flat. Drive hips up squeezing glutes hard. Hold 2 seconds at top. Lower slowly.",
                    "Glutes, Hamstrings, Core", false, 20, 0, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Donkey Kicks", "GLUTES", "BODYWEIGHT",
                    "On all-fours kick extending leg behind for glute isolation",
                    "Start on hands and knees. Keep core tight and back flat. Kick one leg back and up, squeezing glute at top.",
                    "Glutes, Lower Back", false, 15, 0, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Sumo Squat", "GLUTES", "BODYWEIGHT",
                    "Wide-stance squat targeting inner thighs and glutes",
                    "Stand with feet wider than shoulder-width, toes pointed out 45°. Lower until thighs are parallel. Squeeze glutes hard at the top.",
                    "Glutes, Inner Thighs, Quads", false, 15, 0, 3, 45, "BEGINNER"));
            exercises.add(new Exercise("Bulgarian Split Squat", "GLUTES", "BODYWEIGHT",
                    "Single-leg squat with rear foot elevated",
                    "Place rear foot on a chair. Lower your front knee to 90°. Drive through front heel to stand. Keep torso upright.",
                    "Glutes, Quads, Hip Flexors", false, 12, 0, 3, 45, "INTERMEDIATE"));
            exercises.add(new Exercise("Squat Pulses", "GLUTES", "BODYWEIGHT",
                    "Isometric hold at squat bottom with small pulses",
                    "Get into a squat position. Stay low. Do small up-down pulses. Keep tension throughout.",
                    "Quads, Glutes", false, 0, 30, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Glute Kickback Standing", "GLUTES", "BODYWEIGHT",
                    "Standing glute kickback for max range of motion",
                    "Stand holding a wall for balance. Kick one leg back and up. Squeeze glute at top.",
                    "Glutes, Hip Extensors", false, 20, 0, 3, 30, "BEGINNER"));

            // GLUTES - BANDS
            exercises.add(new Exercise("Banded Glute Bridge", "GLUTES", "BANDS",
                    "Glute bridge with resistance band for extra activation",
                    "Place band above knees. Lie on back, feet flat. Drive hips up against band resistance. Squeeze hard at top.",
                    "Glutes, Hamstrings", true, 20, 0, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Banded Donkey Kicks", "GLUTES", "BANDS",
                    "Donkey kick with band for increased resistance",
                    "Loop band around ankle. On hands and knees, kick leg back against resistance. Squeeze at top.",
                    "Glutes", true, 15, 0, 3, 30, "INTERMEDIATE"));
            exercises.add(new Exercise("Banded Side Steps", "GLUTES", "BANDS",
                    "Lateral steps with band for hip abductors",
                    "Band around ankles. Slight squat position. Step sideways keeping tension. Don't let knees cave.",
                    "Hip Abductors, Glute Med", true, 20, 0, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Banded Fire Hydrant", "GLUTES", "BANDS",
                    "Fire hydrant with band for outer glute activation",
                    "Band above knees. On all fours. Lift knee out to side against band. Squeeze at top.",
                    "Glute Med, Hip Abductors", true, 15, 0, 3, 30, "BEGINNER"));

            // LEGS - BODYWEIGHT
            exercises.add(new Exercise("Reverse Lunges", "LEGS", "BODYWEIGHT",
                    "Step backward lunge for quads and glutes",
                    "Step one foot back. Lower back knee toward floor. Keep front shin vertical. Push through front heel.",
                    "Quads, Glutes, Hamstrings", false, 12, 0, 3, 45, "BEGINNER"));
            exercises.add(new Exercise("Wall Sit", "LEGS", "BODYWEIGHT",
                    "Isometric quad hold against wall",
                    "Back flat against wall. Slide down until knees at 90°. Hold. Keep core tight.",
                    "Quads, Glutes, Calves", false, 0, 45, 3, 60, "BEGINNER"));
            exercises.add(new Exercise("Jump Squats", "LEGS", "BODYWEIGHT",
                    "Explosive squat jump for power and cardio",
                    "Squat down. Explode upward. Land softly with bent knees. Go straight into next rep.",
                    "Quads, Glutes, Calves", false, 15, 0, 3, 45, "INTERMEDIATE"));
            exercises.add(new Exercise("Step Ups", "LEGS", "BODYWEIGHT",
                    "Step onto elevated surface for unilateral leg strength",
                    "Use a bench or step. Drive through top heel. Stand fully. Lower back down with control.",
                    "Quads, Glutes, Hamstrings", false, 12, 0, 3, 45, "BEGINNER"));

            // ABS - BODYWEIGHT
            exercises.add(new Exercise("Plank", "ABS", "BODYWEIGHT",
                    "Full body isometric core hold",
                    "Forearms on floor, body in straight line. Squeeze everything. Don't let hips drop.",
                    "Core, Shoulders, Back", false, 0, 40, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Bicycle Crunches", "ABS", "BODYWEIGHT",
                    "Rotational crunch for obliques and full core",
                    "Lie on back, hands behind head. Bring opposite elbow to opposite knee. Alternate in cycling motion.",
                    "Obliques, Rectus Abdominis", false, 16, 0, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Mountain Climbers", "ABS", "BODYWEIGHT",
                    "Dynamic core exercise with cardio benefit",
                    "High plank position. Drive knees to chest alternately. Keep hips level. Move fast.",
                    "Core, Hip Flexors, Shoulders", false, 0, 30, 3, 30, "INTERMEDIATE"));
            exercises.add(new Exercise("Dead Bug", "ABS", "BODYWEIGHT",
                    "Core stability exercise for lower back protection",
                    "Lie on back. Arms up, knees at 90°. Lower opposite arm and leg simultaneously. Keep low back flat.",
                    "Core, Lower Back", false, 10, 0, 3, 30, "BEGINNER"));

            // ARMS - BODYWEIGHT
            exercises.add(new Exercise("Diamond Push Ups", "ARMS", "BODYWEIGHT",
                    "Close grip push up for tricep focus",
                    "Hands form diamond under chest. Lower chest to hands. Push back up. Keep elbows tucked.",
                    "Triceps, Chest, Shoulders", false, 10, 0, 3, 45, "INTERMEDIATE"));
            exercises.add(new Exercise("Tricep Dips", "ARMS", "BODYWEIGHT",
                    "Chair dips for tricep isolation",
                    "Hands on chair, legs extended. Lower body by bending elbows. Push back up. Keep back close to chair.",
                    "Triceps", false, 12, 0, 3, 45, "BEGINNER"));

            // ARMS - BANDS
            exercises.add(new Exercise("Banded Bicep Curl", "ARMS", "BANDS",
                    "Band curl for bicep isolation",
                    "Stand on band. Grip ends. Curl hands toward shoulders. Squeeze at top. Lower slowly.",
                    "Biceps", true, 15, 0, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Banded Tricep Pushdown", "ARMS", "BANDS",
                    "Band pushdown for tricep isolation",
                    "Anchor band overhead. Face anchor. Push down extending arms. Squeeze triceps at bottom.",
                    "Triceps", true, 15, 0, 3, 30, "BEGINNER"));

            // BACK - BODYWEIGHT
            exercises.add(new Exercise("Superman Hold", "BACK", "BODYWEIGHT",
                    "Prone back extension for spinal erectors",
                    "Lie face down. Lift arms and legs off floor simultaneously. Hold. Squeeze back muscles.",
                    "Lower Back, Glutes, Rear Delts", false, 0, 30, 3, 30, "BEGINNER"));
            exercises.add(new Exercise("Bird Dog", "BACK", "BODYWEIGHT",
                    "Core stability exercise protecting lower back",
                    "On hands and knees. Extend opposite arm and leg. Hold 2 seconds. Return and switch.",
                    "Core, Lower Back, Glutes", false, 10, 0, 3, 30, "BEGINNER"));

            // FULL_BODY - BODYWEIGHT
            exercises.add(new Exercise("Burpees", "FULL_BODY", "BODYWEIGHT",
                    "Full body conditioning exercise",
                    "Stand → squat → plank → push up → jump squat. One fluid motion. Land softly.",
                    "Full Body, Cardio", false, 10, 0, 3, 60, "INTERMEDIATE"));
            exercises.add(new Exercise("Bear Crawl", "FULL_BODY", "BODYWEIGHT",
                    "Crawling movement for full body strength",
                    "On all fours, knees 1 inch off ground. Move opposite hand and foot forward. Keep back flat.",
                    "Core, Shoulders, Legs", false, 0, 30, 3, 30, "INTERMEDIATE"));

            // WARMUP / COOLDOWN
            exercises.add(new Exercise("Butt Kicks", "FULL_BODY", "BODYWEIGHT",
                    "Warm-up jog kicking heels to glutes",
                    "Jog in place driving heels up toward glutes. Keep core tight. Breathe rhythmically.",
                    "Quads, Hip Flexors, Cardio", false, 0, 30, 1, 0, "BEGINNER"));
            exercises.add(new Exercise("Glute Stretch", "FULL_BODY", "BODYWEIGHT",
                    "Lying figure-4 glute stretch",
                    "Lie on back. Cross ankle over opposite knee. Pull knee toward chest until you feel stretch in glute.",
                    "Glutes, Piriformis", false, 0, 30, 1, 0, "BEGINNER"));
            exercises.add(new Exercise("Child's Pose", "FULL_BODY", "BODYWEIGHT",
                    "Full body cooldown stretch",
                    "Kneel and sit back on heels. Extend arms forward. Rest forehead down. Breathe deeply.",
                    "Lower Back, Hips, Shoulders", false, 0, 30, 1, 0, "BEGINNER"));

            db.exerciseDao().insertAll(exercises);

            // WORKOUT PLANS
            List<WorkoutPlan> plans = new ArrayList<>();
            plans.add(new WorkoutPlan("Glute Activation", "Beginner glute activation focusing on mind-muscle connection",
                    "BEGINNER", "GLUTES", 20, 120));
            plans.add(new WorkoutPlan("Glute & Hip Builder", "Intermediate glute and hip width program",
                    "INTERMEDIATE", "GLUTES", 25, 160));
            plans.add(new WorkoutPlan("Lower Body Burn", "Full lower body workout for strength and tone",
                    "INTERMEDIATE", "LEGS", 30, 200));
            plans.add(new WorkoutPlan("Core Foundations", "Beginner core and abs program",
                    "BEGINNER", "ABS", 20, 130));
            plans.add(new WorkoutPlan("Full Body Sculpt", "Complete body sculpting for women",
                    "INTERMEDIATE", "FULL_BODY", 35, 250));
            plans.add(new WorkoutPlan("Banded Booty Blast", "Resistance band glute workout",
                    "INTERMEDIATE", "GLUTES", 25, 170));
            db.workoutPlanDao().insertAll(plans);

            // Link exercises to plans
            WorkoutPlan p1 = db.workoutPlanDao().getById(1);
            if (p1 != null) {
                List<WorkoutExercise> p1ex = new ArrayList<>();
                p1ex.add(new WorkoutExercise(1, 1, 0, 3, 20, 0, 30, false, "Hold 2s at top"));
                p1ex.add(new WorkoutExercise(1, 2, 1, 3, 15, 0, 30, true, "Squeeze at top"));
                p1ex.add(new WorkoutExercise(1, 3, 2, 3, 15, 0, 45, false, "Wide stance"));
                p1ex.add(new WorkoutExercise(1, 6, 3, 3, 20, 0, 30, true, "Full extension"));
                p1ex.add(new WorkoutExercise(1, 28, 4, 1, 0, 30, 0, true, "Cooldown"));
                db.workoutExerciseDao().insertAll(p1ex);
            }

            WorkoutPlan p2 = db.workoutPlanDao().getById(2);
            if (p2 != null) {
                List<WorkoutExercise> p2ex = new ArrayList<>();
                p2ex.add(new WorkoutExercise(2, 1, 0, 3, 20, 0, 30, false, "Drive hips high"));
                p2ex.add(new WorkoutExercise(2, 4, 1, 3, 12, 0, 45, true, "Keep torso upright"));
                p2ex.add(new WorkoutExercise(2, 2, 2, 3, 15, 0, 30, true, "Control the motion"));
                p2ex.add(new WorkoutExercise(2, 5, 3, 3, 0, 30, 30, false, "Stay low"));
                p2ex.add(new WorkoutExercise(2, 6, 4, 3, 20, 0, 30, true, "Squeeze at top"));
                p2ex.add(new WorkoutExercise(2, 28, 5, 1, 0, 30, 0, true, "Cooldown"));
                db.workoutExerciseDao().insertAll(p2ex);
            }

            WorkoutPlan p3 = db.workoutPlanDao().getById(3);
            if (p3 != null) {
                List<WorkoutExercise> p3ex = new ArrayList<>();
                p3ex.add(new WorkoutExercise(3, 11, 0, 3, 12, 0, 45, true, "Step deep"));
                p3ex.add(new WorkoutExercise(3, 13, 1, 3, 0, 45, 60, false, "90° knee angle"));
                p3ex.add(new WorkoutExercise(3, 12, 2, 3, 15, 0, 45, false, "Explosive!"));
                p3ex.add(new WorkoutExercise(3, 14, 3, 3, 12, 0, 45, true, "Drive through heel"));
                p3ex.add(new WorkoutExercise(3, 3, 4, 3, 15, 0, 45, false, "Wide stance"));
                p3ex.add(new WorkoutExercise(3, 29, 5, 1, 0, 30, 0, false, "Cooldown"));
                db.workoutExerciseDao().insertAll(p3ex);
            }

            WorkoutPlan p4 = db.workoutPlanDao().getById(4);
            if (p4 != null) {
                List<WorkoutExercise> p4ex = new ArrayList<>();
                p4ex.add(new WorkoutExercise(4, 15, 0, 3, 0, 40, 30, false, "Keep body straight"));
                p4ex.add(new WorkoutExercise(4, 16, 1, 3, 16, 0, 30, false, "Controlled rotation"));
                p4ex.add(new WorkoutExercise(4, 17, 2, 3, 0, 30, 30, false, "Keep hips level"));
                p4ex.add(new WorkoutExercise(4, 18, 3, 3, 10, 0, 30, true, "Back stays flat"));
                p4ex.add(new WorkoutExercise(4, 29, 4, 1, 0, 30, 0, false, "Cooldown"));
                db.workoutExerciseDao().insertAll(p4ex);
            }

            WorkoutPlan p5 = db.workoutPlanDao().getById(5);
            if (p5 != null) {
                List<WorkoutExercise> p5ex = new ArrayList<>();
                p5ex.add(new WorkoutExercise(5, 27, 0, 1, 0, 30, 0, false, "Warmup"));
                p5ex.add(new WorkoutExercise(5, 1, 1, 3, 20, 0, 30, false, null));
                p5ex.add(new WorkoutExercise(5, 11, 2, 3, 12, 0, 45, true, null));
                p5ex.add(new WorkoutExercise(5, 15, 3, 3, 0, 40, 30, false, null));
                p5ex.add(new WorkoutExercise(5, 25, 4, 3, 10, 0, 60, false, null));
                p5ex.add(new WorkoutExercise(5, 16, 5, 3, 16, 0, 30, false, null));
                p5ex.add(new WorkoutExercise(5, 30, 6, 1, 0, 30, 0, false, "Cooldown"));
                db.workoutExerciseDao().insertAll(p5ex);
            }

            WorkoutPlan p6 = db.workoutPlanDao().getById(6);
            if (p6 != null) {
                List<WorkoutExercise> p6ex = new ArrayList<>();
                p6ex.add(new WorkoutExercise(6, 7, 0, 3, 20, 0, 30, false, "Band above knees"));
                p6ex.add(new WorkoutExercise(6, 8, 1, 3, 15, 0, 30, true, "Full extension"));
                p6ex.add(new WorkoutExercise(6, 9, 2, 3, 20, 0, 30, true, "Keep tension"));
                p6ex.add(new WorkoutExercise(6, 10, 3, 3, 15, 0, 30, true, null));
                p6ex.add(new WorkoutExercise(6, 28, 4, 1, 0, 30, 0, true, "Cooldown"));
                db.workoutExerciseDao().insertAll(p6ex);
            }
        });
    }
}