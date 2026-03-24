package com.workoutlibrary.models.workout;

import java.util.Objects;

public class PerformedSetValidation {
	private PerformedSetValidation() {}
	
	protected static void validateExercise(Exercise e) {
		Objects.requireNonNull(e);
	}
	
	protected static <T extends Number> void validateNonNegative(T val) {
		if ((int) val < 0) { throw new IllegalArgumentException("Weight, reps and reps in reserve cannot be negative"); }
	}
	
	protected static void validatePositive(int val) {
		if (val <= 0) { throw new IllegalArgumentException("Reps must be 1 or more"); }
	}
		
}
