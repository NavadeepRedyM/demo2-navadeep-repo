package com.cg.entity; // Defines the package location for entity classes

import java.time.LocalDate; // Import for handling dates (Year-Month-Day)
import java.time.LocalTime; // Import for handling time (Hours-Minutes)

import com.fasterxml.jackson.annotation.JsonFormat; // Annotation to format Date/Time for JSON conversion

import jakarta.persistence.Entity; // Marks this class as a database-mapped entity
import jakarta.persistence.EnumType; // Defines how Enum values are stored in the database
import jakarta.persistence.Enumerated; // Specifies that a field is an Enum type
import jakarta.persistence.GeneratedValue; // Strategy for primary key generation
import jakarta.persistence.GenerationType; // Enumeration for primary key generation types
import jakarta.persistence.Id; // Marks the field as the Primary Key
import jakarta.persistence.JoinColumn; // Defines the foreign key column name
import jakarta.persistence.ManyToOne; // Defines a many-to-one relationship with Airline
import jakarta.persistence.Table; // Specifies the database table name

@Entity // Instructs JPA to treat this class as a table
@Table(name="flights") // Maps this entity to the "flights" table in the database
public class Flight {

	@Id // Sets flightId as the Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID in the database
	private int flightId; // Unique identifier for each flight

	private String enterprise; // Name of the operating company
	private double flightPrice; // Base cost of the flight
	private String source; // Departure city/airport
	private String destination; // Arrival city/airport

	@JsonFormat(pattern = "h:mm a", locale = "en") // Formats time as 12-hour with AM/PM (e.g., 10:30 AM)
	private LocalTime departureTime; // Scheduled time for takeoff

	@JsonFormat(pattern = "yyyy-MM-dd") // Formats date as Year-Month-Day
	private LocalDate arrivalDate; // Scheduled date for landing

	@JsonFormat(pattern = "h:mm a", locale = "en") // Formats arrival time with AM/PM
	private LocalTime arrivalTime; // Scheduled time for landing

	private int duration; // Total flight duration in minutes or hours
	private int totSeat; // Total seating capacity of the flight
	private int availSeat; // Currently available seats for booking

	@Enumerated(EnumType.STRING) // Stores the Enum name as a String (e.g., "BUSINESS") in the DB
	private AirlineClass className; // The service class of the flight (Economy, Business, etc.)

	@ManyToOne // Multiple flights can belong to a single Airline
	@JoinColumn(name = "airline_id") // Creates a foreign key column named "airline_id"
	private Airline airline; // Reference to the parent Airline entity

	@JsonFormat(pattern = "yyyy-MM-dd") // Formats departure date as Year-Month-Day
	private LocalDate departureDate; // Scheduled date for takeoff

	// Default constructor required by JPA
	public Flight() {

	}

	// Full constructor to initialize a Flight object with all attributes
	public Flight(int flightId, String enterprise, double flightPrice, String source, String destination,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			int duration, int totSeat, int availSeat, AirlineClass className, Airline airline) {
		super(); // Calls the Object class constructor
		this.flightId = flightId;
		this.enterprise = enterprise;
		this.flightPrice = flightPrice;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.totSeat = totSeat;
		this.availSeat = availSeat;
		this.className = className;
		this.airline = airline;
	}

	// Getter and Setter methods follow:
	// These allow Spring, Hibernate, and other classes to access and modify private fields

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public double getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(double flightPrice) {
		this.flightPrice = flightPrice;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getTotSeat() {
		return totSeat;
	}

	public void setTotSeat(int totSeat) {
		this.totSeat = totSeat;
	}

	public int getAvailSeat() {
		return availSeat;
	}

	public void setAvailSeat(int availSeat) {
		this.availSeat = availSeat;
	}

	public AirlineClass getClassName() {
		return className;
	}

	public void setClassName(AirlineClass className) {
		this.className = className;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

}
