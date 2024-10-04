package com.proyect.CompilAir.AutomaticSQL;

import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.repositories.IFlightRepository;
import com.proyect.CompilAir.repositories.IRouteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RoutesFlightsDataInitializer {

    @Bean
    CommandLineRunner initDatabase(IFlightRepository iFlightRepository, IRouteRepository iRouteRepository) {
        return args -> {


            Flight flight = new Flight();
            flight.setFlightName("Flight 101");
            flight.setFlightStatus(true);
            flight.setDepartureDate(LocalDateTime.of(2025, 10, 1, 8, 0));
            flight.setReturnDate(LocalDateTime.of(2025, 10, 1, 20, 0));
            flight.setTotalSeats(200L);
            flight.setReservedSeats(50);
            flight.setDestination("Destination X");

            flight = iFlightRepository.save(flight);


            List<Route> routes = List.of(
                new Route("MAD-AST",1L,flight),
                new Route("MAD-BCN",2L,flight),
                new Route("AGP-LPA",3L,flight),
                new Route("BIO-PMI",4L,flight),
                new Route("VLC-SCQ",5L,flight),
                new Route("MAD-SVQ",6L,flight),
                new Route("BCN-IBZ",7L,flight),
                new Route("AGP-MAD",8L,flight)
            );


            iRouteRepository.saveAll(routes);

            System.out.println("Flight and routes automatically generated in the database.");
        };
    }
}
