package com.proyect.CompilAir.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightDTO {
    private long id;
    private String flightName;
    private boolean flightStatus;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private Long totalSeats;
    private long reservedSeats;


    public FlightDTO() {}


    public FlightDTO(long id, String flightName, boolean flightStatus, LocalDateTime departureDate,
                     LocalDateTime returnDate, Long totalSeats, long reservedSeats) {
        this.id = id;
        this.flightName = flightName;
        this.flightStatus = flightStatus;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.totalSeats = totalSeats;
        this.reservedSeats = reservedSeats;
    }
}
