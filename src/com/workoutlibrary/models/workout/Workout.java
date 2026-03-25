package com.workoutlibrary.models.workout;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Workout {
	private List<ExerciseSet> listOfSets = new LinkedList<>();
	
	private Workout(List<ExerciseSet> sets) {
		for (var set : sets) {
			if (set == null) {
				continue;
			}
			listOfSets.add(set);
		}
	}
	
	public static Workout of(List<ExerciseSet> sets) {
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
	
	
	public Map<Exercise, Double> getVolumesPerExercise() {
		return WorkoutAnalyser.getVolumesPerExercise(getAllWeightSets());
	}
	
	
	
	
	
	
	
	
}
