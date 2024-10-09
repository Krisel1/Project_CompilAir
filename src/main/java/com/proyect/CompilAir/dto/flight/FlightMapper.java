package com.proyect.CompilAir.dto.flight;

import com.proyect.CompilAir.models.Flight;
import com.proyect.CompilAir.models.Route;
import com.proyect.CompilAir.repositories.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {

    private final IRouteRepository iRouteRepository;

    @Autowired
    public FlightMapper(IRouteRepository iRouteRepository) {
        this.iRouteRepository = iRouteRepository;
    }

    public static FlightDTO toDTO(Flight flight) {
        return new FlightDTO(
                flight.getId(),
                flight.getFlightName(),
                flight.isFlightStatus(),
                flight.getDepartureDate(),
                flight.getReturnDate(),
                flight.getTotalSeats(),
                flight.getReservedSeats(),
                flight.getDestination(),
                flight.getRoute() != null ? flight.getRoute().getId() : null
        );
    }

    public Flight toEntity(FlightDTO flightDTO) {
        Route route = null;


        if (flightDTO.getRoute_id() != null) {
            route = iRouteRepository.findById(flightDTO.getRoute_id())
                    .orElseThrow(() -> new IllegalArgumentException("Route not found"));
        }

        return new Flight(
                flightDTO.getId(),
                flightDTO.getFlightName(),
                flightDTO.isFlightStatus(),
                flightDTO.getDepartureDate(),
                flightDTO.getReturnDate(),
                flightDTO.getTotalSeats(),
                flightDTO.getReservedSeats(),
                flightDTO.getDestination(),
                route
        );
    }
}

