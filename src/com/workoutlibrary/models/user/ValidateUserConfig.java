package com.workoutlibrary.models.user;

import java.util.Objects;

import com.workoutlibrary.models.user.UserConfig.WeightMetrics;

final class ValidateUserConfig {
	private ValidateUserConfig() {}
	
	protected static void validateBodyWeight(double val, UserConfig uc) {
		String addOn = uc.getWeightPreference().equals(WeightMetrics.KILOGRAMS) ? "kg" : "lbs";
		if (val < 1) {
			throw new IllegalArgumentException("Bodyweight cannot be less than 1 " + addOn);
		}
	}
	
	protected static void validateWeightPreference(UserConfig.WeightMetrics val) {
		Objects.requireNonNull(val);
	}
}
