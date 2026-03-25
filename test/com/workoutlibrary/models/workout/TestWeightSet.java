package com.workoutlibrary.models.workout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestWeightSet {
	
	String exerciseName;
	Exercise ex;
	
	int validReps;
	int invalidRepsZero;
	int invalidRepsNegative;
	
	Integer validRepsInReserve;
	Integer invalidRepsInReserveNegative;
	
	double validWeight;
	double invalidWeightNegative;
	
	WeightSet test;

	@BeforeEach
	void setUp() throws Exception {
		exerciseName = "Bench Press";
		ex = Exercise.create(exerciseName);
		
		validReps = 5;
		invalidRepsZero = 0;
		invalidRepsNegative = -1;
		
		validRepsInReserve = 5;
		invalidRepsInReserveNegative = -2;
		
		validWeight = 100;
		invalidWeightNegative = -1;
		
		
		test = new WeightSet.Builder()
				.weight(validWeight)
				.reps(validReps)
				.repsInReserve(validRepsInReserve)
				.exercise(ex)
				.build();
	}
	
	@Test
	void rejectsInvalidReps() {
		assertThrows(IllegalArgumentException.class, () -> {
			new WeightSet.Builder()
				.weight(validWeight)
				.reps(invalidRepsZero)
				.repsInReserve(validRepsInReserve)
				.exercise(ex)
				.build();
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new WeightSet.Builder()
				.weight(validWeight)
				.reps(invalidRepsNegative)
				.repsInReserve(validRepsInReserve)
				.exercise(ex)
				.build();
		});
	}

	@Test
	void testToMap() {
		fail("Not yet implemented");
	}

	@Test
	void testGetWeight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetReps() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRepsInReserve() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTotalVolume() {
		fail("Not yet implemented");
	}

	

	@Test
	void testGetExercise() {
		fail("Not yet implemented");
	}

}
