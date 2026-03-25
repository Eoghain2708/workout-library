package com.workoutlibrary.models.workout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutAnalyser {
	
	
	protected static Map<Exercise, Double> getVolumesPerExercise(List<WeightSet> sets) {
		Map<Exercise, Double> map = new HashMap<>();
		for (var set : sets) {
			map.merge(set.getExercise(), (Double) set.getTotalVolume(), Double::sum);
		}
		return map;
	}
}
