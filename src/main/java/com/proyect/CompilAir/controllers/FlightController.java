package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.dto.flight.FlightDTO;
import com.proyect.CompilAir.dto.flight.FlightMapper;
import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;


    @PostMapping
    public ResponseEntity<FlightDTO> createFlight(@RequestBody FlightDTO flightDTO) {
        Flight flight = FlightMapper.toEntity(flightDTO);
        Flight savedFlight = flightService.createFlight(flight);
        FlightDTO savedFlightDTO = FlightMapper.toDTO(savedFlight);
        return new ResponseEntity<>(savedFlightDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        Flight flight = FlightMapper.toEntity(flightDTO);
        Flight updatedFlight = flightService.updateFlight(id, flight);
        FlightDTO updatedFlightDTO = FlightMapper.toDTO(updatedFlight);
        return ResponseEntity.ok(updatedFlightDTO);
    }

    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        List<FlightDTO> flightDTOs = flights.stream()
                .map(FlightMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(flightDTOs);
    }

    @GetMapping("/available/{destination}")
    public ResponseEntity<List<FlightDTO>> getAvailableFlights(@PathVariable String destination) {
        List<Flight> availableFlights = flightService.getAvailableFlights(destination);
        List<FlightDTO> availableFlightDTOs = availableFlights.stream()
                .map(FlightMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(availableFlightDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        FlightDTO flightDTO = FlightMapper.toDTO(flight);
        return ResponseEntity.ok(flightDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFlight(@PathVariable Long id) {
        boolean isDeleted = flightService.deleteFlight(id);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
