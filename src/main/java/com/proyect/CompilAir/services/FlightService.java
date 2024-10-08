package com.proyect.CompilAir.services;

import com.proyect.CompilAir.excepcions.ResourceNotFoundException;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.repositories.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    IFlightRepository iFlightRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeExpiredOrFullFlights() {
        List<Flight> flights = iFlightRepository.findAll();
        for (Flight flight : flights) {
            if (flight.getDepartureDate().isBefore(LocalDateTime.now()) || flight.getAvailableSeats() <= 0) {
                iFlightRepository.delete(flight);
            }
        }
    }

    public Flight createFlight(Flight flight) {
        if (flight.getDepartureDate().isBefore(LocalDateTime.now())) {
            flight.setFlightStatus(false);
        }
        return iFlightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight existingFlight = iFlightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + id));

        existingFlight.setFlightName(flightDetails.getFlightName());
        existingFlight.setDepartureDate(flightDetails.getDepartureDate());
        existingFlight.setReturnDate(flightDetails.getReturnDate());
        existingFlight.setTotalSeats(flightDetails.getTotalSeats());
        existingFlight.setReservedSeats(flightDetails.getReservedSeats());

        return iFlightRepository.save(existingFlight);
    }

    public List<Flight> getAllFlights() {
        return iFlightRepository.findAll();
    }

    public List<Flight> getAvailableFlights(String destination) {
        List<Flight> allFlights = iFlightRepository.findByDestination(destination);
        return allFlights.stream()
                .filter(flight -> flight.getAvailableSeats() > 0) // Cambiar a getAvailableSeats()
                .collect(Collectors.toList());
    }

    public Flight getFlightById(Long id) {
        return iFlightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + id));
    }

    public boolean deleteFlight(Long id) {
        if (iFlightRepository.existsById(id)) {
            iFlightRepository.deleteById(id);
            return true;
        }
        return false;
    }

}


