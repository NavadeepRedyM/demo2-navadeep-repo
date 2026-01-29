package com.cg.dto; // Defines the package for Data Transfer Objects

import java.time.LocalDate; // Import for date handling (Year-Month-Day)
import java.time.LocalTime; // Import for time handling (Hour-Minute)

import com.cg.entity.AirlineClass; // Import for the airline category enum (Economy/Business)
import com.fasterxml.jackson.annotation.JsonFormat; // Annotation to define date/time formatting for JSON

// This class acts as a data carrier between the service layer and the UI/Controller
public class FlightDTO {
	private int flightId; // Unique identifier for the flight
	private String enterprise; // Name of the company/enterprise managing the flight
	private double flightPrice; // Cost of the flight ticket
	private String source; // Departure city/airport
	private String destination; // Arrival city/airport

	@JsonFormat(pattern = "yyyy-MM-dd") // Specifies the date format as Year-Month-Day
	private LocalDate departureDate; // Date when the flight takes off

	@JsonFormat(pattern = "h:mm") // Specifies time format as Hour:Minute (12-hour)
	private LocalTime departureTime; // Time when the flight takes off

	@JsonFormat(pattern = "yyyy-MM-dd") // Specifies the date format for arrival
	private LocalDate arrivalDate; // Date when the flight lands

	@JsonFormat(pattern = "h:mm") // Specifies the time format for arrival
	private LocalTime arrivalTime; // Time when the flight lands

	private int duration; // Total travel time (usually in minutes or hours)
	private int totSeat; // Total number of seats available on the aircraft
	private int availSeat; // Number of seats currently remaining for booking
	private AirlineClass className; // Type of class (e.g., Economy, Business, First Class)
	private String airlineName; // Human-readable name of the Airline

	// Default no-argument constructor (required for many frameworks like Jackson/Hibernate)
	public FlightDTO() {

	}

	// Parameterized constructor to initialize all fields at once
	public FlightDTO(int flightId, String enterprise, double flightPrice, String source, String destination,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			int duration, int totSeat, int availSeat, AirlineClass className, String airlineName) {
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
		this.airlineName = airlineName;
	}

	// Getter and Setter methods follow: 
	// They provide controlled access to private fields for data binding and retrieval

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

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

}
