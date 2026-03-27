package com.workoutlibrary.models.workout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.workoutlibrary.models.workout.sets.Exercise;
import com.workoutlibrary.models.workout.sets.WeightSet;
import com.workoutlibrary.weightconversion.Weight;

class TestWeightSet {
	
	String exerciseName;
	Exercise ex;
	
	int validReps;
	int invalidRepsZero;
	int invalidRepsNegative;
	
	Integer validRepsInReserve;
	Integer invalidRepsInReserveNegative;
	
	Weight validWeight;
	
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
		validWeight = Weight.ofKg(100);
	
		
	
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
		int validReps2 = 10;
		var ex2 = Exercise.create("curl");
		WeightSet testForMap = new WeightSet.Builder()
				.weight(validWeight)
				.reps(validReps2)
				.repsInReserve(validRepsInReserve)
				.exercise(ex2)
				.build();
		
		Map<String, Object> testMap = new HashMap<>();
		testMap.put("exercise", ex2);
		testMap.put("reps", validReps2); // 10 reps
		testMap.put("weight", validWeight.toKg()); // 100 weight
		testMap.put("rir", validRepsInReserve);
		assertEquals(testMap, testForMap.toMapKgs());
		
	}

	@Test
	void testGetWeight() {
		assertEquals(validWeight, test.getWeight());
	}

	@Test
	void testGetReps() {
		assertEquals(validReps, test.getReps());
	}

	@Test
	void testGetRepsInReserve() {
		assertEquals(validRepsInReserve, test.getRepsInReserve());
	}

	@Test
	void testGetTotalVolumeKgs() {
		assertEquals((validReps * validWeight.toKg()), test.getTotalVolumeKgs());
	}
	
	@Test
	void testGetTotalVolumeLbs() {
		assertEquals((validReps * validWeight.toLbs()), test.getTotalVolumeLbs());
	}

	

	@Test
	void testGetExercise() {
		assertEquals(ex, test.getExercise());
	}

}
