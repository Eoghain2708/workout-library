package com.workoutlibrary.models.workout;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.workoutlibrary.models.workout.sets.CardioSet;
import com.workoutlibrary.models.workout.sets.Exercise;
import com.workoutlibrary.models.workout.sets.ExerciseSet;
import com.workoutlibrary.models.workout.sets.WeightSet;

public final class Workout {
	private List<ExerciseSet> listOfSets = new LinkedList<>();
	
	private Workout(List<ExerciseSet> sets) {
		for (var set : sets) {
			if (set != null) {
				listOfSets.add(set);
			}
		}
	}
	
	/**
	 * Creates a new Workout from a list of ExerciseSets given as a paramter. If any of the given ExerciseSets is null, it will skip
	 * that ExerciseSet and continue to collect those remaining.
	 * @param sets
	 * @return new Workout consisting of each ExerciseSet in given List
	 * @throws NullPointerException if sets is null or if sets contains multiple nulls and no valid ExerciseSets
	 */
	public static Workout of(List<ExerciseSet> sets) {
		Objects.requireNonNull(sets);
		return new Workout(sets);
	}
	
	public List<ExerciseSet> getAllSets() {
		return List.copyOf(listOfSets);
	}
	
	public List<WeightSet> getAllWeightSets() {
		var sets = getAllSets();
		
		return sets.stream()
				.filter(set -> set instanceof WeightSet)
				.map(set -> (WeightSet) set)
				.toList();
	}
	
	public List<CardioSet> getAllCardioSets() {
		var sets = getAllSets();
		
		return sets.stream()
				.filter(set -> set instanceof CardioSet)
				.map(set -> (CardioSet) set)
				.toList();	
	}
	
	/**
	 * Gets volumes per exercise in kilograms by calling 
	 * @return
	 */
	public Map<Exercise, Double> getVolumesPerExerciseKgs() {
		return WorkoutAnalyser.getVolumesPerExerciseKgs(getAllWeightSets());
	}
	
	/**
	 * Gets volumes per exercise in kilograms by calling 
	 * @return
	 */
	public Map<Exercise, Double> getVolumesPerExerciseLbs() {
		return WorkoutAnalyser.getVolumesPerExerciseLbs(getAllWeightSets());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
