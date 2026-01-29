package com.cg.exception; // Defines the package for global exception handling logic

import java.util.Date; // Imports Date to timestamp the error occurrence

import org.springframework.http.HttpStatus; // Import for standard HTTP status codes
import org.springframework.http.ResponseEntity; // Wrapper to return both data and HTTP status
import org.springframework.web.bind.annotation.ControllerAdvice; // Annotation for global interceptor of exceptions
import org.springframework.web.bind.annotation.ExceptionHandler; // Annotation to define specific exception handlers
import org.springframework.web.context.request.WebRequest; // Used to access request details like URL or description

// Controller advice to handle all application-level exceptions in one place
@ControllerAdvice // Marks this class as a global handler for exceptions thrown by any @Controller
public class GlobalException {

	// Handles custom business logic errors (like missing Flight ID)
	@ExceptionHandler(ResourceNotFound.class) // Tells Spring to run this method when ResourceNotFound is thrown
	public ResponseEntity<?> resourceNotFound(ResourceNotFound ex, WebRequest req) {
		// Creates a structured error object with the message, current time, and request path
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), req.getDescription(false));
		// Returns the error object along with a 404 Not Found HTTP status
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// Catches all other unexpected system errors (NullPointer, Database down, etc.)
	@ExceptionHandler(Exception.class) // Fallback handler for any exception not specifically caught elsewhere
	public ResponseEntity<?> globalExceptionHandler(ResourceNotFound ex, WebRequest req) {
		// Creates a structured error object for internal system failures
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), req.getDescription(false));
		// Returns the error object along with a 500 Internal Server Error HTTP status
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
