package com.proyect.CompilAir.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
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
    private boolean flightStatus;

    @Future(message = "The departure time must be in the future")
    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Future(message = "The arrival time must be in the future")
    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Min(value = 1, message = "The total number of seats must be at least 1")
    @Column(nullable = false)
    private Long totalSeats;


    @Min(value = 0, message = "Reserved seats cannot be negative")
    @Column(nullable = false)
    private long reservedSeats = 0;

    @Column(name = "destination")
    private String destination;

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

    public long availableSeats() {
        return totalSeats - reservedSeats;
    }



    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Route> route;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Booking> booking;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> user;


}

