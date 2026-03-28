package com.workoutlibrary.models.workout.sets;



public enum RIRtoRPE {
	
	TEN(10, 0), NINE(9, 1), EIGHT(8, 2), SEVEN(7, 3), SIX(6, 4), FIVE(5, 5), FOUR(4, 6), THREE(3, 7), TWO(2, 8), ONE(1, 9), ZERO(0, 10);
	
	final Integer rir;
	final Integer rpe;
	RIRtoRPE(Integer rir, Integer rpe) {
		this.rir = rir;
		this.rpe = rpe;
	}

	protected static Integer toRPE(Integer rir) {
		for (var enumVal : RIRtoRPE.values()) {
			if (rir == enumVal.rir) {
				return enumVal.rpe;
			}
		}
		return null;
	}
}
