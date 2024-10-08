package com.proyect.CompilAir.AutomaticSQL;

import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.repositories.IFlightRepository;
import com.proyect.CompilAir.repositories.IRouteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class RoutesFlightsDataInitializer {

    @Bean
    CommandLineRunner initDatabase(IFlightRepository iFlightRepository, IRouteRepository iRouteRepository) {
        return args -> {

            Route route1 = new Route("MAD-AST", 1L);
            Route route2 = new Route("MAD-BCN", 2L);
            Route route3 = new Route("SVQ-IBZ",3L);

            route1 = iRouteRepository.save(route1);
            route2 = iRouteRepository.save(route2);
            route3 = iRouteRepository.save(route3);

            Flight flight1 = new Flight();
            flight1.setFlightName("A001");
            flight1.setRoute(route1);
            flight1.setFlightStatus(true);
            flight1.setDepartureDate(LocalDateTime.of(2025, 10, 1, 8, 0));
            flight1.setReturnDate(LocalDateTime.of(2025, 10, 1, 20, 0));
            flight1.setTotalSeats(200L);
            flight1.setReservedSeats(50);
            flight1.setDestination("Gijon");

            Flight flight2 = new Flight();
            flight2.setFlightName("M002");
            flight2.setRoute(route1);
            flight2.setFlightStatus(true);
            flight2.setDepartureDate(LocalDateTime.of(2025, 10, 2, 9, 0));
            flight2.setReturnDate(LocalDateTime.of(2025, 10, 2, 21, 0));
            flight2.setTotalSeats(200L);
            flight2.setReservedSeats(50);
            flight2.setDestination("Madrid");

            Flight flight3 = new Flight();
            flight3.setFlightName("M003");
            flight3.setRoute(route2);
            flight3.setFlightStatus(true);
            flight3.setDepartureDate(LocalDateTime.of(2025, 10, 2, 9, 0));
            flight3.setReturnDate(LocalDateTime.of(2025, 10, 2, 21, 0));
            flight3.setTotalSeats(200L);
            flight3.setReservedSeats(50);
            flight3.setDestination("Madrid");

            Flight flight4 = new Flight();
            flight4.setFlightName("B004");
            flight4.setRoute(route2);
            flight4.setFlightStatus(true);
            flight4.setDepartureDate(LocalDateTime.of(2025, 10, 2, 9, 0));
            flight4.setReturnDate(LocalDateTime.of(2025, 10, 2, 21, 0));
            flight4.setTotalSeats(200L);
            flight4.setReservedSeats(50);
            flight4.setDestination("Barcelona");

            flight1 = iFlightRepository.save(flight1);
            flight2 = iFlightRepository.save(flight2);
            flight3 = iFlightRepository.save(flight3);
            flight4 = iFlightRepository.save(flight4);

            // Add the flights to their respective routes
            route1.getFlights().add(flight1);
            route1.getFlights().add(flight2);  // Add flight2 to route1 only if applicable

            route2.getFlights().add(flight3);

            iRouteRepository.save(route1);
            iRouteRepository.save(route2);

            System.out.println("Flights and routes have been automatically generated and saved in the database.");
        };
    }
}

