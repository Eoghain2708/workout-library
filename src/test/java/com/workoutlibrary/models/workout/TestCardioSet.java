package com.workoutlibrary.models.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.workoutlibrary.models.workout.sets.CardioSet;
import com.workoutlibrary.models.workout.sets.Exercise;

class TestCardioSet {
	CardioSet test;
	
	String validExerciseName;
	String invalidExerciseName;
	
	Exercise ex;
	
	Duration validDuration;

	

	@BeforeEach
	void setUp() throws Exception {
		validExerciseName = "pullup";
		invalidExerciseName = "";
		
		ex = Exercise.create(validExerciseName);
		
		validDuration = Duration.ofMinutes(30);
	
		
		test = new CardioSet.Builder().duration(Duration.ofMinutes(30))
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
		assertThrows(NullPointerException.class, () -> {
			new CardioSet.Builder()
			.exercise(ex)
			.duration(null)
			.build();
		});
		
	
	}

	@Test
	void testGetDuration() {
		assertEquals(validDuration, test.getDuration());
	}


	@Test
	void testGetExercise() {
		assertEquals(ex, test.getExercise());
	}

}
