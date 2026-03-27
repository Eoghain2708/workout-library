package com.workoutlibrary.weightmetrics;



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


