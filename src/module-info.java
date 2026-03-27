/**
 * 
 */
/**
 * 
 */
module com.workoutlibrary {
	exports com.workoutlibrary.models.workout;
	exports com.workoutlibrary.models.workout.sets;
	exports com.workoutlibrary.weightconversion;
	
	opens com.workoutlibrary.models.workout to org.junit.jupiter.api;
	requires org.junit.jupiter.api;
}