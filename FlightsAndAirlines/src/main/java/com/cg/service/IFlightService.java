package com.cg.service; // Defines the package for service layer interfaces

import java.util.List; // Imports List for handling collections of flight records

import org.springframework.http.ResponseEntity; // Wrapper to return HTTP status codes and flight data

import com.cg.dto.FlightDTO; // Import for the Data Transfer Object used in updates
import com.cg.entity.Airline; // Import for the Airline entity association
import com.cg.entity.AirlineClass; // Import for the enum used in flight searching
import com.cg.entity.Flight; // Import for the core Flight entity
import com.cg.exception.ResourceNotFound; // Custom exception for handling missing records

// Interface defining the contract for Flight-related business operations
public interface IFlightService {
       
       // Abstract method to retrieve a list of all flights (sorted by implementation)
       public List<Flight> getAllFlights();
       
       // Abstract method to save a new flight and link it to a specific airline
       public Flight saveFlight(Flight flight, Airline airline);
       
       // Abstract method to fetch a flight by ID, throwing an exception if not found
       public Flight findIdByFlight(int id) throws ResourceNotFound;
       
       // Abstract method to remove a flight record from the database by its ID
       public void deleteById(int id);
       
       // Abstract method to update an existing flight's details using DTO data
       public ResponseEntity<Flight> updateFlight(Flight flight, FlightDTO flightdto, Airline air);
       
       // Abstract method to perform a dynamic search based on various optional filters
       public List<Flight> searchFlight(String source, String dest, String airline, AirlineClass className);
}
