package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.entity.Airline;
import com.cg.entity.Flight;
import com.cg.exception.ResourceNotFound;
import com.cg.repository.FlightRepository;


@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {
	@Mock
    private FlightRepository repo;

    @InjectMocks
    private FlightService flightService;

    @Test
    void testFindIdByFlight_Success() throws ResourceNotFound {
        // Arrange
        Flight mockFlight = new Flight();
        mockFlight.setFlightId(101);
        when(repo.findById(101)).thenReturn(Optional.of(mockFlight));

        // Act
        Flight result = flightService.findIdByFlight(101);

        // Assert
        assertNotNull(result);
        assertEquals(101, result.getFlightId());
        verify(repo, times(1)).findById(101);
    }

    @Test
    void testFindIdByFlight_ThrowsException() {
        // Arrange
        when(repo.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFound.class, () -> {
            flightService.findIdByFlight(999);
        });
    }

    @Test
    void testGetAllFlights_SortedByPrice() {
        // Arrange
        Flight f1 = new Flight(); f1.setFlightPrice(500.0);
        Flight f2 = new Flight(); f2.setFlightPrice(200.0);
        when(repo.findAll()).thenReturn(Arrays.asList(f1, f2));

        // Act
        List<Flight> result = flightService.getAllFlights();

        // Assert
        assertEquals(2, result.size());
        assertEquals(200.0, result.get(0).getFlightPrice()); // Should be first due to Stream sorting
        verify(repo, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        // Act
        flightService.deleteById(101);

        // Assert
        verify(repo, times(1)).deleteById(101);
    }

    @Test
    void testSaveFlight() {
        // Arrange
        Flight flight = new Flight();
        Airline airline = new Airline();
        when(repo.save(flight)).thenReturn(flight);

        // Act
        Flight saved = flightService.saveFlight(flight, airline);

        // Assert
        assertNotNull(saved);
        assertEquals(airline, saved.getAirline());
        verify(repo, times(1)).save(flight);
    }
}
