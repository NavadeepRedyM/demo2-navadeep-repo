package com.cg.repository; // Defines the package for data access layer interfaces

import java.util.List; // Imports List to handle multiple flight results

import org.springframework.data.jpa.repository.JpaRepository; // Base interface for CRUD operations
import org.springframework.data.jpa.repository.Query; // Annotation for writing custom JPQL queries
import org.springframework.data.repository.query.Param; // Annotation to bind method parameters to query variables

import com.cg.entity.AirlineClass; // Import for the AirlineClass enum filter
import com.cg.entity.Flight; // Import for the Flight entity

// Interface for Flight database operations, extending JpaRepository for built-in methods
public interface FlightRepository extends JpaRepository<Flight, Integer>{
         
	// Custom JPQL query to perform dynamic searching based on multiple optional criteria
	@Query("SELECT f FROM Flight f WHERE " +
	           // Checks if source is null/empty; if not, matches it with flight source
	           "(:source IS NULL OR :source = '' OR f.source = :source) AND " +
	           // Checks if destination is null/empty; if not, matches it with flight destination
	           "(:dest IS NULL OR :dest = '' OR f.destination = :dest) AND " +
	           // Filters by Airline Name through the nested Airline entity relationship
	           "(:airline IS NULL OR :airline = '' OR f.airline.AirName = :airline) AND " +
	           // Filters by the specific AirlineClass enum (Economy, Business, etc.)
	           "(:className IS NULL OR f.className = :className)")
	    List<Flight> searchFlights(
	        @Param("source") String source,      // Binds method 'source' to :source in query
	        @Param("dest") String dest,          // Binds method 'dest' to :dest in query
	        @Param("airline") String airline,    // Binds method 'airline' to :airline in query
	        @Param("className") AirlineClass className // Binds enum to :className in query
	        );
}
