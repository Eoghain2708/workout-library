package com.workoutlibrary.models.workout.sets;

import java.time.Duration;
import java.util.Objects;

/**
 * A CardioSet takes in an Exercise and duration (in minutes) both of which are non optional.
 * 
 */
public class CardioSet extends ExerciseSet {
	
	private final Duration duration;
	
	private CardioSet(Builder b) {
		super(b);
		this.duration = b.duration;
	}
	
	public static class Builder extends ExerciseSet.Builder<Builder> {
		
		private Duration duration;
		
		/**
		 * Add a duration to the CardioSet 
		 * @param val
		 * @return
		 */
		public Builder duration(Duration val) {
			
			this.duration = Objects.requireNonNull(val);
			return this;
		}
		
		@Override
		protected Builder self() {
			return this;
		}
		
		/**
		 * 
		 * @return new CardioSet with given Exercise and duration
		 * @throws NullPointerException if Exercise is null or Duration is null
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
	public Duration getDuration() {
		return duration;
	}
	
	
}
