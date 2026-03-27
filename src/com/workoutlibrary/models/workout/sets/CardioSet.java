package com.workoutlibrary.models.workout.sets;


/**
 * A CardioSet takes in an Exercise and duration (in minutes) both of which are non optional.
 * 
 */
public class CardioSet extends ExerciseSet {
	
	private final double duration;
	
	private CardioSet(Builder b) {
		super(b);
		ExerciseSetValidation.validatePositive(b.duration);
		this.duration = b.duration;
	}
	
	public static class Builder extends ExerciseSet.Builder<Builder> {
		
		private double duration;
		
		/**
		 * Add a duration to the CardioSet (in minutes)
		 * @param val
		 * @return
		 */
		public Builder duration(double val) {
			this.duration = val;
			return this;
		}
		
		@Override
		protected Builder self() {
			return this;
		}
		
		/**
		 * 
		 * @return new CardioSet with given Exercise and duration
		 * @throws IllegalArgumentException if duration <= 0
		 * @throws NullPointerException if Exercise is null
		 */
		@Override
		public CardioSet build() {
			return new CardioSet(this);
		}
	}
	
	/**
	 * Retrieve duration of CardioSet
	 * @return double duration
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * Retrieve duration of CardioSet in seconds
	 * @return double duration * 60
	 */
	public double durationToSeconds() {
		return duration * 60;
	}
}
