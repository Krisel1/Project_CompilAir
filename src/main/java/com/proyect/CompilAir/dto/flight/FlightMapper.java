package com.proyect.CompilAir.dto.flight;

import com.proyect.CompilAir.models.Flight;

public class FlightMapper {
    public static FlightDTO toDTO(Flight flight) {
        return new FlightDTO(
                flight.getId(),
                flight.getFlightName(),
                flight.isFlightStatus(),
                flight.getDepartureDate(),
                flight.getReturnDate(),
                flight.getTotalSeats(),
                flight.getReservedSeats()
        );
    }


    public static Flight toEntity(FlightDTO flightDTO) {
        return new Flight(
                flightDTO.getId(),
                flightDTO.getFlightName(),
                flightDTO.isFlightStatus(),
                flightDTO.getDepartureDate(),
                flightDTO.getReturnDate(),
                flightDTO.getTotalSeats(),
                flightDTO.getReservedSeats()
        );
    }
}
