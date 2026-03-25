package com.workoutlibrary.models.workout;

import java.util.List;

public class App {
	public static void main(String[] args) {
		WeightSet ws = new WeightSet.Builder().exercise(Exercise.create("Bench Press")).reps(15).weight(60).repsInReserve(2).build();
		WeightSet ws2 = new WeightSet.Builder().exercise(Exercise.create("bench Press")).reps(12).weight(90).repsInReserve(1).build();
		WeightSet ws3 = new WeightSet.Builder().exercise(Exercise.create("BenCH Press")).reps(6).weight(105).repsInReserve(0).build();
		var ws4 = new WeightSet.Builder()
				.exercise(Exercise.create("Deadlift"))
				.reps(3)
				.weight(275)
				.build();
		var ws5 = new WeightSet.Builder()
				.exercise(Exercise.create("Deadlift"))
				.reps(3)
				.weight(335)
				.build();

		var ws6 = new WeightSet.Builder()
				.exercise(Exercise.create("Deadlift"))
				.reps(2)
				.weight(350)
				.repsInReserve(0)
				.build();
		
		Workout workout = Workout.of(List.of(ws, ws2, ws3, ws4, ws5, ws6));
		var res = workout.getVolumesPerExercise();
		System.out.println(res.toString());
	}
}
