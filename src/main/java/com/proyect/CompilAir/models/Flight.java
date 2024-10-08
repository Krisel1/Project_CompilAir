package com.proyect.CompilAir.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.*;


@Entity
@Table(name = "Flight")
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Flight number cannot be empty")
    @Size(max = 10, message = "The flight number cannot exceed 10 characters")
    @Column(unique = true, nullable = false)
    private String flightName;

    @NotNull
    @Column(nullable = false)
    private boolean flightStatus = true;

    @Future(message = "Departure date must be in the future")
    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Future(message = "Return date must be in the future")
    @Column(nullable = false)
    private LocalDateTime returnDate;


    @Column(nullable = false)
    private Long totalSeats;

    @Column(nullable = false)
    private long reservedSeats;

    @Column(name = "destination")
    private String destination;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    @JsonIgnore
    private Route route;

    public Flight() {

    }

    public Flight(long id, String flightName, boolean flightStatus, LocalDateTime departureDate, LocalDateTime returnDate, Long totalSeats, long reservedSeats,String destination) {
        this.id = id;
        this.flightName = flightName;
        this.flightStatus = flightStatus;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.totalSeats = totalSeats;
        this.reservedSeats = reservedSeats;
        this.destination = destination;
    }

    public long getAvailableSeats() {
        return totalSeats - reservedSeats;
    }

}

