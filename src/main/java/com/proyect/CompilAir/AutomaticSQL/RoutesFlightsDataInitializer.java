//package com.proyect.CompilAir.AutomaticSQL;
//
//import com.proyect.CompilAir.models.Flight;
//import com.proyect.CompilAir.models.Route;
//import com.proyect.CompilAir.repositories.IFlightRepository;
//import com.proyect.CompilAir.repositories.IRouteRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class RoutesFlightsDataInitializer {
//
//    @Bean
//    CommandLineRunner initDatabase(IFlightRepository iFlightRepository, IRouteRepository iRouteRepository) {
//        return args -> {
//
//            Flight flight = new Flight();
//            flight.setFlightName("Flight 101");
//            flight.setFlightStatus(true);
//            flight.setDepartureDate(LocalDateTime.of(2025, 10, 1, 8, 0));
//            flight.setReturnDate(LocalDateTime.of(2025, 10, 1, 20, 0));
//            flight.setTotalSeats(200L);
//            flight.setReservedSeats(50);
//            flight.setDestination("Destination X");
//
//
//            Route route1 = new Route();
//            route1.setNameRoute("SVQ-IBZ");
//            route1.setFlight(flight);
//
//            Route route2 = new Route();
//            route2.setNameRoute("MAD-BCN");
//            route2.setFlight(flight);
//
//            Route route3 = new Route();
//            route3.setNameRoute("AGP-LPA");
//            route3.setFlight(flight);
//
//            Route route4 = new Route();
//            route4.setNameRoute("BIO-PMI");
//            route4.setFlight(flight);
//
//            Route route5 = new Route();
//            route5.setNameRoute("VLC-SCQ");
//            route5.setFlight(flight);
//
//            Route route6 = new Route();
//            route6.setNameRoute("MAD-SVQ");
//            route6.setFlight(flight);
//
//            Route route7 = new Route();
//            route7.setNameRoute("BCN-IBZ");
//            route7.setFlight(flight);
//
//            Route route8 = new Route();
//            route8.setNameRoute("AGP-MAD");
//            route8.setFlight(flight);
//
//
//            flight.getRoute().add(route1);
//            flight.getRoute().add(route2);
//            flight.getRoute().add(route3);
//            flight.getRoute().add(route4);
//            flight.getRoute().add(route5);
//            flight.getRoute().add(route6);
//            flight.getRoute().add(route7);
//            flight.getRoute().add(route8);
//
//
//            iRouteRepository.save(route1);
//            iRouteRepository.save(route2);
//            iRouteRepository.save(route3);
//            iRouteRepository.save(route4);
//            iRouteRepository.save(route5);
//            iRouteRepository.save(route6);
//            iRouteRepository.save(route7);
//            iRouteRepository.save(route8);
//
//            iFlightRepository.save(flight);
//
//            System.out.println("Flight and routes automatically generated in database");
//        };
//    }
//}
