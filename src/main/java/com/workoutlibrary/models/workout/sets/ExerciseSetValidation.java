package com.workoutlibrary.models.workout.sets;

import java.util.Objects;

final class ExerciseSetValidation {
	private ExerciseSetValidation() {}
	
	protected static void validateExercise(Exercise e) {
		Objects.requireNonNull(e);
	}
	
	protected static void validateNonNegative(int val) {
		if (val < 0) { throw new IllegalArgumentException("Weight and reps cannot be negative"); }
	}
	
	protected static void validateNonNegative(double val) {
		if (val < 0) { throw new IllegalArgumentException("Weight and reps cannot be negative"); }
	}
	

	protected static void validatePositive(int val) {
		if (val <= 0) { throw new IllegalArgumentException("Reps must be 1 or more"); }
	}
	
	protected static void validatePositive(double val) {
		if (val <= 0) { throw new IllegalArgumentException("Reps must be 1 or more"); }
	}
	
	protected static void validateRIR(Integer val) {
		if (val == null) { return; }
		if (val < 0 || val > 10) { throw new IllegalArgumentException("Reps in Reserve cannot be negative or more than 10"); }
	}
		
}
