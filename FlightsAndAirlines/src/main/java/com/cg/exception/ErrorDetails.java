package com.cg.exception; // Defines the package for exception-handling helper classes

import java.util.Date; // Imports the Date class to record when an error occurs

// Model class used to send structured error information back to the client/UI.
public class ErrorDetails {
	private String message; // Field to store a short summary of the error
	private Date timeStamp; // Field to store the exact date and time the error occurred
	private String detail;  // Field to store extra context or the specific cause of the error

	// Default constructor: necessary for frameworks to instantiate the class via reflection
	public ErrorDetails() {

	}

	// Parameterized constructor: used to quickly create an error response with all details
	public ErrorDetails(String message, Date date, String detail) {
		super();            // Calls the constructor of the parent Object class
		this.message = message; // Initializes the error message
		this.timeStamp = date;  // Initializes the timestamp
		this.detail = detail;   // Initializes the error details
	}

	// Getter for message: allows the UI or client to read the error summary
	public String getMessage() {
		return message;
	}

	// Setter for message: allows the global exception handler to set the error summary
	public void setMessage(String message) {
		this.message = message;
	}

	// Getter for timeStamp: retrieves the time the error was triggered
	public Date getTimeStamp() {
		return timeStamp;
	}

	// Setter for timeStamp: records the time of the error
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	// Getter for detail: retrieves the specific context/URI where the error happened
	public String getDetail() {
		return detail;
	}

	// Setter for detail: sets the specific context or URI for the error
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
