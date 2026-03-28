package com.workoutlibrary.weightconversion;

public class Weight {
	private final double kilograms;
	
	private Weight(double kilograms) {
		validate(kilograms);
		this.kilograms = kilograms;
	}
	
	public static Weight ofKg(double kgs) {
		return new Weight(kgs);
	}
	
	public static Weight ofLbs(double lbs) {
		return new Weight(lbs * 0.453592);
	}
	
	
	
	public double toKg() {
		return kilograms;
	}
	
	public double toLbs() {
		return kilograms / 0.453592;
	}
	
	private static void validate(double val) {
		if (val < 0) { throw new IllegalArgumentException("Weight cannot be negative"); }
	}
}
