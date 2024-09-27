package com.proyect.CompilAir.repositories;

import com.proyect.CompilAir.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDestination(String destination);
}
