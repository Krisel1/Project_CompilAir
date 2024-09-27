package com.proyect.CompilAir.models;

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

    @NotBlank(message = "El número de vuelo no puede estar vacío")
    @Size(max = 10, message = "El número de vuelo no puede exceder 10 caracteres")
    @Column(unique = true, nullable = false)
    private String flightName;

    @NotNull
    @Column(nullable = false)
    private boolean flightStatus;

    @Future(message = "La hora de salida debe estar en el futuro")
    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Future(message = "La hora de llegada debe estar en el futuro")
    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Min(value = 1, message = "El número total de asientos debe ser al menos 1")
    @Column(nullable = false)
    private Long totalSeats;


    @Min(value = 0, message = "Los asientos reservados no pueden ser negativos")
    @Column(nullable = false)
    private long reservedSeats = 0;

    

    public Flight() {

    }

    public Flight(long id, String flightName, boolean flightStatus, LocalDateTime departureDate, LocalDateTime returnDate, Long totalSeats, long reservedSeats) {
        this.id = id;
        this.flightName = flightName;
        this.flightStatus = flightStatus;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.totalSeats = totalSeats;
        this.reservedSeats = reservedSeats;
    }

    public long availableSeats() {
        return totalSeats - reservedSeats;
    }



    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Route> route;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Booking> booking;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<User> user;


}

