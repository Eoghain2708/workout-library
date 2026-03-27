package com.workoutlibrary.models.workout.sets;


import java.text.DecimalFormat;



public class StrengthEstimator {
	
	static DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * A standalone method that takes in a given number of reps and weight and returns the user's approximate one rep maximum 
	 * which is calculated by applying the Epley formula and Brzycki formula and averaging the difference between the two
	 * @param reps
	 * @param weight
	 * @return double one-rep-max approximation
	 * @throws IllegalArgumentException if weight or reps is not more than 0.
	 */
	public static double predictOneRepMax(int reps, double weight) {
		ExerciseSetValidation.validatePositive(reps);
		ExerciseSetValidation.validatePositive(weight);
		var r = (double) reps;
		double epley = weight * (1 + r / 30);
		double brzycki = weight * 36 / (37 - r);
		return (epley + brzycki) / 2;
	}

	/**
	 * This method takes in a WeightSet and, using the weight and reps, applies the Epley formula and Brzycki formula to calculate
	 * the user's one-rep maximum potential strength based off the weight they are currently able to do for n number of reps. 
	 * Epley is often cited as more optimistic whilst Brzycki is cited as conservative - this method therefore averages the results of 
	 * both to avoid two methods with potentially unclear method names.
	 * It is worth noting that including reps in reserve here would skew/complicate things greatly - RIR is assumed to be LOW for these
	 * formulae and so is ignored here.
	 * @param ws
	 * @return double One-rep-max estimate formatted to two decimal places
	 */
	protected static double predictOneRepMax(WeightSet ws) {
		double w = ws.getWeight();
		double r = (double) ws.getReps();
	
		
		// epley formula: 1RM = Weight(1 + Reps / 30)
		double epley = w * (1 + r / 30);
		
		// brzycki formula: 1RM = Weight * 36 / 37 - reps
		double brzycki = w * 36 / (37 - r);
		
		// averaging
		return Double.parseDouble(df.format((epley + brzycki) / 2));
	}
	
	
	
	/**
	 * Takes in a weight which is presumed to be the client's one-rep-maximum for an unspecified exercise, and the target number
	 * of repetitions they want to achieve on their set with a presumed low-RIR. It derives the approximate weight which should
	 * make a set of n repititions achievable but difficult based on the given one-rep-max, using the Epley formula - <br>
	 * <br>
	 * Weight = 1RM / 1 + (0.033 * r) <br>   
	 * <br>... where r is the number of repititions targetted for the set.
	 * @throws IllegalArgumentException if either oneRepMax or targetReps is not more than zero
	 */
	public static double getWeightFromOneRepMax(double oneRepMax, int targetReps) {
		ExerciseSetValidation.validatePositive(oneRepMax);
		ExerciseSetValidation.validatePositive(targetReps);
		return Double.parseDouble(df.format(oneRepMax / (1 + (0.03333 * (double) targetReps))));
	}
	
}
