package com.workoutlibrary.models.workout.sets;

import java.util.HashMap;

import java.util.Map;
import java.util.Objects;



/**
 * An Exercise is a basic class created by the method Exercise.create(String name)
 */
public class Exercise {
	private static final Map<String, Exercise> CACHE = new HashMap<>();
	private final String name;
	
	private Exercise(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param String(name)
	 * @return a new Exercise with a name of <b>name</b>
	 * @throws IllegalArgumentException if <b>name</b>.isBlank() returns <b>true</> 
	 * @throws NullPointerException if <b>name</> is null
	 */
	public static Exercise create(String name) {
		validateName(name);
		String key = name.trim().toLowerCase();
		return CACHE.computeIfAbsent(key, Exercise::new);
	}
	
	/**
	 * Retrieve name of Exercise
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		return Objects.equals(name, other.name);
	}
	
	

	@Override
	public String toString() {
		return "Exercise [name=" + name + "]";
	}

	private static void validateName(String val) {
		if (Objects.requireNonNull(val).isBlank()) {
			throw new IllegalArgumentException("Exercise name cannot be blank");
		}
	}
}
