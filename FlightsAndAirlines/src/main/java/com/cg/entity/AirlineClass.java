package com.cg.entity; // Defines the package for the entity-related components

// Enum to define fixed categories of airline seating classes
public enum AirlineClass {
	// Predefined constant values for the enum
	// Each constant passes a display name and a multiplier for price calculation
	ECONOMY("Economy", 1.0), 
	PREMIUM_ECONOMY("Premium Economy", 1.5), 
	BUSINESS("Business", 2.0),
	FIRST_CLASS("First Class", 4.0);

	// Private field to store the user-friendly name of the class
	private final String className;
	
	// Private field to store the multiplier used to calculate ticket costs
	private final double priceFactor;

	// Private constructor (enums cannot be instantiated with 'new')
	// Used to assign values to the constants defined above
	private AirlineClass(String className, double priceFactor) {
		this.className = className;
		this.priceFactor = priceFactor;
	}

	// Getter method to retrieve the display name (e.g., "Premium Economy")
	public String getClassName() {
		return className;
	}

	// Getter method to retrieve the price multiplier (e.g., 2.0 for Business)
	public double getPriceFactor() {
		return priceFactor;
	}

}
