package com.workoutlibrary.models.user;

import java.util.Objects;

/**
 * The UserConfig consists of bodyweight and weight preference in terms of kilograms and pounds (lbs) which can be used when
 * displaying their performance throughout the workout - their weight preference is saved so as to make for easy converting
 * in other parts of the API
 */
public final class UserConfig {

	public enum WeightMetrics {
		KILOGRAMS { public double convert(double val) { 
			if (val < 0) { throw new IllegalArgumentException("Cannot convert negative numbers"); }
			    return val * 0.453592; 
			}
		
		},
		
		POUNDS { public double convert(double val) { 
			if (val < 0) { throw new IllegalArgumentException("Cannot convert negative numbers"); }
			return val * 2.20462; 
			}; 
			
		};
		
		
		/**
		 * Takes a parameter that is either kilograms, or pounds (lbs) as checked against the User's preferences, then converts 
		 * it into lbs.
		 * @param val
		 * @return double
		 * @throws IllegalArgumentException if val < 0
		 */
		public abstract double convert(double val);
	}
	
	private final double bodyWeight;
	private final WeightMetrics weightPreference;
	
	private UserConfig(Builder b) {
		ValidateUserConfig.validateBodyWeight(b.bodyWeight, this);
		this.bodyWeight = b.bodyWeight;
		this.weightPreference = b.weightPreference;
	}
	
	public static class Builder {
		private double bodyWeight;
		private WeightMetrics weightPreference;
		
		public Builder bodyWeight(double val) {
			this.bodyWeight = val;
			return this;
		}
		
		public Builder weightPreference(WeightMetrics wp) {
			this.weightPreference = wp;
			return this;
		}
		
		public UserConfig build() {
			return new UserConfig(this);
		}
	}
	
	public WeightMetrics getWeightPreference() {
		return weightPreference;
	}
	
	public double getBodyWeight() {
		return bodyWeight;
	}
	
	
	/**
	 * 
	 * @param val
	 * @return
	 */
	public UserConfig changeWeightPreference(WeightMetrics val) {
		Objects.requireNonNull(val);
		return new UserConfig.Builder().bodyWeight(bodyWeight).weightPreference(val).build();
	}

	
	
}
