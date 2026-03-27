package com.workoutlibrary.models.workout.sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.workoutlibrary.weightconversion.Weight;

/**
 * A WeightSet consists of an Exercise, Reptitions and Weight, with optional inclusion of Reps in Reserve (RIR).
 * Data for a given set can be retrieved together in a HashMap, with individual data
 * still able to be retrieved by getters. A fuller experience of this API is achieved by including as many optional fields
 * as possible.
 * @apiNote - keys: "exercise", "weight", "reps", "rir"
 * @throws NullPointerException if Exercise is null
 * @throws IllegalArgumentException if reps is not set to a value of 1 or more
 * @author Eoghain Magee
 */
public final class WeightSet extends ExerciseSet {
	
	
	private final Weight weight;
	private final int reps;
	private final Integer repsInReserve;
	
	
	private WeightSet(Builder b) {
		super(b);
		if (b.weight == null) { b.weight = Weight.ofKg(0); }
		this.weight = b.weight;
		this.reps = b.reps;
		this.repsInReserve = b.repsInReserve;
	}
	
	
	public static class Builder extends ExerciseSet.Builder<Builder> {
		private Weight weight;
		private int reps;
		private Integer repsInReserve;
		
		
		/**
		 * Add weight to given set via Builder
		 * @param weight
		 * @return instance of Builder class
		 */
		public Builder weight(Weight weight) {
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
	public Map<String, Object> toMapKgs() {
		Map<String, Object> map = new HashMap<>();
		map.put("exercise", getExercise());
		map.put("reps", reps);
		map.put("weight", weight.toKg());
		map.put("rir", repsInReserve);
		return map;
	}
	
	/**
	 * Returns a map which contains the exercise, reps, weight, and reps in reserve of the set
	 * @return Map<String, Object>
	 */
	public Map<String, Object> toMapLbs() {
		Map<String, Object> map = new HashMap<>();
		map.put("exercise", getExercise());
		map.put("reps", reps);
		map.put("weight", weight.toLbs());
		map.put("rir", repsInReserve);
		return map;
	}
	
	/**
	 * Retrieve the weight used in the set
	 * @return
	 */
	public Weight getWeight() {
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
	 * @return double 
	 */
	public double getTotalVolumeKgs() {
		return reps * weight.toKg();
	}
	
	/**
	 * Retrieve total volume from the set via multiplying reps and weight
	 * @return double 
	 */
	public double getTotalVolumeLbs() {
		return reps * weight.toLbs();
	}
	

	/**
	 * This method applies to a WeightSet and, using the weight and reps, applies the Epley formula and Brzycki formula to calculate
	 * the user's one-rep maximum potential strength based off the weight they are currently able to do for n number of reps. 
	 * Epley is often cited as more optimistic whilst Brzycki is cited as conservative - this method therefore averages the results of 
	 * both to avoid two methods with potentially unclear method names.
	 * It is worth noting that including reps in reserve here would skew/complicate things greatly - RIR is assumed to be LOW for these
	 * formulae and so is ignored here.
	 * @param ws
	 * @return double One-rep-max estimate formatted to two decimal places
	 */
	public double predictOneRepMax() {
		return StrengthEstimator.predictOneRepMax(this);
	}
	
	
	
	
	
	
}
