package com.workoutlibrary.models.workout;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.workoutlibrary.models.workout.sets.Exercise;
import com.workoutlibrary.models.workout.sets.WeightSet;

public class WorkoutAnalyser {
	
	static DecimalFormat df = new DecimalFormat("#.##");
	
	protected static Map<Exercise, Double> getVolumesPerExerciseKgs(List<WeightSet> sets) {
		Map<Exercise, Double> map = new HashMap<>();
		for (var set : sets) {
			double truncated = Double.parseDouble(df.format(set.getTotalVolumeKgs()));
			map.merge(set.getExercise(), truncated, Double::sum);
			
		}
		
		return map;
	}
	
	protected static Map<Exercise, Double> getVolumesPerExerciseLbs(List<WeightSet> sets) {
		Map<Exercise, Double> map = new HashMap<>();
		for (var set : sets) {
			double truncated = Double.parseDouble(df.format(set.getTotalVolumeLbs()));
			map.merge(set.getExercise(), truncated, Double::sum);
			
		}
		
		return map;
	}
	
	
}
