package com.proyect.CompilAir.AutomaticSQL;


import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.repositories.IFlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FlightsDataInitializer {

    @Bean
    CommandLineRunner initDatabase(IFlightRepository iFlightRepository) {
        return args -> {

            Flight flight1 = new Flight();
            flight1.setFlightName("AA304");
            flight1.setFlightStatus(true);
            flight1.setDepartureDate(LocalDateTime.of(2024, 10, 1, 8, 0));
            flight1.setReturnDate(LocalDateTime.of(2024, 10, 1, 20, 0));
            flight1.setTotalSeats(12L);
            flight1.setReservedSeats(1L);
            flight1.setDestination("Roma");

            Flight flight2 = new Flight();
            flight2.setFlightName("AA002");
            flight2.setFlightStatus(true);
            flight2.setDepartureDate(LocalDateTime.of(2024, 10, 2, 8, 0));
            flight2.setReturnDate(LocalDateTime.of(2024, 10, 2, 20, 0));
            flight2.setTotalSeats(15L);
            flight2.setReservedSeats(2L);
            flight2.setDestination("Cadiz");


            iFlightRepository.save(flight1);
            iFlightRepository.save(flight2);

            System.out.println("Flights automatically generated in database");
        };
    }
}
