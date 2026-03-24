package com.workoutlibrary.models.workout;

import java.util.LinkedList;
import java.util.List;

public final class Workout {
	private List<PerformedSet> listOfSets = new LinkedList<>();
	
	private Workout(List<PerformedSet> sets) {
		for (var set : sets) {
			if (set == null) {
				continue;
			}
			listOfSets.add(set);
		}
	}
	
	public static Workout of(List<PerformedSet> sets) {
		return new Workout(sets);
	}
	
	public List<PerformedSet> getWorkoutData() {
		return List.copyOf(listOfSets);
	}
	
	
	
	
}
