package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.services.FlightService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(id, flightDetails);
        return ResponseEntity.ok(updatedFlight);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlight() {
        List<Flight> flights = flightService.getAllFlight();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteFlightById(@PathVariable Long id) {
        boolean ok = flightService.deleteFlight(id);

        if (ok) {
            return "Project with id" + id + "was delete";
        } else {
            return "Project with id" + id + "not found";
        }

    }
}
