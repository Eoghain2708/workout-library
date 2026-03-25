package com.workoutlibrary.models.workout;

import java.util.Objects;

final class ExerciseSetValidation {
	private ExerciseSetValidation() {}
	
	protected static void validateExercise(Exercise e) {
		Objects.requireNonNull(e);
	}
	
	protected static void validateNonNegative(int val) {
		if (val < 0) { throw new IllegalArgumentException("Weight, reps and reps in reserve cannot be negative"); }
	}
	
	protected static void validateNonNegative(double val) {
		if (val < 0) { throw new IllegalArgumentException("Weight, reps and reps in reserve cannot be negative"); }
	}
	

	protected static void validatePositive(int val) {
		if (val <= 0) { throw new IllegalArgumentException("Reps must be 1 or more"); }
	}
	
	protected static void validatePositive(double val) {
		if (val <= 0) { throw new IllegalArgumentException("Reps must be 1 or more"); }
	}
		
}
