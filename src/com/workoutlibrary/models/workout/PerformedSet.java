package com.workoutlibrary.models.workout;


import java.util.HashMap;

import java.util.Map;

/**
 * A PerformedSet consists of an Exercise and Repetitions (non-optional) with optional inclusion of Reps in Reserve (RIR) and 
 * Weight (Which can be left at zero for bodyweight exercises). Data for a given set is kept in a HashMap, with individual data
 * still able to be retrieved by getters. A fuller experience of this API is achieved by including as many optional fields
 * as possible.
 * @apiNote - keys: "exercise", "weight", "reps", "rir"
 * @throws NullPointerException if Exercise is null
 * @throws IllegalArgumentException if reps is not set to a value of 1 or more
 * @author Eoghain Magee
 */
public final class PerformedSet {
	
	private Map<String, Object> setData = new HashMap<>();
	private final Exercise exercise;
	private final double weight;
	private final int reps;
	private final Integer repsInReserve;
	
	
	private PerformedSet(Builder b) {
		this.exercise = b.exercise;
		this.weight = b.weight;
		this.reps = b.reps;
		this.repsInReserve = b.repsInReserve;
		setData.put("exercise", exercise);
		setData.put("weight", weight);
		setData.put("reps", reps);
		setData.put("rir", repsInReserve);
	}
	
	
	public static class Builder {
		private Exercise exercise;
		private double weight;
		private int reps;
		private int repsInReserve;
		
		/**
		 * Instantiate PerformedSet Builder
		 * @param exercise - instance of Exercise, cannot be null
		 */
		public Builder(Exercise exercise) {
			this.exercise = exercise;
		}
		
		/**
		 * Add weight to given set via Builder
		 * @param weight
		 * @return instance of Builder class
		 */
		public Builder weight(double weight) {
			this.weight = weight;
			return this;
		}
		
		/**
		 * Add reps to given set via Builder
		 * @param reps
		 * @return instance of Builder class
		 */
		public Builder reps(int reps) {
			this.reps = reps;
			return this;
		}
		
		/**
		 * Add reps in reserve (RIR) to given set via Builder
		 * @param repsInReserve
		 * @return instance of Builder class
		 */
		public Builder repsInReverse(int repsInReserve) {
			this.repsInReserve = repsInReserve;
			return this;
		}
		
		/**
		 * Create a new PerformedSet with parameters passed through the Builder. 
		 * @return PerformedSet
		 * @throws IllegalArgumentException if weight or repsInReserve are less than 0, or if reps is less than 1.
		 * @throws NullPointerException if exercise is null
		 */
		public PerformedSet build() {
			PerformedSetValidation.validateExercise(exercise);
			PerformedSetValidation.validateNonNegative(weight);
			PerformedSetValidation.validatePositive(reps);
			PerformedSetValidation.validateNonNegative(repsInReserve);
			return new PerformedSet(this);
		}

	}


	public Exercise getExercise() {
		return exercise;
	}


	public double getWeight() {
		return weight;
	}


	public int getReps() {
		return reps;
	}


	public Integer getRepsInReserve() {
		return repsInReserve;
	}
	
	public Map<String, Object> getSetData() { 
		return Map.copyOf(setData);
	}
	
	public double getTotalVolume() {
		return weight * reps;
	}
	
	
}
