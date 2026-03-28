package com.workoutlibrary.models.workout.sets;


public abstract class ExerciseSet {
	private final Exercise exercise;
	
	
	protected ExerciseSet(Builder<?> b) {
		ExerciseSetValidation.validateExercise(b.exercise);
		this.exercise = (b.exercise);
	}
	
	public abstract static class Builder<T extends Builder<T>> {
		
		private Exercise exercise;
		
		/**
		 * Add exercise to ExerciseSet (non-optional)
		 * @param ex
		 * @return
		 */
		public T exercise(Exercise ex) {
			this.exercise = ex;
			return self();
		}
		
		protected abstract T self();
		
		public abstract ExerciseSet build();
	}
	
	public Exercise getExercise() {
		return exercise;
	}
}
