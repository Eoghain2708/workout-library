package com.workoutlibrary.models.workout;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Exercise {
	private static Set<Exercise> cache = new HashSet<>();
	private final String name;
	
	private Exercise(String name) {
		this.name = name;
	}
	
	public static Exercise create(String name) {
		validateName(name);
		for (var ex : cache) {
			if (ex.name.equalsIgnoreCase(name)) { 
				return ex;
			}
		}
		var newExercise = new Exercise(name);
		cache.add(newExercise);
		return newExercise;
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

	private static void validateName(String val) {
		if (Objects.requireNonNull(val).isBlank()) {
			throw new IllegalArgumentException("Exercise name cannot be blank");
		}
	}
}
