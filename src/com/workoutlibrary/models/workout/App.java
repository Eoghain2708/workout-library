package com.workoutlibrary.models.workout;

import java.util.List;

import com.workoutlibrary.models.workout.sets.Exercise;
import com.workoutlibrary.models.workout.sets.StrengthEstimator;
import com.workoutlibrary.models.workout.sets.WeightSet;
import com.workoutlibrary.weightconversion.Weight;

public class App {
	public static void main(String[] args) {
		WeightSet ws = new WeightSet.Builder().exercise(Exercise.create("Bench Press")).reps(15).weight(Weight.ofKg(60)).repsInReserve(2).build();
		WeightSet ws2 = new WeightSet.Builder().exercise(Exercise.create("bench Press")).reps(12).weight(Weight.ofKg(60)).repsInReserve(1).build();
		WeightSet ws3 = new WeightSet.Builder().exercise(Exercise.create("BenCH Press")).reps(6).weight(Weight.ofKg(60)).repsInReserve(0).build();
		var ws4 = new WeightSet.Builder()
				.exercise(Exercise.create("Deadlift"))
				.reps(3)
				.weight(Weight.ofLbs(275))
				.build();
		var ws5 = new WeightSet.Builder()
				.exercise(Exercise.create("Deadlift"))
				.reps(3)
				.weight(Weight.ofLbs(335))
				.build();

		var ws6 = new WeightSet.Builder()
				.exercise(Exercise.create("Deadlift"))
				.reps(2)
				.weight(Weight.ofLbs(375))
				.repsInReserve(0)
				.build();
		
		Workout workout = Workout.of(List.of(ws, ws2, ws3, ws4, ws5, ws6));
		var res = workout.getVolumesPerExerciseLbs();
		System.out.println("volumes" + res.toString());
		
		var ws7 = new WeightSet.Builder()
				.exercise(Exercise.create("bench press"))
				.reps(7)
				.weight(Weight.ofKg(90))
				.build();
		double oneRepMax = (ws7.predictOneRepMax());
		
		System.out.println(StrengthEstimator.getWeightFromOneRepMax(oneRepMax, 5));
	}
}
