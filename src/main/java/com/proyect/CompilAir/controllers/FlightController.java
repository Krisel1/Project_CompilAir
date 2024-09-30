package com.proyect.CompilAir.controllers;

import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;


    @PostMapping("")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(id, flightDetails);
        return ResponseEntity.ok(updatedFlight);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlight() {
        List<Flight> flight = flightService.getAllFlight();
        return ResponseEntity.ok(flight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }



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
