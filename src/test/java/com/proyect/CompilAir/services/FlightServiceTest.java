package com.proyect.CompilAir.services;

import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.repositories.IFlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private IFlightRepository flightRepository;

    private Flight flight;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        flight = new Flight(1, "FL123", true, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), 150);
    }

    @Test
    public void createFlight() {
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight createdFlight = flightService.createFlight(flight);

        assertEquals("FL123", createdFlight.getFlightName());
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    public void updateFlight() {
        when(flightRepository.findById(eq(1L))).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight updatedFlight = flightService.updateFlight(1L, flight);

        assertEquals("FL123", updatedFlight.getFlightName());
        verify(flightRepository, times(1)).findById(eq(1L));
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    public void getAllFlight() {
        List<Flight> flightList = Arrays.asList(flight);
        when(flightRepository.findAll()).thenReturn(flightList);

        List<Flight> flights = flightService.getAllFlight();

        assertEquals(1, flights.size());
        assertEquals("FL123", flights.get(0).getFlightName());
        verify(flightRepository, times(1)).findAll();
    }

    @Test
    public void getFlightById() {
        when(flightRepository.findById(eq(1L))).thenReturn(Optional.of(flight));

        Flight foundFlight = flightService.getFlightById(1L);

        assertEquals("FL123", foundFlight.getFlightName());
        verify(flightRepository, times(1)).findById(eq(1L));
    }

    @Test
    public void testDeleteFlight_Success() {
        when(flightRepository.existsById(eq(1L))).thenReturn(true);

        boolean result = flightService.deleteFlight(1L);

        assertTrue(result);
        verify(flightRepository, times(1)).existsById(eq(1L));
        verify(flightRepository, times(1)).deleteById(eq(1L));
    }

    @Test
    public void deleteFlight_NotFound() {
        when(flightRepository.existsById(eq(1L))).thenReturn(false);

        boolean result = flightService.deleteFlight(1L);

        assertTrue(!result);
        verify(flightRepository, times(1)).existsById(eq(1L));
        verify(flightRepository, never()).deleteById(anyLong());
    }

}