package com.workoutlibrary;

import java.util.Objects;

import com.workoutlibrary.weightconversion.Weight;

public class User {
	private final Weight bodyWeight;
	
	private User(Weight bodyWeight) {
		validate(bodyWeight);
		this.bodyWeight = bodyWeight;
	}
	
	public static User ofBodyWeight(Weight bodyWeight) {
		return new User(bodyWeight);
	}
	
	public Weight getBodyWeight() {
		return bodyWeight;
	}
	
	private void validate(Weight val) {
		Objects.requireNonNull(val);
	}
}
