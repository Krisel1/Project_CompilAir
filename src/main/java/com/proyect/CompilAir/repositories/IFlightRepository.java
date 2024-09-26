package com.proyect.CompilAir.repositories;

import com.proyect.CompilAir.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightRepository extends JpaRepository<Flight, Long> {
}
