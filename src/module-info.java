/**
 * 
 */
/**
 * 
 */
module com.workoutlibrary {
	exports com.workoutlibrary.models.user;
	exports com.workoutlibrary.models.workout;
	
	opens com.workoutlibrary.models.user to org.junit.jupiter.api;
	opens com.workoutlibrary.models.workout to org.junit.jupiter.api;
	requires org.junit.jupiter.api;
}