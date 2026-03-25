package com.workoutlibrary.models.workout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCardioSet {
	CardioSet test;
	
	String validExerciseName;
	String invalidExerciseName;
	
	Exercise ex;
	
	int validDuration;
	int invalidDurationZero;
	int invalidDurationNegative;
	
	

	@BeforeEach
	void setUp() throws Exception {
		validExerciseName = "pullup";
		invalidExerciseName = "";
		
		ex = Exercise.create(validExerciseName);
		
		validDuration = 15;
		invalidDurationZero = 0;
		invalidDurationNegative = -15;
		
		test = new CardioSet.Builder().duration(validDuration)
				.exercise(ex)
				.build();
				
				
	}
	
	@Test
	void rejectsInvalidExercise() {
		assertThrows(IllegalArgumentException.class, () -> {
			new CardioSet.Builder()
			.exercise(Exercise.create(invalidExerciseName))
			.duration(validDuration)
			.build();
		});
		
		assertThrows(NullPointerException.class, () -> {
			new CardioSet.Builder()
			.exercise(null)
			.duration(validDuration)
			.build();
		});
	}
	
	@Test
	void rejectsInvalidDuration() {
		assertThrows(IllegalArgumentException.class, () -> {
			new CardioSet.Builder()
			.exercise(ex)
			.duration(invalidDurationZero)
			.build();
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new CardioSet.Builder()
			.exercise(ex)
			.duration(invalidDurationNegative)
			.build();
		});
	}

	@Test
	void testGetDuration() {
		assertEquals(validDuration, test.getDuration());
	}

	@Test
	void testDurationToSeconds() {
		assertEquals(900, test.durationToSeconds());
	}

	@Test
	void testGetExercise() {
		assertEquals(ex, test.getExercise());
	}

}
