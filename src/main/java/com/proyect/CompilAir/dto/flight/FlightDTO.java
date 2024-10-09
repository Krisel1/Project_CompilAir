package com.proyect.CompilAir.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightDTO {
    private Long id;
    private String flightName;
    private boolean flightStatus;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private Long totalSeats;
    private Long reservedSeats;
    private String destination;
    private Long route_id;


    public FlightDTO() {}


    public FlightDTO(long id, String flightName, boolean flightStatus, LocalDateTime departureDate,
                     LocalDateTime returnDate, Long totalSeats, long reservedSeats, String destination, Long route_id) {
        this.id = id;
        this.flightName = flightName;
        this.flightStatus = flightStatus;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.totalSeats = totalSeats;
        this.reservedSeats = reservedSeats;
        this.destination = destination;
        this.route_id = route_id;
    }

}
