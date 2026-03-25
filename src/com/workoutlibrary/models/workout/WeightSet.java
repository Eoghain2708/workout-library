package com.workoutlibrary.models.workout;

import java.util.HashMap;
import java.util.Map;

/**
 * A WeightSet consists of an Exercise and Repetitions (non-optional) with optional inclusion of Reps in Reserve (RIR) and 
 * Weight (Which can be left at zero for bodyweight exercises). Data for a given set can be retrieved together in a HashMap, with individual data
 * still able to be retrieved by getters. A fuller experience of this API is achieved by including as many optional fields
 * as possible.
 * @apiNote - keys: "exercise", "weight", "reps", "rir"
 * @throws NullPointerException if Exercise is null
 * @throws IllegalArgumentException if reps is not set to a value of 1 or more
 * @author Eoghain Magee
 */
public final class WeightSet extends ExerciseSet {
	
	
	private final double weight;
	private final int reps;
	private final Integer repsInReserve;
	
	
	private WeightSet(Builder b) {
		super(b);
		this.weight = b.weight;
		this.reps = b.reps;
		this.repsInReserve = b.repsInReserve;
	
	}
	
	
	public static class Builder extends ExerciseSet.Builder<Builder> {
		private double weight;
		private int reps;
		private Integer repsInReserve;
		
		
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
		public Builder reps(Integer reps) {
			this.reps = reps;
			return this;
		}
		
		/**
		 * Add reps in reserve (RIR) to given set via Builder
		 * @param repsInReserve
		 * @return instance of Builder class
		 */
		public Builder repsInReserve(int repsInReserve) {
			this.repsInReserve = repsInReserve;
			return this;
		}
		
		/**
		 * Create a new PerformedSet with parameters passed through the Builder. 
		 * @return PerformedSet
		 * @throws IllegalArgumentException if weight or repsInReserve are less than 0, or if reps is less than 1.
		 * @throws NullPointerException if exercise is null
		 */
		public WeightSet build() {
			ExerciseSetValidation.validateNonNegative(weight);
			ExerciseSetValidation.validatePositive(reps);
			if (repsInReserve != null) {
				ExerciseSetValidation.validateNonNegative(repsInReserve);
			}
			return new WeightSet(this);
		}

		@Override
		protected Builder self() {
			// TODO Auto-generated method stub
			return this;
		}

	}
	
	/**
	 * Returns a map which contains the exercise, reps, weight, and reps in reserve of the set
	 * @return Map<String, Object>
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("exercise", getExercise());
		map.put("reps", reps);
		map.put("weight", weight);
		map.put("rir", repsInReserve);
		return map;
	}
	
	/**
	 * Retrieve the weight used in the set
	 * @return
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Retrieve the number of reps performed in the set
	 * @return
	 */
	public int getReps() {
		return reps;
	}

	/**
	 * Retrieve the number of reps in reserve when the set ended
	 * @return Integer (reps in reserve, can be null/not set)
	 */
	public Integer getRepsInReserve() {
		return repsInReserve;
	}
	
	/**
	 * Retrieve total volume from the set via multiplying reps and weight
	 * @return
	 */
	public double getTotalVolume() {
		return weight * reps;
	}
	
	
}
