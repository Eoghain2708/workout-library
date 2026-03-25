package com.workoutlibrary.models.workout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestExercise {
	String validName;
	String validNameCachedCapitals;
	String validNameUnique;
	
	String invalidNameBlank;
	Exercise test;
	

	@BeforeEach
	void setUp() throws Exception {
		validName = "bench press";
		validNameCachedCapitals = " BENCH PRESS ";
	
		invalidNameBlank = " ";
		test = Exercise.create(validName);
	}

	@Test
	void testCreateAndCache() {
		Exercise test2 = Exercise.create(validNameCachedCapitals);
		assertEquals(test, test2);
	}
	
	@Test
	void rejectsInvalidName() {
		assertThrows(NullPointerException.class, () -> {
			Exercise.create(null);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Exercise.create(invalidNameBlank);
		});
	}

	@Test
	void testGetName() {
		assertEquals("bench press", test.getName());
	}

}
