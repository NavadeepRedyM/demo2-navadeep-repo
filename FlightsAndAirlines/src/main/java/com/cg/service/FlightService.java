package com.cg.service; // Defines the package for flight-related business logic

import java.util.Comparator; // Import for defining sorting logic
import java.util.List; // Import for handling collections of flights
import java.util.Optional; // Import for null-safe object handling

import org.slf4j.Logger; // Interface for logging system messages
import org.slf4j.LoggerFactory; // Factory to create logger instances
import org.springframework.beans.factory.annotation.Autowired; // For automatic dependency injection
import org.springframework.http.ResponseEntity; // Wrapper for HTTP responses
import org.springframework.stereotype.Service; // Marks this class as a Service component

import com.cg.dto.FlightDTO; // Import for Data Transfer Object
import com.cg.entity.Airline; // Import for Airline entity
import com.cg.entity.AirlineClass; // Import for AirlineClass enum
import com.cg.entity.Flight; // Import for Flight entity
import com.cg.exception.ResourceNotFound; // Custom exception for missing flights
import com.cg.repository.FlightRepository; // Interface for database operations

@Service // Tells Spring this class manages business logic for flights
public class FlightService implements IFlightService{
	@Autowired // Injects the FlightRepository implementation
	FlightRepository repo;
	
	// Initializes the logger to record application events
	private static final Logger log = LoggerFactory.getLogger(FlightService.class);
	
	// Retrieves all flights and sorts them by price in ascending order using Java Streams
	public List<Flight> getAllFlights(){
		log.info("Getting the all flightss....."); // Logs the start of the retrieval process
		return repo.findAll().stream() // Converts list to stream
				.sorted(Comparator.comparingDouble(Flight::getFlightPrice)) // Sorts by flight price
				.toList(); // Converts stream back to a list
	}

	// Associates a flight with an airline and saves it to the database
	public Flight saveFlight(Flight flight, Airline airline) {
		log.info("Saving the New Flight"); // Logs the save operation
		flight.setAirline(airline); // Sets the relationship between flight and airline
		return repo.save(flight); // Persists flight to database
	}
	
	// Finds a flight by ID; throws a custom exception if not found using a lambda expression
	public Flight findIdByFlight(int id) throws ResourceNotFound{
		log.info("Searching for the flight with ID{} "+id); // Logs the search attempt
		 return repo.findById(id).orElseThrow(()-> new ResourceNotFound("Flight not find by id :"+id));
	}

	@Override // Overrides the delete method from the interface
	public void deleteById(int id) {
		log.info("Admin Warning : Flight was Deleting...."); // Logs the deletion warning
		 repo.deleteById(id); // Removes the flight record by ID
	}
	
	@Override // Overrides the search method from the interface
	public List<Flight> searchFlight(String source, String dest, String airline, AirlineClass className) {
		// Calls the custom query method defined in the repository
		return repo.searchFlights(source, dest, airline, className);
	}
	
	// Maps Flight entity data to a FlightDTO for transferring to the UI/API
	public FlightDTO convertToDTO(Flight flight) {
		 if(flight == null) return null; // Returns null if input flight is null
		 return new FlightDTO(
				flight.getFlightId(),
				flight.getEnterprise(),
				flight.getFlightPrice(),
				flight.getSource(),
				flight.getDestination(),
				flight.getDepartureDate(),
				flight.getDepartureTime(),
				flight.getArrivalDate(),
				flight.getArrivalTime(),
				flight.getDuration(),
				flight.getTotSeat(),
				flight.getAvailSeat(),
				flight.getClassName(),
				flight.getAirline().getAirName() // Flattens airline name into the DTO
				 );
	}

	@Override // Overrides the update method to modify existing flight details
	public ResponseEntity<Flight> updateFlight(Flight existFlight, FlightDTO flightdto, Airline air) {
                // Updates fields of the existing entity with values from the DTO
                existFlight.setEnterprise(flightdto.getEnterprise());
                existFlight.setSource(flightdto.getSource());
                existFlight.setDepartureDate(flightdto.getDepartureDate());
                existFlight.setDepartureTime(flightdto.getDepartureTime());
                existFlight.setDestination(flightdto.getDestination());
                existFlight.setArrivalDate(flightdto.getArrivalDate());
                existFlight.setArrivalTime(flightdto.getArrivalTime());
                existFlight.setAvailSeat(flightdto.getAvailSeat());
                existFlight.setTotSeat(flightdto.getTotSeat());
                existFlight.setDuration(flightdto.getDuration());
                existFlight.setFlightPrice(flightdto.getFlightPrice());
                existFlight.setClassName(flightdto.getClassName());
                existFlight.setAirline(air); // Updates the associated airline
                
             // Saves updated entity and returns it with an HTTP 200 OK status
             return ResponseEntity.ok(repo.save(existFlight));
	}
}
