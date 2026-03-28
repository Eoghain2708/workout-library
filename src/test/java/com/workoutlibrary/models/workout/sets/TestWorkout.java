package com.workoutlibrary.models.workout.sets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.workoutlibrary.models.workout.Workout;
import com.workoutlibrary.weightconversion.Weight;

class TestWorkout {
	WeightSet ws1;
	WeightSet ws2;
	
	CardioSet cs1;
	CardioSet cs2;
	
	Workout test;
	

	@BeforeEach
	void setUp() throws Exception {
		ws1 = new WeightSet.Builder()
				.exercise(Exercise.create("pullup"))
				.reps(10)
				.weight(Weight.ofKg(12))
				.build();
		
		ws2 = new WeightSet.Builder()
				.exercise(Exercise.create("pushup"))
				.reps(12)
				.weight(Weight.ofKg(12))
				.build();
		
		cs1 = new CardioSet.Builder()
				.exercise(Exercise.create("Running"))
				.duration(Duration.ofMinutes(25))
				.build();
		
		cs2 = new CardioSet.Builder()
				.exercise(Exercise.create("HIIT"))
				.duration(Duration.ofMinutes(20))
				.build();
		
		test = Workout.of(List.of(ws1, ws2, cs1, cs2));
	}

	@Test
	void testRejectsNull() {
		assertThrows(NullPointerException.class, () -> {
			Workout.of(null);
		});
	}
	
	@Test
	void testRejectsMultipleNullSets() {
		assertThrows(NullPointerException.class, () -> {
			Workout.of(List.of(null, null, null));
		});
	}

	@Test
	void testGetAllSets() {
		List<ExerciseSet> list = List.of(ws1, ws2, cs1, cs2);
		assertEquals(list, test.getAllSets());
	}
	
	@Test
	void testGetAllSets_DoesNotCountNull() {
		List<ExerciseSet> list = List.of(ws1, ws2, cs1, cs2);
		Workout nullWorkout = Workout.of(new ArrayList<>(Arrays.asList(ws1, ws2, null, cs1, null, cs2)));
		System.out.println(nullWorkout.getAllSets());
		
		assertEquals(list.size(), nullWorkout.getAllSets().size());
	}
	

	@Test
	void testGetAllWeightSets() {
		List<WeightSet> expected = Arrays.asList(ws1, ws2);
		assertEquals(expected, test.getAllWeightSets());
	}

	@Test
	void testGetAllCardioSets() {
		List<CardioSet> expected = Arrays.asList(cs1, cs2);
		assertEquals(expected, test.getAllCardioSets());
	}

	@Test
	void testGetVolumesPerExercise() {
		Map<Exercise, Double> map = new HashMap<>();
		map.put(ws1.getExercise(), ws1.getTotalVolumeKgs());
		map.put(ws2.getExercise(), ws2.getTotalVolumeKgs());
	
		
		assertEquals(map, test.getVolumesPerExerciseKgs());
	}

}
