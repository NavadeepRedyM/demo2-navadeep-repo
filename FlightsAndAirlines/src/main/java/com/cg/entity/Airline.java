package com.cg.entity; // Defines the package for database entity classes

import java.util.ArrayList; // Import for the ArrayList implementation
import java.util.List; // Import for the List interface to handle collections

import jakarta.persistence.CascadeType; // Import for defining how operations propagate to child entities
import jakarta.persistence.Entity; // Marks this class as a JPA entity (mapped to a DB table)
import jakarta.persistence.GeneratedValue; // Annotation to configure the way increment happens
import jakarta.persistence.GenerationType; // Strategy for primary key generation (e.g., Auto-increment)
import jakarta.persistence.Id; // Marks the primary key field
import jakarta.persistence.OneToMany; // Defines a one-to-many relationship with another entity
import jakarta.persistence.Table; // Specifies the name of the database table

@Entity // Tells JPA that this class should be managed and stored in the database
@Table(name="airlines") // Map this class specifically to the "airlines" table in the DB
public class Airline {
	@Id // Designates this field as the unique Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically increments the ID (Identity column)
	private int Aid; // Unique ID for the airline

	private String AirName; // Field to store the name of the airline

	// Establishes a 1-to-N relationship; 'mappedBy' points to the field in the Flight class
	// CascadeType.ALL ensures if an airline is deleted/updated, its flights are too
	@OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
	private List<Flight> flights = new ArrayList<>(); // Collection of flights belonging to this airline

	// Default no-argument constructor (required by JPA/Hibernate)
	public Airline() {

	}

	// Parameterized constructor to initialize an airline with an ID and name
	public Airline(int aid, String airName) {
		super(); // Calls the base Object constructor
		Aid = aid;
		AirName = airName;
	}

	// Getter and Setter methods follow:
	// These allow JPA and other classes to read and write private field values

	public int getAid() {
		return Aid;
	}

	public void setAid(int aid) {
		this.Aid = aid;
	}

	public String getAirName() {
		return AirName;
	}

	public void setAirName(String airName) {
		this.AirName = airName;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	// Overrides the default toString to provide a readable string representation of the object
	@Override
	public String toString() {
		return "Airline [Aid=" + Aid + ", AirName=" + AirName + ", flights=" + flights + "]";
	}

}
