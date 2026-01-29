package com.cg.controller; // Defines the package structure for the controller

import java.util.List; // Imports the List interface for handling collections of data

import org.springframework.beans.factory.annotation.Autowired; // For Dependency Injection
import org.springframework.stereotype.Controller; // Marks this class as a Web Controller (returns views)
import org.springframework.ui.Model; // Used to pass data from the controller to the UI (Thymeleaf)
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests
import org.springframework.web.bind.annotation.ModelAttribute; // Binds form data to an object
import org.springframework.web.bind.annotation.PathVariable; // Extracts values from the URL path
import org.springframework.web.bind.annotation.PostMapping; // Maps HTTP POST requests
import org.springframework.web.bind.annotation.RequestMapping; // Sets the base URL path for this controller
import org.springframework.web.bind.annotation.RequestParam; // Extracts query parameters from the URL

import com.cg.dto.FlightDTO; // Import for Data Transfer Object
import com.cg.entity.Airline; // Import for Airline entity
import com.cg.entity.AirlineClass; // Import for AirlineClass enum
import com.cg.entity.Flight; // Import for Flight entity
import com.cg.exception.ResourceNotFound; // Custom exception for missing records
import com.cg.service.AirlineService; // Service layer for Airline logic
import com.cg.service.FlightService; // Service layer for Flight logic

@Controller // Tells Spring this class handles web requests and returns HTML views
@RequestMapping("/api") // Base prefix for all URLs in this class (e.g., /api/list)
public class FlightController {

	@Autowired // Automatically injects the FlightService implementation
	FlightService service;
    
	@Autowired // Automatically injects the AirlineService implementation
	AirlineService AService;

	@GetMapping("/list") // Handles GET requests to /api/list
	public String getAllFlights(Model model, @RequestParam(required = false) String source,
			@RequestParam(required = false) String dest, @RequestParam(required = false) String airline,
			@RequestParam(required = false) String className) {

		// Checks if all search filters are provided
		if (source != null && dest != null && airline != null && className != null) {
			// Converts the string className to the AirlineClass Enum type safely
			AirlineClass flightClass = (className != null && !className.isEmpty()) ? AirlineClass.valueOf(className)
					: null;

			// Calls service to find specific flights based on search criteria
			List<Flight> flights = service.searchFlight(source, dest, airline, flightClass);
			model.addAttribute("airlines", AService.getAllAirlines()); // Adds airline list to dropdown
			model.addAttribute("flights", flights); // Adds filtered flights to the table
		} else {
			// Default view: shows all airlines and all flights if no search is performed
			model.addAttribute("airlines", AService.getAllAirlines());
			model.addAttribute("flights", service.getAllFlights());
		}
		return "Flight/index"; // Returns the Flight/index.html view
	}

	@GetMapping("/addAir") // Displays the form to add a new Airline
	public String addAir(Model model) {
		model.addAttribute("air", new Airline()); // Provides an empty Airline object for the form
		return "Flight/add-air"; // Returns Flight/add-air.html
	}

	@PostMapping("/addAir") // Processes the submission of the new Airline form
	public String addedAir(@ModelAttribute("air") Airline air) {
		AService.saveAir(air); // Saves the airline object to the database
		return "redirect:/api/list"; // Redirects back to the flight list page
	}

	@GetMapping("/add") // Displays the form to add a new Flight
	public String addFlight(Model model) {
		model.addAttribute("airlines", AService.getAllAirlines()); // List of airlines for the dropdown
		model.addAttribute("flight", new Flight()); // Empty Flight object for form binding
		return "Flight/add-flight"; // Returns Flight/add-flight.html
	}

	@PostMapping("/add") // Processes the submission of the new Flight form
	public String AddedFlight(@ModelAttribute("flight") Flight flight, @RequestParam("enterprise") int airlineId) {
		Airline airline = AService.findById(airlineId).get(); // Fetches the selected Airline by ID
		service.saveFlight(flight, airline); // Saves the flight associated with the chosen airline
		return "redirect:/api/list"; // Redirects to the flight list
	}

	@GetMapping("/showAirline") // Handles request to view all airlines
	public String showAirlines(Model model) {
		model.addAttribute("allAirlines", AService.getAllAirlines()); // Adds all airlines to the model
		return "Flight/show-AllAirlines"; // Returns Flight/show-AllAirlines.html
	}

	@GetMapping("/delete/{id}") // Handles flight deletion via URL ID
	public String deleteFlight(@PathVariable int id) {
		service.deleteById(id); // Calls service to delete the flight from database
		return "redirect:/api/list"; // Refresh the list page
	}

	@GetMapping("/book/{id}") // Handles flight booking request for a specific ID
	public String bookFlight(@PathVariable int id, Model model) throws ResourceNotFound {
		model.addAttribute("flight", service.findIdByFlight(id)); // Finds flight details to show on booking page
		return "Flight/book"; // Returns Flight/book.html
	}

	@GetMapping("/update/{id}") // Displays the update form for a flight
	public String updateFlight(@PathVariable int id, Model model) throws ResourceNotFound {
		Flight flight = service.findIdByFlight(id); // Retrieves the existing flight
		FlightDTO flightdto = service.convertToDTO(flight); // Converts entity to DTO for the form
		model.addAttribute("airlines", AService.getAllAirlines()); // Provides airline list for potential changes
		model.addAttribute("flight", flightdto); // Passes the flight data to the form
		return "Flight/update"; // Returns Flight/update.html
	}

	@PostMapping("/update") // Processes the flight update
	public String updatedFlight(@ModelAttribute("flight") FlightDTO flightdto, @RequestParam("enterprise") int airId)
			throws ResourceNotFound {
		Flight existFlight = service.findIdByFlight(flightdto.getFlightId()); // Gets the current flight from DB
		Airline air = AService.findById(airId).get(); // Gets the airline selected in the update form
		service.updateFlight(existFlight, flightdto, air); // Updates the flight details in service layer
		return "redirect:/api/list"; // Redirects to the list view
	}

	@GetMapping("/airDelete/{id}") // Handles airline deletion
	public String deleteAirline(@PathVariable("id") int airId) {
		AService.deleteById(airId); // Deletes the airline by ID
		return "redirect:/api/showAirline"; // Redirects back to airline management page
	}

	@GetMapping("/airUpdate/{id}") // Displays the update form for an Airline
	public String updateAirline(@PathVariable("id") int airId, Model model) {
		Airline air = AService.findById(airId).get(); // Finds the airline to be updated
		model.addAttribute("air", air); // Passes airline data to the view
		return "Flight/updateAir"; // Returns Flight/updateAir.html
	}
}
