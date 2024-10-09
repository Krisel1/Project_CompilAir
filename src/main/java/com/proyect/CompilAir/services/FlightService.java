package com.proyect.CompilAir.services;

import com.proyect.CompilAir.excepcions.ResourceNotFoundException;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.repositories.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class FlightService {
    private final IFlightRepository iFlightRepository;

    @Autowired
    public FlightService(IFlightRepository iFlightRepository){
        this.iFlightRepository = iFlightRepository;
    }




    public Flight createFlight(Flight flight) {

        if (flight.getDepartureDate().isBefore(LocalDateTime.now())) {
            flight.setFlightStatus(false);
        }
        flight.availableSeats();

        return iFlightRepository.save(flight);
    }

    public Flight updateFlight(Long id,Flight flightDetails) {
        Flight existingFlight = iFlightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id " + id));

        existingFlight.setFlightName(flightDetails.getFlightName());
        existingFlight.setDepartureDate(flightDetails.getDepartureDate());
        existingFlight.setReturnDate(flightDetails.getReturnDate());
        existingFlight.setTotalSeats(flightDetails.getTotalSeats());
        existingFlight.setReservedSeats(flightDetails.getReservedSeats());


        existingFlight.availableSeats();

        return iFlightRepository.save(existingFlight);
    }

    public List<Flight> getAllFlights() {
        return iFlightRepository.findAll();
    }

    public List<Flight> getAvailableFlights(String destination) {
        List<Flight> allFlights = iFlightRepository.findByDestination(destination);
        return allFlights.stream()
                .filter(flight -> flight.availableSeats() > 0)
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


