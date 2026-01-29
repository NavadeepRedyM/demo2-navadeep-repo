package com.cg.service; // Defines the package for business logic components

import java.util.List; // Import for returning multiple airline records
import java.util.Optional; // Import to handle cases where an airline might not exist (null-safety)

import org.springframework.beans.factory.annotation.Autowired; // For automatic dependency injection
import org.springframework.stereotype.Service; // Marks this class as a Service component in Spring

import com.cg.entity.Airline; // Import for the Airline entity
import com.cg.repository.IAirlineRepository; // Import for the Airline database repository

@Service // Tells Spring that this class contains the business logic for Airlines
public class AirlineService {
      
	@Autowired // Automatically injects the implementation of IAirlineRepository
	IAirlineRepository repo;
	
	// Method to fetch all airlines currently stored in the database
	public List<Airline> getAllAirlines(){
		return repo.findAll(); // Calls built-in JpaRepository method to get all records
	}

	// Method to save a new airline or update an existing one
	public Airline saveAir(Airline air) {
		return repo.save(air); // Persists the airline object to the database
	}

	// Method to find a specific airline using its unique ID
	public Optional<Airline> findById(int id) {
		Optional<Airline> air = repo.findById(id); // Returns an Optional to prevent NullPointerExceptions
		return air;
	}

	// Method to remove an airline record from the database by ID
	public void deleteById(int id) {
		repo.deleteById(id); // Deletes the record matching the provided ID
	}
}
